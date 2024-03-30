package ru.academits.danilov_e.tree;

import java.util.LinkedList;
import java.util.Stack;


public class BinarySearchTree<T> {
    private TreeNode<T> root;
    private int size;

    public BinarySearchTree(T data) {
        root = new TreeNode<>(null, null, data);
        size = 1;
    }

    public T getRoot() {
        return root.data();
    }

    public void setRoot(T root) {
        this.root = new TreeNode<>(null, null, root);
    }

    public void add(T data) {
        TreeNode<T> currentNode = root;

        int level = 1; // Данные отладки
        StringBuilder path = new StringBuilder(); // Данные отладки

        while (true) {
            if (Math.abs(data.hashCode()) < Math.abs(currentNode.data().hashCode())) {
                path.append("Лево "); // Данные отладки

                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                    level++; // Данные отладки
                } else {
                    path.deleteCharAt(path.length() - 1); // Данные отладки
                    currentNode.setLeftChild(new TreeNode<>(null, null, data, path.toString(), level));
                    break;
                }
            } else if (Math.abs(data.hashCode()) > Math.abs(currentNode.data().hashCode())) {
                path.append("Право "); // Данные отладки

                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                    level++; // Данные отладки
                } else {
                    path.deleteCharAt(path.length() - 1); // Данные отладки
                    currentNode.setRightChild(new TreeNode<>(null, null, data, path.toString(), level));
                    break;
                }
            }
        }

        size++;
    }

    public void widthTreeShow() {
        LinkedList<TreeNode<T>> treeList = new LinkedList<>();

        treeList.add(root);

        while (!treeList.isEmpty()) {
            System.out.println("Значение узла: " + treeList.getFirst() + ". Проход по дереву при добавлении узла: "
                    + treeList.getFirst().getTurn() + ". Уровень расположения узла: " + treeList.getFirst().getLevel() + ".");

            if (treeList.getFirst().getLeftChild() != null) {
                treeList.addLast(treeList.getFirst().getLeftChild());
            }

            if (treeList.getFirst().getRightChild() != null) {
                treeList.addLast(treeList.getFirst().getRightChild());
            }

            treeList.removeFirst();
        }
    }

    public void widthTreeShowWithRecursion() {

    }

    public void deepTreeShow() {
        Stack<TreeNode<T>> treeStack = new Stack<>();
        TreeNode<T> elem;

        treeStack.push(root);

        while (!treeStack.isEmpty()) {
            elem = treeStack.pop();
            System.out.println(elem);

            if (elem.getRightChild() != null) {
                treeStack.push(elem.getRightChild());
            }

            if (elem.getLeftChild() != null) {
                treeStack.push(elem.getLeftChild());
            }
        }
    }

    public void deepTreeShowWithRecursion() {

    }

    public void remove(T data) {
        TreeNode<T> current = getNode(data);

        if (current != null) {
            TreeNode<T> parent = getParent(data, root);

            if (parent == null) {

            } else if(current.getLeftChild() == null && current.getRightChild() == null){
                if(Math.abs(parent.data().hashCode()) > Math.abs(data.hashCode())){
                    parent.setLeftChild(null);
                }else if(Math.abs(parent.data().hashCode()) < Math.abs(data.hashCode())){
                    parent.setRightChild(null);
                }
            }else if(current.getLeftChild() == null || current.getRightChild() == null){

            }else {

            }
        } else {
            System.out.println("Данное значение отсутствует в дереве");
        }

    }

    private TreeNode<T> getParent(T data, TreeNode<T> current) {
        if (Math.abs(data.hashCode()) == Math.abs(current.data().hashCode())) {
            return null;
        }

        if (Math.abs(current.getLeftChild().data().hashCode()) == Math.abs(data.hashCode())
                || Math.abs(current.getRightChild().data().hashCode()) == Math.abs(data.hashCode())) {
            return current;
        }

        if (Math.abs(current.data().hashCode()) > Math.abs(data.hashCode())) {
            return getParent(data, current.getLeftChild());
        }

        return getParent(data, current.getRightChild());
    }

    private TreeNode<T> getNode(T data) {
        TreeNode<T> currentNode = root;

        while (true) {
            if (Math.abs(data.hashCode()) == Math.abs(currentNode.data().hashCode())) {
                return currentNode;
            }

            if (Math.abs(data.hashCode()) < Math.abs(currentNode.data().hashCode())) {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    break;
                }
            } else {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    break;
                }
            }
        }

        return null;
    }

    public boolean binarySearch(T data) {
        return getNode(data) != null;
    }

    public int size() {
        return size;
    }
}
