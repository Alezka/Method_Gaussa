package com.company;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        /* Ввід даних */
        System.out.println("Введіть розмір матриці");

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("Введіть коефіцієнти рівнянь:\n Якщо р-ня виду aх1-bx2=z,   то введіть a -b z і тд.");
        int m = s.nextInt();
        double[][] A = new double[100][100];
        double[] b = new double[100];
        for (int i = 0; i < n; i++) {
            A[i] = new double[100];
            for (int j = 0; j < m; j++) {
                A[i][j] = s.nextDouble();
            }
            b[i] = s.nextDouble();
        }

        /* Метод Гаусса */

        int N  = n;
        for (int p = 0; p < N; p++) {

            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            if (Math.abs(A[p][p]) <= 1e-10) {
                System.out.println("NO");
                return;
            }

            for (int i = p + 1; i < N; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // зворотній прохід

        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }

        /* вивід результатів*/

        if (n < m) {
            System.out.print("INF");
        } else {
            System.out.println("YES");
            for (int i = 0; i < N; i++) {
                System.out.print(x[i] + " ");
            }
        }

    }
}
//    Програма, яка вирішує систему лінійних алгебраїчних рівнянь методом Гаусса.
//        Формат вхідних даних:
//        У першому рядку задаються два числа: кількість рівнянь n (n≥1) і кількість невідомих m (m≥1).
//        Далі йдуть n рядків, кожен з яких містить m + 1 число. Перші m чисел - це коефіцієнти i-го рівняння системи,
//        а останнє, (m + 1) -е число - коефіцієнт bi, що стоїть в правій частині i-го рівняння.
//        Формат вихідних даних:
//        Якщо виводеться слово YES, то рішення існує і єдине, слово NO - рішення не існує,
//        і слово INF - існує нескінченно багато. Якщо рішення існує і одне,
//        то у наступному вивоиться рішення системи у вигляді m чисел, розділених пропуском.

//Приклади:
//        Sample Input 1:
//        3 3
//        4 2 1 1
//        7 8 9 1
//        9 1 3 2
//        Sample Output 1:
//        YES
//        0.2608695652173913 0.04347826086956526 -0.1304347826086957
//        Sample Input 2:
//        2 3
//        1 3 4 4
//        2 1 4 5
//        Sample Output 2:
//        INF
//        Sample Input 3:
//        3 3
//        1 3 2 7
//        2 6 4 8
//        1 4 3 1
//        Sample Output 3:
//        NO
//
