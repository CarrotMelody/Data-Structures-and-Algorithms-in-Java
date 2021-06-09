package Stack;
import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner sc = new Scanner(System.in);
        while(loop) {
            System.out.println("s(show): 顯示棧 | a(add): 添加數據到棧中 | d(del): 將數據從棧中移出 | e(exit): 退出程序");
            key = sc.next();
            switch (key) {
                case "s":
                    arrStack.list();
                    break;
                case "e":
                    sc.close();
                    loop = false;
                    break;
                case "a":
                    System.out.println("請輸入要添加的數據: ");
                    int data = sc.nextInt();
                    arrStack.push(data);
                    break;
                case "d":
                    try {
                        int res = arrStack.pop();
                        System.out.printf("出棧的數據為 %d\n",res);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }
            
        }
        System.out.println("程序退出!");
    }
}

//定義一個類表示 棧
class ArrayStack {
    private int maxSize; //棧的大小
    private int[] stack; //陣列,模擬棧
    private int top = -1; //棧頂,初始化為-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判斷棧滿
    public boolean isFull() {
        return top == maxSize -1;
    }

    //判斷棧空
    public boolean isEmpty() {
        return top == -1;
    }

    //入棧
    public void push(int value) {
        if(isFull()) {
            System.out.println("棧滿");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出棧
    public int pop() {
        if(isEmpty()) {
            throw new RuntimeException("棧空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍歷棧,從棧頂開始顯示數據
    public void list() {
        if(isEmpty()) {
            System.out.println("棧空,沒有數據");
            return;
        }
        for(int i = top; i >= 0; i--){
            System.out.printf("stack[%d] = %d\n", i , stack[i]);
        }
    }

}
