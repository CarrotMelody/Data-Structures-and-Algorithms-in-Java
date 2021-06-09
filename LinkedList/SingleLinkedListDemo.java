package LinkedList;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args){
        HeroNode hero1 = new HeroNode(1, "宋江", "及時雨");
        HeroNode hero2 = new HeroNode(2, "盧俊義", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吳用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林沖", "豹子頭");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //按照編號順序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();

        // System.out.println("====反轉鏈結串列====");
        // reverseList(singleLinkedList.getHead());
        // singleLinkedList.list();

        System.out.println("====逆序輸出鏈結串列====");
        reversePrint(singleLinkedList.getHead());
        // singleLinkedList.list();
    }

    //逆序單向鏈結串列
    public static void reversePrint(HeroNode head) {
        if(head.next == null) return;
        //創建一個stack將各個節點push進stack中
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //將stack中節點輸出
        while(stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //將單向鏈結串列反轉
    public static void reverseList(HeroNode head) {
        //如果鏈結串列為空或者只有一個就無需反轉
        if(head.next == null || head.next.next == null) return;

        //輔助指針 幫助我們遍歷原來的鏈結串列
        HeroNode cur = head.next; //第一筆數據
        HeroNode next = null; //指向cur的下一個節點;
        HeroNode reverseHead = new HeroNode(0, "", "");

        //遍歷原來的鏈結串列,每遍歷一個節點就將其取出並放在reverseHead的最前端
        while(cur != null) {
            next = cur.next; //先保留當前節點的下一個節點
            cur.next = reverseHead.next; //將cur的下一個節點指向新的鏈結串列最前端
            reverseHead.next = cur; //將cur連結到新的鏈結串列上
            cur = next; //讓 cur 後移遍歷
        }

        //將head.next 指向 reverseHead.next
        head.next = reverseHead.next;
    }

    //獲取鏈結串列長度
    public static int getLength(HeroNode head) {
        
        if(head.next == null)  return 0;
        int length = 0;
        HeroNode cur = head.next;
        while(cur != null) {
            length++;
            cur = cur.next;
        }

        return length;
    }

    //查找倒數第n個節點
    public static HeroNode findLastIndexNode(HeroNode head,int index) {
        if (head.next == null) return null;
        int size = getLength(head);
        if (index <= 0 || index > size) return null;
        HeroNode cur = head.next;
        for(int i=0; i< size-index; i++) {
            cur = cur.next;
        }
        return cur;
    }

}

//定義SingleLinkedList 管理英雄
class SingleLinkedList {
    //先初始化一個頭節點,頭節點不要動,不存放具體數據
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }
    //添加節點到單向連結串列
    //思路:當不考慮編號順序時
    //1.找到當前連結串列的最後節點
    //2.將最後節點的next域指向新的節點
    public void add(HeroNode heroNode) {

        //因為head節點不能動,因此我們需要一個輔助遍歷 temp
        HeroNode temp = head;

        //遍歷連結串列,找到最後
        while(true) {
            if (temp.next == null) {
                break;
            }

            temp = temp.next; //沒找到就將temp後移一個節點
        }

        //當退出while循環時,temp就指向了連結串列的最後
        //將最後這個節點的next指向新的節點
        temp.next = heroNode;

    }

    public void addByOrder(HeroNode heroNode) {
        //因為頭節點不能動,因此仍用輔助變數來幫助找到添加的位置
        //因為是單向連結串列，所以temp是位於添加位置的前一個節點
        HeroNode temp = head;
        boolean flag = false; //添加的編號是否已經存在

        while(true) {
            if(temp.next == null) break;
            if(temp.next.no > heroNode.no) { //位置找到, 就在temp的後面插入
                break;
            }else if(temp.next.no == heroNode.no) { //編號已經存在
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //判斷flag的值
        if(flag) {
            System.out.printf("準備添加的英雄編號 %d 已經存在，不能添加\n",heroNode.no);
        }else{
            //插入到連結串列中, temp的後面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改節點的資訊,根據編號來修改
    public void updata(HeroNode newHeroNode) {
        if(head.next == null) {
            System.out.println("連結串列為空");
            return;
        }
        //找到需要修改的節點
        //定義一個輔助變數
        HeroNode temp = head.next;
        boolean flag = false; //是否找到該節點

        while(true) {
            if (temp == null) break; //已遍歷結束
            if (temp.no == newHeroNode.no) { //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根據flag 判斷是否找到
        if(flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("沒有找到編號 %d 的節點, 不能修改\n",newHeroNode.no);
        }

    }

    //刪除
    public void delete(HeroNode heroNode) {
        HeroNode temp = head.next;
        boolean flag = false; 

        while(true) {
            if (temp == null) break; //已遍歷結束

            if (temp.no == heroNode.no) { 
                flag = true;
                System.out.println("flag: "+flag);
                break;
            } 

            temp = temp.next;
        }

        if(flag) {
            temp.next = heroNode.next;
        } else {
            System.out.printf("沒有找到編號 %d 的節點, 不能刪除\n",heroNode.no);
        }

    }

    //顯示連結串列
    public void list() {
        if(head.next == null) {
            System.out.println("連結串列為空");
            return;
        }

        HeroNode temp = head.next;

        while(true) {
            if(temp == null) {
                break;
            }
            //輸出節點訊息
            System.out.println(temp);
            //將temp後移, 不然是死循環
            temp = temp.next;
        }
    }

    

}

//定義HeroNode, 每個HeroNode 對象就是一個節點
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一個節點

    //構造函數
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //重寫toString
    @Override
    public String toString() {
        return "HeroNode [no = " + no + ", name = " + name + ", nickname = " + nickname + "]";
    }

}
