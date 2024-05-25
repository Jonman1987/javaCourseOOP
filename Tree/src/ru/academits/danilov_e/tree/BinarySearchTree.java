package ru.academits.danilov_e.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;

    private final Comparator<E> COMPARATOR;

    public BinarySearchTree() {
        this.COMPARATOR = null; // IDEA запросила указать компаратор
    }

    public <T> BinarySearchTree(Comparator<E> comparator) {
        this.COMPARATOR = comparator;
    }

    public void add(E data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;
            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int compareResult = compare(data, currentNode.getData());

            if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    break;
                }
            } else if (compareResult > 0) {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    break;
                }
            } else {
                return;
            }
        }

        size++;
    }

    public void visitInWidth(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.remove();

            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void visitInDepth(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        Deque<TreeNode<E>> deque = new LinkedList<>();

        deque.addFirst(root);

        while (!deque.isEmpty()) {
            TreeNode<E> node = deque.removeFirst(); // Возвращает и удаляет
            consumer.accept(node.getData());

            if (node.getRight() != null) {
                deque.addFirst(node.getRight());
            }

            if (node.getLeft() != null) {
                deque.addFirst(node.getLeft());
            }
        }
    }

    public void visitInDepthRecursively(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        visitInDepthRecursively(root, consumer);
    }

    private void visitInDepthRecursively(TreeNode<E> node, Consumer<E> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            visitInDepthRecursively(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visitInDepthRecursively(node.getRight(), consumer);
        }
    }

    private TreeNode<E> getMostLeftNode(TreeNode<E> deletedNode) {
        TreeNode<E> currentNode = deletedNode.getRight();

        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }

        return currentNode;
    }

    // TODO: не нужно дублировать код для удаления корня и не корня.
    public boolean remove(E data) {
        TreeNode<E> deletedNode = getNode(data);

        if (deletedNode == null) {
            return false;
        }

        TreeNode<E> deletedNodeParent = getParent(data, root);

        if (deletedNodeParent == null) {
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
            } else if (root.getLeft() == null) {
                root = root.getRight();
            } else if (root.getRight() == null) {
                root = root.getLeft();
            } else {
                TreeNode<E> lastLeftChild = getMostLeftNode(root);

                if (lastLeftChild.getRight() == null) {
                    root.getRight().setLeft(null);
                } else {
                    root.getRight().setLeft(lastLeftChild.getRight());
                }

                lastLeftChild.setRight(root.getRight());
                lastLeftChild.setLeft(root.getLeft());

                root = lastLeftChild;
            }
        } else if (deletedNode.getLeft() == null && deletedNode.getRight() == null) {
            if (compare(deletedNodeParent.getData(), data) > 0) {
                deletedNodeParent.setLeft(null);
            } else if (compare(deletedNodeParent.getData(), data) < 0) {
                deletedNodeParent.setRight(null);
            }
        } else if (deletedNode.getLeft() == null || deletedNode.getRight() == null) {
            if (compare(deletedNodeParent.getData(), data) > 0) {
                if (deletedNode.getLeft() == null) {
                    deletedNodeParent.setLeft(deletedNode.getRight());
                } else {
                    deletedNodeParent.setLeft(deletedNode.getLeft());
                }
            } else if (compare(deletedNodeParent.getData(), data) < 0) {
                if (deletedNode.getLeft() == null) {
                    deletedNodeParent.setRight(deletedNode.getRight());
                } else {
                    deletedNodeParent.setRight(deletedNode.getLeft());
                }
            }
        } else {
            TreeNode<E> lastLeftChild = getMostLeftNode(deletedNode);
            TreeNode<E> leftNodeParent = getParent(getMostLeftNode(deletedNode).getData(), root);

            if (leftNodeParent != null) {
                leftNodeParent.setLeft(lastLeftChild.getRight());
            }

            lastLeftChild.setRight(deletedNode.getRight());
            lastLeftChild.setLeft(deletedNode.getLeft());

            if (compare(deletedNodeParent.getData(), data) > 0) {
                deletedNodeParent.setLeft(lastLeftChild);
            } else if (compare(deletedNodeParent.getData(), data) < 0) {
                deletedNodeParent.setRight(lastLeftChild);
            }
        }

        size--;
        return true;
    }

    private TreeNode<E> getParent(E data, TreeNode<E> currentNode) {
        if (compare(data, currentNode.getData()) == 0) {
            return null;
        }

        if (compare(currentNode.getLeft().getData(), data) == 0 || compare(currentNode.getRight().getData(), data) == 0) {
            return currentNode;
        }

        if (compare(currentNode.getData(), data) > 0) {
            return getParent(data, currentNode.getLeft());
        }

        return getParent(data, currentNode.getRight());
    }

    private TreeNode<E> getNode(E data) {
        if (size == 0) {
            return null;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int compareResult = compare(data, currentNode.getData());

            if (compareResult == 0) {
                return currentNode;
            }

            if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return null;
                }
            }
        }
    }

    public boolean contains(E data) {
        return getNode(data) != null;
    }

    public int size() {
        return size;
    }

    public int compare(E data1, E data2) {
        if (COMPARATOR != null) {
            return COMPARATOR.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        Comparable<E> comparableData1 = (Comparable<E>) data1;

        return comparableData1.compareTo(data2);
    }
}