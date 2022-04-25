package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    //создаем массив случайных чиссел
    //@sz - размер массива
    public static  int[] make_array(int sz) {
        Random random = new Random();
        final int ARR_SIZE = sz;
        int arr[] =  new int[ARR_SIZE];
        for (int k = 0; k < ARR_SIZE; k++) {
            int i = random.nextInt() % 100;
            if (i<0) i*=-1;
            arr[k] = i ;
        }
        return arr;
    }
    //выедем массив на экран
    //@arr - массив для вывода
    public static void print_array(int []arr) {
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k] + ", ");
        }
        System.out.println();
    }

    public static void swap(int []arr,int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static boolean check_array(int []arr) {
        int N = arr.length;
        for(int i = 0; i < N-1; i++) {
            if ( arr[i] > arr[i+1] ) {
                System.out.println(" arr[" + i +"]" + "> arr[" + (i+1) + "]" );
                return false;
            }
        }
        return true;
    }
    //сортировка вставками
    public static StatInfo insert_sort(int []arr) {
        StatInfo stat = new StatInfo();
        stat.setAlgoName("INSERT");
        System.out.println("Insert sort starting... array size: " + arr.length);
        long compares = 0;
        long exchanges = 0;
        stat.start();
        int N = arr.length;

        for (int i  = 1 ; i<N; i++) {
            int j=i;
            while (j > 0 && arr[j-1]>arr[j] ) {
                compares++;
                exchanges++;
                swap(arr,j-1, j);
                j--;
            }
        }
        stat.stopTimer();

        boolean result = check_sort(arr);
        stat.setCorrect(result);
        stat.setArraySize(N);
        return stat;
    }
    //
    //
    // 0 1 2 3 4 5 6 7 8
    public static StatInfo shell_sort(int []arr) {
        StatInfo stat = new StatInfo();
        stat.setAlgoName( "SHELL" );
        stat.setArraySize(arr.length);
        stat.start();
        long compares=0;
        long exchanges=0;
        int N = arr.length;

        for (int step = N / 2; step > 0; step /= 2) {
            //System.out.println("Step: " + step);
            for (int k = 0; k < step; k++) {

                //Сортируем элементы удаленные на step друг от друга
                for (int shift = k; shift < N - step; shift += step) {
                    boolean flChanged = false;
                    for (int j = k; j < N - step ; j += step) {
                        compares++;
                        //System.out.println("pos fisrt: " + j );
                        if (arr[j] > arr[j + step]) {
                            exchanges++;
                            //System.out.println("change " + arr[j] + " " + arr[j + step]);
                            swap(arr, j, j + step);
                            flChanged = true;
                        }
                    }
                    //не было никаких изменений на проходе, значит все отсортировали
                    if ( flChanged == false ) {
                        break;
                    }
                }
                //print_array(arr);
            }
            //bubble_sort(arr);
        }
        stat.stopTimer();
        boolean result = check_sort(arr);
        stat.setCorrect(result);
        return stat;
    }
        //shell sort из википедии
    public static void shell_sort_w(int []a) {
        int N = a.length;
        for (int step = N / 2; step > 0; step /= 2) {
            for (int i = step; i < N; i++) {
                for (int j = i - step; j >= 0 && a[j] > a[j + step]; j -= step) {
                    int x = a[j];
                    a[j] = a[j + step];
                    a[j + step] = x;
                }
            }
        }
    }
    //Сортировка пузырьком
    public static StatInfo bubble_sort(int []arr) {


        //сравнения
        long compares=0;
        //обмены
        long exchanges=0;

        int N = arr.length;

        StatInfo statInfo = new StatInfo();
        statInfo.setAlgoName("BUBBLE");
        statInfo.setArraySize(N);
        //менялись ли местами эллементы массива
        boolean flSwap = false;
        statInfo.start();
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0 ; j < N - 1 - i ; j++) {
                compares++;
                if ( arr[j] > arr[j+1] ) {
                    exchanges++;
                    flSwap = true;
                    swap(arr, j + 1, j);
                }

            }
            //если за проход по неотсортированному куску мы не совершили обменов
            //тогда массив отсортирован
            if ( flSwap == false ) {
                break;
            }
        }
        statInfo.stopTimer();

        boolean result = check_sort(arr);
        statInfo.setCorrect(result);
        return statInfo;
    }
    public static void main(String[] args) {

            ArrayList<StatInfo> statistics = new ArrayList<>();
            for (  int arr_size = 100; arr_size<=10000; arr_size*=10) {

                int[] arr = make_array(arr_size);
                int[] bubble_arr = new int[arr_size];
                int[] shell_arr = new int[arr_size];
                int[] insert_arr = new int[arr_size];

                System.arraycopy(arr, 0, bubble_arr, 0, arr.length);
                System.arraycopy(arr, 0, shell_arr, 0, arr.length);
                System.arraycopy(arr, 0, insert_arr, 0, arr.length);
                //System.arraycopy(arr,0, bubble_arr,0, size());
                //print_array(arr);
                StatInfo st_shell = shell_sort(bubble_arr);
                StatInfo st_bubble = bubble_sort(shell_arr);
                StatInfo st_insert = insert_sort(arr);
                statistics.add(st_shell);
                statistics.add(st_bubble);
                statistics.add(st_insert);
            }
            for(StatInfo st : statistics) {
                System.out.println(st.algoName() + "\tnum: " + st.arraySize() + " duration: " + st.duration() + "\tchecked " + st.correct());
            }
       System.out.println("");
       //print_array(arr);

    }
    public static boolean  check_sort(int []arr) {
        if (check_array(arr)) {
            //System.out.println("Sorted!");
            return true;
        } else {
            return false;
            //System.out.println("Sort failed!");
        }
    }
}
