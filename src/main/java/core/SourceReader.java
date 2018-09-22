package core;

import java.io.IOException;

/**
 * An interface describing generic source code reader, which reads the source symbol by symbol.
 * Such a reader is intended to be used by LexicalAnalyzer.
 */
public interface SourceReader {
    /**
     * Read and return next symbol from the source code file.
     * @return next symbol from the source. Return null if reached the end of the file.
     */
    String getNextSymbol() throws IOException;
}
