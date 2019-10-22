/**
 * Implementation of a Binary Tree Node
 * @param <K> - type for node key
 * @param <T> - type for node info
 */

public class BinaryTreeNode<K, T> {

    //Node's key, info pair
    protected K key;
    protected T info;

    //References to node's left and right child.
    protected BinaryTreeNode<K,T> left;
    protected BinaryTreeNode<K,T> right;

    /**
     * Node constructor
     * @param key - key of new node
     * @param info - info of new node
     */
    public BinaryTreeNode(K key, T info) {
        this.key = key;
        this.info = info;
        left = right = null;
    }

    /**
     *
     * @return key of node
     */
    public K getKey() {
        return key;
    }

    /**
     *
     * @return Info of a node.
     */
    public T getInfo() {
        return info;
    }

    /**
     * @return reference to root of the left subtree
     */
    public BinaryTreeNode<K, T> getLeft() {
        return left;
    }

    /**
     * @return reference to root of the right subtree
     */
    public BinaryTreeNode<K,T> getRight() {
        return right;
    }

}
