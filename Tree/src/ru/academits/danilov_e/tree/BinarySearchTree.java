package ru.academits.danilov_e.tree;

import java.util.LinkedList;
import java.util.Stack;


public class BinarySearchTree<T> {
    private TreeNode<T> root;
    private int size;

    public BinarySearchTree(T data){
        root = new TreeNode<>(null, null, data);
        size = 1;
    }

    public void add(T data){
        TreeNode<T> currentNode = root;

        int level = 1; // Данные отладки
        StringBuilder path = new StringBuilder(); // Данные отладки

        while (true){
            if(Math.abs(data.hashCode()) < Math.abs(currentNode.data().hashCode())){
                path.append("Лево"); // Данные отладки

                if(currentNode.getLeftChild() != null){
                    currentNode = currentNode.getLeftChild();
                    level++; // Данные отладки
                }else{
                    currentNode.setLeftChild(new TreeNode<>(null, null, data, path.toString(), level));
                    break;
                }
            }else if(Math.abs(data.hashCode()) > Math.abs(currentNode.data().hashCode())){
                path.append("Право"); // Данные отладки

                if(currentNode.getRightChild() != null){
                    currentNode = currentNode.getRightChild();
                    level++; // Данные отладки
                }else{
                    currentNode.setRightChild(new TreeNode<>(null, null, data, path.toString(), level));
                    break;
                }
            }
        }

        size++;
    }

    public void widthTreeShow(){
        LinkedList<TreeNode<T>> treeList = new LinkedList<>();

        treeList.add(root);

        while (!treeList.isEmpty()){
            System.out.println(treeList.getFirst() + " " + treeList.getFirst().getTurn() + " " + treeList.getFirst().getLevel());

            if(treeList.getFirst().getLeftChild() != null){
                treeList.addLast(treeList.getFirst().getLeftChild());
            }

            if(treeList.getFirst().getRightChild() != null){
                treeList.addLast(treeList.getFirst().getRightChild());
            }

            treeList.removeFirst();
        }
    }

    public void widthTreeShowWithRecursion(){

    }

    public void deepTreeShow(){
        Stack<TreeNode<T>> treeStack = new Stack<>();
        TreeNode<T> elem;

        treeStack.push(root);

        while (!treeStack.isEmpty()){
            elem = treeStack.pop();
            System.out.println(elem);

            if(elem.getRightChild() != null){
                treeStack.push(elem.getRightChild());
            }

            if(elem.getLeftChild() != null){
                treeStack.push(elem.getLeftChild());
            }
        }
    }

    public void deepTreeShowWithRecursion(){

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
