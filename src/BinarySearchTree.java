import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implementation of a binary search tree
 * @param <K> - data type for the key of a tree node
 * @param <T> - data type for the info stored in a tree node
 */
public class BinarySearchTree<K extends Comparable<? super K>, T> {

    //Define root of tree
    private BinaryTreeNode<K,T> root;

    /**
     * Default constructor
     */
    public BinarySearchTree() {
        root = null;
    }


    /**
     * Calls a recursive method to insert a new node into the tree given:
     * @param key - key to be inserted
     * @param info - info associated with key to be inserted
     */
    public void insert(K key, T info) {
        root = insert(root, key, info);
    }

    /**
     * Private recursive method
     * @param node - current node
     * @param key - key to be inserted
     * @param info - info associated with key to be inserted
     * @return root of subtree
     */
    private BinaryTreeNode<K, T> insert(BinaryTreeNode<K,T> node, K key, T info) {

        if (node == null) //base case
            node = new BinaryTreeNode<>(key, info);
        else {  //Progress Case
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
               node.left = insert(node.left, key, info);
            } else if (cmp > 0){
                node.right = insert(node.right, key, info);
            }
        }
        return node;
    }

    /**
     * Alternative implementation of insert to demonstrate a non-recursive tree method.
     * @param key - key to be inserted
     * @param info - info associated with key to be inserted
     */
    public void insert2(K key, T info) {

        if (root == null) {
            root = new BinaryTreeNode<>(key, info);
            return;
        }

        BinaryTreeNode<K,T> cur = root;
        BinaryTreeNode<K,T> prev = root;

        while (cur != null) {

            prev = cur;
            if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }

        if (key.compareTo(prev.key) < 0) {
            prev.left = new BinaryTreeNode<>(key, info);
        }
        else {
            prev.right = new BinaryTreeNode<>(key,info);
        }
    }


    /**
     * Calls a recursive method for preorder traversal of tree that prints each element
     */
    public void printPreOrder() {
        preOrderTraverse(root);
    }

    /**
     * Recursive method for preorder traversal of the tree.
     * @param cur - current node
     */
    private void preOrderTraverse(BinaryTreeNode<K,T> cur) {
        if (cur==null) return; //base case

        //Progress case
        System.out.println(cur.key + " => " + cur.info);
        preOrderTraverse(cur.left);
        preOrderTraverse(cur.right);
    }

    /**
     * Calls a recursive method for inorder traversal of tree that prints each element
     */
    public void printInOrder() {
        inOrderTraverse(root);
    }

    /**
     * Recursive method for inorder traversal of the tree.
     * @param cur - current node
     */
    private void inOrderTraverse(BinaryTreeNode<K,T> cur) {
        if (cur==null) return;
        inOrderTraverse(cur.left);
        System.out.println(cur.key + " => " + cur.info);
        inOrderTraverse(cur.right);
    }

    /**
     * Calls a recursive method for post traversal of tree that prints each element
     */
    public void printPostOrder() {
        postOrderTraverse(root);
    }

    /**
     * Recursive method for postorder traversal of the tree.
     * @param cur - current node
     */
    private void postOrderTraverse(BinaryTreeNode<K,T> cur) {
        if (cur==null) return;
        postOrderTraverse(cur.left);
        postOrderTraverse(cur.right);
        System.out.println(cur.key + " => " + cur.info);
    }


    /**
     * Impelementation of breadth first search
     */
    public void printBreadthFirst() {

        Deque<BinaryTreeNode<K,T>> q = new ArrayDeque<BinaryTreeNode<K,T>>();
        q.add(root);

        while (!q.isEmpty()) {
            BinaryTreeNode<K,T> cur = q.remove();
            if (cur.left != null) q.add(cur.left);
            if (cur.right != null) q.add(cur.right);
            System.out.println(cur.key + " => " + cur.info);
        }
    }

    /**
     * Retrieves a tree node's info give its key.
      * @param key - key for desired node
     * @return info of matching node
     */
    public T search(K key) {
        return search(root, key);
    }

    /**
     * Recursive method to implement search.
     * @param cur - current node
     * @param key - key to locate
     * @return info of key'd node or null if not found
     */
    private T search(BinaryTreeNode<K,T> cur, K key) {
        if (cur == null) return null;
        if (key.equals(cur.key)) return cur.info;

        if (key.compareTo(cur.key) < 0) {
            return search(cur.left, key);
        }
        return search(cur.right, key);
    }

    /**
     * Deletes a tree node with matching key
     * @param key - key for node to delete.
     */
    public void delete(K key) {
        root = deleteByCopy(root, key);
    }

    /**
     *
     * @param cur - root of current subtree for deletion recursive task
     * @param key - key of the node to delete.
     * @return root of the subtree after deletion call is complete.
     */
    private  BinaryTreeNode<K,T> deleteByCopy(BinaryTreeNode<K,T> cur, K key) {

        if (cur == null) return null;

        if (key.compareTo(cur.key) < 0) {
            cur.left = deleteByCopy(cur.left, key);
            return cur;
        } else if (key.compareTo(cur.key) > 0) {
            cur.right = deleteByCopy(cur.right, key);
        } else {

            if (cur.left == null) return cur.right;
            else if (cur.right == null) return cur.left;
            else {
                BinaryTreeNode<K, T> successor = findMin(cur.right);
                cur.key = successor.key;
                cur.info = successor.info;
                cur.right = deleteByCopy(cur.right, cur.key);
                return cur;
            }
        }
        return null;
    }

    /**
     * @return tree node with minimum value
     */
    private BinaryTreeNode<K, T> findMin() {
        if (root == null) return null;
        return findMin(root);
    }


    /**
     * Recursive call to find the minimum tree node for a given subtree
     * @param cur - root of subtree
     * @return tree node with minimum value within subtree.
     */
    private BinaryTreeNode<K, T> findMin(BinaryTreeNode<K,T> cur) {
        if (cur.left == null)
            return cur;

        return findMin(cur.left);
    }


    /**
     * @return tree node with maximum value in the tree
     */
    private BinaryTreeNode<K, T> findMax() {
        if (root == null) return null;
        return findMax(root);
    }

    /**
     * Returns the maximum tree node for a subtree.
     * @param cur - root of subtree
     * @return maximum value of subtree
     */
    private BinaryTreeNode<K, T> findMax(BinaryTreeNode<K,T> cur) {
        if (cur.right == null)
            return cur;

        return findMax(cur.right);
    }


    /**
     *
     * @return height of the tree
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * Returns height of subtree
     * @param node - root of subtree
     * @return height of subtree
     */
    private int getHeight(BinaryTreeNode<K, T> node) {

        if (node == null) return -1;

        int heightLeft = getHeight(node.left);
        int heightRight = getHeight(node.right);

        if (heightLeft > heightRight)
            return heightLeft + 1;

        else
            return heightRight + 1;
    }

    /**
     *
     * @return true if tree is balanced, false otherwise
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * Determines if a subtree is balanced
     * @param node - root of subtree
     * @return true if balanced, false otherwise.
     */
    private boolean isBalanced(BinaryTreeNode<K, T> node) {
        if (node == null) return true;

        int heightLeft = getHeight(node.left);
        int heightRight = getHeight(node.right);

        if (Math.abs(heightLeft - heightRight) > 1)
            return false;
        else {
            return isBalanced(node.left) && isBalanced(node.right);
        }
    }


    public static void main(String[] args) {
        System.out.println("Hello World!");

        BinarySearchTree<Integer,Integer> tree = new BinarySearchTree<>();

        int [] arr = {6, 2, 8, 1, 4, 3};
        for (int i=0; i < arr.length; i++) {
            tree.insert(arr[i], arr[i]);
        }

        System.out.println("\nIn-Order");
        tree.printInOrder();

        System.out.println("\nPre-Order");
        tree.printPreOrder();

        System.out.println("\nPost-Order");
        tree.printPostOrder();


        System.out.println("\nBreadth-Fist:");
        tree.printBreadthFirst();

        System.out.print("\nHeight:" + tree.getHeight() + "\n\n");

        System.out.print("\nBalanced:" + tree.isBalanced() + "\n\n");

        tree.insert(0,0);
        System.out.println(tree.search(0));
        tree.delete(0);
        System.out.println(tree.search(0));
        tree.insert(0,0);
        System.out.println(tree.search(0));


        System.out.println(6);
        tree.delete(6);
        System.out.println(tree.search(6));

    }
}
