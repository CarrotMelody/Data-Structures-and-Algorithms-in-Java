package Sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int []arr = {17, 3, 25, 14, 20, 9};
        insertSort(arr);
    }

    public static void insertSort(int [] arr) {
        for(int i = 1; i < arr.length; i++) {
            // 定義待插入的數
            int insertVal = arr[i];
            int insertIndex = i-1; // 即 arr[i] 前面這個數的索引

            // 給insertVal找到插入的位置
            // 1.保證在insertVal找插入位置, 不越界
            // 2.insertVal < arr[insertIndex]待插入的數還沒找到插入位置
            // 3.就需要將arr[insertIndex] 後移
            while(insertIndex >= 0 && insertVal < arr[insertIndex]) { 
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 當退出while循環時,說明插入的位置找到,insertIndex + 1

            if(insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            
            System.out.println("第"+(i+1)+"輪插入排序後 "+Arrays.toString(arr));
        } 
    }
}
