package ru.academits.danilov_e.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private Node<E> head;
    private int count;

    public SinglyLinkedList(E data) {
        head = new Node<>(data, null);
        count = 1;
    }

    public SinglyLinkedList(E[] dataArray) {
        if(dataArray == null){
            throw new NullPointerException("Array is null");
        }

        if (dataArray.length == 0) {
            head = null;
            count = 0;
        } else if (dataArray.length == 1) {
            head = new Node<>(dataArray[0], null);
            head.setNext(null);
            count = 1;
        } else {
            head = new Node<>(dataArray[0], null);
            head.setNext(new Node<>(dataArray[1], null));
            count = 2;

            Node<E> node;
            int i;

            for (node = head.getNext(), i = 2; i < dataArray.length; i++) {
                node.setNext(new Node<>(dataArray[i], null));
                count++;
                node = node.getNext();
            }
        }
    }

    public int getCount() {
        return count;
    }

    public E getFirst() {
        if(count == 0){
            throw new NoSuchElementException("There are no items in the list.");
        }

        return head.get();
    }

    private static void checkingBounds(int index, int count) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("The index must belong to the range [0; " + (count - 1) + "]. Index is "
                    + index + ".");
        }
    }

    private Node<E> getSearchedNode(Node<E> head, int index) {
        Node<E> node;
        Node<E> searchedNode = null;

        int i;

        for (i = 0, node = head; node != null; node = node.getNext(), i++) {
            if (i == index) {
                searchedNode = node;
                break;
            }
        }

        return searchedNode;
    }

    public E get(int index) {
        checkingBounds(index, count);

        if (getSearchedNode(head, index) == null) {
            return null;
        }

        return getSearchedNode(head, index).get();
    }

    public E set(int index, E data) {
        checkingBounds(index, count);

        if (getSearchedNode(head, index) == null) {
            return null;
        }

        Node<E> node = getSearchedNode(head, index);
        E oldData = node.get();
        node.set(data);

        return oldData;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        Node<E> node;

        for (node = head; node != null; node = node.getNext()) {
            stringBuilder.append(node.get()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']');

        return stringBuilder.toString();
    }

    public E delete(int index) {
        checkingBounds(index, count);

        if (getSearchedNode(head, index) == null) {
            return null;
        }

        E deletedData;
        Node<E> node;

        if (index == 0) {
            node = getSearchedNode(head, index);
            deleteFirst();
            return node.get();
        }

        if (index == count - 1) {
            node = getSearchedNode(head, count - 1);
            node.setNext(null);
        }

        node = getSearchedNode(head, index - 1);
        deletedData = node.getNext().get();
        node.setNext(node.getNext().getNext());
        count--;

        return deletedData;
    }

    public void addFirst(E data) {
        Node<E> node = new Node<>(data, null);
        node.setNext(head);
        head = node;
        count++;
    }

    public void add(int index, E data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("The index must belong to the range [0; " + count + "]. Index is "
                    + index + ".");
        }

        Node<E> currentNode;

        if (index == 0) {
            addFirst(data);
        } else if (index == count) {
            currentNode = getSearchedNode(head, count - 1);
            Node<E> node = new Node<>(data, null);
            currentNode.setNext(node);
            count++;
        } else {
            currentNode = getSearchedNode(head, index - 1);
            Node<E> previousNode = currentNode.getNext();
            Node<E> node = new Node<>(data, null);
            currentNode.setNext(node);
            node.setNext(previousNode);
            count++;
        }
    }

    public boolean deleteByData(E data) {
        Node<E> node;
        boolean isDeleted = false;
        int i = 0;

        for (node = head; node != null; node = node.getNext()) {
            if (node.get().equals(data)) {
                delete(i);
                isDeleted = true;
            }

            i++;
        }

        return isDeleted;
    }

    public E deleteFirst() {
        if(count == 0){
            throw new NoSuchElementException("There are no items in the list.");
        }

        Node<E> deletedNode = head;
        head = head.getNext();
        count--;

        return deletedNode.get();
    }

    public void reverse() {
        Node<E> reversedNode = null;
        Node<E> node = head;

        while (node != null) {
            Node<E> nextNode = node.getNext();
            node.setNext(reversedNode);
            reversedNode = node;
            node = nextNode;
        }

        head = reversedNode;
    }

    public SinglyLinkedList<E> copy() {
        Node<E> node;

        SinglyLinkedList<E> newSinglyLinkedList = new SinglyLinkedList<>(head.get());
        int i = 1;

        for (node = head.getNext(); node != null; node = node.getNext()) {
            newSinglyLinkedList.add(i, node.get());

            i++;
        }

        return newSinglyLinkedList;
    }
}
