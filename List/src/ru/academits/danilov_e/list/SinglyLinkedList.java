package ru.academits.danilov_e.list;

public class SinglyLinkedList<E> {
    private Node<E> head;
    private int count;

    public SinglyLinkedList(E data) {
        head = new Node<>(data, null);
        count = 1;
    }

    public SinglyLinkedList(E[] dataArray) { // Почему если я делаю public SinglyLinkedList(Node<T>... nodes),
        // то вылазит warning: Possible heap pollution from parameterized vararg type? Это из-за того,
        // что я могу указать через запятую разные типы объектов?
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
        /*head = nodes[0];
        head.setNext(nodes[1]);
        count = 2;

        Node<E> node;
        int i;

        for (node = head.getNext(), i = 2; i < nodes.length; i++) {
            node.setNext(nodes[i]);
            count++;
            node = node.getNext();
        }*/
    }

    public int getCount() {
        return count;
    }

    public E getFirst() {
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
            stringBuilder.append(node.get());
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']');

        return stringBuilder.toString();
    }

    public E delete(int index) {
        if (count == 1) {
            throw new IllegalArgumentException("You try to delete last node");
        }

        checkingBounds(index, count);

        E deleteData;
        Node<E> node;

        if (index == 0) {
            deleteFirst();
        }

        if (index == count) {
            if (getSearchedNode(head, index) == null) {
                return null;
            }

            node = getSearchedNode(head, count - 1);
            node.setNext(null);
        }

        node = getSearchedNode(head, index - 1);
        deleteData = node.getNext().get();
        node.setNext(node.getNext().getNext());
        count--;

        return deleteData;
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

        Node<E> newNode;
        int i = 0;

        if (index == 0) {
            addFirst(data);
        } else if (index == count) {
            for (newNode = head; newNode != null; newNode = newNode.getNext()) { // заменить
                if (i == count - 1) {
                    Node<E> node = new Node<>(data, null);
                    newNode.setNext(node);
                    break;
                }

                i++;
            }

            count++;
        } else {
            Node<E> oldNode;

            for (newNode = head; newNode != null; newNode = newNode.getNext()) { // заменить
                if (i == index - 1) {
                    oldNode = newNode.getNext();
                    Node<E> node = new Node<>(data, null);
                    newNode.setNext(node);
                    node.setNext(oldNode);
                }

                i++;
            }

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
