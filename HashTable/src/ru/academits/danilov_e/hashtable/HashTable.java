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
    public boolean contains(Object o) {
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentArrayIndex = -1;
        private int currentListIndex = -1;
        private int visitedElementsCount = -1;
        private final int expectedModificationsCount = modificationsCount;

        @Override
        public boolean hasNext() {
            return visitedElementsCount + 1 < size;
        }

        @Override
        public E next() { // Возможно я не правильно переделал логику с учетом visitedElementsCount, так как я
            // не сильно понял как мне использовать количество посещенных элементов
            if (expectedModificationsCount != modificationsCount) {
                throw new ConcurrentModificationException("HashTable has been changed");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("HashTable has not next element");
            }

            if ((lists[currentArrayIndex + 1] != null || lists[currentArrayIndex + 1].isEmpty())
                    && currentListIndex + 1 < lists[currentArrayIndex + 1].size()) {

                visitedElementsCount++;
                return lists[currentArrayIndex + 1].get(++currentListIndex);
            }

            currentListIndex = -1;
            boolean hasVisited = true;

            while (lists[currentArrayIndex + 1] == null || lists[currentArrayIndex + 1].isEmpty() || hasVisited) {
                currentArrayIndex++;
                hasVisited = false;
            }

            visitedElementsCount++;

            return lists[currentArrayIndex + 1].get(++currentListIndex);
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

        for (LinkedList<E> list : lists) {
            if (list != null) {
                int k = 0;

                while (k < list.size()) {
                    objects[i] = list.get(k);
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

        if (!lists[index].remove(o)) {
            return false;
        }

        size--;
        modificationsCount++;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
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

        for (LinkedList<E> list : lists) {
            if (list != null) {
                int listSize = list.size();

                if (list.retainAll(c)) {
                    size -= (listSize - list.size());
                    hasChanged = true;
                    modificationsCount++;
                }
            } else if (c.contains(null)) {
                remove(null);
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
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (LinkedList<E> list : lists) {
            if (list != null) {
                stringBuilder.append(list).append(", ");
            }
        }

        return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']').toString();
    }
}