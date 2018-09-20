package core;

/**
 * An interface describing a particular instance of a generic Token found in the source code.
 * @param <T> actual type of token this instance corresponds to.
 */
public interface TokenInstance<T extends Token> {
    /**
     * The token that corresponds to this instance.
     */
    T getToken();

    /**
     * The line of source code this instance is found at.
     */
    int getLine();

    /**
     * Offset from the beginning of the line to the position, this instance was found at.
     */
    int getOffset();

    /**
     * Lexeme found in the source code.
     */
    String getLexeme();
}
