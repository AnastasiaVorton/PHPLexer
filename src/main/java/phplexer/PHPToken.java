package phplexer;

import core.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PHPToken implements Token {
    T_ABSTRACT("abstract"), // class abstraction (abstract) CHECKED
    T_AND_EQUAL("&="),  // bitwise and assignment operator (&=) CHECKED
    T_ARRAY_CAST("\\(array\\)"), // casting to array ( (array) ) CHECKED
    T_AS("as"), // foreach operator (as) CHECKED
    T_BOOLEAN_AND("\\&\\&"), // logical and operator (&&) CHECKED
    T_BOOLEAN_OR("\\|\\|"), // logical or operator (||) CHECKED
    T_BOOL_CAST("\\(bool\\)|\\(boolean\\)"), // casting to boolean ( (bool) or (boolean) ) CHECKED
    T_BREAK("break"), // break operator (break) CHECKED
    T_CALLABLE("callable"), // callable keyword (callable) CHECKED
    T_CASE("case"), // switch-case operator (case)
    T_CATCH("catch"), // exception catch keyword (catch) CHECKED
    T_CLASS("class"), // class declaration keyword (class) CHECKED
    T_CLASS_C("__CLASS__"), // class magic constant (__CLASS__)
    T_CLONE("clone"), // object cloning operator (clone)
    T_CLOSE_TAG("\\?\\>|%\\>"), // escaping tag (?> or %>) CHECKED
    T_CLOSE_CURLY("\\}"), // closing curly bracket CHECKED
    T_CLOSE_ROUND("\\)"), // closing curly bracket CHECKED
    T_COALESCE("\\?\\?"), // null Coalescing operator (??)
    T_COMMENT("\\/\\/.*|#.*|\\/\\*.\n*\\*\\/"), // comments (// or # and /**/) TODO in smt
    T_COMMA("\\,"), // comma
    T_CONCAT_EQUAL("\\.="), // concatenation assignment operator (.=)
    T_CONST("const"), // class constant (const)
    T_CONTINUE("continue"), // continue keyword (continue)
    T_CURLY_OPEN("\\{\\$"), // passing complex variables as inputs ({$)
    T_DEC("--"), // decrementing operator (--)
    T_DECLARE("declare"), // declare keyword (declare)
    T_DEFAULT("default"), // default keyword for switch-case (default)
    T_DIR("__DIR__"), // directory magic constant (__DIR__)
    T_DIV_EQUAL("/="), // division assignment  operator (/=)
    T_DNUMBER("(\\d+)\\.\\d+"), // double numbers (0.21 etc) CHECKED
    T_DOC_COMMENT("/\\*\\*.*\\*\\*/"), // docstyle comment (/** */) TODO check
    T_DO("do"), // do keyword for while statement (do)
    T_DOLLAR_OPEN_CURLY_BRACES("\\$\\{"), //passing complex variables as inputs (${)
    T_DOUBLE_ARROW("\\=\\>"), // array key => value assignment (=>) CHECKED
    T_DOUBLE_CAST("\\(real\\)|\\(double\\)|\\(float\\)"), // casting to double ( (real), (double) or (float) )
    T_DOUBLE_COLON("::"), // (::) TODO exaplain where used
    T_ECHO("echo"), // output strings (echo) CHECKED
    T_ELLIPSIS("\\.\\.\\."), // a variable number of arguments in a function (...)
    T_ELSE("else"), // else keyword (else)
    T_ELSEIF("elseif"), // elseif keyword (elseif)
    T_EMPTY("empty"), // determine whether a variable is empty (empty)
    T_ENDDECLARE("enddeclare"), // enddeclare keyword (enddeclare)
    T_ENDFOR("endfor"), // endfor keyword (endfor)
    T_ENDFOREACH("endforeach"), // endforeach keyword (endforeach)
    T_ENDIF("endif"), // endif keyword (endif)
    T_ENDSWITCH("endswitch"), // endswitch keyword (endswitch)
    T_ENDWHILE("endwhile"), // endwhile keyword (endwhile)
    //    T_END_HEREDOC(""), // a way to delimit strings () TODO how it's represented?
    T_EXIT("exit|die"), // output a message and terminate the current script (exit or die)
    T_EXTENDS("extends"), // inheritance keyword (extends)
    T_FILE("__FILE__"), // file magic constant (__FILE__)
    T_FINAL("final"), // final keyword (final)
    T_FINALLY("finally"), // an exception block specified after or instead of catch blocks (finally)
    T_FOREACH("foreach"), // foreach keyword (foreach) CHECKED
    T_FOR("for"), // for keyword (for)
    T_FUNCTION("function|cfunction"), // function declaration keyword (function or cfunction) CHECKED
    T_FUNC_C("__FUNCTION__"), // function magic constant (__FUNCTION__)
    T_GLOBAL("global"), // variable scope (global)
    T_GOTO("goto"), // goto keyword (goto)
    T_HALT_COMPILER("__halt_compiler"), // halts the compiler execution (__halt_compiler)
    T_IF("if"), // if keyword (if) CHECKED
    T_IMPLEMENTS("implements"), // interface implementation (implements)
    T_INC("\\+\\+"), // incrementing operator (++)
    T_INCLUDE("include\\("), // include keyword (include())
    T_INCLUDE_ONCE("include_once\\("), // includes and evaluates the file during the execution of the script (include_once()) TODO whats in brackets?
    T_INLINE_HTML("<\\w+>.*<\\w+/>"), // text outside PHP (HTML text) TODO
    T_INSTANCEOF("instanceof"), // instance of a class (instanceof)
    T_INSTEADOF("insteadof"), // trait (code reuse) conflict resolution keyword (insteadof)
    T_INT_CAST("(int)|(integer)"), // casting to integer ( (int) or (integer) )
    T_INTERFACE("interface"), // interface keyword (interface)
    T_ISSET("isset\\("), // determine if a variable is set and is not NULL (isset()) TODO
    T_IS_EQUAL("=="), // comparison operator (==) CHECKED
    T_IS_GREATER_OR_EQUAL(">="), // comparison operator (>=)
    T_IS_IDENTICAL("==="), // comparison operator (===)
    T_IS_NOT_EQUAL("!=|\\<\\>"), // comparison operator (!= or <>)
    T_IS_NOT_IDENTICAL("!=="), // comparison operator (!==)
    T_IS_SMALLER_OR_EQUAL("<="), // comparison operator (<=) CHECKED
    T_SPACESHIP("<\\=\\>"), // comparison operator (<=>)
    T_LINE("__LINE__"), // line magic constant (__LINE__)
    T_LIST("list\\("), // assign variables as if they were an array (list())
    T_LNUMBER("\\d+"), // integer number (decimal, hex, oct, bin) CHECKED
    T_LOGICAL_AND("^and$"), // logical and operator (and)
    T_LOGICAL_OR("^or$"), // logical or operator (or)
    T_LOGICAL_XOR("^xor$"), // logical xor operator (xor)
    T_METHOD_C("__METHOD__"), // magic constant for method (__METHOD__)
    T_MINUS_EQUAL("-="), // minus equal operator (-=)
    T_MOD_EQUAL("%="), // mod equal operator (%=)
    T_MUL_EQUAL("\\*="), // multiply equal operator (*=)
    T_NAMESPACE("namespace"), // namespace keyword ()
    T_NS_C("__NAMESPACE__"), // magic constant for namespace
    T_NEW("new"), // new keyword
    T_NUM_STRING("\\$\\w+\\[\\d+\\]"), // numeric array index inside string
    T_OBJECT_CAST("\\(object\\)"), // casting to object
    T_OBJECT_OPERATOR("->"), // object operator CHECKED
    T_OPEN_CURLY("\\{"), // open curly bracket CHECKED
    T_OPEN_TAG("\\<\\?php|<\\?|\\<%"), // escaping from HTML CHECKED
    T_OPEN_TAG_WITH_ECHO("<\\?\\=|<%="), // escaping from HTML
    T_OR_EQUAL("\\|="), // bitwise or assignment operator
    T_PLUS_EQUAL("\\+="), // plus assignment operator
    T_POW("\\*\\*"), // power arithmetic operator
    T_POW_EQUAL("\\*\\*="), // power assignment operator
    T_PRIVATE("private"), // scope keyword
    T_PUBLIC("public"), // scope keyword CHECKED
    T_PROTECTED("protected"), // scope keyword
    T_REQUIRE("require\\(|require_once\\("), // require keyword
    T_RETURN("return"), // return keyword
    T_SEMICOLON(";"), // semicolon CHECKED
    T_SL("<<"), // bitwise operator
    T_SL_EQUAL("<<="), // bitwise assignment operator
    T_SR(">>"), // bitwise operator
    T_SR_EQUAL(">>+"), // bitwise assignment operator
    T_START_HEREDOC("<<<"), // starting heredoc
    T_STATIC("static"), // scope keyword CHECKED
    T_STRING_CAST("\\(string\\)"), // casting to string
    T_STRING_VARNAME("\\$\\{\\w+"), // including vars, arr elements etc as a string
    T_SWITCH("switch"), // condition keyword
    T_THROW("throw"), // exception keyword
    T_TRAIT("trait"), // trait keyword
    T_TRAIT_C("__TRAIT__"), // trait magic constant
    T_TRY("try"), // exceptions keyword CHECKED
    T_UNSET_CAST("\\(unset\\)"), // casting to unset CHECKED
    T_USE("use"), // use keyword CHECKED
    T_VAR("var"), // var keyword CHECKED
    T_VARIABLE("\\$\\w+"), // variable declaration CHECKED
    T_WHILE("while"), // while keyword CHECKED
    T_XOR_EQUAL("\\^="), // xor assignment CHECKED
    T_YIELD_FROM("yield from"), // yield from keyword CHECKED
    T_YIELD("yield"), // yield keyword CHECKED
    T_GREATER("\\>"), // CHECKED
    T_LESS("\\<"), // CHECKED
    T_MULTIPLY("\\*"), // multiplication operator CHECKED
    T_OPEN_ROUND("\\("), // open round bracket CHECKED
    T_REFERENCE("\\&"), // pass parameter by reference CHECKED
    T_CONCAT("\\."), // CHECKED
    T_STRING_IDENT("\\w+"), // identifiers, e.g. keywords like parent and self, function names, class names and more are matched. See also T_CONSTANT_ENCAPSED_STRING. CHECKED
    T_CONSTANT_ENCAPSED_STRING("'.*'|\".*\""), // TODO do for ""
    T_ASSIGNMENT("="); // assignment operator CHECKED

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


