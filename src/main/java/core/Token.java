package core;

import java.util.regex.Pattern;

public interface Token {
    /**
     * A regular expression pattern to match the token.
     */
    Pattern getPattern();
}
