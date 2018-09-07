import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.utils.Pair;
import com.google.common.base.Joiner;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScqmParser {

    private final int maxLength;

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(new Option("l", "length", true, "Maximum method length for the model."));

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            Integer maxLength = Integer.parseInt(cmd.getOptionValue("length", "200"));
            new ScqmParser(maxLength).tokenize();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("SCQM Parser", options);
        }
    }

    public ScqmParser(int maxLength) {
        this.maxLength = maxLength;
    }

    public void tokenize() {
        String beforePath = "ApplicationContextAwareProcessor.java";
        Map<String, Pair<MethodDeclaration, List<Integer>>> methods = MethodTokenizer.tokenize(beforePath);
//            Map<String, Pair<MethodDeclaration, String>> before = MethodTokenizer.tokenize(beforePath);
//            Path diffsPath = filePath.getParent().getParent().resolve("diffs");
        List<Integer> lengths = new ArrayList<>();
        List<String> inputs = new ArrayList<>();
        List<String> methodSources = new ArrayList<>();
        new File("../scqm-input").mkdirs();
        methods.values().forEach(methodBefore -> {
            if (methodBefore.b.size() <= this.maxLength) {
                lengths.add(methodBefore.b.size());
                System.out.println(methodBefore.a.getNameAsString());
                while (methodBefore.b.size() < this.maxLength) methodBefore.b.add(0);
                inputs.add(Joiner.on(",").join(methodBefore.b));
                methodSources.add(methodBefore.a.toString());
            }
            try {
                try (PrintWriter out = new PrintWriter("../scqm-input/input.csv")) {
                    out.print(Joiner.on("\n").join(inputs));
                }
                try (PrintWriter out = new PrintWriter("../scqm-input/lengths.csv")) {
                    out.print(Joiner.on(",").join(lengths));
                }
                try (PrintWriter out = new PrintWriter("../scqm-input/SampleClass.java")) {
                    out.print("public class SampleClass {\n" + Joiner.on("\n\n").join(methodSources) + "\n}");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

//                if (after.containsKey(methodBefore.a.getNameAsString())) {
//                    Pair<MethodDeclaration, String> methodAfter = after.get(methodBefore.a.getNameAsString());
//                    if (!methodBefore.b.equals(methodAfter.b)) {
//                        String diffFilename = filePath.getFileName().toString() + methodBefore.a.getNameAsString() + ".txt";
//                        try (PrintWriter out = new PrintWriter(diffsPath.resolve(diffFilename).toFile())) {
//                            out.println(Joiner.on(DIFF_SEPARATOR).join(
//                                    methodBefore.a.toString(),
//                                    methodAfter.a.toString(),
//                                    methodBefore.b,
//                                    methodAfter.b
//                            ));
//                        } catch (FileNotFoundException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
        });
    }
}

