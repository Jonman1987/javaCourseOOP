package ru.academits.danilov_e.list;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private int count;

    public SinglyLinkedList(Node<T> node) {
        head = node;
        count = 1;
    }

    public SinglyLinkedList(Node<T>[] nodes) { // Почему если я делаю public SinglyLinkedList(Node<T>... nodes),
        // то вылазит warning: Possible heap pollution from parameterized vararg type? Это из-за того,
        // что я могу указать через запятую разные типы объектов?
        head = nodes[0];
        head.setNext(nodes[1]);
        count = 2;

        int nodeIndex;
        Node<T> currentElement;

        for (currentElement = head.getNext(), nodeIndex = 2; nodeIndex < nodes.length; nodeIndex++) {
            currentElement.setNext(nodes[nodeIndex]);
            count++;
            currentElement = currentElement.getNext();
        }
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        return head.getData();
    }

    public T getData(int index) {
        if (index < 1 || index > count) {
            throw new IllegalArgumentException("The index must belong to the range [1; " + count + "]. Index is "
                    + index + ".");
        }

        T data = null;
        int nodeIndex = 1;
        Node<T> currentElement;

        for (currentElement = head; currentElement != null; currentElement = currentElement.getNext()) {
            if (nodeIndex == index) {
                data = currentElement.getData();
            }

            nodeIndex++;
        }

        return data;
    }

    public T setData(int index, T data) {
        if (index < 1 || index > count) {
            throw new IllegalArgumentException("The index must belong to the range [1; " + count + "]. Index is "
                    + index + ".");
        }

        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        T oldData = null;
        int nodeIndex = 1;
        Node<T> currentNode;

        for (currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            if (nodeIndex == index) {
                oldData = currentNode.getData();
                currentNode.setData(data);
            }

            nodeIndex++;
        }

        return oldData;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Node<T> currentNode;

        for (currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            stringBuilder.append(currentNode.getData().toString());
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']');

        return stringBuilder.toString();
    }

    public T delete(int index) {
        if (count == 1) {
            throw new IllegalArgumentException("You try to delete last element");
        }

        if (index < 1 || index > count) {
            throw new IllegalArgumentException("The index must belong to the range [1; " + count + "]. Index is "
                    + index + ".");
        }

        T deleteData = null;
        int nodeIndex = 1;
        Node<T> currentNode;

        if (index == 1) {
            deleteFirst();
        }

        if (index == getCount()) {
            for (currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
                if (nodeIndex == getCount()) {
                    currentNode.setNext(null);
                }

                nodeIndex++;
            }
        }

        nodeIndex = 1;
        for (currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            if (nodeIndex == index - 1) {
                deleteData = currentNode.getNext().getData();
                currentNode.setNext(currentNode.getNext().getNext());
                count--;
            }

            nodeIndex++;
        }

        return deleteData;
    }

    public void inputFirst(Node<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Input node is null.");
        }

        Node<T> oldHead = head;
        head = node;
        head.setNext(oldHead);
        count++;
    }

    public void input(int index, Node<T> node) {
        if (index < 1 || index > count + 1) {
            throw new IllegalArgumentException("The index must belong to the range [1; " + count + "]. Index is "
                    + index + ".");
        }

        if (node == null) {
            throw new IllegalArgumentException("Input node is null.");
        }

        Node<T> currentNode;
        int nodeIndex = 1;

        if (index == 1) {
            inputFirst(node);
        } else if (index == count + 1) {
            for (currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
                if (nodeIndex == count) {
                    currentNode.setNext(node);
                }

                nodeIndex++;
            }

            count++;
        } else {
            Node<T> oldNode;

            for (currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
                if (nodeIndex == index - 1) {
                    oldNode = currentNode.getNext();
                    currentNode.setNext(node);
                    node.setNext(oldNode);
                }

                nodeIndex++;
            }

            count++;
        }
    }

    public boolean deleteData(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }

        Node<T> currentNode;
        int nodeIndex = 1;
        boolean isDeleted = false;

        for (currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            if (currentNode.getData().equals(data)) {
                delete(nodeIndex);
                isDeleted = true;
            }

            nodeIndex++;
        }

        return isDeleted;
    }

    public T deleteFirst() {
        Node<T> deletedNode = head;
        head = head.getNext();
        count--;

        return deletedNode.getData();
    }

    public void deploy() {
        Node<T> currentStartNode = null;
        Node<T> currentNode;
        Node<T> startNode = null;
        int nodeCount = count;

        while (nodeCount != 0) {
            int nodeIndex = 1;

            for (currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
                if (nodeIndex == count) {
                    startNode = currentNode;
                    currentStartNode = startNode;
                } else if (nodeIndex == nodeCount) {
                    assert startNode != null;
                    currentNode.setNext(null);
                    currentStartNode.setNext(currentNode);
                    currentStartNode = startNode.getNext();
                }

                nodeIndex++;

                if (nodeIndex > nodeCount) {
                    break;
                }
            }

            nodeCount--;
        }

        head = startNode;
    }

    public SinglyLinkedList<T> copy() {
        Node<T> currentNode;
        Node<T> newHead = new Node<>(head.getData(), null);

        SinglyLinkedList<T> newSinglyLinkedList = new SinglyLinkedList<>(newHead);
        int nodeIndex = 2;

        for (currentNode = head.getNext(); currentNode != null; currentNode = currentNode.getNext()) {
            newSinglyLinkedList.input(nodeIndex, new Node<>(currentNode.getData(), null));

            nodeIndex++;
        }

        return newSinglyLinkedList;
    }
}
