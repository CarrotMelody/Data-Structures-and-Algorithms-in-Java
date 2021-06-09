package Queue;
import java.util.Scanner;

public class CircularQueueDemo {
    public static void main(String[] args){
        CircularQueue queue = new CircularQueue(4); //說明設置4, 其佇列有效數據最大是3
        char key = ' ';
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        
        while(loop){
            System.out.println("s(show)顯示環形佇列 | e(exit)退出程序 | a(add)添加數據到佇列 | g(get)從佇列取出數據 | h(head)查看佇列頭的數據");
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

class CircularQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircularQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];

    }

    //判斷佇列是否滿
    public boolean isFull() {
        return (rear + 1) % maxSize == front ;
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
        
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //獲取佇列數據出佇列
    public int getQueue() {
        //判斷佇列是否空
        if(isEmpty()){
            throw new RuntimeException("佇列為空,無值可取"); //throw本身造成代碼return 不須另外寫return
        }
        //這裡需要分析出front是指向佇列的第一個元素
        //1.先把front對應的值保留到一個臨時變數
        //2.將front後移
        //3.將臨時保存的變數返回
        int value = arr[front];
        front = (front + 1) % maxSize;

        return value;
    }

    //顯示佇列數據
    public void showQueue() {
        if(isEmpty()){
            System.out.println("佇列空的,沒有數據");
            return;
        }
        //思路: 從front開始遍歷,遍歷多少個元素

        for(int i= front ;i< front + size();i++){
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]); //i % maxSize, 是因為rear 有可能會比front小 比如說rear = 1 front = 3
        }
    }

    //求出當前佇列有效數據個數
    public int size(){
        return (rear + maxSize - front) % maxSize; 
    }

    //顯示佇列頭數據
    public int headQueue() {
        //判斷佇列是否空
        if(isEmpty()){
            throw new RuntimeException("佇列為空,無值可取"); //throw本身造成代碼return 不須另外寫return
        }
        return arr[front]; //因為front指向的是頭部前一位
    }

}