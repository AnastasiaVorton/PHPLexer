package phplexer;

import core.LexicalAnalysisException;
import core.LexicalAnalyzer;
import java.io.IOException;
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
        try {
            // last time we put more chars than the length of the found identifier
            // so try to find more identifiers
            if (buffer.length() != 0) return matchAndResetCurrentBuffer();

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
                buffer.append(next);

                // reached obvious end of token
                break;
            }

            PHPTokenInstance result = matchAndResetCurrentBuffer();
            if (result == null) {
                // reached the end, close the reader
                reader.close();
            }

            return result;
        } catch (Exception e) {
            // Close the reader and rethrow the exception
            reader.close();
            throw e;
        }
    }

    /**
     * Iterates through all PHPToken cases and tries to find a match in the current buffer.
     * Deletes a matching lexeme from the buffer, if one is found.
     * @return a token instance containing the information about the token that was found. null if buffer is empty.
     * @throws LexicalAnalysisException thrown if no matching lexeme was found in the buffer.
     */
    private PHPTokenInstance matchAndResetCurrentBuffer() throws LexicalAnalysisException, IOException {
        if (buffer.length() == 0) return null;

        // handle special cases
        if (buffer.length() >= 2) {
            switch (buffer.substring(0, 2)) {
                case "//":
                    // single line comments
                    String slashComment = handleStartEndLexeme(2, "\n", false, false);
                    return new PHPTokenInstance("//" + slashComment, PHPToken.T_COMMENT);

                case "/*":
                    // multiline comments
                    String multiline = handleStartEndLexeme(2, "*/", true, false);
                    return new PHPTokenInstance("/*" + multiline + "*/", PHPToken.T_COMMENT);
            }
        }

        if (buffer.length() >= 1) {
            switch (buffer.charAt(0)) {
                case '\'':
                    // special case: single line string literal
                    String singleLine = handleStartEndLexeme(1, "\'", false, true);
                    return new PHPTokenInstance("\'" + singleLine + "\'", PHPToken.T_LITERAL_STRING);
                case '\"':
                    // special case: multiline string literal
                    String multiline = handleStartEndLexeme(1, "\"", true, true);
                    return new PHPTokenInstance("\"" + multiline + "\"", PHPToken.T_LITERAL_STRING);
                case '#':
                    // single line comments
                    String singleComment = handleStartEndLexeme(1, "\n", false, false);
                    return new PHPTokenInstance("#" + singleComment, PHPToken.T_COMMENT);

                default:
                    break;
            }
        }

        // we don't need separation symbols in the buffer anymore
        for (Character symbol : endOfTokenSymbols) {
            int index;
            while ((index = buffer.indexOf(Character.toString(symbol))) != -1) {
                buffer.deleteCharAt(index);
            }
        }

        // try to match all patterns with current buffer
        PHPTokenInstance bestGuess = null;
        for (PHPToken token : PHPToken.values()) {
            int end = token.endOfMatch(buffer.toString());

            if (end != -1) {
                String lexeme = buffer.substring(0, end);
                PHPTokenInstance instance = new PHPTokenInstance(lexeme, token);

                // obey longest match rule, i.e. find longest matching token
                if (bestGuess == null || bestGuess.getLexeme().length() < instance.getLexeme().length()) {
                    bestGuess = instance;
                }
            }
        }

        if (bestGuess == null) {
            // After going through all the tokens, we didn't find a match
            // Thus, throw a lexical analysis error
            throw new LexicalAnalysisException();
        } else {
            // reset reader cursor to the end of consumed token's lexeme
            buffer.delete(0, bestGuess.getLexeme().length());

            return bestGuess;
        }
    }

    private String handleStartEndLexeme(int bufferOffset, String closingString, boolean isMultiline, boolean failsOnEof)
            throws IOException, LexicalAnalysisException {
        StringBuilder lexemeBuilder = new StringBuilder();

        int counter = bufferOffset;
        while (true) {
            char nextChar;
            if (counter < buffer.length()) {
                nextChar = buffer.charAt(counter);
                counter++;
            } else {
                int charIndex = reader.read();

                if (charIndex == -1) {
                    // if its the end of file and we still didn't finish our string, then its an error
                    if (failsOnEof) {
                        throw new LexicalAnalysisException();
                    } else {
                        break;
                    }
                }
                nextChar = (char) charIndex;
            }

            String next = Character.toString(nextChar);
            lexemeBuilder.append(next);

            if (lexemeBuilder.length() >= closingString.length()) {
                String currentEnding = lexemeBuilder
                        .substring(lexemeBuilder.length() - closingString.length(), lexemeBuilder.length());
                if (currentEnding.equals(closingString)) {
                    // found an end
                    buffer = new StringBuilder();
                    return lexemeBuilder.toString().replace(closingString, "");
                }
            }

            if ((next.equals("\n") || next.equals("\r")) && !isMultiline) {
                System.out.println("Current lexeme builder " + lexemeBuilder.toString());
                // end of line while still in the cycle, means we didn't find second ' symbol, and it's an error
                throw new LexicalAnalysisException();
            }
        }

        buffer = new StringBuilder();
        return lexemeBuilder.toString();
    }
}
