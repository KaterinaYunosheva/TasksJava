import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task3 {
    public static void main(String[] args) {
        System.out.println("_№1_");
        System.out.println(solutions(1, 0, -1));
        System.out.println(solutions(1, 0, 0));
        System.out.println(solutions(1, 0, 1));
        System.out.println(solutions(0, 1, 1));
        System.out.println("_№2_");
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));
        System.out.println("_№3_");
        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(28));
        System.out.println(checkPerfect(496));
        System.out.println(checkPerfect(12));
        System.out.println(checkPerfect(97));
        System.out.println("_№4_");
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(flipEndChars("ada"));
        System.out.println(flipEndChars("Ada"));
        System.out.println(flipEndChars("z"));
        System.out.println("_№5_");
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(isValidHexCode("#eaecee"));
        System.out.println(isValidHexCode("#CD5C58C"));
        System.out.println(isValidHexCode("#CD5C5Z"));
        System.out.println(isValidHexCode("#CD5C&C"));
        System.out.println(isValidHexCode("CD5C5C"));
        System.out.println("_№6_");
        System.out.println(same(new int [] {1, 3, 4, 4, 4}, new int [] {2, 5, 7}));
        System.out.println(same(new int [] {9, 8, 7, 6}, new int [] {4, 4, 3, 1}));
        System.out.println(same(new int [] {2}, new int [] {3, 3, 3, 3, 3}));
        System.out.println("_№7_");
        System.out.println(isKaprekar(3));
        System.out.println(isKaprekar(5));
        System.out.println(isKaprekar(297));
        System.out.println(isKaprekar(0));
        System.out.println(isKaprekar(1));
        System.out.println("_№8_");
        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111"));
        System.out.println("_№9_");
        System.out.println(nextPrime(12));
        System.out.println(nextPrime(24));
        System.out.println(nextPrime(11));
        System.out.println("_№10_");
        System.out.println(rightTriangle(3, 4, 5));
        System.out.println(rightTriangle(145, 105, 100));
        System.out.println(rightTriangle(70, 130, 110));

    }

    //1. кол-во решений квадратного уравнения
    public static int solutions(int a, int b, int c) {
        if (a != 0 && (b * b - 4 * a * c) > 0) {
            return 2;
        }
        else if (a == 0 || (b * b - 4 * a * c) == 0) {
            return 1;
        }
        else {
            return 0;
        }
    }

    //2. позиция "zip"
    public static int findZip(String str){
        int a = str.indexOf("zip");
        if(a == -1)return -1;
        return str.indexOf("zip", a+1); // ищем zip начиная с первого
    }

    //3. совершенное число
    public static boolean checkPerfect(int n) {
        int sum = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if(n % i == 0) {
                arrayList.add(i); //добавляются в массив делители числа
            }
        }
        for (int j = 0; j < arrayList.size(); j++) {
            sum += arrayList.get(j);
        }
        return (sum == n);
    }

    //4. поменять местами 1 и последний символ
    public static String flipEndChars(String str) {
        if (str.length() < 2) {
            return "Incompatible.";
        }
        else if (str.charAt(0) == str.charAt(str.length()-1)) {
            return "Two's a pair.";
        }
        else {
            return str.substring(str.length()-1) + str.substring(1, str.length()-1) + str.substring(0, 1);
        }
    }

    //5. допустимый 16-ричный код
    public static boolean isValidHexCode(String str) {
        String s = "abcdefABCDEF1234567890";
        String substr = (str.toLowerCase()).substring(1);
        if (str.charAt(0) == '#' && substr.length() == 6) {
            for (int i = 0; i < substr.length(); i++) {
                if (s.indexOf(substr.charAt(i)) != -1) continue; //смотрим все ли символы допустимы, т.е. из s
                else return false;
            }
        }
        else return false;
        return true;
    }

    //6. массивы имеют одинаковое кол-во уникальных элементов
    public static boolean same(int[] arr1, int[] arr2){
        Set<Integer> set1 = new HashSet<>(); //set не может содержать одинаковых символов
        Set<Integer> set2 = new HashSet<>();
        for(int elem : arr1) set1.add(elem);
        for(int elem : arr2) set2.add(elem);
        return set1.size() == set2.size();
    }

    //7. число Капрекара
    public static boolean isKaprekar(int n) {
        int a, b;
        int sqr = n*n;
        String s = Integer.toString(sqr);
        if (n == 0 || n == 1) {
            return true;
        }
        else if (s.length() > 1 && s.length() % 2 == 0) {
            a = Integer.parseInt(s.substring(0, s.length()/2));
            b = Integer.parseInt(s.substring(s.length()/2, s.length()));
            return (a + b == n);
        }
        else if (s.length() > 1 && s.length() % 2 != 0) {
            a = Integer.parseInt(s.substring(0, (s.length() - 1)/2));
            b = Integer.parseInt(s.substring((s.length() - 1)/2, s.length()));
            return (a + b == n);
        }
        return false;

    }
  //8. самая длинная последовательность нулей
    public static String longestZero(String str) {
        String[] arr = str.split("1");
        String s = "";
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() > max) {
                max = arr[i].length();
                s = arr[i];
            }
        }
        return s;
    }

    //9. простое число или следующее простое число
    public static int nextPrime(int n){
        for(int i = n+1; true; i++){
            boolean a = true;
            for(int j = 2; j < i; j++){ // перебираем делители
                if(i % j == 0){
                    a = false;
                    break;
                }
            }
            if(a)return i;
        }
    }



    //10. ребра прямоугольного треугольника
    public static boolean rightTriangle(int a, int b, int c){
        if((a*a + b*b == c*c) || (a*a + c*c == b*b) || (b*b + c*c == a*a)) return true;
        return false;
    }


}