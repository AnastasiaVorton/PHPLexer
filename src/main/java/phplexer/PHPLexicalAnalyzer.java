package phplexer;

import core.LexicalAnalysisException;
import core.LexicalAnalyzer;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Lexical analyzer for PHP language.
 */
public class PHPLexicalAnalyzer implements LexicalAnalyzer<PHPTokenInstance> {
    private Reader reader;
    private StringBuilder buffer = new StringBuilder();

    /**
     * All characters that indicate the end of current token.
     */
    private static Set<Character> endOfTokenSymbols = new HashSet<>(
            Arrays.asList(' ', '\n', '\r', '\b', '\t')
    );

    public PHPLexicalAnalyzer(Reader reader) {
        this.reader = reader;
    }

    @Override
    public PHPTokenInstance getNextToken() throws LexicalAnalysisException, IOException {
        // last time we put more chars than the length of the found identifier
        // so try to find more identifiers
        if (buffer.length() != 0) return matchCurrentBufferAndResetReaderCursor();

        int charIndex;
        // read until the end of file
        while ((charIndex = reader.read()) != -1) {
            char next = (char) charIndex;

            // gather symbols until we're sure that its the end of the current token
            if (!endOfTokenSymbols.contains(next)) {
                buffer.append(next);
                continue;
            }

            // skip end of token characters
            if (buffer.length() == 0) continue;

            // reached obvious end of token
            break;
        }

        return matchCurrentBufferAndResetReaderCursor();
    }

    private PHPTokenInstance matchCurrentBufferAndResetReaderCursor() throws LexicalAnalysisException, IOException {
        if (buffer.length() == 0) return null;

        PHPTokenInstance bestGuess = null;
        for (PHPToken t : PHPToken.values()) {
            int end = t.endOfMatch(buffer.toString());

            if (end != -1) {
                String lexeme = buffer.substring(0, end);
                // TODO: add line calculation
                PHPTokenInstance instance = new PHPTokenInstance(lexeme, t, 0, end - lexeme.length());

                // obey longest match rule, i.e. find longest matching token
                if (bestGuess == null || bestGuess.getLexeme().length() < instance.getLexeme().length()) {
                    bestGuess = instance;
                }
            }
        }

        if (bestGuess == null) {
            // TODO: add position calculation
            throw new LexicalAnalysisException(0, 0);
        } else {
//            System.out.println("Found token: " + bestGuess.getToken() + ", lexeme: " + bestGuess.getLexeme());
//            System.out.println("Buffer before deleting found token: " + buffer.toString());
            // reset reader cursor to the end of consumed token's lexeme
            buffer.delete(0, bestGuess.getLexeme().length());
//            System.out.println("Buffer after deleting found token: " + buffer.toString() + ", length: " + buffer.length());

            return bestGuess;
        }
    }
}
