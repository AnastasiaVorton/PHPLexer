package phplexer;

import core.LexicalAnalysisException;
import core.LexicalAnalyzer;
import core.SourceReader;

import java.io.IOException;

/**
 * Lexical analyzer for PHP language.
 */
public class PHPLexicalAnalyzer implements LexicalAnalyzer<PHPTokenInstance> {
    private SourceReader reader;

    public PHPLexicalAnalyzer(SourceReader reader) {
        this.reader = reader;
    }

    @Override
    public PHPTokenInstance getNextToken() throws LexicalAnalysisException, IOException {
        return null; // TODO: add proper implementation
    }
}
