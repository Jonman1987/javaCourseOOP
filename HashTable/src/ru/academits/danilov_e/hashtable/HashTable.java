package ru.academits.danilov_e.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final LinkedList<E>[] lists;
    private int size;
    private int modificationsCount;

    private static final int DEFAULT_CAPACITY = 100;

    public HashTable() {
        lists = (LinkedList<E>[]) new LinkedList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0. Capacity is "
                    + capacity + ".");
        }

        lists = (LinkedList<E>[]) new LinkedList[capacity];
    }

    public int getCapacity() { // Метод отладки используется для контроля размера массива. В дальнейшем можно удалить
        return lists.length;
    }

    private int getIndex(Object o) {
        return Math.abs(Objects.hashCode(o) % lists.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }

    @Override
    public boolean contains(Object o) { // TODO: не понятно, что делать с null данными листа
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentArrayIndex = -1;
        private int currentListIndex = -1;
        private int currentElement = -1;
        private final int expectedModificationsCount = modificationsCount;

        @Override
        public boolean hasNext() {
            return currentElement + 1 < size;
        }

        @Override
        public E next() {
            if (expectedModificationsCount != modificationsCount) {
                throw new ConcurrentModificationException("HashTable has been changed");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("HashTable has not next element");
            }

            while (lists[currentArrayIndex + 1] == null || lists[currentArrayIndex + 1].isEmpty()) {
                currentArrayIndex++;
            }

            if (currentListIndex + 1 < lists[currentArrayIndex + 1].size()) {
                currentListIndex++;
                currentElement++;
                return lists[currentArrayIndex + 1].get(currentListIndex);
            }

            currentListIndex = -1;
            boolean hasVisited = true;

            while (lists[currentArrayIndex + 1] == null || lists[currentArrayIndex + 1].isEmpty() || hasVisited) {
                currentArrayIndex++;
                hasVisited = false;
            }

            currentArrayIndex++;

            currentElement++;
            return lists[currentArrayIndex].getFirst();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        int i = 0;

        for (int j = 0; j < lists.length; j++) {
            if (lists[j] != null) {
                int k = 0;

                while (k < lists[j].size()) {
                    objects[i] = lists[j].get(k);
                    k++;
                    i++;
                }
            }
        }

        return objects;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (size > a.length) {
            return (T[]) Arrays.copyOf((T[]) toArray(), size, a.getClass());
        }

        System.arraycopy(toArray(), 0, a, 0, size);

        a[size] = null;

        return a;
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }

        lists[index].add(e);
        size++;
        modificationsCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] != null && lists[index].size() == 1 && lists[index].getFirst().equals(o)) {
            lists[index].clear();
            size--;
            modificationsCount++;

            return true;
        }

        if (lists[index] != null && lists[index].size() > 1 && lists[index].contains((E) o)) {
            lists[index].remove(o);
            size--;
            modificationsCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int matchesCount = 0;

        for (Object object : c) {
            if (contains(object)) {
                matchesCount++;
            }
        }

        return matchesCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            if (!add(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object object : c) {
            while (contains(object)) {
                if (!remove(object)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean hasChanged = false;

        for (int i = 0, j = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                while (lists[i] != null && j < lists[i].size()) {
                    if (!c.contains(lists[i].get(j))) {
                        remove(lists[i].get(j));

                        if (lists[i] != null && lists[i].size() > 1) {
                            j--;
                        }

                        hasChanged = true;
                    }

                    j++;
                }

                j = 0;
            }
        }

        return hasChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (LinkedList<E> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        size = 0;
        modificationsCount++;
    }

    @Override
    public String toString() {
        return "";
    }
}