import core.LexicalAnalyzer;
import core.TokenInstance;
import phplexer.PHPLexicalAnalyzer;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            Reader fileReader = new FileReader("in.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
            LexicalAnalyzer lexer = new PHPLexicalAnalyzer(fileReader);

            TokenInstance token;
            while ((token = lexer.getNextToken()) != null) {
                writer.write("lexeme: " + token.getLexeme() + ", token: " + token.getToken().toString());
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception in Main");
            e.printStackTrace();
        }
    }
}
