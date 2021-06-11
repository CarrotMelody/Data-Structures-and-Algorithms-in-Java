package Search;

import java.util.ArrayList;
import java.util.List;

//注意：使用二元搜尋的前提是該陣列是有序的。
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> resIndexList = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println("1000 在陣列中的索引為: "+resIndexList);
    }
    /**
     * 
     * @param arr 陣列
     * @param left 左邊的索引
     * @param right 右邊的索引
     * @param findVal 要搜尋的值
     * @return 如果找到就返回索引,如果沒找到就返回 -1
     */
    public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int findVal) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        // 當 lfet > right 代表遞迴整個陣列但是沒有找到
        if(left > right) {
            return new ArrayList<Integer>(); // 返回空
        }

        if(findVal > midVal) { // 向右遞迴
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();

            int temp = mid - 1;
            while(true) {
                if(temp < 0 || arr[temp] != findVal) { // 向左掃到底了
                    break;
                }
                // 否則,把 temp 放入 resIndexList
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);

            // 向 mid 索引值的右邊掃描, 將所有滿足的元素索引加入到 resIndexList
            temp = mid + 1;
            while(true) {
                if(temp > right || arr[temp] != findVal) { // 向右掃到底了
                    break;
                }
                // 否則,把 temp 放入 resIndexList
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }

    }
}
