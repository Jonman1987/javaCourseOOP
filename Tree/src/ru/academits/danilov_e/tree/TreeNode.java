package ru.academits.danilov_e.tree;

class TreeNode<E> implements Comparable<E> {
    private TreeNode<E> left;
    private TreeNode<E> right;
    private E data;

    public TreeNode(E data) {
        this.data = data;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public int compareTo(E data){
        if(this.data.equals(data)){
            return 0;
        }

        if(this.data.hashCode() < data.hashCode()){ // Все равно я не понимаю в этом моменте через что сравнивать E data? Напрямую
            // сравнить this.data и data я не могу. И я так понимаю, что нужно писать свой hashcode и equal для TreeNode?
            // в этот класс я должен буду передавать компаратор?
            return -1;
        }

        return 1;
    }
}