import core.LexicalAnalysisException;
import phplexer.PHPLexicalAnalyzer;
import phplexer.PHPToken;
import phplexer.PHPTokenInstance;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class Utils {
    static void assertSingleToken(String sourceText, PHPToken token) throws AssertionError, LexicalAnalysisException, IOException {
        PHPLexicalAnalyzer lexer = new PHPLexicalAnalyzer(new StringReader(sourceText));
        PHPTokenInstance tokenInstance = lexer.getNextToken();

        assertEquals(sourceText, tokenInstance.getLexeme());
        assertEquals(token, tokenInstance.getToken());
        assertNull(lexer.getNextToken());
    }

    static List<PHPTokenInstance> getAllTokens(String sourceText) throws LexicalAnalysisException, IOException {
        PHPLexicalAnalyzer lexer = new PHPLexicalAnalyzer(new StringReader(sourceText));

        List<PHPTokenInstance> tokens = new ArrayList<>();
        PHPTokenInstance token;
        while ((token = lexer.getNextToken()) != null) {
            tokens.add(token);
        }

        return tokens;
    }
}
