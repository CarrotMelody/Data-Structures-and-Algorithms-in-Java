package Recursion;
import java.util.Scanner;

public class MiGong {
    public static void main(String[] args) {   
        Map map = new Map();
        map.BuildMap();
        map.ShowMap();

        //使用遞迴給小球找路
        System.out.println("輸出新的地圖,小球走過並標示的遞迴");
        map.setWay2(1, 1);
        map.ShowMap();
    }
}

class Map{
    private int[][] map;
    private int row,col;
    private int[] gateRow = new int[8];
    private int[] gateCol = new int[8];
    private int n,m;

    //地圖產生
    public void BuildMap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("輸入列數：");
        row = sc.nextInt();
        System.out.print("輸入行數：");
        col = sc.nextInt();
        sc.close();
        map = new int[row][col];

        if(row < col) {
            m = row;
        } else {
            m = col;
        }

        //檔板產生
        for(int i=0; i < m-3 ; i++) {        
            do{
                n = (int)(Math.random() * row - 1);
                gateRow[i] = n;
                n = (int)(Math.random() * col - 1);
                gateCol[i] = n;
            } while(gateRow[i] == 0 || gateCol[i] == 0 || gateRow[i] == row || gateCol[i] == col || (gateRow[i] == 1 && gateCol[i] == 1)  || (gateRow[i] == row-2 && gateCol[i] == col-2));
        }

        //使用 1 表示牆
        //上下皆為牆(第一列和最後一列)全部設置為 1
        for(int i=0; i < col; i++) {
            map[0][i] = 1;
            map[col-1][i] = 1;
        }

        //左右皆為牆(第一行和最後一行)全部設置為 1
        for(int i=1; i < row-1; i++) {
            map[i][0] = 1;
            map[i][row-1] = 1;
        }

        //檔板
        for(int i=0; i < m-3 ; i++) {
            map[gateRow[i]][gateCol[i]] = 1;
        } 
    }

    //輸出地圖
    public void ShowMap() {    
        for(int i=0;i < row;i++) {
            for(int j=0;j < col;j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /** 使用遞迴回溯來給小球找路
     * 說明:
     * 1. map 表示地圖
     * 2. i,j 表示從地圖的哪個位置開始出發
     * 3. 如果小球能到 map[6][5]位置,則說明通路找到
     * 4. 約定: 當map[i][j] 為 0 表示該點沒有走過, 當為1 表示牆, 2表示可以走, 3表示該位置已經走過但是走不通
     * 5. 在走迷宮時,需要確定一個策略,下->右->上->左,如果該點走不通再回溯*/

    /**
     * @param map 表示地圖
     * @param i 從哪個位置開始找
     * @param j
     * @return 如果找到通路就返回true,否則返回false
     */
    public boolean setWay(int i, int j) {
        if(map[row-2][col-2] == 2){ //通路已經找到
            return true;
        } else {
            if(map[i][j] == 0) { //當前點還沒走過
                //按照策略 下->右->上->左 走
                map[i][j] = 2; //假定該點能走通

                if(setWay(i+1 ,j )) { //向下走
                    return true;
                } else if(setWay(i ,j+1 )) { //向右走
                    return true;
                } else if(setWay(i-1 ,j )) { //向上走
                    return true;
                } else if(setWay(i ,j-1 )) { //向左走
                    return true;
                } else {
                    // 說明該點是走不通,是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0,可能是 1,2,3
                return false;
            }
        }
    }

    
    //修改找路的策略,改成 上->右->下->左
    public boolean setWay2(int i, int j) {
        if(map[row-2][col-2] == 2){ //通路已經找到
            return true;
        } else {
            if(map[i][j] == 0) { //當前點還沒走過
                //按照策略 上->右->下->左 走
                map[i][j] = 2; //假定該點能走通

                if(setWay2(i-1 ,j )) { //向上走
                    return true;
                } else if(setWay2(i ,j+1 )) { //向右走
                    return true;
                } else if(setWay2(i+1 ,j )) { //向下走
                    return true;
                } else if(setWay2(i ,j-1 )) { //向左走
                    return true;
                } else {
                    // 說明該點是走不通,是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0,可能是 1,2,3
                return false;
            }
        }
    }

}