import java.util.*;


public class Task4 {
    public static void main(String[] args) {
        System.out.println("_№1_");
        System.out.println(bessie(10, 7, "hello my name is Bessie and this is my essay"));
        System.out.println("_№2_");
        System.out.println(split("()()()"));
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));
        System.out.println("_№3_");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));
        System.out.println("_№4_");
        System.out.println(overTime(new double [] {9, 17, 30, 1.5}));
        System.out.println(overTime(new double [] {16, 18, 30, 1.8}));
        System.out.println(overTime(new double [] {13.25, 15, 30, 1.5}));
        System.out.println("_№5_");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println("_№6_");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println("_№7_");
        System.out.println(toStarShorthand("abbccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));
        System.out.println("_№8_");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println("_№9_");
        System.out.println(trouble(451999277, 41177722899L));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println("_№10_");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));

    }
    //1. разбиение строки
    public static String bessie(int n, int k, String s) { // n - слов, к - символов
        String []arr = s.split(" "); //делим строку на части после пробела
        StringBuilder str = new StringBuilder();
        int count = 0; //счетчик колва символов в строке
        for (int i = 0; i < n; i++) { // проходится по словам
            if (arr[i].length() + count <= k) { // сравниваем кол-во букв в текущей строке с максимально возможным кол-вом букв
                count += arr[i].length();
                str.append(arr[i]).append(" ");
            }
            else {
                str.append("\n").append(arr[i]).append(" ");
                count = arr[i].length();
            }
        }
        return str.toString();
    }

    //2. кластер скобок
    public static ArrayList<String> split(String s) {
        StringBuilder str = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') { //кластер скобок открывается
                count++;
                str.append("(");
            }
            if (s.charAt(i) == ')') {
                count--;
                str.append(")");
            }
            if (count == 0) { //кластер скобок закрывается
                list.add(str.toString()); //добавляем в список
                str = new StringBuilder();
            }
        }
        return list;
    }

    //3. змея и верблюд
    public static String toCamelCase(String str) {
        String arr[] = str.split("_"); //отделяем по нижнему подчеркиванию
        StringBuilder s = new StringBuilder(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            // каждая первая буква слова добавляется с большой буквы, кроме первой
            s.append((arr[i]).substring(0, 1).toUpperCase()).append((arr[i]).substring(1));
        }
        return s.toString();

    }
    public static String toSnakeCase(String str) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {//смотрит наличие буквы в верхнем регистре
            if (Character.isUpperCase(str.charAt(i))) {//когда находит
                s.append("_").append(str.substring(i,i+1).toLowerCase()); //отрезает букву, она переводится в нижний регистр
            }
            else s.append(str.charAt(i));
        }
        return s.toString();

    }

    //4. зарплата за день
    public static String overTime(double arr[]) {
        double sum = 0;
        double start = arr[0]; // начало
        double end = arr[1]; //конец
        double payment = arr[2]; //зарплата в час
        double bonus = arr[3]; //коэффициент сверхурочной работы в час
        if(start > 17){ //когда только сверхурочно
            sum += (end - start) * payment * bonus;
        } else if(end > 17){
            sum += (17 - start) * payment; //часть нормально
            sum += (end - 17) * payment * bonus; //часть сверхурочно
        } else {//нормально
            sum += (end - start) * payment;
        }
        return "$" + sum;
    }

    //5. индекс массы тела
    public static String BMI(String weight, String height) {
        double w, h;
        if (weight.contains("pounds")) {
            w = Double.parseDouble(weight.substring(0, weight.indexOf(" "))) / 2.2046; //преобразуем в кг, вытаскивая число из строки
        }
        else {
            w = Double.parseDouble(weight.substring(0, weight.indexOf(" ")));
        }
        if (height.contains("inches")) {
            h = Double.parseDouble(height.substring(0, height.indexOf(" "))) * 0.0254; // в метры
        }
        else {
            h = Double.parseDouble(height.substring(0, height.indexOf(" ")));
        }
        double imt = Math.ceil(w/(h*h) * Math.pow(10, 1)) / Math.pow(10, 1);
        if (imt < 18.5) {
            return Double.toString(imt) + " Underweight";
        }
        if (imt >= 25) {
            return Double.toString(imt) + " Overweight";
        }
        else return Double.toString(imt) + " Normal weight";

    }

    //6. кол-во умножений до одноцифрового числа
    public static int bugger(int n) {
        int x = n;
        int count = 0;
        while (x > 9) {//пока не станет однозначным
            x = 1;
            while (n > 0) {
                x *= n % 10; // умножаем на последнюю цифру числа
                n = n / 10;
            }
            count += 1;
            n = x;
        }
        return count;
    }

    //7. звездная стенография
    public static String toStarShorthand(String s) {
        if (s.isEmpty()) {
            return "";
        }
        s+= " "; //прибавляет в конец пробел
        StringBuilder str = new StringBuilder();
        char x = s.charAt(0); //сравниваемый символ
        int count = 1; // счетчик кол-ва повторений

        for (int i = 1; i < s.length(); i++) {
            if (x != s.charAt(i)) { //если символы не равны
                if (count == 1) { //если первое повторение
                    str.append(x);

                }
                else {
                    str.append(x).append("*").append(count);
                }
                count = 1;
                x = s.charAt(i);
            }
            else {
                count ++;
            }
        }
        return str.toString();
    }

    //8. рифма
    public static boolean doesRhyme(String str1, String str2) {
        StringBuilder list1 = new StringBuilder();
        StringBuilder list2 = new StringBuilder();
        String letters = "aeiouy";
        String[] words = str1.split(" "); // разделяем слова
        for (char lit: words[words.length-1].toLowerCase().toCharArray()) { //последнее слово в массив символов
            if (letters.contains(lit+"")) list1.append(lit);//в лист1 лежат все гласные последнего слова первого предложения
        }
        words = str2.split(" ");
        for (char lit: words[words.length-1].toLowerCase().toCharArray()) { //последнее слово второй строки в массив символов
            if (letters.contains(lit + "")) list2.append(lit);
        }
        return list1.toString().equals(list2.toString());
    }

    //9. повторение цифр
    public static boolean trouble(long num1, long num2) {
        String str1 = String.valueOf(num1); //цифры в строки
        String str2 = String.valueOf(num2);
        Character ch = null;
        for (int i = 2; i < str1.length(); i++) {
            if (str1.charAt(i-2) == str1.charAt(i-1) && str1.charAt(i-1) == str1.charAt(i)) { //ищет, где повторяется три раза
                ch = str1.charAt(i);
                for (int j = 1; j < str2.length(); j++) {
                    if (str2.charAt(j-1) == str2.charAt(j) && str2.charAt(j-1) == ch) {//два раза
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //10. уникальные книги
    public static int countUniqueBooks(String s, char ch) {
        StringBuilder unic = new StringBuilder();
        String arr[] = s.split("[" + ch + "]"); //разбивается на слова между данными символами
        for (int i = 1; i < arr.length; i+=2) { //1,3,5
            for (char lit: arr[i].toCharArray()){ // рассматривает символы слов
                if (unic.indexOf(lit + "") == -1){ //если уникален
                    unic.append(lit); //добавляем
                }
            }
        }
        return unic.length();
    }

}
