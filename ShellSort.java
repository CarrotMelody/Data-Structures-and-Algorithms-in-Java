package Sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int [] arr = {13,14,94,33,82,25,59,94,65,23,45,27,73,25,39,10};
        shellsort2(arr);
    }

    //交換法
    public static void shellsort(int [] arr) { 
        int temp = 0;
        int step = arr.length /2 ;
        int count = 0;
        while (step >= 1) {
            for(int i = step; i < arr.length; i++) {
                // 遍歷各組中所有的元素 , 步長為 arr.length/2 
                for(int j = i - step; j >= 0; j -= step) {
                    // 如果當前元素大於加上步長後的那個元素,說明需要交換
                    if(arr[j] > arr[j+step]) {
                        temp = arr[j];
                        arr[j] = arr[j+step];
                        arr[j+step] = temp;
                    }
                }
            }
            step = step / 2;
            count++;
            System.out.println("第 "+(count)+" 輪希爾排序後為 "+Arrays.toString(arr));
        }
    }

    //移位法
    public static void shellsort2(int [] arr) {
        int temp = 0;
        int step = arr.length /2 ;
        int count = 0;
        int j;

        while(step >= 1) {
            for(int i = step; i < arr.length; i++) {
                j = i;
                temp = arr[j];
                if(arr[j] < arr[j - step]) {
                    while(j - step >= 0 && temp < arr[j - step]) {
                        //移動
                        arr[j] = arr[j - step];
                        j -= step;
                    }
                    // 當退出while循環,就給temp找到插入的位置
                    arr[j] = temp;
                }
            }
            step = step / 2;
            count++;
            System.out.println("第 "+(count)+" 輪希爾排序後為 "+Arrays.toString(arr));
        }
    }

}
