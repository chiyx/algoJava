package algo.ch03;

import algo.utils.SortHelper;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序的实现
 * Created by chiyx on 2016/10/1.
 */
public class QuickSorter {

    public static void quickSort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }


    private static void sort(Comparable[] arr, int lo, int hi) {
        if (lo < hi) {
            int partition = partition(arr, lo, hi);
            sort(arr, lo, partition - 1);
            sort(arr, partition + 1, hi);
        }
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo + 1, j = hi;
        while (true) {
            while (SortHelper.less(a[i++], a[lo])) {
                if (i == hi) break;
            }
            while (SortHelper.less(a[lo], a[j--])) {
                if (j == lo) break;
            }
            if (i >= j) break;
            SortHelper.exch(a, i, j);
        }
        SortHelper.exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] arr = {7, 8, 2, 1, 5, 9, 10, 2, 30, 7};
        quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
           StdOut.print(i + "\t");
        }
        StdOut.println();
    }

}
