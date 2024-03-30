package ru.academits.danilov_e.tree;
class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T data){
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public TreeNode<T> getLeftChild(){
        return left;
    }

    public void setLeftChild(TreeNode<T> left){
        this.left = left;
    }

    public TreeNode<T> getRightChild(){
        return right;
    }

    public void setRightChild(TreeNode<T> right){
        this.right = right;
    }

    public T data(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    @Override
    public String toString(){
        return data + "";
    }
}
