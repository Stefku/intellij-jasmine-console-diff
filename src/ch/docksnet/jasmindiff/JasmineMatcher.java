package ch.docksnet.jasmindiff;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Stefan Zeller
 */
public class JasmineMatcher {

    private final Pattern pattern = Pattern.compile("\\tExpected Object\\((.*)\\) to equal Object\\((.*)\\).");

    public JasmineMatch hasToEqual(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.lookingAt()) {
            return JasmineMatch.createMatch(matcher.group(1), matcher.group(2));
        }
        return JasmineMatch.NO_MATCH;
    }

}
