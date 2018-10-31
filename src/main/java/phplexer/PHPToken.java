package phplexer;

import core.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PHPToken implements Token {
    /** Escaping from HTML */
    T_TAG_OPEN("<\\?=|<\\?php|<\\?|<%=|<%"),
    /** Escaping tag */
    T_TAG_CLOSE("\\?\\>|%\\>"),

    /** Logical operators. */
    T_OPERATOR_LOGICAL("&&|\\|\\||and|or|xor|!"),
    /** Arithmetic operators including power (**) */
    T_OPERATOR_ARITHMETIC("\\+|-|\\*|\\/|%|\\*\\*"),
    /** Assignment operators */
    T_OPERATOR_ASSIGNMENT("&=|\\.=|\\/=|-=|%=|\\*=|\\|=|\\+=|\\*\\*=|<<=|>>=|\\^=|="),
    /** Ampersand operator. Separated from bitwise, because can also be pass-by-reference operator. */
    T_OPERATOR_AMPERSAND("&"),
    /** Bitwise operations operators. */
    T_OPERATOR_BITWISE("<<|>>|\\||\\^|~"),
    /** Null Coalescing operator (??) . */
    T_OPERATOR_COALESCE("\\?\\?"),
    /** Dot operator, used for string concatenation. */
    T_OPERATOR_CONCAT("\\."),
    T_OPERATOR_DOLLAR("\\$"),

    /** Well, semicolon ¯\_(ツ)_/¯ */
    T_SEMICOLON(";"),
    T_COMMA(","),
    T_COLON(":"),
    /** Namespace separator '\'. Used for accessing identifiers nested in a namespace. */
    T_NAMESPACE_SEPARATOR("\\\\"),

    /** Opening curly bracket, i.e. brace. */
    T_BRACE_OPEN("\\{"),
    /** Closing curly bracket, i.e. brace. */
    T_BRACE_CLOSE("\\}"),
    /** Opening round bracket, i.e. parenthesis. */
    T_PARENTHESIS_OPEN("\\("),
    /** Closing round bracket, i.e. parenthesis. */
    T_PARENTHESIS_CLOSE("\\)"),
    /** Closing square bracket. */
    T_BRACKET_OPEN("\\["),
    /** Closing square bracket. */
    T_BRACKET_CLOSE("\\]"),

    /**
     * Comments, i.e. '//', '#' or '/*'
     * Must be matched manually by the lexical analyzer, due to the token being encapsulated by open/close
     * comment operators or open comment operator and the end of the line.
     */
    T_COMMENT("\\\\/\\\\/.*|#.*|\\\\/\\\\*(.|\\\\n)*\\\\*\\\\/"),

    /** Integer and floating point numbers (including binary (0b10101), octal (01234) and hexadecimal (0xaf)). */
    T_LITERAL_NUMBER("\\.\\d+|0[xX][0-9a-fA-F]+|0[bB][0-1]+|\\d+"),
    /**
     * String literals. Must be matched manually because of its complex structure.
     */
    T_LITERAL_STRING("'.*'|\".*\""),

    /** Increment and decrement operators */
    T_INC_DEC("--|\\+\\+"),
    /** Array key - value assignment operator. */
    T_DOUBLE_ARROW("\\=\\>"),
    /** Scope Resolution Operator. */
    T_DOUBLE_COLON("::"),
    /** Operator denoting variable number of arguments in a function. */
    T_ELLIPSIS("\\.\\.\\."),
    /** Comparison operators. */
    T_COMPARISON("==|===|!=|<>|!==|<|>|<=|>=|<=>"),
    /** Object operator, used in object scope to access methods and properties of an object */
    T_OBJECT_OPERATOR("->"),

    /** Reserved keywords */
    T_KEYWORD("yield from|trait|array|list|yield|while|namespace|var|use|try|throw|switch|catch|callable|foreach|for"+
            "|require_once|require|cfunction|function|if|new|public|private|protected|return|abstract|static|as|class"+
            "|break|case|echo|clone|const|continue|declare|default|do|elseif|else|empty|enddeclare|endforeach|endfor"+
            "|endif|endswitch|endwhile|exit|die|extends|finally|final|global|goto|implements|include_once|include"+
            "|instanceof|insteadof|interface|isset"),
    /** Identifiers, s.a keywords, function names, class names etc */
    T_IDENTIFIER("[a-zA-Z_][a-zA-Z0-9_]*");

    private final Pattern pattern;

    PHPToken(String regex) {
        pattern = Pattern.compile("^(" + regex + ")");
    }

    int endOfMatch(String s) {
        Matcher m = pattern.matcher(s);

        if (m.find()) { // Attempts to find the next subsequence of the input sequence that matches the pattern.
            return m.end(); // Returns the offset after the last character matched.
        }
        return -1;
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }
}


