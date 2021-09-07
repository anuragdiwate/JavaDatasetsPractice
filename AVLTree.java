public class AVLTree {
    class AvlNode {
        int val, height;
        AvlNode right, left, parent;

        AvlNode(int value) {
            val = value;
            height = 1;
        }

        int getVal() {
            return val;
        }

        AvlNode getRightChild() {
            return right;
        }

        AvlNode getLeftChild() {
            return left;
        }

        AvlNode getParent() {
            return parent;
        }

        void setVal(int value) {
            val = value;
        }

        void setRightChild(AvlNode newRight) {
            right = newRight;
        }

        void setLeftChild(AvlNode newLeft) {
            left = newLeft;
        }

        void setParent(AvlNode newParent) {
            parent = newParent;
        }
    }

    AvlNode root;

    public AVLTree() {
        root = null;
    }

    AvlNode search(int value) {
        return searchHelp(value, root);
    }

    AvlNode searchHelp(int value, AvlNode root) {
        if (root == null || root.val == value) {
            return root;
        }
        if (root.getVal() < value) {
            return searchHelp(value, root.getRightChild());
        }
        return searchHelp(value, root.getLeftChild());
    }

    AvlNode inOrderSuccessor(AvlNode root) {
        AvlNode node = root;
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    int getHeight(AvlNode node) {
        if (node == null)
            return 0;

        return node.height;
    }

    AvlNode rightRotate(AvlNode y) {
        AvlNode x = y.left;
        AvlNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        // Return new root
        return x;
    }

    AvlNode leftRotate(AvlNode x) {
        AvlNode y = x.right;
        AvlNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(AvlNode N) {
        if (N == null)
            return 0;

        return getHeight(N.left) - getHeight(N.right);
    }

    AvlNode insert(AvlNode node, int key) {

        // BST insertion
        if (node == null)
            return (new AvlNode(key));

        if (key < node.val)
            node.left = insert(node.left, key);
        else if (key > node.val)
            node.right = insert(node.right, key);
        else // Duplicate keys not allowed
            return node;

        // Updating the height
        node.height = 1 + Math.max(getHeight(node.left),
                getHeight(node.right));

        // Check balance factor
        int balance = getBalance(node);

        // Case 1 LL rotation
        if (balance > 1 && key < node.left.val)
            return rightRotate(node);

        // Case 2 RR rotation
        if (balance < -1 && key > node.right.val)
            return leftRotate(node);

        // Case 3 LR rotation
        if (balance > 1 && key > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Case 4 RL rotation
        if (balance < -1 && key < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    AvlNode deleteNode(AvlNode root, int key)
    {
        // BST removal
        if (root == null)
            return root;

        if (key < root.val)
            root.left = deleteNode(root.left, key);

        else if (key > root.val)
            root.right = deleteNode(root.right, key);

        else
        {   // node with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                AvlNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {   // two children
                AvlNode temp = inOrderSuccessor(root.right);

                // Copy the inorder successor's data to this node
                root.val = temp.val;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.val);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // Updating the height
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

        // Check balance factor
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    void preOrder(AvlNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 0);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, -1);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 2);

        System.out.println("Preorder traversal of the constructed tree is: ");
        tree.preOrder(tree.root);
        System.out.println();

//        AvlNode temp = tree.search(45);
//        System.out.println(temp.val);

        tree.root = tree.deleteNode(tree.root, 10);
        tree.preOrder(tree.root);
        System.out.println();
//
        tree.root = tree.deleteNode(tree.root, 1);
        tree.preOrder(tree.root);
        System.out.println();
    }
}
