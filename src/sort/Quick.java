package sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author MXDC
 * @date 2019/9/8
 **/
public class Quick {

    private static boolean less (Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 将数组切分为a[lo..i-1],a[i],a[i+1..hi]
     * @param a
     * @param lo
     * @param
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        // i为左指针，j为右指针
        int i = lo, j = hi+1;
        // v为切分元素
        Comparable v = a[0];
        while(true){
            // 左指针指向的元素小于切分元素，继续向右扫描
            // 直到找到大于等于切分元素时停止循环
            // 防止越界
            while(less(a[++i], v)){ if (i==hi){break;}}
//            左边边界检测可以去掉，当j到最左边时即a[j]==v,它不可能小于自己
//            while (less(v,a[--j])){ if (j==lo){break;}}
            while (less(v,a[--j])){}
            if (i>=j) {break;}
            exch(a, i, j);
        }
        //将 v = a[j]放入正确的位置
        exch(a, lo, j);
        return j;
    }


    public static void sort(Comparable[] a){
//        StdRandom
        sort(a, 0, a.length -1);
    }

    private static void sort(Comparable[] a, int lo , int hi){
        if (hi<=lo) {return;}
        int j = partition(a, lo, hi);
        sort(a, lo,j-1);
        sort(a,j+1,hi);
    }

    public static void main(String[] args) {
        Integer [] a = new Integer[] {6,1,3,5,7,2,4,6,8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
