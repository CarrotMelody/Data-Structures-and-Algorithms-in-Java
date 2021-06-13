package BinaryTree;

public class BinaryTreeDemo {
    public static void main(String[] args) throws Exception {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吳用");
        HeroNode node3 = new HeroNode(3, "盧俊義");
        HeroNode node4 = new HeroNode(4, "林沖");
        HeroNode node5 = new HeroNode(5, "關勝");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        System.out.println("===前序遍歷===");
        binaryTree.preOrder();
        System.out.println("===中序遍歷===");
        binaryTree.inOrder();
        System.out.println("===後序遍歷===");
        binaryTree.postOrder();

        System.out.println("~~~前序搜尋~~~");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if(resNode != null) {
            System.out.printf("訊息為 no = %d , name = %s\n",resNode.getNo(),resNode.getName());
        } else {
            System.out.println("找不到資訊");
        }
        
        System.out.println("~~~中序搜尋~~~");
        resNode = binaryTree.infixOrderSearch(5);
        if(resNode != null) {
            System.out.printf("訊息為 no = %d , name = %s\n",resNode.getNo(),resNode.getName());
        } else {
            System.out.println("找不到資訊");
        }

        System.out.println("~~~後序搜尋~~~");
        resNode = binaryTree.postOrderSearch(5);
        if(resNode != null) {
            System.out.printf("訊息為 no = %d , name = %s\n",resNode.getNo(),resNode.getName());
        } else {
            System.out.println("找不到資訊");
        }
        
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    // 前序遍歷
    public void preOrder() {
        if(this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二元樹為空");
        }
    }
    // 中序遍歷
    public void inOrder() {
        if(this.root != null) {
            this.root.inOrder();
        } else {
            System.out.println("二元樹為空");
        }
    }
    // 後序遍歷
    public void postOrder() {
        if(this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二元樹為空");
        }
    }

    // 前序搜尋
    public HeroNode preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    // 中序搜尋
    public HeroNode infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    // 後序搜尋
    public HeroNode postOrderSearch(int no) {
        if(root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

// 先創建 HeroNode 節點
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no,String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode [no = " + no + ", name = " + name + " ]";
    }

    public void preOrder() {
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }

    public void inOrder() {
        if(this.left != null) {
            this.left.inOrder();
        }
        System.out.println(this);
        if(this.right != null) {
            this.right.inOrder();
        }
    }

    public void postOrder() {
        if(this.left != null) {
            this.left.postOrder();
        }
        if(this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 前序遍歷搜尋
    public HeroNode preOrderSearch(int no) {
        
        System.out.println("前序遍歷 1 次");
        if(this.no == no) {
            return this;
        }
        // 1.判斷當前節點的左子節點是否為空, 如果不為空則遞迴前序搜尋
        // 2.如果左遞迴前序搜尋, 找到節點就返回
        HeroNode resNode = null;
        if(this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }

        if(resNode != null) { // 說明左子樹找到
            return resNode;
        }
        // 1.左遞迴前序搜尋, 找到節點就返回, 否則繼續判斷
        // 2.當前節點的右子節點是否為空, 如果不空, 則繼續向右遞迴前序搜尋
        if(this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    // 中序遍歷搜尋
    public HeroNode infixOrderSearch(int no) {
        // 判斷當前節點的左子節點是否為空, 如果不為空則遞迴中序搜尋
        HeroNode resNode = null;
        if(this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null) { 
            return resNode;
        }

        System.out.println("中序遍歷 1 次"); // 計算遍歷了幾次才找到
        // 如果找到則返回, 沒找到就和當前節點比較, 如果是則返回當前節點
        if(this.no == no) {
            return this;
        }

        // 否則繼續右遞迴中序搜尋
        if(this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 後序遍歷搜尋
    public HeroNode postOrderSearch(int no) {
        // 判斷當前節點的左子節點是否為空, 如果不為空則遞迴後序搜尋
        HeroNode resNode = null;
        if(this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null) { 
            return resNode;
        }
        
        // 否則繼續右遞迴後序搜尋
        if(this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if(resNode != null) { 
            return resNode;
        }

        System.out.println("後序遍歷 1 次"); // 計算遍歷了幾次才找到

        if(this.no == no) {
            return this;
        }

        return resNode;
    }
}