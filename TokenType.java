/**
 * Типы токенов
 */
public enum TokenType {
    VAR("VAR"),
    INTEGER("INTEGER"),
    BEGIN("BEGIN"),
    END("END"),

    IDENTIFIER("идентификатор"),
    NUMBER("число"),

    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),

    EQUALS("="),
    COMMA(","),
    COLON(":"),
    SEMICOLON(";"),

    LPAREN("("),
    RPAREN(")"),

    EOF("конец файла");

    private final String representation;

    TokenType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}