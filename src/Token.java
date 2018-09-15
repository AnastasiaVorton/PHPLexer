public class Token {
    private String name;
    private String value;
    private int position;
    private int offset;

    Token(String name, String value, int position, int offset){
        this.name = name;
        this.value = value;
        this.position = position;
        this.offset = offset;

    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    public int getOffset() {
        return offset;
    }
}
