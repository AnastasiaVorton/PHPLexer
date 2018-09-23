package core;

/**
 * An exception that can occur during lexical analysis stage of the compilation.
 * Lexical analysis errors occur when a found character sequence couldn't be matched with any token category.
 */
public class LexicalAnalysisException extends Exception {
    /**
     * Line of the source of the position, where the error was found.
     */
    private int line;

    /**
     * Offset from the beginning of the line of the position, where the error was found.
     */
    private int offset;

    public LexicalAnalysisException() {
        super("A lexical analysis error occurred");
    }
}
