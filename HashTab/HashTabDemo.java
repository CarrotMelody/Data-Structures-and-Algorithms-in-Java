package HashTab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        // 創建雜湊表
        HashTable hashTab = new HashTable(7);

        String key = "";
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("a(add): 添加員工資訊 | f(find): 搜尋員工資訊 | l(list): 顯示員工資訊 | e(exit): 退出系統");
            key = sc.next();
            switch (key) {
                case "a":
                    System.out.println("輸入員工id: ");
                    int id = sc.nextInt();
                    System.out.println("輸入員工名字: ");
                    String name = sc.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "l":
                    hashTab.list();
                    break;
                case "f":
                    System.out.println("請輸入要搜尋的id: ");
                    id = sc.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "e":
                    sc.close();
                    System.out.println("退出系統");
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//Hash Table 管理多條鏈結串列
class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    /**
     * 
     * @param size 多少條鏈結串列
     */
    public HashTable(int size) {
        this.size = size;
        // 初始化 empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        // 分別初始化每個鏈結串列
        for(int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加員工
    public void add(Emp emp) {
        int empLinkedListNum = hashFun(emp.id);
        // 將 emp 添加到對應鏈結串列中
        empLinkedListArray[empLinkedListNum].add(emp);
    }

    // 遍歷所有鏈結串列, 遍歷hash table
    public void list() {
        for(int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }
    // 根據id搜尋員工
    public void findEmpById(int id) {
        // 使用雜湊函數確定到哪條鏈結串列搜尋
        int empLinkedListNum = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNum].findEmpById(id);
        if(emp != null) {
            System.out.printf("在第 %d 條鏈結串列中找到員工id = %d , name = %s a\n",(empLinkedListNum + 1), id, emp.name);
        } else {
            System.out.println("在雜湊表中沒有找到該員工資訊");
        }
    }

    // 編寫雜湊函數, 使用簡單的取模法
    public int hashFun(int id) {
        return id % size;
    }

}

class Emp {
    public int id;
    public String name;
    public Emp next; // 默認為null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    // 頭節點 指向第一個 Emp, 這個鏈結串列的 head 是直接指向第一個員工
    private Emp head; // 默認null

    // 添加員工到鏈結串列
    // 1. 假定添加員工時 id 自增長 , id 的分配從小到大
    //    因此我們將該員工直接加入到本鏈結串列的最後即可
    public void add(Emp emp) {
        // 如果是添加第一個員工
        if(head == null) {
            head = emp;
            return;
        }
        // 如果不是第一個員工, 則使用第一個輔助的指針, 幫助定位到最後
        Emp curEmp = head;
        while(true) {
            if(curEmp.next == null) { // 說明到鏈結串列最後
                break;
            }
            curEmp = curEmp.next;
        }

        // 退出時直接將emp 加入鏈結串列
        curEmp.next = emp;
    }

    // 遍歷鏈結串列的員工資訊
    public void list(int no) {
        if(head == null){
            System.out.println("第 "+(no+1)+" 鏈結串列為空");
            return;
        }
        System.out.print("第 "+(no+1)+" 鏈結串列資訊為");
        Emp curEmp = head;
        while(true) {
            System.out.printf(" => id = %d , name = %s\t",curEmp.id,curEmp.name);
            if(curEmp.next == null){ //curEmp為最後節點
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    // 根據 id 搜尋員工
    // 搜尋到就返回Emp, 沒有則返回null
    public Emp findEmpById(int id) {
        if(head == null) {
            System.out.println("鏈結串列為空");
            return null;
        }
        Emp curEmp = head;
        while(true) {
            if(curEmp.id == id) {
                break;
            }
            // 遍歷結束沒找到 退出
            if(curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}