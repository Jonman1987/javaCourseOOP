package ru.academits.danilov_e.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private Node<E> head;
    private int count;

    public SinglyLinkedList() {

    }

    public SinglyLinkedList(E[] dataArray) {
        if (dataArray == null) {
            throw new NullPointerException("Array is null");
        }

        if (dataArray.length == 0) {
            return;
        }

        head = new Node<>(dataArray[0]);

        int i = 1;

        for (Node<E> node = head; i < dataArray.length; i++) {
            node.setNext(new Node<>(dataArray[i]));
            node = node.getNext();
        }

        count = dataArray.length;
    }

    public int getCount() {
        return count;
    }

    public E getFirst() {
        if (count == 0) {
            throw new NoSuchElementException("There are no items in the list.");
        }

        return head.getData();
    }

    private static void checkIndex(int index, int count) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("The index must belong to the range [0; " + (count - 1) + "]. Index is "
                    + index + ".");
        }
    }

    private Node<E> getNode(int index) {
        int i = 0;

        for (Node<E> node = head; index < count; node = node.getNext(), i++) {
            if (i == index) {
                return node;
            }
        }

        return null;
    }

    public E get(int index) {
        checkIndex(index, count);

        return getNode(index).getData();
    }

    public E set(int index, E data) {
        checkIndex(index, count);

        Node<E> node = getNode(index);
        E oldData = node.getData();
        node.setData(data);

        return oldData;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (Node<E> node = head; node != null; node = node.getNext()) {
            stringBuilder.append(node.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']');

        return stringBuilder.toString();
    }

    public E delete(int index) {
        checkIndex(index, count);

        if (index == 0) {
            return deleteFirst();
        }

        Node<E> node = getNode(index - 1);
        E deletedData = node.getNext().getData();
        node.setNext(node.getNext().getNext());
        count--;

        return deletedData;
    }

    public void addFirst(E data) {
        head = new Node<>(data, head);
        count++;
    }

    public void add(int index, E data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("The index must belong to the range [0; " + count + "]. Index is "
                    + index + ".");
        }

        Node<E> previousNode;

        if (index == 0) {
            addFirst(data);
            return;
        }

        previousNode = getNode(index - 1);
        Node<E> currentNode = previousNode.getNext();
        previousNode.setNext(new Node<>(data, currentNode));
        count++;
    }

    public boolean deleteByData(E data) {
        if (count == 0) {
            return false;
        }

        if (head.getData().equals(data)) {
            deleteFirst();
            return true;
        }

        // Если честно не совсем понял замечание "здесь в цикле лучше иметь ссылки на текущий и предыдущий узлы
        // и упростить код"
        for (Node<E> previousNode = head, nextNode = previousNode.getNext().getNext(); previousNode.getNext() != null;
             previousNode = previousNode.getNext(), nextNode = nextNode.getNext()) {
            if (previousNode.getNext().getData().equals(data)) {
                previousNode.setNext(nextNode);
                count--;
                return true;
            }
        }

        return false;
    }

    public E deleteFirst() {
        if (count == 0) {
            throw new NoSuchElementException("There are no items in the list.");
        }

        Node<E> deletedNode = head;
        head = head.getNext();
        count--;

        return deletedNode.getData();
    }

    public void reverse() {
        Node<E> newNode = null;
        Node<E> node = head;

        while (node != null) {
            Node<E> nextNode = node.getNext();
            node.setNext(newNode);
            newNode = node;
            node = nextNode;
        }

        head = newNode;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> listCopy = new SinglyLinkedList<>();

        if (count == 0) {
            return listCopy;
        }

        listCopy.addFirst(head.getData());

        for (Node<E> currentNode = head.getNext(), currentCopyNode = listCopy.head; currentNode != null;
             currentNode = currentNode.getNext(), currentCopyNode = currentCopyNode.getNext()) {
            currentCopyNode.setNext(new Node<>(currentNode.getData()));
        }

        listCopy.count = getCount();

        return listCopy;
    }
}