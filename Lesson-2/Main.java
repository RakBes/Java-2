/**
 * Java. Level 2; Lesson 3; HomeWork3
 *
 * @coauthor: Раков Валерий;
 * @version dated: 24 июля 2019 года
 */

/** Есть строка вида: "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0"; (другими словами матрица 4x4)
        1 3 1 2
        2 3 2 2
        5 6 7 1
        3 3 1 0

        Написать метод, на вход которого подаётся такая строка, метод должен преобразовать строку в двумерный массив типа String[][];

        Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и вернуть результат;

        Ваши методы должны бросить исключения с собственными названиями в случаях:
        Если размер матрицы, полученной из строки, не равен 4x4;
        Если в одной из ячеек полученной матрицы не число; (например символ или слово)

        В методе main необходимо вызвать полученный метод, обработать возможные исключения и вывести результат расчета. */

public class Main {

    public static void main(String[] args) {
        String s = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0"; // OK
//        String s = "3 f1 2\n2 3 2 2\n5 f6 7 1\n3 3 1 0"; // MyException: NOT 4x4 ARRAY Exception
//        String s = "1 c 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0"; // NumberFormatException

        String[][] stringArray = convertStringToArray(s);
        System.out.println(calculate(stringArray));
    }

    static String[][] convertStringToArray(String s) {
        String tmp = s + "\n";
        for (int i = 0, space = 0, n = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == ' ') space++;
            if (tmp.charAt(i) == '\n') {
                if (space == 3) space = 0;
                else throw new MyException("размер матрицы из строки, не равен 4x4");
            }
        }

        int length = s.split("\n").length;
        String[][] result = new String[length][length];
        String[] clearN = s.split("\n");

        for (int i = 0; i < length; i++) {
            String[] clearSpaces = clearN[i].split(" ");
            for (int j = 0; j < length; j++) {
                result[i][j] = clearSpaces[j];
            }
        }

        return result;
    }

    static double calculate(String[][] s) {
        int sum = 0;

        for (String[] s1 : s) {
            for (String s2 : s1) {
                try {
                    sum += Integer.parseInt(s2);
                } catch (NumberFormatException e) {
                    System.out.println("в одной из ячеек матрицы не число");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        return sum / 2;
    }
}