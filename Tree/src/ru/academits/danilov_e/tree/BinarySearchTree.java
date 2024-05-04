package ru.academits.danilov_e.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;

    public BinarySearchTree(E data) {
        root = new TreeNode<>(data);
        size = 1;
    }

    public void add(E data) {
        TreeNode<E> currentNode = root;

        while (true) {
            if (Math.abs(data.hashCode()) < Math.abs(currentNode.getData().hashCode())) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    break;
                }
            } else if (Math.abs(data.hashCode()) > Math.abs(currentNode.getData().hashCode())) {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    break;
                }
            }
        }

        size++;
    }

    public void widthVisit(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        Queue<TreeNode<E>> treeList = new LinkedList<>(); // Правильно ли я использовал интерфейс очереди?

        treeList.add(root);
        TreeNode<E> node;

        while (!treeList.isEmpty()) {
            node = treeList.remove();

            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                treeList.add(node.getLeft());
            }

            if (node.getRight() != null) {
                treeList.add(node.getRight());
            }
        }
    }

    public void depthVisit(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        LinkedList<TreeNode<E>> list = new LinkedList<>();

        list.addFirst(root);
        TreeNode<E> node;

        while (!list.isEmpty()) {
            node = list.removeFirst(); // Возвращает и удаляет
            consumer.accept(node.getData());

            if (node.getRight() != null) {
                list.addFirst(node.getRight());
            }

            if (node.getLeft() != null) {
                list.addFirst(node.getLeft());
            }
        }
    }

    public void recursivelyDepthVisit(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        depthVisit(root, consumer);
    }

    private void depthVisit(TreeNode<E> node, Consumer<E> consumer) {
        consumer.accept(node.getData());

        if(node.getLeft() != null){
            depthVisit(node.getLeft(), consumer);
        }

        if(node.getRight() != null){
            depthVisit(node.getRight(), consumer);
        }
    }

    private TreeNode<E> getMostLeftNode(TreeNode<E> deletedNode) {
        TreeNode<E> foundNode = deletedNode.getRight();

        while (foundNode.getLeft() != null) {
            foundNode = foundNode.getLeft();
        }

        return foundNode;
    }

    public boolean remove(E data) { //TODO: в if-else лучше короткую ветку ставить первой, это читается лучше
        //TODO: сейчас код сильно дублируется для удаления из корня и не из корня.
        //TODO: Лучше сделать без дублирования.
        //TODO: Лучше сначала найти узел на замену удаляемому узлу, а потом присвоить его в корень или не в корень
        TreeNode<E> current = getNode(data);

        if (current != null) {
            TreeNode<E> parent = getParent(data, root);

            if (parent == null) {
                if (root.getLeft() == null && root.getRight() == null) {
                    root = null;
                } else if (root.getLeft() == null) {
                    root = root.getRight();
                } else if (root.getRight() == null) {
                    root = root.getLeft();
                } else {
                    TreeNode<E> lastLeftChild = getMostLeftNode(root);

                    if (lastLeftChild.getRight() != null) {
                        root.getRight().setLeft(lastLeftChild.getRight());
                    } else {
                        root.getRight().setLeft(null);
                    }

                    lastLeftChild.setRight(root.getRight());
                    lastLeftChild.setLeft(root.getLeft());

                    root = lastLeftChild;
                }
            } else if (current.getLeft() == null && current.getRight() == null) {
                if (Math.abs(parent.getData().hashCode()) > Math.abs(data.hashCode())) {
                    parent.setLeft(null);
                } else if (Math.abs(parent.getData().hashCode()) < Math.abs(data.hashCode())) {
                    parent.setRight(null);
                }
            } else if (current.getLeft() == null || current.getRight() == null) {
                if (Math.abs(parent.getData().hashCode()) > Math.abs(data.hashCode())) {
                    if (current.getLeft() == null) {
                        parent.setLeft(current.getRight());
                    } else {
                        parent.setLeft(current.getLeft());
                    }
                } else if (Math.abs(parent.getData().hashCode()) < Math.abs(data.hashCode())) {
                    if (current.getLeft() == null) {
                        parent.setRight(current.getRight());
                    } else {
                        parent.setRight(current.getLeft());
                    }
                }
            } else {
                TreeNode<E> lastLeftChild = getMostLeftNode(current);
                TreeNode<E> leftNodeParent = getParent(getMostLeftNode(current).getData(), root);

                if (leftNodeParent != null) {
                    leftNodeParent.setLeft(lastLeftChild.getRight());
                }

                lastLeftChild.setRight(current.getRight());
                lastLeftChild.setLeft(current.getLeft());

                if (Math.abs(parent.getData().hashCode()) > Math.abs(data.hashCode())) {
                    parent.setLeft(lastLeftChild);
                } else if (Math.abs(parent.getData().hashCode()) < Math.abs(data.hashCode())) {
                    parent.setRight(lastLeftChild);
                }
            }

            size--;
            return true;
        }

        return false;
    }

    private TreeNode<E> getParent(E data, TreeNode<E> currentNode) {
        if (Math.abs(data.hashCode()) == Math.abs(currentNode.getData().hashCode())) {
            return null;
        }

        if (Math.abs(currentNode.getLeft().getData().hashCode()) == Math.abs(data.hashCode())
                || Math.abs(currentNode.getRight().getData().hashCode()) == Math.abs(data.hashCode())) {
            return currentNode;
        }

        if (Math.abs(currentNode.getData().hashCode()) > Math.abs(data.hashCode())) {
            return getParent(data, currentNode.getLeft());
        }

        return getParent(data, currentNode.getRight());
    }

    private TreeNode<E> getNode(E data) {
        TreeNode<E> currentNode = root;

        while (true) {
            if (Math.abs(data.hashCode()) == Math.abs(currentNode.getData().hashCode())) {
                return currentNode;
            }

            if (Math.abs(data.hashCode()) < Math.abs(currentNode.getData().hashCode())) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    break;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    break;
                }
            }
        }

        return null;
    }

    public boolean contains(E data) {
        return getNode(data) != null;
    }

    public int size() {
        return size;
    }
}
