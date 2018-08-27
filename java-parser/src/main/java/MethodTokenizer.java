import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.Pair;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MethodTokenizer {

    public static Set<String> ALL_TOKENS = new HashSet<>();

    private static final Set<String> SIMPLE_NODES = ImmutableSet.of(
            SimpleName.class.getSimpleName(),
            NameExpr.class.getSimpleName(),
            StringLiteralExpr.class.getSimpleName(),
            NullLiteralExpr.class.getSimpleName(),
            Name.class.getSimpleName(),
            LineComment.class.getSimpleName()
    );

    public static Map<String, Pair<MethodDeclaration, String>> tokenize(String filePath) {
        return new MethodTokenizer().doTokenize(filePath);
    }

    private Map<String, Pair<MethodDeclaration, String>> methods = new HashMap<>();

    private Set<String> overloadedMethods = new HashSet<>();

    private Map<String, Pair<MethodDeclaration, String>> doTokenize(String filePath) {
        // creates an input stream for the file to be parsed
        FileInputStream in = null;
        try {
            in = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // parse it
        CompilationUnit cu = JavaParser.parse(in);

        // visit and print the methods names
        cu.accept(new MethodVisitor(), null);

        this.overloadedMethods.forEach(this.methods::remove);

        return this.methods;
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration method, Void arg) {
            final StringBuilder ast = new StringBuilder();

            if (methods.containsKey(method.getNameAsString())) {
                overloadedMethods.add(method.getNameAsString());
                return;
            }

            VoidVisitor<Integer> v = new TreeVisitior() {
                @Override
                public void in(Node n, Integer indentLevel) {
                    String token = n.getClass().getSimpleName();
                    ALL_TOKENS.add(token);
                    if (SIMPLE_NODES.contains(token)) {
                        ast.append(StringUtils.repeat('\t', indentLevel) + token + '\n');
                    } else {
                        ast.append(StringUtils.repeat('\t', indentLevel) + "(" + token + '\n');
                    }
                }

                @Override
                public void out(Node n, Integer indentLevel) {
                    if (!SIMPLE_NODES.contains(n.getClass().getSimpleName())) {
                        ast.append(StringUtils.repeat('\t', indentLevel) + ")" + '\n');
                    }
                }
            };

            method.accept(v, 0);

            String buildAst = ast.toString();//.replaceAll("\\(([^\\)]+)\\)", "$1");

            methods.put(method.getNameAsString(), new Pair<>(method, buildAst));

            super.visit(method, arg);
        }
    }
}
