class Task1 {
    public static void main(String[] args) {
        System.out.println("_№1_");
        remainder(1, 3);
        remainder(3, 4);
        remainder(-9, 45);
        remainder(5, 5);

        System.out.println("_№2_");
        triArea(3, 2);
        triArea(7, 4);
        triArea(10, 10);

        System.out.println("_№3_");
        animals(2, 3, 5);
        animals(1, 2, 3);
        animals(5, 2, 8);

        System.out.println("_№4_");
        profitableGamble(0.2, 50, 9);
        profitableGamble(0.9, 1, 2);
        profitableGamble(0.9, 3, 2);

        System.out.println("_№5_");
        operation(24, 15, 9);
        operation(24, 26, 2);
        operation(15, 11, 11);

        System.out.println("_№6_");
        ctoa('A');
        ctoa('m');
        ctoa('[');
        ctoa('\\');

        System.out.println("_№7_");
        addUpTo(3);
        addUpTo(10);
        addUpTo(7);

        System.out.println("_№8_");
        nextEdge(8, 10);
        nextEdge(5, 7);
        nextEdge(9, 2);

        System.out.println("_№9_");
        sumOfCubes(new int[] {1, 5, 9});
        sumOfCubes(new int[] {3, 4, 5});
        sumOfCubes(new int[] {2});
        sumOfCubes(new int[] {});

        System.out.println("_№10_");
        abcmath(42, 5, 10);
        abcmath(5, 2, 1);
        abcmath(1, 2, 3);
    }
    //1
    public static void remainder(int a, int b) {
        int c = a % b;
        System.out.println(c);
    }

    //2
    public static void triArea(int a, int b) {
        int c = (a * b) / 2;
        System.out.println(c);
    }

    //3
    public static void animals(int a, int b, int c) {
        int n = a * 2 + b * 4 + c * 4;
        System.out.println(n);
    }

    //4
    public static void profitableGamble(double prob, int prize, int pay) {
        System.out.println((prob * prize) > pay ? true : false);
    }

    //5
    public static void operation(int N, int a, int b) {
        if (N == (a + b)) {
            System.out.println("added");
        }
        else if (N == (a - b)) {
            System.out.println("substracted");
        }
        else if (N == (a * b)) {
            System.out.println("multiplied");
        }
        else if (N == (a / b)) {
            System.out.println("divided");
        }
        else {
            System.out.println("none");
        }
    }

    //6
    public static void ctoa(char c) {
        System.out.println((int) c);
    }

    //7
    public static void addUpTo(int a) {
        int sum = 0;
        for (int i = 1; i <= a; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    //8
    public static void nextEdge(int a, int b) {
        int c = (a + b) - 1;
        System.out.println(c);
    }

    //9
    public static void sumOfCubes(int [] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] * arr[i] * arr[i];
        }
        System.out.println(sum);
    }

    //10
    public static void abcmath(int a, int b, int c) {
        for (int i = 1; i <= b; i++) {
            a = a + a;
        }
        System.out.println(a % c == 0 ? true : false);
    }
}
