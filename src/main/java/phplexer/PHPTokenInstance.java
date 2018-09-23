package phplexer;

import core.TokenInstance;

/**
 * This class represents a particular token instance found in the provided PHP source.
 */
public class PHPTokenInstance implements TokenInstance<PHPToken> {
    private String lexeme;
    private PHPToken token;
    private int line;
    private int offset;

    PHPTokenInstance(String lexeme, PHPToken token, int line, int offset) {
        this.lexeme = lexeme;
        this.token = token;
        this.line = line;
        this.offset = offset;
    }

    public String getLexeme() {
        return lexeme;
    }

    public PHPToken getToken() {
        return token;
    }

    public int getLine() {
        return line;
    }

    public int getOffset() {
        return offset;
    }
}
