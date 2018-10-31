import core.LexicalAnalysisException;
import org.junit.Test;
import phplexer.PHPToken;

import java.io.IOException;

public class CommentTests {
    @Test
    public void singleLine() throws LexicalAnalysisException, IOException {
        String testString = "// this is a single line comment";
        Utils.assertSingleToken(testString, PHPToken.T_COMMENT);
    }

    @Test
    public void singleLineTag() throws LexicalAnalysisException, IOException {
        String testString = "# this is a single line comment";
        Utils.assertSingleToken(testString, PHPToken.T_COMMENT);
    }

    @Test
    public void multilineComment() throws LexicalAnalysisException, IOException {
        String testString =
                "/* this is a multiline comment\n" +
                "this is the second line of the multiline comment */";
        Utils.assertSingleToken(testString, PHPToken.T_COMMENT);
    }

    @Test
    public void singleLineMultilineComment() throws LexicalAnalysisException, IOException {
        String testString =
                "/* this is a pseudo-multiline comment */";
        Utils.assertSingleToken(testString, PHPToken.T_COMMENT);
    }

    @Test(expected = AssertionError.class)
    public void invalidMultilineComment() throws  LexicalAnalysisException, IOException {
        String testString = "// this is a single line comment\nwith invalid second line";
        Utils.assertSingleToken(testString, PHPToken.T_COMMENT);
    }
}
