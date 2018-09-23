import core.LexicalAnalyzer;
import core.TokenInstance;
import phplexer.PHPLexicalAnalyzer;
import java.io.FileReader;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        try {
            Reader fileReader = new FileReader("in.txt");
            LexicalAnalyzer lexer = new PHPLexicalAnalyzer(fileReader);

            TokenInstance token;
            while ((token = lexer.getNextToken()) != null) {
                System.out.println("lexeme: " + token.getLexeme() + ", token: " + token.getToken().toString());
            }
        } catch (Exception e) {
            System.out.println("Exception in Main");
            e.printStackTrace();
        }
    }
}
