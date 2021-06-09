package LinkedList;
public class JosephusDemo {
    public static void main(String[] args) {
        CircularSinglyLinkedList csll = new CircularSinglyLinkedList();
        csll.addNode(5); // 加入5個節點
        csll.showNode();
        System.out.println("===出隊列順序===");
        csll.countNode(1, 2, 5);
    }
}

// 創建一個環形的單向鏈結串列
class CircularSinglyLinkedList {
    // 創建一個first節點, 當前沒有編號
    private Node first = new Node(-1);

    // 添加節點, 構建成一個環形的鏈結串列
    public void addNode(int nums) {
        // nums 數據校驗
        if (nums < 1) {
            System.out.println("nums值不正確! ");
            return;
        }

        Node cur = null; // 輔助變數 幫助構建環形鏈結串列
        // 使用for循環創建環形鏈結串列
        for (int i = 1; i <= nums; i++) {
            // 根據編號創建節點
            Node child = new Node(i);
            // 若為第一個節點
            if (i == 1) {
                first = child;
                first.setNext(first); // 構成環
                cur = first; // 讓cur 指向第一個節點
            } else {
                cur.setNext(child);
                child.setNext(first);
                cur = child;
            }

        }
    }

    // 遍歷當前環形鏈結串列
    public void showNode() {
        // 判斷鏈結串列是否為空
        if (first == null) {
            System.out.println("鏈結串列為空");
            return;
        }
        // 因為 first不能動,因此需要使用一個輔助指針
        Node cur = first;
        while (true) {
            System.out.printf("節點編號 %d \n", cur.getNo());
            if (cur.getNext() == first) { // 遍歷完畢
                break;
            }
            cur = cur.getNext(); // 往後遍歷
        }
    }

    /**
     * 
     * @param startNo  表示從第幾個node開始數
     * @param countNum 表示數幾次
     * @param nums     表示最初有多少個node在環中
     */
    // 根據使用者的輸入,計算出節點出隊列的順序
    public void countNode(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("參數輸入有誤，請重新輸入");
            return;
        }
        // 創建一個輔助指針,幫助完成node出隊列
        Node helper = first;
        while (true) {
            if (helper.getNext() == first) { // helper指向最後一個節點
                break;
            }
            helper = helper.getNext();
        }

        //先讓first和helper 移動startNo-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //當節點報數時，讓first和helper指針同時移動 countNum- 1次 然後出隊列
        while(true) {
            if(helper == first) {
                //說明環中只有一個node
                break;
            }
            //讓 first和helper同時移動 countNum -1次
            for (int i = 0; i < countNum -1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //這時 first 指向的節點就是 要出隊列 的節點
            System.out.printf("節點 %d 出隊列\n",first.getNo());
            //將first指向的節點出隊列
            first = first.getNext(); //出隊列後往下一個節點繼續數
            helper.setNext(first);  //把helper歸回第一個數的節點繼續遍歷
        }
        System.out.printf("最後留在環中的節點為 %d \n",first.getNo());
    }
}

// 創建一個Node類,表示一個節點
class Node {
    private int no; // 編號
    private Node next; // 指向下一個節點

    public Node(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}