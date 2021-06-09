package Sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {7,2,5,4,5,8,7};
        System.out.println("原陣列: "+Arrays.toString(arr));
        bubbleSort(arr);
    }

    public static void bubbleSort(int[] arr) {
        //泡沫排序，將最大的數排在最後
        int temp = 0; //臨時變數
        boolean flag = false; //標示變數，表示是否進行交換
        int count = 0;
        for(int j = 0; j < arr.length -1; j++) {
            for(int i = 0; i < arr.length - 1 - j; i++) {
                //如果當前的數比後一位的數大則交換
                if(arr[i] > arr[i+1]) {
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }

            if(!flag) { //在一輪排序中一次交換都沒有發生過
                if(count==0) {
                    System.out.println("總共排序了 0 次");
                }
                break;
            } else {
                count++;
                flag = false; //重置flag進行下一輪排序
            }
            System.out.println("第 "+(j+1)+" 輪排序: "+Arrays.toString(arr));

        }
    }
}
