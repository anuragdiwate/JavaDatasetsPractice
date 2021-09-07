public class BST {
    class TreeNode {
        int val;
        TreeNode right, left, parent;

        TreeNode(int value) {
            this.val = value;
        }

        int getVal() {
            return val;
        }

        TreeNode getRightChild() {
            return right;
        }

        TreeNode getLeftChild() {
            return left;
        }

        TreeNode getParent() { return parent; }

        void setVal(int value) {
            val = value;
        }

        void setRightChild(TreeNode newRight) {
            right = newRight;
        }

        void setLeftChild(TreeNode newLeft) {
            left = newLeft;
        }

        void setParent(TreeNode newParent){  parent = newParent;}
    }

    TreeNode root;

    public BST() {
        root = null;
    }

    TreeNode search(int value) {
        return searchHelp(value, root);
    }

    TreeNode searchHelp(int value, TreeNode root) {
        if (root == null || root.val == value) {
            return root;
        }
        if (root.getVal() < value) {
            return searchHelp(value, root.getRightChild());
        }
        return searchHelp(value, root.getLeftChild());
    }

    void remove(int value){
        root = recRemove(root, value);
    }

    TreeNode recRemove(TreeNode root, int val){
        if(root == null){
            return root;        //Tree is empty
        }

        if(val < root.val){
            root.left = recRemove(root.left, val);          //Recursively find the node to be deleted
        }else if(val > root.val){
            root.right = recRemove(root.right, val);
        }else{                  //Node found!
            if(root.left == null){
                return root.right;              //One or no child
            }else if(root.right == null){
                return root.left;
            }
            //2 children
            root.val = inOrderSuccessor(root.right);
            root.right = recRemove(root.right, root.val);
        }
        return root;
    }

    int inOrderSuccessor(TreeNode root){
        int min = root.val;
        while(root.left != null){
            min = root.left.val;
            root = root.left;
        }
        return min;
    }

    void insert(int value) {
        root = insertRec(value, root);
    }

    TreeNode insertRec(int value, TreeNode root) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value < root.getVal()) {
            root.setLeftChild(insertRec(value, root.getLeftChild()));
        } else if (value > root.getVal()) {
            root.setRightChild(insertRec(value, root.getRightChild()));
        }

        return root;
    }

    void print() {
        inOrdPrint(root);
    }

    void inOrdPrint(TreeNode cur) {
        if (cur != null) {
            inOrdPrint(cur.getLeftChild());
            System.out.print(cur.getVal() + " ");
            inOrdPrint(cur.getRightChild());
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(50);
        tree.insert(10);
        tree.insert(20);
        tree.insert(45);
        tree.insert(80);
        tree.insert(90);

        tree.print();
        System.out.println();

        TreeNode temp = tree.search(45);
        System.out.println(temp.val);

        tree.remove(45);
        tree.print();
        System.out.println();

        tree.remove(50);
        tree.print();
    }
}