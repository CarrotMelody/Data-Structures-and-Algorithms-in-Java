package Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolishNotation {
    public static void main(String[] args) {
        //1. 1+((2+3)*4)-5 => 123+4*+5-
        //2. 直接對str進行操作並不方便，因此先將字串轉成一個中綴表示法的list
        //3. 即 1+((2+3)*4)-5 => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList); //[1,+,(,(,2,+,3,),*,4,),-,5]

        //4. 將得到的中綴表達式對應的List => 後綴表達式對應的List
        //即 [1,+,(,(,2,+,3,),*,4,),-,5] => [1,2,3,+,4,*,+,5,-] , 小括號被消除掉了
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpressionList); //[1,2,3,+,4,*,+,5,-]

        //計算後綴表達式結果
        System.out.printf("expression = %d",calculate(suffixExpressionList));
    }

    //方法：將得到的中綴表達式對應的list => 後綴表達式的list
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定義數棧和運算子棧
        Stack<String> s1 = new Stack<String>(); //運算子棧
        //由於s2這個棧在整個過程中沒有push & pop 並且最後我們還需要逆序 因此不使用stack , 直接使用list
        //Stack<String> s2 = new Stack<String>(); 
        List<String> s2 = new ArrayList<String>(); //儲存中間結果

        for (String item : ls) {
            //數, 加入s2
            if(item.matches("\\d+")) {
                s2.add(item);
            } else if(item.equals("(")) { //左括號
                s1.push(item);
            } else if(item.equals(")")) { //右括號 
                while(!s1.peek().equals("(")) { //遇到左括號前將s1 pop到s2
                    s2.add(s1.pop());
                }
                s1.pop(); //將左括號消除
            } else {
                //當item的優先級小於等於s1棧頂運算子
                while(s1.size() != 0 && (Operation.getValue(s1.peek()) >= Operation.getValue(item))) {
                    s2.add(s1.pop());
                }
                //還需要將item push入棧
                s1.push(item);
            }
        }

        //將s1中剩餘的運算子依次pop出並加入s2
        while(s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2; //因為是存在list中, 所以按順序輸出就是對應的後綴表達式
    }

    //方法：將中綴表達式轉為對應的list
    public static List<String> toInfixExpressionList(String s) {
        //定義一個list,存放中綴表達式
        List<String> ls = new ArrayList<String>();
        int i = 0; //用於遍歷expression
        String str; //對多位數的拼接
        char c; //每遍歷一個字符就放入到c

        do{
            //如果 c 非數字, 需要加入到ls
            if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                str = ""; //將 str 清空
                while(i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c; //多位數拼接
                    i++;
                }
                ls.add(str);
            }
        } while(i < s.length()); 
        
        return ls;
    }

    //完成對後綴表示法的運算
    public static int calculate(List<String> ls) {
        //創建棧
        Stack<String> stack = new Stack<String>();
        //遍歷 ls
        for (String item : ls) {
            //使用正則取出數
            if(item.matches("\\d+")) { // +:匹配多位數
                //入棧
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")) {
                    res = num1 + num2;
                } else if(item.equals("-")) {
                    res = num1 - num2;
                } else if(item.equals("*")) {
                    res = num1 * num2;
                } else if(item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("運算子有誤");
                }
                // 把res入棧
                stack.push(res + "");
            }
        }
        //最後留在stack中的數據為運算結果
        return Integer.parseInt(stack.pop());
    }
}

//返回一個運算子對應的優先級
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("運算子有誤");
                break;
        }
        return result;
    }
}