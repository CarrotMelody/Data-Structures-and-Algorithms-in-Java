package Search;

public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int index = seqSearch(arr, 10);
        if(index == -1) {
            System.out.println("沒有查找到");
        } else {
            System.out.println("找到, 索引為 = "+index);
        }
    }

    public static int seqSearch(int[] arr, int value) {
        //逐一比對，發現有相同值就返回索引
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == value) {
                return i;
            }
        }
        return -1;
    } 
}
