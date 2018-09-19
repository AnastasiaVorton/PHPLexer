package lexer;

import java.util.regex.Pattern;

/**
 * This class represents a particular token instance found in the provided source.
 */
public class TokenInstance {
    private String type; // TODO: decide whether this property is needed at all

    /**
     * The token this class represents an instance of.
     */
    private Token token;

    /**
     * The line of code this instance is found at.
     */
    private int line;

    /**
     * Offset from the beginning of the line to the position this instance if found at.
     */
    private int offset;

    TokenInstance(String name, Token value, int position, int offset, Pattern pattern) {
        this.type = name;
        this.token = value;
        this.line = position;
        this.offset = offset;
    }

    public String getType() {
        return type;
    }

    public Token getToken() {
        return token;
    }

    public int getLine() {
        return line;
    }

    public int getOffset() {
        return offset;
    }
}
