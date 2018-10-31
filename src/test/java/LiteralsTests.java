import core.LexicalAnalysisException;
import org.junit.Assert;
import org.junit.Test;
import phplexer.PHPToken;

import java.io.IOException;

public class LiteralsTests {
    @Test
    public void simpleStringLiteral() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("\"string\"", PHPToken.T_LITERAL_STRING);
    }

    @Test
    public void longStringLiteral() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("\"this is a string literal\"", PHPToken.T_LITERAL_STRING);
    }

    @Test(expected = LexicalAnalysisException.class)
    public void invalidStringLiteral() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("'this is a string\nliteral'", PHPToken.T_LITERAL_STRING);
    }

    @Test
    public void simpleStringLiteral2() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("'string'", PHPToken.T_LITERAL_STRING);
    }

    @Test
    public void decimalNumberLiteral() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("16", PHPToken.T_LITERAL_NUMBER);
    }

    @Test
    public void hexNumberLiteral() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("0xff", PHPToken.T_LITERAL_NUMBER);
    }

    @Test
    public void octalNumberLiteral() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("0123", PHPToken.T_LITERAL_NUMBER);
    }

    @Test
    public void binaryNumberLiteral() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("0b0101", PHPToken.T_LITERAL_NUMBER);
    }

    @Test(expected = AssertionError.class)
    public void invalidBinaryNumberLiteral() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("0b2345", PHPToken.T_LITERAL_NUMBER);
    }

    @Test(expected = AssertionError.class)
    public void invalidHexNumberLiteral() throws LexicalAnalysisException, IOException {
        Utils.assertSingleToken("0x2345j", PHPToken.T_LITERAL_NUMBER);
    }
}
