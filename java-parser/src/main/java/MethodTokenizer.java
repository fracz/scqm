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
import com.google.common.io.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.IntStream;

public class MethodTokenizer {

    private static final Map<String, Integer> TOKENS = new HashMap<>();

    private static final Set<String> SIMPLE_NODES = ImmutableSet.of(
            SimpleName.class.getSimpleName(),
            NameExpr.class.getSimpleName(),
            StringLiteralExpr.class.getSimpleName(),
            NullLiteralExpr.class.getSimpleName(),
            Name.class.getSimpleName(),
            LineComment.class.getSimpleName()
    );

    public static Map<String, Pair<MethodDeclaration, List<Integer>>> tokenize(String filePath) {
        return new MethodTokenizer().doTokenize(filePath);
    }

    private Map<String, Pair<MethodDeclaration, List<Integer>>> methods = new HashMap<>();

    private Set<String> overloadedMethods = new HashSet<>();

    private Map<String, Pair<MethodDeclaration, List<Integer>>> doTokenize(String filePath) {
        try {
            List<String> lines = Files.readLines(new File("tokens-java.txt"), Charset.forName("utf-8"));
            TOKENS.clear();
            IntStream.range(0, lines.size()).forEach(idx -> TOKENS.put(lines.get(idx), idx));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
            final List<Integer> ast = new ArrayList<>();

            if (methods.containsKey(method.getNameAsString())) {
                overloadedMethods.add(method.getNameAsString());
                return;
            }

            VoidVisitor<Integer> v = new TreeVisitior() {
                @Override
                public void in(Node n, Integer indentLevel) {
                    String token = n.getClass().getSimpleName();
                    if (!SIMPLE_NODES.contains(token)) {
                        ast.add(TOKENS.get("("));
                    }
                    ast.add(TOKENS.get(token));
                }

                @Override
                public void out(Node n, Integer indentLevel) {
                    if (!SIMPLE_NODES.contains(n.getClass().getSimpleName())) {
                        ast.add(TOKENS.get(")"));
                    }
                }
            };

            method.accept(v, 0);

            methods.put(method.getNameAsString(), new Pair<>(method, ast));

            super.visit(method, arg);
        }
    }
}
