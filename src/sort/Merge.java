package sort;

import java.util.Arrays;


/**
 * @author MXDC
 * @date 2019/9/7
 **/
public class Merge {
    /** 归并需要的临时辅助数组 */
    private static Comparable[] aux ;


    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    /**
     * 原地归并方法,将a[lo..mid] 和 a[mid+1..hi]归并
     * @param a 要归并的数组
     * @param lo 左边数组第一个值的索引
     * @param mid 左边数组最后一个值的索引
     * @param hi 右边数组最后一个值的索引
     */
    private static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo, j = mid+1;
        for (int k = lo; k<=hi; k++) {
            //将a[lo..hi]复制到aux[lo..hi]
            aux[k] = a[k];
        }
        // 归并回到a[lo..hi]
        for (int k = lo; k<=hi; k++) {
            // 左边数组取完
            if (i > mid){ a[k] = aux[j++]; }
            else if (j > hi) { a[k] = aux[i++]; }
            else if (less(aux[j],aux[i])) { a[k] = aux[j++]; }
            else { a[k] = aux[i++]; }
        }
    }

    /**
     * 归并排序使用递归,自顶向下
     * @param a 待排序数组
     */
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }

    /**
     * 归并排序使用循环，自底向上
     * 一一归二，二二归四....
     * @param a 带排序数组
     */
    public static void sortBU(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        // sz为和并的子数组大小,当sz大小等于数组的大小时已完成归并
        for (int sz = 1; sz < N; sz+=sz) {
            // 当数组剩下的元素不够sz大小时（N-1-lo+1），不用合并
            for (int lo = 0; sz < N-lo; lo += 2*sz) {
                // 每一轮最后一次归并的第二个数组可能小于第一个数组的大小
                merge(a, lo, lo+sz-1,Math.min(lo+2*sz-1,N-1));
            }
        }
    }
    /***
     * 将数组a[lo..hi]排序
     * @param a 待排序数组
     * @param lo 待排序数组下边界索引
     * @param hi 待排序数组上边界索引
     */
    public static void sort(Comparable[] a ,int lo, int hi){
        // 只有一个元素自己返回
        if(hi <= lo) {return;}
        int mid = (lo + hi)/2;
        // 左边半边排序
        sort(a, lo, mid);
        // 右半边排序
        sort(a, mid+1, hi);
        // 归并结果
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        Integer [] a = new Integer[] {6,1,3,5,7,2,4,6,8};
        sortBU(a);
        System.out.println(Arrays.toString(a));
    }
}
