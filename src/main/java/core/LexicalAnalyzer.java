package core;

/**
 * An interface describing a generic lexical analyzer. It parses the source code into a sequence of tokens.
 * @param <T> Actual type of TokenInstance to use.
 */
public interface LexicalAnalyzer<T extends TokenInstance> {
    T getNextToken();
}
