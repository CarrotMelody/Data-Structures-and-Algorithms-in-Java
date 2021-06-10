package Sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53,  3,  542,  748,  14,  214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {
        // 得到陣列中最大的數的位數
        int max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        
        int maxLength = (max + "").length(); 

        // 定義一個二維陣列,表示10個桶,每個桶就是一個一維陣列
        // 1. 二維陣列包含10個一維陣列
        // 2. 為了防止在放入數的時候，數據溢出，則每個一維陣列大小定為arr.length => 空間換時間
        int[][] bucket = new int[10][arr.length];

        // 定義為一個一維陣列,紀錄各個桶每次放入的數據個數
        int[] bucketElementCounts = new int[10];

        // 針對每個元素的各位數進行排序處理
        for(int i = 0 , n = 1; i < maxLength; i++, n *= 10) {
            for(int j = 0; j < arr.length; j++) {
                // 取出每個元素的的對應位數的值
                int digitOfElement = arr[j] / n % 10;
                // 放入到對應的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++ ;
            }
    
            // 遍歷每個桶, 並將桶中數據放入到原陣列
            int index = 0;
            for(int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有數據才放入到原陣列
                if(bucketElementCounts[k] != 0) {
                    // 遍歷該桶 放入
                    for(int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                // 每輪處理後要將 bucketElementCounts 清零
                bucketElementCounts[k] = 0;
            }
            System.out.println("第 "+(i+1)+" 輪排序後為："+Arrays.toString(arr));
        }
    }
}
