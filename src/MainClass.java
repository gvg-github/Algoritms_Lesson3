/* Гурьевских В.Г.
(task1) Попробовать оптимизировать пузырьковую сортировку. Сравнить количество операций сравнения оптимизированной и не оптимизированной программы.
        Написать функции сортировки, которые возвращают количество операций.

(task2) Реализовать бинарный алгоритм поиска в виде функции, которой передается отсортированный массив.
        Функция возвращает индекс найденного элемента или -1, если элемент не найден.

(task3) Реализовать шейкерную сортировку.
 */

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {

        //Пузырьковая сортировка
//        task1();

        //Бинарный алгоритм поиска
//        task2();

        //Шейкерная сортировка
        task3();
    }

    //Шейкерная сортировка
    private static void task3() {

        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(arr));

        int last = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {

            if (i % 2 == 0) {
                for (int j = 0; j < last; j++) {

                    if (arr[j] > arr[j + 1]) {
                        int x = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = x;
                    }
                }
            } else {
                for (int j = last; j > 0; j--) {

                    if (arr[j - 1] > arr[j]) {
                        int x = arr[j - 1];
                        arr[j - 1] = arr[j];
                        arr[j] = x;
                    }
                }
            }
        }
        System.out.println("Shaker sort." + Arrays.toString(arr));
    }

    //Пузырьковая сортировка
    private static void task1() {
        int[] arr = new int[10];
        int[] arrSort = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
            arrSort[i] = arr[i];
        }
        System.out.println(Arrays.toString(arrSort));
        int x1 = bubbleSort(arrSort);
        System.out.println("Bubble sort. Count: " + x1 + ", array:" + Arrays.toString(arrSort));
        arrSort = arr;
        System.out.println(Arrays.toString(arrSort));
        int x2 = myBubbleSort(arrSort);
        System.out.println("Optimized bubble sort. Count: " + x2 + ", array:" + Arrays.toString(arrSort));
    }

    //Оптимизированная пузырьковая сортировка
    private static int myBubbleSort(int[] arr) {
        int count = 0;
        int last = arr.length - 1;
        boolean changed;
        for (int i = 0; i < arr.length; i++) {
            changed = false;
            count++;
            for (int j = 0; j < last - i; j++) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    int x = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = x;
                    changed = true;
                }
            }
            if (!changed) break;
        }
        return count;
    }

    //Пузырьковая сортировка из методички
    private static int bubbleSort(int[] arr) {
        int count = 0;
        int last = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            count++;
            for (int j = 0; j < last; j++) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    int x = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = x;
                }
            }
        }
        return count;
    }

    //Бинарный алгоритм поиска
    private static void task2() {
        int[] arr = new int[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        Arrays.sort(arr);
        int y = (int) (Math.random() * 100);

        System.out.println("Number: " + y + ", array:" + Arrays.toString(arr));
        int x = LessonSearchBinary(arr, y); // Бинарный алгоритм поиска из методички
        System.out.println("Index: " + x);

        System.out.println("Number: " + y + ", array:" + Arrays.toString(arr));
        x = mySearchBinary(arr, y); // Так реализовал, не заглядывая в методичку
        System.out.println("my binaty search index: " + x);
    }

    //Бинарный алгоритм поиска
    private static int LessonSearchBinary(int[] arr, int y) {
        int x = -1;
        int length = arr.length;


        if (arr[0] > y || arr[length - 1] < y) return x;
        if (arr[0] == y) return arr[0];
        if (arr[length - 1] == y) return arr[length - 1];

        int l = 0;
        int r = arr.length - 1;
        int z = l + (r - l) / 2;
        while (true) {
            if (l <= r && arr[z] != y) {
                if (arr[z] < y) {
                    l = z + 1;
                } else {
                    r = z - 1;
                }
                z = l + (r - l) / 2;
            } else {
                if (arr[z] == y) {
                    return z;
                } else break;
            }
        }
        return x;
    }

    //Моя реализация бинарного алгоритма поиска
    private static int mySearchBinary(int[] arr, int find) {

        int x = -1;
        int length = arr.length;

        if (arr[0] > find || arr[length - 1] < find) return x;
        if (arr[0] == find) return arr[0];
        if (arr[length - 1] == find) return arr[length - 1];

        int z = (length / 2) + (length % 2);

        while (true) {
            if (arr[z] == find) return z;
            if (length == 1) break;
            length = length - (length / 2);
            if (arr[z] > find) {
                z = z - ((length / 2) + (length % 2));
            } else {
                z = z + ((length / 2) + (length % 2));
            }
            if (z < 1 || z > arr.length - 1) break;
        }
        return x;
    }
}
