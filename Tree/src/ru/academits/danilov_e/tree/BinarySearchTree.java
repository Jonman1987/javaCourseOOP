package ru.academits.danilov_e.tree;

import java.util.LinkedList;


public class BinarySearchTree<T> {
    private TreeNode<T> root;
    private int size;

    public BinarySearchTree(T data){
        root = new TreeNode<>(null, null, data);
        size = 1;
    }

    public void add(T data){
        TreeNode<T> currentNode = root;

        if(Math.abs(data.hashCode()) < Math.abs(currentNode.data().hashCode())){
            while (currentNode.getLeftChild() != null && Math.abs(data.hashCode()) < Math.abs(currentNode.data().hashCode())){
                currentNode = currentNode.getLeftChild();
            }

            currentNode.setLeftChild(new TreeNode<>(null, null, data));
        }else if(Math.abs(data.hashCode()) > Math.abs(currentNode.data().hashCode())){
            while (currentNode.getRightChild() != null && Math.abs(data.hashCode()) > Math.abs(currentNode.data().hashCode())){
                currentNode = currentNode.getRightChild();
            }

            currentNode.setRightChild(new TreeNode<>(null, null, data));
        }
    }

    public void widthTreeShow(){
        LinkedList<TreeNode<T>> treeList = new LinkedList<>();

        treeList.add(root);

        while (!treeList.isEmpty()){
            System.out.println(treeList.getFirst());

            if(treeList.getFirst().getLeftChild() != null){
                treeList.add(treeList.getFirst().getLeftChild());
            }

            if(treeList.getFirst().getRightChild() != null){
                treeList.add(treeList.getFirst().getRightChild());
            }

            treeList.removeFirst();
        }
    }

    public void deeperTreeShow(){

    }

    public void remove(T data){

    }

    public boolean binarySearch(T data){
        return false;
    }

    public int size(){
        return size;
    }
}
