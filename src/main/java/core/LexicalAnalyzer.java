package core;

import java.io.IOException;

/**
 * An interface describing a generic lexical analyzer. It parses the source code into a sequence of tokens.
 * @param <T> Actual type of TokenInstance to use.
 */
public interface LexicalAnalyzer<T extends TokenInstance> {
    /**
     * Parses and returns next token from the source code.
     * @return next token from the source code.
     * @throws LexicalAnalysisException thrown if a lexical error occurs.
     * For more please read LexicalAnalysisException documentation.
     * @throws IOException thrown when any kind of source file reading error occurs.
     */
    T getNextToken() throws LexicalAnalysisException, IOException;
}
