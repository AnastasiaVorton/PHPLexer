package phplexer;

import core.LexicalAnalyzer;
import core.SourceReader;

/**
 * Lexical analyzer for PHP language.
 */
public class PHPLexicalAnalyzer implements LexicalAnalyzer<PHPTokenInstance> {
    private SourceReader reader;

    public PHPLexicalAnalyzer(SourceReader reader) {
        this.reader = reader;
    }

    @Override
    public PHPTokenInstance getNextToken() {
        return null; // TODO: add proper implementation
    }
}
