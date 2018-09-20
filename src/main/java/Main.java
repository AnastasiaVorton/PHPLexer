import phplexer.Lexer;

public class Main {
    public static void main(String[] args) {
//        System.out.println("hello");
        Lexer lexer = new Lexer("in.txt");
        System.out.println(lexer.input);
        while (!lexer.isExausthed()) {
            System.out.println("lexema: " + lexer.currentLexema() + ", token: " + lexer.currentToken());
            lexer.moveAhead();
        }

        if (lexer.errorMessage() != null && !lexer.errorMessage().isEmpty()) {
            System.out.println(lexer.errorMessage());
        } else {
            System.out.println("No errors found");
        }
    }
}
