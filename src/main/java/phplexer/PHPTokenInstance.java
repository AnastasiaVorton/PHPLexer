package phplexer;

import core.TokenInstance;

/**
 * This class represents a particular token instance found in the provided PHP source.
 */
public class PHPTokenInstance implements TokenInstance<PHPToken> {
    private String lexeme;
    private PHPToken token;

    PHPTokenInstance(String lexeme, PHPToken token) {
        this.lexeme = lexeme;
        this.token = token;
    }

    public String getLexeme() {
        return lexeme;
    }

    public PHPToken getToken() {
        return token;
    }
}
