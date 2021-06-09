package Queue;
import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args){
        ArrayQueue queue = new ArrayQueue(4);
        char key = ' ';
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        
        while(loop){
            System.out.println("s(show)顯示佇列 | e(exit)退出程序 | a(add)添加數據到佇列 | g(get)從佇列取出數據 | h(head)查看佇列頭的數據");
            key = sc.next().charAt(0);
            switch(key){

                case 's':
                    queue.showQueue();
                    break;

                case 'e':
                    sc.close();
                    loop = false;
                    break;

                case 'a':
                    System.out.println("請輸入要添加的數據:");
                    int value = sc.nextInt();
                    queue.addQueue(value);
                    break;

                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的數據是 %d\n",result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'h':
                    try {
                        int result = queue.headQueue();
                        System.out.printf("佇列頭的數據是 %d\n",result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

            
        }
        System.out.println("程序已結束~");

    }
}

class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    //創建佇列的構造函數
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;  //指向佇列頭部,分析出front是指向佇列頭部的前一個位置
        rear = -1; //指向佇列尾部,指向佇列尾部的數據(佇列最後一個數據)
    }

    //判斷佇列是否滿
    public boolean isFull() {
        return rear == maxSize-1;
    }

    //判斷佇列是否為空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加數據到佇列
    public void addQueue(int n) {
        //判斷佇列是否滿
        if(isFull()){
            System.out.println("佇列已滿,不能加入數據");
            return;
        }
        rear++; //讓rear後移
        arr[rear] = n;
    }

    //獲取佇列數據出佇列
    public int getQueue() {
        //判斷佇列是否空
        if(isEmpty()){
            throw new RuntimeException("佇列為空,無值可取"); //throw本身造成代碼return 不須另外寫return
        }
        front++; //讓rear後移
        return arr[front];
    }

    //顯示佇列數據
    public void showQueue() {
        if(isEmpty()){
            System.out.println("佇列空的,沒有數據");
            return;
        }
        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //顯示佇列頭數據
    public int headQueue() {
        //判斷佇列是否空
        if(isEmpty()){
            throw new RuntimeException("佇列為空,無值可取"); //throw本身造成代碼return 不須另外寫return
        }
        return arr[front+1]; //因為front指向的是頭部前一位
    }

}