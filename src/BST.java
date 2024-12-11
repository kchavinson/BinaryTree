import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Your Name Here
 * @version: Date
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        //return the search helper function with the root and value
        return searchHelp(val, root);
    }

    public boolean searchHelp(int val, BSTNode node)
    {
        //check if there is a node
        if (node == null)
        {
            return false;
        }
        //then check if is equal to val, if so, you found it!
        if (node.getVal() == val)
        {
            return true;
        }
        //now if the val is less than the node, recall search help on the left node
        if (val < node.getVal())
        {
            return searchHelp(val, node.getLeft());
        }
        //otherwise, call search help on the right node
        return searchHelp(val, node.getRight());
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        //create arraylist and call helper
        ArrayList<BSTNode> tree = new ArrayList<BSTNode>();
        helpInOrder(root, tree);
        return tree;
    }
    public void helpInOrder(BSTNode node, ArrayList<BSTNode> tree)
    {
        //if you have reached a null child, return back to the parent
        if (node == null)
        {
            return;
        }
        //if there is a valid node add its left child, then itself (the root), then its right child
        helpInOrder(node.getLeft(), tree);
        tree.add(node);
        helpInOrder(node.getRight(), tree);
    }


    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {
        //create arraylist and call helper
        ArrayList<BSTNode> tree = new ArrayList<BSTNode>();
        helpPreOrder(root, tree);
        return tree;
    }
    public void helpPreOrder(BSTNode node, ArrayList<BSTNode> tree)
    {
        //if you have reached a null child return
        if (node == null)
        {
            return;
        }
        //if there is a valid node add itself (the root), then its left child then right child
        tree.add(node);
        helpPreOrder(node.getLeft(), tree);
        helpPreOrder(node.getRight(), tree);



    }
    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        //create arraylist and call helper
        ArrayList<BSTNode> tree = new ArrayList<BSTNode>();
        helpPostOrder(root, tree);
        return tree;
    }
    public void helpPostOrder(BSTNode node, ArrayList<BSTNode> tree)
    {
        //if you have reached a null child return
        if (node == null) {
            return;
        }
        //if there is a valid node add its left child, then its right child then itself (the root)
        helpPostOrder(node.getLeft(), tree);
        helpPostOrder(node.getRight(), tree);
        tree.add(node);
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        //if the value is not all ready in the tree, then add it
        if(search(val))
        {
            return;
        }
        insertHelper(val, root);

    }
    public void insertHelper(int val, BSTNode node)
    {
        //if the val is less than the value of the node and the left child is null, add it
        if(val < node.getVal())
        {
            if(node.getLeft() == null)
            {
                node.setLeft(new BSTNode(val));
                return;
            }
            //if not null, recall helper function on left child
            insertHelper(val, node.getLeft());
            return;
        }
        //now the val is greater node, and right child does not exist add it
        if(node.getRight() == null)
        {
            node.setRight(new BSTNode(val));
            return;
        }
        //if not null, recall helper function on right child
        insertHelper(val, node.getRight());
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        //if there is no tree the tree is not valid
        if (root == null)
        {
            return false;
        }
        //call helper function to determine if BST is valid
        return isValidBSTHelper(root, Integer.MAX_VALUE, Integer.MIN_VALUE );
    }
    public boolean isValidBSTHelper(BSTNode node, int max, int min)
    {
        //base case: if you have reached the end of a branch and never been declared false, that branch is valid
        if (node == null)
        {
            return true;
        }
        //if the node is less than the max and greater than the min
        //then recall this function on its left and right nodes
        if (node.getVal() < max && node.getVal() > min)
        {
            //declaring the nodes value as the max for left node, and min value for right node to ensure BST logic
            return isValidBSTHelper(node.getLeft(), node.getVal(), min) &&
                    isValidBSTHelper(node.getRight(), max, node.getVal());
        }
        //if the node is less than min or greater than max then it is not a valid BST
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);


        System.out.println("\n" + tree.isValidBST());

    }
}
