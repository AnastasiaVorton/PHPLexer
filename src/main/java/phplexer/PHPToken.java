package phplexer;

import core.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PHPToken implements Token {
    /** Logical operators. */
    T_OPERATOR_LOGICAL("&&|\\|\\||and|or|xor|!"),
    T_OPERATOR_ARITHMETIC("\\+|-|\\*|\\/|%|\\*\\*"), // CHECKED
    T_OPERATOR_ASSIGNMENT("&=|\\.=|\\/=|-=|%=|\\*=|\\|=|\\+=|\\*\\*=|<<=|>>=|\\^=|="), // CHECKED

    T_TAG_OPEN("<\\?=|<\\?php|<\\?|<%=|<%"), // escaping from HTML CHECKED
    T_TAG_CLOSE("\\?\\>|%\\>"), // escaping tag (?> or %>) CHECKED

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
    T_COMMENT(null),

    T_LITERAL_NUMBER("(\\d+)|\\.\\d+"), // double numbers (0.21 etc) CHECKED

    T_COALESCE("\\?\\?"), // null Coalescing operator (??) CHECKED

    T_COMMA(","), // comma CHECKED
    T_INC_DEC("--|\\+\\+"), // decrementing/INCREMENTING operator (--) CHECKED
    T_DOUBLE_ARROW("\\=\\>"), // array key => value assignment (=>) CHECKED
    T_DOUBLE_COLON("::"), // (::) CHECKED
    T_ELLIPSIS("\\.\\.\\."), // a variable number of arguments in a function (...) CHECKED
    T_COMPARISON("==|===|!=|<>|!==|<|>|<=|>=|<=>"), // comparison operator (==) CHECKED
    T_OBJECT_OPERATOR("->"), // object operator CHECKED

    T_SEMICOLON(";"), // semicolon CHECKED
    T_BITWISE("<<|>>|&|\\||\\^|~"), // bitwise operator CHECKED
    T_VARIABLE("\\$\\w+"), // variable declaration CHECKED
    T_REFERENCE("&\\$\\w+"), // pass parameter by reference CHECKED
    T_CONCAT("\\."), // CHECKED
    T_KEYWORD("yield from|trait|array|list|yield|while|namespace|var|use|try|throw|switch|catch|callable|foreach|for|require_once|require|cfunction|function|if|new|public|private|protected|return|abstract|static|as|class|break|case|echo|clone|const|continue|declare|default|do|elseif|else|empty|enddeclare|endforeach|endfor|endif|endswitch|endwhile|exit|die|extends|finally|final|global|goto|implements|include_once|include|instanceof|insteadof|interface|isset"),
    T_IDENTIFIER("\\w+"), // identifiers, e.g. keywords like parent and self, function names, class names and more are matched. See also T_CONSTANT_ENCAPSED_STRING. CHECKED
    T_START_HEREDOC("<<<"), // starting heredoc

    T_CONSTANT_ENCAPSED_STRING("'.*'|\".*\""); // CHECKED

    private final Pattern pattern;

    PHPToken(String regex) {
        pattern = Pattern.compile(regex);
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


