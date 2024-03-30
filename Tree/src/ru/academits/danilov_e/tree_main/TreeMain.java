package ru.academits.danilov_e.tree_main;

import ru.academits.danilov_e.tree.BinarySearchTree;

public class TreeMain {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(14);

        tree.add(4);
        tree.add(22);
        tree.add(3);


        tree.widthTreeShow();
    }
}