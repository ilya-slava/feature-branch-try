import java.util.ArrayList;
import java.util.List;

/**
 * Утилитарный класс для лексического анализа.
 * Разбивает входной текст на токены и распознает
 * ключевые слова, идентификаторы, числа и операции
 */
public final class Lexer {
    private Lexer() {
        throw new UnsupportedOperationException("Это утилитарный класс");
    }

    /**
     * Разбивает входную строку на токены
     *
     * @param INPUT входная строка
     * @return список токенов
     */
    public static List<Token> tokenize(final String INPUT) {
        List<Token> tokens = new ArrayList<>();

        int pos = 0;

        while (pos < INPUT.length()) {
            char c = INPUT.charAt(pos);

            //пропускаем пробелы и переносы
            if (Character.isWhitespace(c)) {
                pos++;
            }

            //если число
            else if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();

                //перебираем с текущей позиции все цифры, чтобы собрать число воедино
                while (pos < INPUT.length() && Character.isDigit(INPUT.charAt(pos))) {
                    number.append(INPUT.charAt(pos));
                    pos++;
                }

                tokens.add(new Token(TokenType.NUMBER, number.toString()));
            }

            //если идентификатор/ключевое слово
            else if (isLatinLetter(c)) {
                StringBuilder letters = new StringBuilder();

                //перебираем с текущей позиции все буквы, чтобы собрать слово воедино
                while (pos < INPUT.length() && isLatinLetter(INPUT.charAt(pos))) {
                    letters.append(INPUT.charAt(pos));
                    pos++;
                }

                String word = letters.toString();

                if (word.length() > 7)
                    throw new RuntimeException("Идентификатор '" + word + "' длиннее 7 символов");

                TokenType type = switch (word) {
                    case "VAR" -> TokenType.VAR;
                    case "INTEGER" -> TokenType.INTEGER;
                    case "BEGIN" -> TokenType.BEGIN;
                    case "END" -> TokenType.END;
                    default -> TokenType.IDENTIFIER;
                };

                tokens.add(new Token(type, word));
            }

            //если спец символ
            else {
                switch (c) {
                    case '+' -> tokens.add(new Token (TokenType.PLUS, "+"));
                    case '-' -> tokens.add(new Token (TokenType.MINUS, "-"));
                    case '/' -> tokens.add(new Token (TokenType.DIVIDE, "/"));
                    case '=' -> tokens.add(new Token (TokenType.EQUALS, "="));
                    case ',' -> tokens.add(new Token (TokenType.COMMA, ","));
                    case ':' -> tokens.add(new Token (TokenType.COLON, ":"));
                    case ';' -> tokens.add(new Token (TokenType.SEMICOLON, ";"));
                    case '(' -> tokens.add(new Token (TokenType.LPAREN, "("));
                    case ')' -> tokens.add(new Token (TokenType.RPAREN, ")"));
                    default -> throw new RuntimeException("Неизвестный символ '" + c + "'");
                }

                pos++;
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));

        return tokens;
    }

    /**
     * Проверяет, принадлежит ли символ к латинскому алфавиту
     *
     * @param c входной символ
     * @return true, если принадлежит, иначе false
     */
    private static boolean isLatinLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
}