import core.SourceReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * A SourceReader that reads the input character by character.
 */
public class SymbolSourceReader implements SourceReader {

    /**
     * File reader used to read the file with given name.
     */
    private PushbackReader reader;

    public SymbolSourceReader(String fileName) throws FileNotFoundException {
        this.reader = new PushbackReader(new FileReader(fileName));
    }

    @Override
    public String getNextSymbol() throws IOException {
        try {
            int symbolCode = reader.read();
            if (symbolCode != -1) {
                return Character.toString((char) symbolCode);
            } else {
                // we reached the end of file
                reader.close();
                return null;
            }
        } catch(Exception e) {
            reader.close();
            throw e;
        }
    }

    @Override
    public String peekSymbol() throws IOException {
        String next = getNextSymbol();
        reader.unread(next.charAt(0));

        return next;
    }
}
