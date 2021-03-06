package Recursion;
public class EightQueen {
    int max = 8; //共有多少個皇后
    int [] array = new int[max]; //保存皇后放置位置的結果
    static int count;
    public static void main(String[] args) {
        EightQueen EightQueen = new EightQueen();
        EightQueen.checkPostion(0);
        System.out.printf("一共有 %d 種解法",count);
    }

    //方法:放置第n個皇后
    //特別注意: checkPostion 每一次遞迴時進入到 checkPostion 中都有 for(int i = 0; i < max; i++), 因此會有回溯
    private void checkPostion(int n) {
        if(n == max) {
            printPosition();
            return;
        }

        //依次放入皇后 並判斷是否衝突
        for(int i = 0; i < max; i++) {
            //先把當前這個皇后放到該列的第一行
            array[n] = i;
            //判斷當放置第n個皇后到i行時是否衝突
            if(judgePosition(n)) { //不衝突
                //接著放 n+1個皇后, 即開始遞迴
                checkPostion(n+1);
            }

            //如果衝突,就繼續執行 array[n] = i; 並且i+1
        }
    }

    //查看當我們放置第n個皇后, 就去檢測該皇后是否和前面已擺放的皇后衝突
    /**
     * 
     * @param n 表示第n個皇后
     * @return
     */
    private boolean judgePosition(int n) {
        for(int i = 0; i < n; i++) {
            //1. array[i] == array[n] 判斷第n個皇后是否和前面的n-1個皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 判斷第n個皇后是否和前面n-1個皇后在同一對角線
            //第二個皇后 n = 1 , 假設放在第二行{0,1...} 代表 array[1] = 1
            //Math.abs(1-0) = 1 , Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            //3. 判斷是否在同一列沒有必要,因為n每次都在遞增
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) { 
                return false;
            }
        }
        return true;
    }

    //將皇后擺放位置輸出
    private void printPosition() {
        count++;
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
