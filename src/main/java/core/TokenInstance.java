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
     * Lexeme found in the source code.
     */
    String getLexeme();
}
