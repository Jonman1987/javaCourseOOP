package ru.academits.danilov_e.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

// TODO: 8. Неэффективно отдельно искать узел, а потом заново искать его родителя. Лучше искать узел и его родителя одновременно.
//  Т.е. делать методы, которые сразу выполняют какую-то работу с помощью узла и его родителя.
//  Либо можно сначала искать родителя нужного узла, а потом, зная родителя, быстро находить его ребенка

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;
    private Comparable<E> comparator;

    public BinarySearchTree(E data) {
        root = new TreeNode<>(data);
        size = 1;
    }

    public <T> BinarySearchTree(E data, Comparable<T> comparator) {
        root = new TreeNode<>(data);
        size = 1;
        this.comparator = (Comparable<E>) comparator;
    }

    public <T> Comparable<T> getComparator(){
        return (Comparable<T>) comparator;
    }

    public <T> void setComparator(Comparable<T> comparator){
        this.comparator = (Comparable<E>) comparator;
    }

    public void add(E data) {
        TreeNode<E> currentNode = root;

        while (true) {
            if (Math.abs(data.hashCode()) < Math.abs(currentNode.getData().hashCode())) { // TODO: Тут я заменю hashCode на compare()
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    break;
                }
            } else if (Math.abs(data.hashCode()) > Math.abs(currentNode.getData().hashCode())) { // TODO: Тут я заменю hashCode на compare()
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

        while (!treeList.isEmpty()) {
            TreeNode<E> node = treeList.remove();

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

        while (!list.isEmpty()) {
            TreeNode<E> node = list.removeFirst(); // Возвращает и удаляет
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
                if (Math.abs(parent.getData().hashCode()) > Math.abs(data.hashCode())) { // TODO: Тут я заменю hashCode на compare()
                    parent.setLeft(null);
                } else if (Math.abs(parent.getData().hashCode()) < Math.abs(data.hashCode())) { // TODO: Тут я заменю hashCode на compare()
                    parent.setRight(null);
                }
            } else if (current.getLeft() == null || current.getRight() == null) {
                if (Math.abs(parent.getData().hashCode()) > Math.abs(data.hashCode())) { // TODO: Тут я заменю hashCode на compare()
                    if (current.getLeft() == null) {
                        parent.setLeft(current.getRight());
                    } else {
                        parent.setLeft(current.getLeft());
                    }
                } else if (Math.abs(parent.getData().hashCode()) < Math.abs(data.hashCode())) { // TODO: Тут я заменю hashCode на compare()
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

                if (Math.abs(parent.getData().hashCode()) > Math.abs(data.hashCode())) { // TODO: Тут я заменю hashCode на compare()
                    parent.setLeft(lastLeftChild);
                } else if (Math.abs(parent.getData().hashCode()) < Math.abs(data.hashCode())) { // TODO: Тут я заменю hashCode на compare()
                    parent.setRight(lastLeftChild);
                }
            }

            size--;
            return true;
        }

        return false;
    }

    private TreeNode<E> getParent(E data, TreeNode<E> currentNode) {
        if (Math.abs(data.hashCode()) == Math.abs(currentNode.getData().hashCode())) { // TODO: Тут я заменю hashCode на compare()
            return null;
        }

        if (Math.abs(currentNode.getLeft().getData().hashCode()) == Math.abs(data.hashCode()) // TODO: Тут я заменю hashCode на compare()
                || Math.abs(currentNode.getRight().getData().hashCode()) == Math.abs(data.hashCode())) { // TODO: Тут я заменю hashCode на compare()
            return currentNode;
        }

        if (Math.abs(currentNode.getData().hashCode()) > Math.abs(data.hashCode())) { // TODO: Тут я заменю hashCode на compare()
            return getParent(data, currentNode.getLeft());
        }

        return getParent(data, currentNode.getRight());
    }

    private TreeNode<E> getNode(E data) {
        TreeNode<E> currentNode = root;

        while (true) {
            if (Math.abs(data.hashCode()) == Math.abs(currentNode.getData().hashCode())) { // TODO: Тут я заменю hashCode на compare()
                return currentNode;
            }

            if (Math.abs(data.hashCode()) < Math.abs(currentNode.getData().hashCode())) { // TODO: Тут я заменю hashCode на compare()
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

    public int compare(E data, Comparable<E> comparable){
        // В данном месте я вообще не могу понять, что и с чем я сравниваю. По идее мне нужно сравнить элементы E data узлов
        // Видимо этот метод нужен, только для того чтобы передать компаратор принятый конструктором на уровень ниже в TreeNode<E>.
        // Иначе мне не понятно, зачем этот метод вызывать от самого дерева. В дереве много элементов. Если я вызываю от дерева,
        // то какой элемент дерева будет сравниваться если их много. Или метод нужно делать в виде
        // public int compare(E data1, E data2, Comparable<E> comparable). Тогда мне в целом понятно, что я просто
        // сравниваю данные 2х произвольных узлов и значит этот метод можно сделать статическим?
        // Опять же я не понимаю что мне сделать с компоратором, я его просто таскаю из метода в метод и не понимаю к чему его прицепить.
        // Какой ментод в итоге у меня заберет метод @compareTo моего компоратора Comparable<E> comporable
        return 0;
    }

    // И видимо тут мне нужно сделать имплементацию метода compareTo из интерфейса Comparable<T>. Для чего он будет использоваться?
    // Для сортировки дерева?
}