import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;


public class Task5 {
    public static void main(String[] args) throws Exception {
        System.out.println("_№1_");
        System.out.println(encrypt("Hello"));
        System.out.println(decrypt(new int [] {72, 33, -73, 84, -12, -3, 13, -13, -68 }));
        System.out.println(encrypt("Sunshine"));
        System.out.println("_№2_");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println("_№3_");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println("_№4_");
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
        System.out.println("_№5_");
        System.out.println(sameVowelGroup(new String [] {"toe", "ocelot", "maniac"}));
        System.out.println(sameVowelGroup(new String [] {"many", "carriage", "emit", "apricot", "animal"}));
        System.out.println(sameVowelGroup(new String [] {"hoops", "chuff", "bot", "bottom"}));
        System.out.println("_№6_");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println("_№7_");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(809));
        System.out.println(numToRus(0));
        System.out.println(numToRus(18));
        System.out.println(numToRus(126));
        System.out.println(numToRus(909));
        System.out.println("_№8_");
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));
        System.out.println("_№9_");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println("_№10_");
        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(21));
        System.out.println(hexLattice(37));
    }

    //1. Шифрует сообщение, где число символа зависит от разницы текущего символа и предыдущего
    public static int[] encrypt(String word) {
        int[] encrypted = new int[word.length()];
        encrypted[0] = word.charAt(0);// первая буква попадает в массив как число
        int pred = encrypted[0];
        for (int i = 1; i < word.length(); i++) { //проходимся по всем символам слова со второй буквы
            encrypted[i] = word.charAt(i) - pred; //разница в кодировке
            pred = word.charAt(i);
        }
        return encrypted;
    }

    // Функция дешифрует сообщения, зашифрованные функцией encrypt
    public static String decrypt(int[] encrypted) {
        StringBuilder decrypted = new StringBuilder("" + (char) encrypted[0]); //чар переводит число в букву
        int prev = encrypted[0];
        for (int i = 1; i < encrypted.length; i++) {
            decrypted.append((char) (encrypted[i] + prev)); // записываем букву по её коду
            prev = encrypted[i] + prev; //записывает код буквы
        }
        return decrypted.toString();
    }

    // 2. Шахматы
    public static boolean canMove(String figure, String start, String end) {
        int startRow = start.charAt(0) - 'A'; //начальная позиция в число
        int startCol = start.charAt(1) - '1';
        int endRow = end.charAt(0) - 'A'; //конечная позиция
        int endCol = end.charAt(1) - '1';
        if (startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7 || endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7)
            return false; // потому что такого не может быть
        switch (figure) {
            case "Pawn": // пешка
                return startRow == endRow && (endCol - startCol == 1 || (startCol == 1 && endCol - startCol == 2));
            case "Rook": // ладья (по горизонтали или вертикали)
                return startRow == endRow || startCol == endCol;
            case "Knight": // конь г
                return Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1 || Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2;
            case "Bishop": // слон (по диагонали)
                return Math.abs(startRow - endRow) == Math.abs(startCol - endCol);
            case "Queen": //как ладья + слон
                return startRow == endRow || startCol == endCol || Math.abs(startRow - endRow) == Math.abs(startCol - endCol);
            case "King": //король в любую сторону на 1
                return Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1;
            default:
                return false;
        }
    }

    // 3. Функция проверяет все ли символы строки subword последовательно находятся в строке word
    public static boolean canComplete(String subword, String word) {
        int startIdx = 0;
        for (int i = 0; i < subword.length(); i++) { // идём по маленькому слову
            int idx = word.indexOf(subword.charAt(i), startIdx); //ищем следующую букву от индекса текущей, чтобы буквы были по порядку
            if (idx == -1) return false;
            startIdx = idx + 1;
        }
        return true;
    }

    // 4. Функция перемножает цифры суммы всех чисел до тех пор, пока не получится однозначное число
    public static int sumDigProd(int... numbers) { //принимаем массив неизвестной длины
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) { //складываем все числа массива
            sum += numbers[i];
        }
        while (sum > 9) {
            int prod = 1;
            while (sum > 0) {
                prod *= (sum % 10);
                sum /= 10;
            }
            sum = prod;
        }
        return sum;
    }

    // 5. Вспомогательная функция, считает уникальное численное значение на основе уникальных гласных в строке
    public static int countVowels(String word) {
        final String vowels = "aeiouy";
        StringBuilder unique = new StringBuilder();
        int sum = 0;
        for (char lit : word.toLowerCase().toCharArray()) {
            if (vowels.indexOf(lit) != -1 && !unique.toString().contains(lit + "")) { // если  есть в списке гласных и в строке
                sum += lit; // считает сумму уникальных гласных
                unique.append(lit); //добавляет в строку уникальных символов
            }
        }
        return sum;
    }

    // Функция выбирает все слова с одинаковым набором гласных
    public static String[] sameVowelGroup(String[] words) {
        String[] result = new String[words.length];
        int resultIdx = 0;
        int baseVowels = countVowels(words[0]); //уникальная сумма для главного слова
        for (int i = 0; i < words.length; i++) {
            if (baseVowels == countVowels(words[i])) { //сравниваем текущую и главную сумму
                result[resultIdx] = words[i]; //помещаем совпавшее слово в массив
                resultIdx++;
            }
        }
        return Arrays.copyOf(result, resultIdx); //отрезается массив до первой пустой ячейки
    }

    // 6. Функция для валидации банковских карт
    public static boolean validateCard(long number) {
        String num = number + ""; // переводим счисло в строку
        if (num.length() < 14 || num.length() > 19) return false;
        int sum = 0;
        int last = Integer.parseInt(num.charAt(num.length() - 1) + ""); //последняя/контрольная цифра
        for (int i = 0; i < num.length() - 1; i++) { //проходим по числу до предпоследней цифры
            int digit = Integer.parseInt(num.charAt(i) + ""); //цифра числа
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) digit -= 9; // то же самое, что найти сумму цифр
            }
            sum += digit;
        }
        return (10 - sum % 10) == last;
    }

    // 7. Функция для преобразования числа в его строковое представление на английском языке
    public static String numToEng(int number) {
        String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String hundred = "hundred";
        StringBuilder answer = new StringBuilder();
        if (number > 99) {
            answer.append(digits[number / 100]).append(" ").append(hundred).append(" ");
            number %= 100;
        }
        if (number > 19) {
            answer.append(tens[number / 10 - 2]).append(" ");
            number %= 10;
            answer.append(digits[number]);
        } else {
            if (number > 9) {
                answer.append(teens[number - 10]);
            } else {
                answer.append(digits[number]);
            }
        }
        return answer.toString();
    }

    // Функция для преобразования числа в его строковое представление на русском языке
    public static String numToRus(int number) {
        String[] digits = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        String[] teens = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
        String[] tens = {"двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        String[] hundreds = {"сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        StringBuilder answer = new StringBuilder();
        if (number > 99) {
            answer.append(hundreds[number / 100 - 1]).append(" ");
            number %= 100;
        }
        if (number > 19) {
            answer.append(tens[number / 10 - 2]).append(" ");
            number %= 10;
            answer.append(digits[number]);
        } else {
            if (number > 9) {
                answer.append(teens[number - 10]);
            } else {
                answer.append(digits[number]);
            }
        }
        return answer.toString();
    }

    // 8. Функция для получения зашифрованной строки на основе входной при помощи SHA-2 256
    public static String getSha256Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashInBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 9. Функция исправляет размеры слов за исключением the, of, in, and
    public static String correctTitle(String input){
        String[] words = input.split(" ");  // в список слов по пробелу
        List<String> exceptions = Arrays.stream(new String[]{"the", "of", "in", "and"}).toList(); //список исключений
        for(int i = 0; i < words.length; i++){
            String word = words[i].toLowerCase(); //делаем слово в нижнем регистре
            if(exceptions.contains(word)){ //если содержится в исключениях
                words[i] = word;//так и добавляем
            }
            else{
                words[i] = word.substring(0, 1).toUpperCase() + word.substring(1);
            }
        }
        return String.join(" ", words); // соединяем всё в одно
    }

    // 10. Гексагональная решетка
    public static String hexLattice(int n) {
        int count = 1;
        int i = 0;
        int step = 6;
        // Проверяем является ли число центрированным шестиугольным
        // находим количество итераций
        while (count < n) {
            i++;
            count += step * i;
        }
        if (count == n) {
            StringBuilder s = new StringBuilder(new String());
            int space = i;
            // Строки до центра включая
            for (int j = 0; j < i + 1; j++) {
                String line = new String();
                line += " ".repeat(space - j);
                String middle = new String();
                middle += "o ".repeat(i + 1 + j);
                middle = middle.strip();
                line += middle;
                line += " ".repeat(space - j) + "\n";
                s.append(line);
            }
            // Строки от центра
            for (int j = i - 1; j >= 0; j--) {
                String line = "";
                line += " ".repeat(space - j);
                String middle = "";
                middle += "o ".repeat(i + 1 + j);
                middle = middle.strip();
                line += middle;
                line += " ".repeat(space - j) + "\n";
                s.append(line);
            }
            return s.toString();
        }
        else return "Invalid";
    }
}
