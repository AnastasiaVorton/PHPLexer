import core.LexicalAnalysisException;
import org.junit.Test;
import phplexer.PHPTokenInstance;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class IdentifierTests {
    @Test
    public void simpleIdentifier() throws LexicalAnalysisException, IOException {
        PHPTokenInstance identifier = Utils.getAllTokens("$abc").get(1);
        assertEquals("abc", identifier.getLexeme());
    }

    @Test
    public void underscoreIdentifier() throws LexicalAnalysisException, IOException {
        PHPTokenInstance identifier = Utils.getAllTokens("$abc_def").get(1);
        assertEquals("abc_def", identifier.getLexeme());
    }

    @Test(expected = LexicalAnalysisException.class)
    public void invalidIdentifier() throws LexicalAnalysisException, IOException {
        PHPTokenInstance emojiIdentifier = Utils.getAllTokens("$\uD83D\uDE0E").get(1);
        assertEquals("\uD83D\uDE0E", emojiIdentifier.getLexeme());
    }
}
