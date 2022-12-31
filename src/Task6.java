import java.util.*;


public class Task6 {
    public static void main(String[] args) throws Exception {
        System.out.println("_№1_");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println(bell(4));
        System.out.println("_№2_");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println("_№3_");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));
        System.out.println("_№4_");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] {"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[] {"b"}));
        System.out.println("_№5_");
        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade"));
        System.out.println(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"));
        System.out.println(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit"));
        System.out.println(getHashTags("Science Visualizing"));
        System.out.println("_№6_");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));
        System.out.println("_№7_");
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));
        System.out.println("_№8_");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));
        System.out.println(convertToRoman(400));
        System.out.println(convertToRoman(66));
        System.out.println(convertToRoman(46));
        System.out.println(convertToRoman(996));
        System.out.println(convertToRoman(2499));
        System.out.println("_№9_");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 - 17 = 1"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println("_№10_");
        System.out.println(palindromeDescendant(11211230));
        System.out.println(palindromeDescendant(13001120));
        System.out.println(palindromeDescendant(23336014));
        System.out.println(palindromeDescendant(11));
        System.out.println(palindromeDescendant(123456));
    }
    //1. Функция, возвращающая соответсвующее число Белла для числа n
    public static int bell(int n){
        int[][] bell = new int[n+1][n+1];//Двумерный массив для хранения треугольника Белла по слоям
        bell[0][0] = 1;//Треугольник начинается с 1
        //Треугольник заполняется по слоям так, что первый элемент нового слоя равен последнему предыдущего
        for (int i = 1; i <= n; i++) {
            bell[i][0] = bell[i-1][i-1];
            for (int j = 1; j <= i; j++) {
                bell[i][j] = bell[i-1][j-1] + bell[i][j-1];
            }
        }
        return bell[n][0];//Следовательно, первый элемент последнего слоя и есть ответ
    }

    //2. Функция для перевода слова в свиную латынь
    public static String translateWord(String word){
        if(word.length() == 0)return "\"\"";
        String vowels = "aeiouyAEIOUY";//Константы гласных, согласных и всех разрешённых символов
        String consonants = "bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ";
        String allowedSymbols = consonants + vowels;

        //Находим конец слова в строке, т.к. кроме слова в строке могут присутствовать знаки препинания
        int endIdx = word.length();
        while(allowedSymbols.indexOf(word.charAt(endIdx - 1))== -1)endIdx--;

        if(vowels.indexOf(word.charAt(0)) == -1){//Проверка на что начинается слово: согласные/гласные
            //Обрезка первой буквы слова добавление его в конец вместе с "ay", а затем остальной части строки
            return word.substring(1, endIdx) + word.charAt(0) + "ay" + word.substring(endIdx);
        } else {
            //Обрезка слова в строке , добавление "yay", а затем остальной части строки
            return word.substring(0, endIdx) + "yay" + word.substring(endIdx);
        }
    }

    // Функция для перевода предложения в свиную латынь
    public static String translateSentence(String sentence){
        String[] words = sentence.split(" ");//Разбиваем строку на слова по пробелам

        for(int i = 0; i < words.length; i++){//Применяем функцию перевода слова к каждому слову в массиве
            words[i] = translateWord(words[i]);
        }
        words[0] = words[0].substring(0, 1).toUpperCase() + words[0].substring(1).toLowerCase();
        return String.join(" ", words);
    }

    //3. Функция проверяет на валидность строку содержащую цвет в формате RGB/RGBA
    public static boolean validColor(String color){
        if(!color.contains("rgb"))return false;//Проверка содержит ли строка "rgb"
        boolean isRgba = false;
        if(color.contains("rgba")){ //Проверка на содержание в строке "rgb" или "rgba" и обрезка этой части строки
            isRgba = true;
            color = color.substring(4);
        } else {
            color = color.substring(3);
        }
        //Проверка строки на наличие скобок и затем обрезка их
        if(color.charAt(0) != '(' || color.charAt(color.length() - 1) != ')')return false;
        color = color.substring(1, color.length() - 1);

        //Разбивка оставшихся чисел на массив строк
        String[] numbers = color.split(",");

        //Проверка на то, что цвет не в формате RGBA, а чисел не три
        if(!isRgba && numbers.length != 3)return false;

        //Попытка собрать три первых числа и сразу проверка на то, что они в интервале [0, 255]
        for(int i = 0; i < 3; i++){
            try{
                int num = Integer.parseInt(numbers[i]);
                if(num < 0 || num > 255)return false;
            } catch (NumberFormatException e){
                return false;
            }
        }
        //Когда цвет в формате RGBA пытаемся проверить четвёртое дробное число на интервал [0, 1.0]
        if(numbers.length == 4){
            try{
                double num = Double.parseDouble(numbers[3]);
                if(num < 0 || num > 1)return false;
            } catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    //4. Функция для исключения повторяющихся параметров из строки URL, а также для удаления исключённых параметров
    public static String stripUrlParams(String url, String[] ...subParamsToStrip){
        //Узнаём где у нас в строке '?' слева получается чистый url, справа параметры
        int askIdx = url.indexOf('?');
        String exc = "";
        if(askIdx == -1)return url;//Если параметров нет, то ответом будет url
        String cleanUrl = url.substring(0, askIdx);//Получаем чистый url
        //Получаем параметры разбивая правую часть строки по '&'
        String[] params = url.substring(askIdx + 1).split("&");
        int excludedLength = params.length;
        int excludedPtr = 0;//Указатель на пустую ячейку исключений
        //Объявляем массив исключений, его длина - максимальное число исключений, т.е кол-во параметров + кол-во исключений
        String[] excluded;
        if(subParamsToStrip.length != 0){//Если есть исключения
            //Длина массива исключений увеличивается
            excludedLength += subParamsToStrip[0].length;
            //Если длина исключений больше единицы, то параметры заданы неправильно
            if(subParamsToStrip.length > 1) return "Error";
            excluded = new String[excludedLength];//Создаём массив исключений
            //Добавляем исключения в массив исключений
            for(int i = 0; i < subParamsToStrip[0].length; i++){
                excluded[excludedPtr] = subParamsToStrip[0][i];
                excludedPtr++;
            }
        } else {
            excluded = new String[excludedLength];//Если нет исключения, то массив исключений будет пустым
        }
        StringBuilder answer = new StringBuilder();
        //Инициализируем массив для разбиения параметра, а также переменную для ключа и значения
        String paramWord;
        String paramVal;
        for(int i = 0; i < params.length; i++){//Итерируем по параметрам
            paramWord = params[i].split("=")[0];//Получаем ключ из параметра.
            //Если параметр уже в исключениях, то пропускаем его
            if(Arrays.asList(excluded).contains(paramWord))continue;
            //Ищем СловоПараметра + "=" в массиве параметров, с конца, чтобы получить последнее значение
            String toFind = paramWord + "=";
            int ptr = params.length - 1;//ptr - указатель на значение параметра с конца
            int paramIdx = params[ptr].indexOf(toFind);
            while(ptr > -1 && paramIdx != 0){
                paramIdx = params[ptr].indexOf(toFind);
                ptr--;
            }
            //Сохраняем последнее значение параметра в строку
            paramVal = params[ptr].substring(paramIdx + toFind.length());
            //Если параметр первый, то добавляем ? иначе &
            if(i == 0)answer.append("?");
            else answer.append("&");
            answer.append(paramWord).append("=").append(paramVal);//Добавляем параметр в ответ
            excluded[excludedPtr] = paramWord;//Добавляем параметр в исключения
            excludedPtr++;
        }
        answer.insert(0, cleanUrl);
        return answer.toString();
    }

    //5. Функция возвращает слово, находящееся в строке
    public static String findWord(String word){
        String allowed = "qwertyuiopasdfghjklzxcvbnm";//Допустимые символы
        int wordLength = word.length();
        String lowerWord = word.toLowerCase();//Слово, по которому будет проходить обработка
        int lIdx = 0; int rIdx = wordLength - 1;//Указатель на начало и конец слова
        //Ищем с какого индекса начинается слово
        while(allowed.indexOf(lowerWord.charAt(rIdx))== -1 && rIdx > -1)rIdx--;
        //Ищем до какого индекса длиться слово
        while(allowed.indexOf(lowerWord.charAt(lIdx))== -1 && lIdx < wordLength)lIdx++;
        return word.substring(lIdx, rIdx + 1);//Возвращаем слово
    }

    // Функция возвращает три слова максимальной длины в нижнем регистре
    public static String[] getHashTags(String seq){
        int MAX_WORDS_COUNT = 3;
        String[] words = seq.split(" ");
        int len = words.length;
        //Если слов не больше трёх, то возвращаем их
        if(len == 0)return new String[0];
        if(len == 1)return new String[]{"#" + words[0].toLowerCase()};
        if(len == 2)return new String[]{"#" + words[0].toLowerCase(), "#" + words[1].toLowerCase()};
        //Храним три больших слова
        String[] maxWords = new String[MAX_WORDS_COUNT];
        for(int i = 0; i < MAX_WORDS_COUNT; i++){
            maxWords[i] = "";
        }
        //Итерируем в цикле
        for (String word : words) {
            word = findWord(word).toLowerCase();
            //Если слово больше первого по величине, то сдвигаем остальные слова и записываем новое
            if (word.length() > (maxWords[0].length() - 1)) {
                maxWords[2] = maxWords[1];
                maxWords[1] = maxWords[0];
                maxWords[0] = "#" + word;
                //Если слово больше второго, то сдвигаем слово после него и записываем новое
            } else if (word.length() > (maxWords[1].length() - 1)) {
                maxWords[2] = maxWords[1];
                maxWords[1] = "#" + word;
                //Если слово меньше третьего, то записываем туда новое
            } else if (word.length() > (maxWords[2].length() - 1)) {
                maxWords[2] = "#" + word;
            }
        }
        return maxWords;
    }

    //6. Функция находящая n-ое число последовательности Улама
    public static int ulam(int n){
        //Если число меньше трёх, то возвращаем его значение
        if(n < 1)return 0;
        if(n == 1)return 1;
        if(n == 2)return 2;
        int[] ulamSeq = new int[n];//Создаём массив для хранения чисел Улама
        //Вносим первые элементы
        ulamSeq[0] = 1;
        ulamSeq[1] = 2;
        int ulamSeqIdx = 2;
        int ulamNum = 3;
        //Так как сумма всегда возрастает от числа к числу, то мы перебираем последовательно все числа от 3 до n
        //Итерируем в цикле, пока номер числа улана не будет равен n
        while(ulamSeqIdx < n){
            int counter = 0;//Считаем сколькими способами можно представить
            for(int i = 0; i < ulamSeqIdx; i++){
                for(int j = i + 1; j < ulamSeqIdx; j++){
                    if(ulamSeq[i] + ulamSeq[j] == ulamNum)counter++;
                }
            }
            if(counter == 1){//Если способ один, то это число Улама, записываем число в массив и увеличиваем индекс
                ulamSeq[ulamSeqIdx] = ulamNum;
                ulamSeqIdx++;
            }
            ulamNum++;
        }
        return ulamSeq[n - 1];
    }

    //7. Функция, находящая самую длинную, неповторяющуюся символьную последовательность в строке
    public static String longestNonrepeatingSubstring(String seq){
        int seqLength = seq.length();
        if(seqLength == 0)return "";
        if(seqLength == 1)return seq;
        //Инициализация переменных максимальной длины последовательности и индекса её начала
        int maxLen = 1;
        int maxIdx = 0;
        //Инициализация переменных текущей длины последовательности и индекса её начала
        int currLen = 1;
        int currIdx = 0;
        StringBuilder usedChars = new StringBuilder();
        usedChars.append(seq.charAt(0));
        for(int i = 1; i < seqLength; i++){
            //Получаем индекс текущего итерируемого символа
            int charIdx = usedChars.indexOf(String.valueOf(seq.charAt(i)));
            //Если его нет в сохранённой последовательности, то добавляем его туда и идём дальше
            if(charIdx == -1){
                currLen++;
                usedChars.append(seq.charAt(i));
            } else {
                //Если же он там есть, то уникальная последовательность закончилась, и мы сохраняем максимальную из них
                if(currLen > maxLen){
                    maxLen = currLen;
                    maxIdx = currIdx;
                }
                currLen = 1;
                currIdx = i;
                usedChars = new StringBuilder();
                usedChars.append(seq.charAt(i));
            }
        }
        //Последняя проверка, т.к. последовательность может закончиться в конце строки
        if(currLen > maxLen){
            maxLen = currLen;
            maxIdx = currIdx;
        }
        return seq.substring(maxIdx, maxIdx + maxLen);
    }

    //8. Функция для перевода числа в строку римских цифр
    public static String convertToRoman(int num){
        if(num < 1 || num > 3999)return "Error";
        StringBuilder answer = new StringBuilder();
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int i = 0;
        while(num > 0){//Итерируем по массиву чисел, кратным римским
            if(num >= nums[i]){
                answer.append(romans[i]);
                num -= nums[i];
            } else i++;
        }
        return answer.toString();
    }

    //9. Функция решает простейшие математические выражения путём обращения в рекурсию при каждой новой операции
    public static int solvePart(String part){
        //Строка со всеми допущенными операциями
        String operations = "+-*/";
        //Находим индекс символа операции в строке
        int idx = 0;
        while(idx < part.length() && operations.indexOf(part.charAt(idx)) == -1)idx++;
        //Если операция не найдена в строке, то мы считам что там уже нет операций и возвращаем число
        if(idx == part.length())return Integer.parseInt(part.strip());
        //Получаем операцию и в зависимости от неё возвращаем рекурсивно обработку левой и правой части по необходимой операции
        char operation = part.charAt(idx);
        return switch (operation) {
            case '+' -> solvePart(part.substring(0, idx)) + solvePart(part.substring(idx + 1));
            case '-' -> solvePart(part.substring(0, idx)) - solvePart(part.substring(idx + 1));
            case '*' -> solvePart(part.substring(0, idx)) * solvePart(part.substring(idx + 1));
            case '/' -> solvePart(part.substring(0, idx)) / solvePart(part.substring(idx + 1));
            default -> 0;
        };
    }

    // Функция проверяет строку с формулой на математическую достоверность
    public static boolean formula(String form){
        int equalsIdx = form.indexOf("=");//Проверяем есть ли '=' в строке
        if(equalsIdx == -1)return false;//Проверяем больше ли одного "=" в строке
        if(form.indexOf("=", equalsIdx + 1) != -1)return false;//Разбиваем строку на две части по "="
        String[] parts = form.split("=");
        //Если "=" в начале или конце строки, то формула неверна
        if(parts.length != 2)return false;
        //Получаем вычисленные части формулы
        int leftPart = solvePart(parts[0]);
        int rightPart = solvePart(parts[1]);
        return leftPart == rightPart;//Сравниваем их
    }

    //10. Функция проверяет, является ли строка, содержащая чётное число, палиндромом
    public static boolean isPalindrome(String palindrome){
        if(palindrome.length() == 0)return false;
        if(palindrome.length() % 2 != 0)return false;
        return palindrome.substring(0, palindrome.length() / 2).equals(new StringBuilder(palindrome.substring(palindrome.length() / 2)).reverse().toString());
    }

    // Функция проверяет, является ли число или его потомок палиндромом
    public static boolean palindromeDescendant(int digit){
        //Преобразуем число в строку.
        String num = String.valueOf(digit);
        if(num.length() % 2 != 0)return false;//Если строка нечётная длиной, то уже неверно
        StringBuilder nw = new StringBuilder();
        while(num.length() > 1){//Цикл по строке
            if(isPalindrome(num))return true; //Проверка на палиндром
            for(int i = 0; i < num.length() / 2; i++){//Итерация по числу с шагом 2
                int first = Character.getNumericValue(num.charAt(i * 2));//Получаем левую цифру при шаге 2
                int second = Character.getNumericValue(num.charAt(i * 2 + 1)); //Получаем правую цифру
                nw.append(first + second);//Добавляем новое число в строку
            }
            num = nw.toString();//Переносим получившуюся строку в num
            nw = new StringBuilder();//Очищаем строку
        }
        return false;
    }
}

