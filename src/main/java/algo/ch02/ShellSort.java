package algo.ch02;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 希尔排序的实现
 * Created by chiyx on 2016/9/21.
 */
public class ShellSort {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(1000);
        }
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + ",");
            if (i % 10 == 0 && i != 0) {
                System.out.println();
            }
        }
    }

}
