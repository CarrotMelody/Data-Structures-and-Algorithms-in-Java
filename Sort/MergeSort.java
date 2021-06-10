package Sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length];
        System.out.println("原序列為："+Arrays.toString(arr));
    
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("合併排序後序列為："+Arrays.toString(arr));
    }

    // divide and conquer
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if(left < right) {
            int mid = (left + right) / 2;
            // 向左遞迴進行分解
            mergeSort(arr, left, mid, temp);
            // 向右遞迴進行分解
            mergeSort(arr, mid + 1, right, temp);
            // 合併
            merge(arr, left, mid, right, temp);
        }
    }

    // merge
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i, 左邊有序序列的初始索引
        int j = mid + 1; // 初始化j, 右邊有序序列的初始索引
        int t = 0; // 指向temp陣列的當前索引

        // (一) 左右子序列比大小, 將小的放到temp陣列
        while(i <= mid && j <= right) { // 左右子序列都還沒遍歷到最後時
            if(arr[i] <= arr[j]) { // 若左子序列值小於等於右子序列值，則將之放到 temp 陣列中
                temp[t] = arr[i];
                t += 1; // temp陣列索引後移
                i += 1; // 遍歷下一個
            } else { // 右子序列值小於等於左子序列值
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // (二) 把有剩餘數據的子序列依次全部放入temp
        while(i <= mid) { // 左子序列有剩餘的元素
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while(j <= right) { // 右子序列有剩餘的元素
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // (三) 將 tmep 陣列的元素 copy 到 arr , 並不是每次都 copy 所有
        t = 0;
        int tempLeft = left; // 用於暫時遍歷 temp 陣列
        while(tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }

    }
}
