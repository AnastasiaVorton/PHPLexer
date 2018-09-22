import core.SourceReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A SourceReader that reads the input character by character.
 */
public class SymbolSourceReader implements SourceReader {

    /**
     * File reader used to read the file with given name.
     */
    private FileReader fileReader;

    public SymbolSourceReader(String fileName) throws FileNotFoundException {
        this.fileReader = new FileReader(fileName);
    }

    @Override
    public String getNextSymbol() throws IOException {
        try {
            int symbolCode = fileReader.read();
            if (symbolCode != -1) {
                return Character.toString((char) symbolCode);
            } else {
                // we reached the end of file
                fileReader.close();
                return null;
            }
        } finally {
            fileReader.close();
        }
    }
}
