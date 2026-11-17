/*
Пример для ввода:
VAR A, B : INTEGER; BEGIN A = 10; B = 2; END
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Магазинный автомат. " +
                "Разработали ст-ты гр.25ВП2 Бобрышева&Редин");
        System.out.println("""
               Вид:
                VAR <список переменных> : INTEGER; BEGIN <список присваиваний> END
               Условия:
                Expression → Term { (+ | -) Term }
                Term       → Factor { / Factor }
                Factor     → - Factor | ( Expression ) | NUMBER | IDENTIFIER
               """);

        while (true) {
            System.out.print("Введите текст программы или EXIT для выхода: ");

            String s = scanner.nextLine();

            if (s.equalsIgnoreCase("exit"))
                return;

            try {
                new Parser(Lexer.tokenize(s)).parse();
                System.out.println("Ошибки не найдены");
            } catch (RuntimeException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}