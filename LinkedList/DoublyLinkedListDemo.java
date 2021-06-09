package LinkedList;
public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("雙向鏈結串列的測試");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及時雨");
        HeroNode2 hero2 = new HeroNode2(2, "盧俊義", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吳用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林沖", "豹子頭");

        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        //按照編號順序添加
        doublyLinkedList.addByOrder(hero1);
        doublyLinkedList.addByOrder(hero4);
        doublyLinkedList.addByOrder(hero2);
        doublyLinkedList.addByOrder(hero3);
        doublyLinkedList.list();

        System.out.println("===修改測試===");
        HeroNode2 newHeroNode = new HeroNode2(4, "公孫勝", "入雲龍");
        doublyLinkedList.updata(newHeroNode);
        doublyLinkedList.list();

        System.out.println("===刪除測試===");
        doublyLinkedList.delete(2);
        doublyLinkedList.list();
    }
}

class DoublyLinkedList {
    //先初始化一個頭節點,頭節點不要動,不存放具體數據
    private HeroNode2 head = new HeroNode2(0, "", "");
    public HeroNode2 getHead() {
        return head;
    }

    //遍歷雙向鏈結串列
    public void list() {
        if(head.next == null) {
            System.out.println("連結串列為空");
            return;
        }
        HeroNode2 temp = head.next;
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

    //添加數據到雙向鏈結串列的最後
    public void add(HeroNode2 heroNode) {
        //因為head節點不能動,因此我們需要一個輔助遍歷 temp
        HeroNode2 temp = head;
        //遍歷連結串列,找到最後
        while(true) {
            if (temp.next == null) {
                break;
            }

            temp = temp.next; //沒找到就將temp後移一個節點
        }
        //當退出while循環時,temp就指向了連結串列的最後
        //形成一個雙向鏈結串列
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            heroNode.prev = temp;
        }
    }

    //修改節點內容
    public void updata(HeroNode2 newHeroNode) {
        if(head.next == null) {
            System.out.println("連結串列為空");
            return;
        }
        //找到需要修改的節點
        //定義一個輔助變數
        HeroNode2 temp = head.next;
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

    //刪除節點
    public void delete(int no) {
        HeroNode2 temp = head.next;
        boolean flag = false; 
        if(head.next == null) {
            System.out.println("鏈結串列為空,無法刪除");
            return;
        }
        while(true) {
            if (temp == null) break; //已遍歷結束
            if(temp.no == no) { //要刪除的節點為第一個節點
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag) {
            temp.prev.next = temp.next;
            //如果是最後一個節點就不需要執行下面這句話,否則會出現空指針異常
            if(temp.next != null) {
                temp.next.prev = temp.prev;
            }
            System.out.printf("刪除了 %d 節點.\n",no);
        }else {
            System.out.printf("沒有找到編號 %d 的節點, 不能刪除\n",no);
        }
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一個節點, 默認為null
    public HeroNode2 prev; //指向前一個節點, 默認為null
    //構造函數
    public HeroNode2(int no, String name, String nickname) {
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