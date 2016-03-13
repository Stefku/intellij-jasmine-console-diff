package ch.docksnet.jasmindiff;

/**
 * @author Stefan Zeller
 */
public class JasmineMatch {

    public static final JasmineMatch NO_MATCH = new JasmineMatch(false);

    private final boolean hasMatch;
    private final String expected;
    private final String toEqual;

    private JasmineMatch(boolean hasMatch) {
        this.hasMatch = hasMatch;
        this.expected = null;
        this.toEqual = null;
    }

    private JasmineMatch(String expected, String toEqual) {
        this.expected = expected;
        this.toEqual = toEqual;
        this.hasMatch = true;
    }

    public boolean hasMatch() {
        return hasMatch;
    }

    public String expected() {
        return expected;
    }

    public String toEqual() {
        return toEqual;
    }

    public static JasmineMatch createMatch(String expected, String toEqual) {
        return new JasmineMatch(expected, toEqual);
    }

}
