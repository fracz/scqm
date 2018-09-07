package pl.edu.agh.scqm;

import java.util.List;

public class AstResultDto {
    private final String methodName;
    private final List<Integer> tokens;
    private final String sourceCode;

    public AstResultDto(String methodName, List<Integer> tokens, String sourceCode) {
        this.methodName = methodName;
        this.tokens = tokens;
        this.sourceCode = sourceCode;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<Integer> getTokens() {
        return tokens;
    }

    public String getSourceCode() {
        return sourceCode;
    }
}
