package Stack;
public class Calculator {
    public static void main(String[] args) {
        String expression = "5+6*2-3";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        
        //定義需要的相關變數
        int index = 0; //用於遍歷
        int num1 = 0,num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //將每次遍歷得到的char保存到ch
        String keepNum = ""; //用於拼接多位數

        //開始while循環遍歷expression
        while(true) {
            //依次得到expression的每個字符
            ch = expression.substring(index, index+1).charAt(0);
            //若為運算子
            if(operStack.isOper(ch)) {
                //判斷當前符號棧是否為空
                if(!operStack.isEmpty()){
                    //處理當前運算子優先級小於等於棧中運算子優先級
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把運算結果入數棧
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //當前運算子優先級大於符號棧中運算子優先級
                        operStack.push(ch);
                    }
                } else {
                    //如果為空
                    operStack.push(ch);
                }
            } else { //如果是數，直接入棧
                // numStack.push(ch - 48); //ch是字元 要轉為數字 請對照ASCII表 
                // 處理多位數
                keepNum += ch;
                //如果ch已經是expression的最後一位，就直接入棧
                if(index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判斷下一個字符是否為數字
                    if(operStack.isOper(expression.substring(index+1, index+2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //將keepNum清空
                        keepNum = "";
                    }
                }
            }
            //讓 index +1, 並判斷是否遍歷到expression最後
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //遍歷完畢,就順序的從數棧和符號棧中pop出相應的數和運算子,並計算
        while(true) {
            //如果符號棧為空,則計算到最後的結果,數棧中只有一個數字[結果]
            if(operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res); //入棧
        }
        //將數棧最後的數pop出來,就是結果
        System.out.printf("表達式 %s = %d",expression, numStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize; //棧的大小
    private int[] stack; //陣列,模擬棧
    private int top = -1; //棧頂,初始化為-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //返回棧頂值
    public int peek() {
        return stack[top];
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

    //返回運算子的優先級，優先級是programmer來定的
    //優先級使用數字表示，數字越大，則優先級越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假定目前的表達式只有 +-*/
        }
    }

    //判斷是不是一個運算子
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //計算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; //用於存放計算結果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //注意順序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}