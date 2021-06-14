package BinaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, "Tom");
        TreeNode node2 = new TreeNode(3, "Jack");
        TreeNode node3 = new TreeNode(6, "Smith");
        TreeNode node4 = new TreeNode(8, "Mary");
        TreeNode node5 = new TreeNode(10, "King");
        TreeNode node6 = new TreeNode(14, "Candy");

        // 簡單處理使用手動創建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 測試中序線索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        System.out.println("===前序===");
        threadedBinaryTree.preThreadedNodes();
        threadedBinaryTree.preThreadedList(); //前:1, 3, 8, 10, 6, 14
        
//        // 原本 10 的左指針為空 , 線索化後左指針指向前驅節點 3
//        TreeNode leftNode = node5.getLeft();
//        System.out.println("10號節點的前驅節點為: "+leftNode);
//        // 原本 10 的右指針為空 , 線索化後右指針指向後繼節點 1
//        TreeNode rightNode = node5.getRight();
//        System.out.println("10號節點的後繼節點為: "+rightNode);
        
//        System.out.println("===中序===");
//        threadedBinaryTree.infixThreadedNodes();
//        threadedBinaryTree.infixThreadedList(); //中:8, 3, 10, 1, 14, 6
        

    }
}

class ThreadedBinaryTree {
    private TreeNode root;
    private TreeNode prev = null;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    // 重載線索化方法
    public void preThreadedNodes() {
        this.preThreadedNodes(root);
    }
    
    public void infixThreadedNodes() {
        this.infixThreadedNodes(root);
    }
    
    // 中序遍歷線索化二元樹
    public void infixThreadedList() {
        // 定義一個變數, 臨時存儲當前遍歷的節點, 從root開始
        TreeNode node = root;
        while(node != null) {
            // 循環的找到leftType = 0 的節點, 說明該節點是按照線索化處理後的有效節點
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 輸出當前節點
            System.out.println(node);
            // 如果當前節點的右指針指向的是後繼節點, 就一直輸出
            while(node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            // 替換這個遍歷的節點
            node = node.getRight();
        }
    }
    
    // 前序遍歷線索化二元樹
    public void preThreadedList() {
        TreeNode node = root;
        while(node != null) {
        	
        	System.out.println(node);
        	
            while(node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            
            while(node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            
            node = node.getRight();
        }
    }

    /**
     * 
     * @param node 當前需要線索化的節點
     */
    //對二元樹進行中序線索化的方法
    public void infixThreadedNodes(TreeNode node) {
        if(node == null) {
            return;
        }

        // 1. 先線索化左子樹
        infixThreadedNodes(node.getLeft());
        // 2. 線索化當前節點
        // 處理當前節點的前驅節點
        if(node.getLeft() == null) {
            // 讓當前節點的左指針指向前驅節點
            node.setLeft(prev);
            // 修改當前節點的左指針類型
            node.setLeftType(1);
        }
        // 處理後繼節點
        if(prev != null && prev.getRight() == null) {
            // 讓前驅節點的右指針指向當前節點
            prev.setRight(node);
            // 修改當前節點的右指針類型
            prev.setRightType(1);
        }
        // 每處理一個節點後, 讓當前節點是下一個節點的前驅節點
        prev = node;
        // 3. 再線索化右子樹
        infixThreadedNodes(node.getRight());
    }

    //對二元樹進行前序線索化的方法
    public void preThreadedNodes(TreeNode node) {
    	if(node == null) {
            return;
        }
    	
    	if(node.getLeft() == null) {
            // 讓當前節點的左指針指向前驅節點
            node.setLeft(prev);
            // 修改當前節點的左指針類型
            node.setLeftType(1);
        }
    	
    	if(prev != null && prev.getRight() == null) {
            // 讓前驅節點的右指針指向當前節點
            prev.setRight(node);
            // 修改當前節點的右指針類型
            prev.setRightType(1);
        }
    	
    	prev = node;
    	
    	if(node.getLeftType() == 0) {
    		preThreadedNodes(node.getLeft());
    	}
    	
    	if(node.getRightType() == 0) {
    		preThreadedNodes(node.getRight());
    	}
    	
    }

//     // 刪除節點
//     public void delNode(int no) {
//         if(root != null) {
//             // 如果只有一個root節點, 需要判斷root是不是就是要刪除的節點
//             if(root.getNo() == no) {
//                 root = null;
//             } else {
//                 // 遞迴刪除
//                 root.delNode(no);
//             }
//         } else {
//             System.out.println("空樹");
//         }
//     }

//     // 前序遍歷
//     public void preOrder() {
//         if(this.root != null) {
//             this.root.preOrder();
//         } else {
//             System.out.println("二元樹為空");
//         }
//     }
//     // 中序遍歷
//     public void inOrder() {
//         if(this.root != null) {
//             this.root.inOrder();
//         } else {
//             System.out.println("二元樹為空");
//         }
//     }
//     // 後序遍歷
//     public void postOrder() {
//         if(this.root != null) {
//             this.root.postOrder();
//         } else {
//             System.out.println("二元樹為空");
//         }
//     }

//     // 前序搜尋
//     public TreeNode preOrderSearch(int no) {
//         if(root != null) {
//             return root.preOrderSearch(no);
//         } else {
//             return null;
//         }
//     }

//     // 中序搜尋
//     public TreeNode infixOrderSearch(int no) {
//         if(root != null) {
//             return root.infixOrderSearch(no);
//         } else {
//             return null;
//         }
//     }

//     // 後序搜尋
//     public TreeNode postOrderSearch(int no) {
//         if(root != null) {
//             return root.postOrderSearch(no);
//         } else {
//             return null;
//         }
//     }
}

class TreeNode {
    private int no;
    private String name;
    private TreeNode left;
    private TreeNode right;
    // 1. 如果 leftType = 0 表示指向左子樹 , leftType = 1 表示指向前驅節點
    // 2. 如果 rightType = 0 表示指向右子樹 , rightType = 1 表示指向後繼節點
    private int leftType;
    private int rightType;

    public TreeNode(int no,String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode [no = " + no + ", name = " + name + " ]";
    }

//     // 遞迴刪除節點
//     public void delNode(int no) {
//         // 如果當前節點的左子節點不為空，並且左子節點就是要刪除節點，就將 this.left = null; 並且就返回(結束遞迴刪除)
//         if(this.left != null && this.left.no == no) {
//             this.left = null;
//             return;
//         }
//         // 如果當前節點的右子節點不為空，並且右子節點就是要刪除節點，就將this.right = null; 並且就返回(結束遞迴刪除)
//         if(this.right != null && this.right.no == no) {
//             this.right = null;
//             return;
//         }
//         // 如果前面兩步都沒有刪除節點，就需要向左子樹進行遞迴刪除
//         if(this.left != null) {
//             this.left.delNode(no);
//         }
//         // 如果上一步也沒有刪除節點，則應向右子樹進行遞迴刪除
//         if(this.right != null) {
//             this.right.delNode(no);
//         }
//     }

//     public void preOrder() {
//         System.out.println(this);
//         if(this.left != null) {
//             this.left.preOrder();
//         }
//         if(this.right != null) {
//             this.right.preOrder();
//         }
//     }

//     public void inOrder() {
//         if(this.left != null) {
//             this.left.inOrder();
//         }
//         System.out.println(this);
//         if(this.right != null) {
//             this.right.inOrder();
//         }
//     }

//     public void postOrder() {
//         if(this.left != null) {
//             this.left.postOrder();
//         }
//         if(this.right != null) {
//             this.right.postOrder();
//         }
//         System.out.println(this);
//     }

//     // 前序遍歷搜尋
//     public TreeNode preOrderSearch(int no) {
        
//         System.out.println("前序遍歷 1 次");
//         if(this.no == no) {
//             return this;
//         }
//         // 1.判斷當前節點的左子節點是否為空, 如果不為空則遞迴前序搜尋
//         // 2.如果左遞迴前序搜尋, 找到節點就返回
//         TreeNode resNode = null;
//         if(this.left != null) {
//             resNode = this.left.preOrderSearch(no);
//         }

//         if(resNode != null) { // 說明左子樹找到
//             return resNode;
//         }
//         // 1.左遞迴前序搜尋, 找到節點就返回, 否則繼續判斷
//         // 2.當前節點的右子節點是否為空, 如果不空, 則繼續向右遞迴前序搜尋
//         if(this.right != null) {
//             resNode = this.right.preOrderSearch(no);
//         }
//         return resNode;
//     }

//     // 中序遍歷搜尋
//     public TreeNode infixOrderSearch(int no) {
//         // 判斷當前節點的左子節點是否為空, 如果不為空則遞迴中序搜尋
//         TreeNode resNode = null;
//         if(this.left != null) {
//             resNode = this.left.infixOrderSearch(no);
//         }
//         if(resNode != null) { 
//             return resNode;
//         }

//         System.out.println("中序遍歷 1 次"); // 計算遍歷了幾次才找到
//         // 如果找到則返回, 沒找到就和當前節點比較, 如果是則返回當前節點
//         if(this.no == no) {
//             return this;
//         }

//         // 否則繼續右遞迴中序搜尋
//         if(this.right != null) {
//             resNode = this.right.infixOrderSearch(no);
//         }
//         return resNode;
//     }

//     // 後序遍歷搜尋
//     public TreeNode postOrderSearch(int no) {
//         // 判斷當前節點的左子節點是否為空, 如果不為空則遞迴後序搜尋
//         TreeNode resNode = null;
//         if(this.left != null) {
//             resNode = this.left.postOrderSearch(no);
//         }
//         if(resNode != null) { 
//             return resNode;
//         }
        
//         // 否則繼續右遞迴後序搜尋
//         if(this.right != null) {
//             resNode = this.right.postOrderSearch(no);
//         }

//         if(resNode != null) { 
//             return resNode;
//         }

//         System.out.println("後序遍歷 1 次"); // 計算遍歷了幾次才找到

//         if(this.no == no) {
//             return this;
//         }

//         return resNode;
//     }
}
