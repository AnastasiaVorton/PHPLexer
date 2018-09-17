import java.util.regex.Pattern;

public class Token {
    private String type;
    private TokenTest value;
    private int position;
    private int offset;

    Token(String name, TokenTest value, int position, int offset, Pattern pattern){
        this.type = name;
        this.value = value;
        this.position = position;
        this.offset = offset;
    }

    public String getType() {
        return type;
    }

    public TokenTest getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    public int getOffset() {
        return offset;
    }
}
