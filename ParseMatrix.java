import java.io.*;

public class ParseMatrix {
    public static void main(String[] args) throws Exception {

        System.out.println("==========二維陣列===========");
        //創建原始的二維陣列 11*11
        //0: 沒有旗子, 1:黑子, 2:藍子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        //輸出原始二維陣列
        for(int[] row: chessArr1){
            for(int data:row){
                System.out.printf("%d  ",data);
            }
            System.out.println();
        }

        //1.先遍歷二維陣列,獲取非0數據個數
        int sum = 0;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr1[i][j] !=0) sum++;
            }
        }
        System.out.println("==========稀疏矩陣===========");

        //2.創建對應的稀疏矩陣
        int sparseArr[][] = new int[sum+1][3]; //列:sum為非0值的個數+1列存放原始二維陣列資訊 行:列,行,非0值

        //給稀疏矩陣賦值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍歷二維陣列,將非0值存放到稀疏矩陣中
        int count=0; //用於紀錄是第幾個非0數據
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr1[i][j] !=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                };
            }
        }

        //輸出稀疏矩陣
        for(int[] row: sparseArr){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // System.out.println("==========恢復原始二維陣列===========");
        // //恢復原始二維陣列
        // int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        // //從稀疏矩陣第二行開始讀取數據,並賦給原始二維陣列
        // for(int i=1;i<sparseArr.length;i++){
        //     chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        // }

        // for(int[] row: chessArr2){
        //     for(int data:row){
        //         System.out.printf("%d  ",data);
        //     }
        //     System.out.println();
        // }
        
        System.out.println("==========寫入/讀取檔案===========");
        File file = new File("./data.txt");
        FileWriter out = new FileWriter(file); 

        //寫入稀疏矩陣內容
        for(int i=0;i<sum+1;i++){
            for(int j=0;j<3;j++){
                out.write(sparseArr[i][j]+"\t");
            }
            out.write("\r\n");
        }
        out.close();

        //讀取稀疏矩陣內容
        BufferedReader in = new BufferedReader(new FileReader(file));  
        String line;  //一行資料
        int row=0;
        int[][] arr2 = new int[sum+1][3]; //讀取後存放在此二維陣列

        //逐行讀取,並將每個陣列放入到陣列中
        while((line = in.readLine()) != null){
            String[] temp = line.split("\t"); 
            for(int j=0;j<temp.length;j++){
                arr2[row][j] = Integer.parseInt(temp[j]);
            }
            row++;
        }
        in.close();

        //顯示讀取出的陣列
        for(int i=0;i<sum+1;i++){
            for(int j=0;j<3;j++){
                System.out.print(arr2[i][j]+"\t");
            }
            System.out.println();
        }
        }
    
}
