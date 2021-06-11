package Search;

import java.util.Arrays;

public class FibSearch {
    static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index = "+fibSearch(arr, 1));
        System.out.println("index = "+fibSearch(arr, 8));
        System.out.println("index = "+fibSearch(arr, 1000));
        System.out.println("index = "+fibSearch(arr, 555));
    }
    // 非遞迴方式得到一個費氏數列
    public static int[] fib() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for(int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * 非遞迴方式寫費氏搜尋
     * @param arr 陣列
     * @param key 需要搜尋的值
     * @return 返回對應的索引, 如果沒有則返回 -1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; // 表示費氏分割數值的索引, 也就是 k
        int mid = 0;
        int f[] = fib(); // 獲取費氏數列
        // 獲取費氏分割數值的索引
        while(high > f[k] - 1) {
            k++;
        }
        // 因為f[k] 值可能大於 arr 的長度, 因此需要構建一個新的陣列指向arr
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 使用 arr 最後的數填充temp
        // {1, 8, 10, 89, 1000, 1234, 0, 0, 0...} => {1, 8, 10, 89, 1000, 1234, 1234, 1234, 1234...}
        for(int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        // 使用while來循環處理, 找到 key
        while(low <= high) {
            mid = low + f[k-1] - 1;
            if(key < temp[mid]) { // 應該繼續向左尋找
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else { // 找到
                if(mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
