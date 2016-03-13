package ch.docksnet.jasmindiff;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Stefan Zeller
 */
public class JasmineMatcherTest {

    List<String> consoleOut = Arrays.asList(
            "\n",
            "\tExpected Object({ a: 1, b: 2 }) to equal Object({ a: 1, b: 2, c: 3 }).\n",
            "\t    at /Users/stefan/dev/test-esta-webjs/spec.bundle.js:9 <- spec.bundle.js:1:24\n",
            "\n");

    @Test
    public void testMatchObject() throws Exception {
        JasmineMatcher sut = new JasmineMatcher();

        JasmineMatch result = sut.hasToEqual(consoleOut.get(1));

        assertTrue(result.hasMatch());
        assertEquals("{ a: 1, b: 2 }", result.expected());
        assertEquals("{ a: 1, b: 2, c: 3 }", result.toEqual());
    }

    @Test
    public void testNoMatch() throws Exception {
        JasmineMatcher sut = new JasmineMatcher();

        JasmineMatch result = sut.hasToEqual(consoleOut.get(2));

        assertFalse(result.hasMatch());
    }

}