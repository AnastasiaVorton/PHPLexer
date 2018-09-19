package core;

public interface TokenInstance<T extends Token> {
    T getToken();
    int getLine();
    int getOffset();
}
