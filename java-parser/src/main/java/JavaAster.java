import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.utils.Pair;
import com.google.common.base.Joiner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class JavaAster {

    public static final String DIFF_SEPARATOR = "||||||||";

    private int count = 0;

    public static void main(String[] args) throws Exception {

        new JavaAster().tokenizeAll();
    }

    public void tokenizeAll() throws IOException {
        String projectsDir = "D:\\projects\\refactor-extractor\\results-java\\";
        System.out.println("");
        try (Stream<Path> paths = Files.walk(Paths.get(projectsDir))) {
            paths
                    .filter(Files::isDirectory)
                    .filter(path -> path.getFileName().toString().equals("after"))
                    .forEach(this::tokenizeProject);
        }
        dumpTokens();

//        tokenize("src/main/java/MethodTokenizer.java");
    }

    public void tokenizeProject(Path projectPath) {
        try (Stream<Path> paths = Files.walk(projectPath)) {
            paths.filter(Files::isRegularFile).forEach(this::tokenizeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tokenizeFile(Path filePath) {
        try {
            Map<String, Pair<MethodDeclaration, String>> after = MethodTokenizer.tokenize(filePath.toString());
            String beforePath = filePath.getParent().getParent().resolve("before").resolve(filePath.getFileName()).toString();
            Map<String, Pair<MethodDeclaration, String>> before = MethodTokenizer.tokenize(beforePath);
            Path diffsPath = filePath.getParent().getParent().resolve("diffs");
            diffsPath.toFile().mkdirs();
            count++;
            if (count % 100 == 0) {
                System.out.println("\r" + count);
            }
            before.values().forEach(methodBefore -> {
                if (after.containsKey(methodBefore.a.getNameAsString())) {
                    Pair<MethodDeclaration, String> methodAfter = after.get(methodBefore.a.getNameAsString());
                    if (!methodBefore.b.equals(methodAfter.b)) {
                        String diffFilename = filePath.getFileName().toString() + methodBefore.a.getNameAsString() + ".txt";
                        try (PrintWriter out = new PrintWriter(diffsPath.resolve(diffFilename).toFile())) {
                            out.println(Joiner.on(DIFF_SEPARATOR).join(
                                    methodBefore.a.toString(),
                                    methodAfter.a.toString(),
                                    methodBefore.b,
                                    methodAfter.b
                            ));
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        } catch (ParseProblemException e) {
            System.out.println(e.getMessage());
        }
        if (count % 1000 == 0) {
            dumpTokens();
        }
    }

    private void dumpTokens() {
        try (PrintWriter out = new PrintWriter("tokens-java.txt")) {
            out.println(Joiner.on('\n').join(MethodTokenizer.ALL_TOKENS));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
