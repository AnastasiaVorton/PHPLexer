package phplexer;

import core.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PHPToken implements Token {
    T_ARRAY_CAST("\\(array\\)|\\(bool\\)|\\(boolean\\)|\\(real\\)|\\(double\\)|\\(float\\)|\\(int\\)|\\(integer\\)|\\(object\\)|\\(string\\)|\\(unset\\)"),
    T_BOOLEAN_AND("&&|\\|\\||^and$|^or$|^xor$"), // logical operators
    T_MAGIC_CONSTANT("__CLASS__|__DIR__|__FILE__|__FUNCTION__|__LINE__|__METHOD__|__NAMESPACE__|__TRAIT__"), // magic constants
    T_CLOSE_TAG("\\?\\>|%\\>"), // escaping tag (?> or %>) CHECKED
    T_CURLY("\\}|\\{"), //  curly bracket CHECKED
    T_COALESCE("\\?\\?"), // null Coalescing operator (??)
    T_COMMENT("\\/\\/.*|#.*|\\/\\*(.|\\n)*\\*\\/"), // comments (// or # and /**/)
    T_COMMA("\\,"), // comma
    T_CURLY_OPEN("\\{\\$"), // passing complex variables as inputs ({$)
    T_INC_DEC("--|\\+\\+"), // decrementing operator (--)
    T_DNUMBER("(\\d+)\\.\\d+"), // double numbers (0.21 etc) CHECKED
    T_DOLLAR_OPEN_CURLY_BRACES("\\$\\{"), //passing complex variables as inputs (${)
    T_DOUBLE_ARROW("\\=\\>"), // array key => value assignment (=>) CHECKED
    T_DOUBLE_COLON("::"), // (::)
    T_ELLIPSIS("\\.\\.\\."), // a variable number of arguments in a function (...)
    T_HALT_COMPILER("__halt_compiler"), // halts the compiler execution (__halt_compiler)
    T_INLINE_HTML("<\\w+>.*<\\/\\w+>"), // text outside PHP (HTML text)
    T_COMPARISON("==|===|!=|<>|!==|<|>|<=|>=|<=>"), // comparison operator (==) CHECKED
    T_LNUMBER("\\d+"), // integer number (decimal, hex, oct, bin) CHECKED
    T_NUM_STRING("\\$\\w+\\[\\d+\\]"), // numeric array index inside string
    T_OBJECT_OPERATOR("->"), // object operator CHECKED
    T_OPEN_TAG("<\\?php|<\\?|<%|<%=|<\\?="), // escaping from HTML CHECKED
    T_SEMICOLON(";"), // semicolon CHECKED
    T_BITWISE("<<|>>|&|\\||\\^|~"), // bitwise operator
    T_VARIABLE("\\$\\w+"), // variable declaration CHECKED
    T_ROUND("\\(|\\)"), // open round bracket CHECKED
    T_REFERENCE("&$\\W+"), // pass parameter by reference CHECKED
    T_CONCAT("\\."), // CHECKED
    T_KEYWORD("yield from|trait|array|list|yield|while|namespace|var|use|try|throw|switch|catch|callable|foreach|for|require_once|require|cfunction|function|if|new|public|private|protected|return|abstract|static|as|class|break|case|echo|clone|const|continue|declare|default|do|elseif|else|empty|enddeclare|endfor|endforeach|endif|endswitch|endwhile|exit|die|extends|final|finally|global|goto|implements|include_once|include|instanceof|insteadof|interface|isset"), // trait keyword CHECKED
    T_STRING_IDENT("\\w+"), // identifiers, e.g. keywords like parent and self, function names, class names and more are matched. See also T_CONSTANT_ENCAPSED_STRING. CHECKED
    T_START_HEREDOC("<<<"), // starting heredoc
    T_ASSIGNMENT("&=|\\.=|\\/=|-=|%=|\\*=|\\|=|\\+=|\\*\\*=|<<=|>>=|\\^=|="),
    T_CONSTANT_ENCAPSED_STRING("'.*'|\".*\""), // CHECKED
    T_ARITHMETIC_OPERATOR("\\+|-|\\*|\\/|%|\\*\\*"),
    T_NEGATE("\\!"); // CHECKED

    private final Pattern pattern;

    PHPToken(String regex) {
        pattern = Pattern.compile("^" + regex);
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


