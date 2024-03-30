package ru.academits.danilov_e.tree;
class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;
    private String turn; // Отладочное поле
    private int level; // Отладочное поле

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T data){
        this.left = left;
        this.right = right;
        this.data = data;

        turn = "Центр"; // Отладочное поле
        level = 0; // Отладочное поле
    }

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T data, String turn, int level){ // Отладочный конструктор
        this.left = left;
        this.right = right;
        this.data = data;

        this.turn = turn;
        this.level = level;
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

    public String getTurn(){ // Отладочный метод
        return turn;
    }

    public void setTurn(String turn){ // Отладочный метод
        this.turn = turn;
    }

    public int getLevel(){ // Отладочный метод
        return level;
    }

    public void setLevel(int level){ // Отладочный метод
        this.level = level;
    }


}
