public class Token {
    private String type;
    private String value;
    private int position;
    private int offset;

    public enum TokenTypes {
        T_ABSTRACT, // class abstraction (abstract)
        T_AND_EQUAL,  // bitwise and assignment operator (&=)
        T_ARRAY, // array declaration ( array() )
        T_ARRAY_CAST, // casting to array ( (array) )
        T_AS, // foreach operator (as)
        T_BAD_CHARACTER, // anything below ASCII 32 except \t (0x09), \n (0x0a) and \r (0x0d)
        T_BOOLEAN_AND, // logical and operator (&&)
        T_BOOLEAN_OR, // logical or operator (||)
        T_BOOL_CAST, // casting to boolean ( (bool) or (boolean) )
        T_BREAK, // break operator (break)
        T_CALLABLE, // callable keyword (callable)
        T_CASE, // switch-case operator (case)
        T_CATCH, // exception catch keyword (catch)
        T_CLASS, // class declaration keyword (class)
        T_CLASS_C, // class magic constant (__CLASS__)
        T_CLONE, // object cloning operator (clone)
        T_CLOSE_TAG, // escaping from HTML tag (?> or %>)
        T_COALESCE, // null Coalescing operator (??)
        T_COMMENT, // comments (// or # and /**/)
        T_CONCAT_EQUAL, // concatenation assignment operator (.=)
        T_CONST, // class constant (const)
        T_CONSTANT_ENCAPSED_STRING, // TODO meaning? just a string?
        T_CONTINUE, // continue keyword (continue)
        T_CURLY_OPEN, // passing complex variables as inputs ({$)
        T_DEC, // decrementing operator (--)
        T_DECLARE, // declare keyword (declare)
        T_DEFAULT, // default keyword for switch-case (default)
        T_DIR, // directory magic constant (__DIR__)
        T_DIV_EQUAL, // division assignment  operator (/=)
        T_DNUMBER, // double numbers (0.21 etc)
        T_DOC_COMMENT, // docstyle comment (/** */)
        T_DO, // do keyword for while statement (do)
        T_DOLLAR_OPEN_CURLY_BRACES, //passing complex variables as inputs (${)
        T_DOUBLE_ARROW, // array key => value assignment (=>)
        T_DOUBLE_CAST, // casting to double ( (real), (double) or (float) )
        T_DOUBLE_COLON, // (::) TODO exaplain where used
        T_ECHO, // output strings (echo)
        T_ELLIPSIS, // a variable number of arguments in a function (...)
        T_ELSE, // else keyword (else)
        T_ELSEIF, // elseif keyword (elseif)
        T_EMPTY, // determine whether a variable is empty (empty)
        T_ENCAPSED_AND_WHITESPACE, // variable used in string declaration ("&varname")
        T_ENDDECLARE, // enddeclare keyword (enddeclare)
        T_ENDFOR, // endfor keyword (endfor)
        T_ENDFOREACH, // endforeach keyword (endforeach)
        T_ENDIF, // endif keyword (endif)
        T_ENDSWITCH, // endswitch keyword (endswitch)
        T_ENDWHILE, // endwhile keyword (endwhile)
        T_END_HEREDOC, // a way to delimit strings () TODO how it's represented?
        T_EVAL, // evaluate a string as PHP code (eval())
        T_EXIT, // output a message and terminate the current script (exit or die)
        T_EXTENDS, // inheritance keyword (extends)
        T_FILE, // file magic constant (__FILE__)
        T_FINAL, // final keyword (final)
        T_FINALLY, // an exception block specified after or instead of catch blocks (finally)
        T_FOR, // for keyword (for)
        T_FOREACH, // foreach keyword (foreach)
        T_FUNCTION, // function declaration keyword (function or cfunction)
        T_FUNC_C, // function magic constant (__FUNCTION__)
    }


    Token(String name, String value, int position, int offset){
        this.type = name;
        this.value = value;
        this.position = position;
        this.offset = offset;

    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    public int getOffset() {
        return offset;
    }
}
