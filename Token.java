/**
 * Хранит тип токена из enum, а также значение
 */
public class Token {
    private final TokenType TYPE;
    private final String VALUE;

    Token(TokenType type, String value) {
        TYPE = type;
        VALUE = value;
    }

    public TokenType getType() {
        return TYPE;
    }

    public String getValue() {
        return VALUE;
    }
}