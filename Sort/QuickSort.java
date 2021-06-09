// package Sort;

import java.util.Arrays;

public class QuickSort {
    static int []arr = {19,4,5,21,19,4};
    public static void main(String[] args) {
        System.out.println("原序列为："+Arrays.toString(arr));
        quickSort(arr);
    }

    public static void quickSort(int [] arr) {
        sort(0,arr.length-1);
        System.out.println("排序后为："+Arrays.toString(arr));
    }

    /**
     *
     * @param left => 设为基准值
     * @param right => arr.length - 1
     */
    public static void sort(int left,int right) {
        if(left < right) {
            int i = left; // 由左至右的索引
            int j = right + 1; // 由右至左的索引
            while(true) {
                while( i+1 < arr.length && arr[++i] < arr[left]); // 向右找, 直到找到比基准值大的数
                while( j-1 >= 0 && arr[--j] > arr[left]); // 向左找, 值到找到比基准值小的数
                if( i >= j) break; // 若i,j重叠或i超过j后则退出循环
                swap(i , j);
            }
            swap(left , j); // 基准点与 j 交换
            sort(left , j - 1); // 递回排序基准点左子序列
            sort(j + 1 , right); // 递回排序基准点右子序列
            
        }
    }

    public static void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}