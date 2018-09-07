package pl.edu.agh.scqm;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.utils.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ScqmController {
    @RequestMapping("/")
    public String index() {
        InputStream index = getClass().getResourceAsStream("index.html");
        String html = new BufferedReader(new InputStreamReader(index)).lines().parallel().collect(Collectors.joining("\n"));
        return html;
    }

    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public List<AstResultDto> parse(@RequestParam String source) {
        Map<String, Pair<MethodDeclaration, List<Integer>>> methods = new MethodTokenizer().tokenize(source);
        List<AstResultDto> response = methods.values().stream().map(pair -> new AstResultDto(pair.a.getNameAsString(), pair.b, pair.a.toString())).collect(Collectors.toList());
        return response;
    }
}
