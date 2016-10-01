package algo.utils;

/**
 * 排序中用到的帮助类
 * Created by chiyx on 2016/10/1.
 */
public class SortHelper {

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
