package Sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int arr[] = {8,3,2,1,7,4,6,5};
        selectionSort(arr);
    }

    public static void selectionSort(int [] arr) {
        // int arr[] = {8,3,2,1,7,4,6,5};
        int min; //紀錄本輪最小值
        int index = 0; //紀錄最小值位置索引

        for(int i = 0; i < arr.length - 1; i++) { //總共比 陣列大小-1 輪
            min = arr[i]; //將當前數設為最小
            for(int j = i+1; j <= arr.length - 1; j++) {//對剩下的元素進行比較
                if(arr[j] < min) { //若有比當前數還小的元素
                    min = arr[j]; //紀錄最小值
                    index = j; //紀錄最小值索引
                }
            }
            if(index != i) {
                arr[index] = arr[i]; //將當前值放到最小值位置完成交換
                arr[i] = min; //把最小值與當前值做交換
            }
            System.out.println("第"+(i+1)+" 輪結果為: "+Arrays.toString(arr));
        }
    }
}
