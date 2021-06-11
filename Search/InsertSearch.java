package Search;

import java.util.Arrays;

public class InsertSearch {
    public static void main(String[] args) {
        int arr[] = new int[100];
        for(int i = 0; i < 100 ; i++) {
            arr[i] = i + 1;
        }

        System.out.println("原陣列: "+Arrays.toString(arr));

        int index = insertValueSearch(arr, 0, arr.length - 1, 55);
        System.out.println("55 在陣列中索引為: "+index);
    }

    /**
     * 插值搜尋要求陣列有序
     * @param arr 傳入的陣列
     * @param left 左邊索引
     * @param right 右邊索引
     * @param findVal 搜尋的值
     * @return 找到返回對應的索引, 否則返回 -1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) { // 沒找到 或 搜尋的值大於小於陣列數值範圍
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if(findVal > midVal) { // 向右遞迴
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左遞迴
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

}
