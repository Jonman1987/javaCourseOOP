package ru.academits.danilov_e.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> { // TODO: 2
    private final LinkedList<E>[] lists;
    private int size;
    private int modificationsCount;

    private static final int DEFAULT_CAPACITY = 100;

    public HashTable() {
        //noinspection unchecked
        lists = (LinkedList<E>[]) new LinkedList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0. Capacity is "
                    + capacity + ".");
        }

        //noinspection unchecked
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
                System.arraycopy(list.toArray(), 0, objects, i, list.size());
                i += list.size();
            }
        }

        return objects;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (size > a.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf((T[]) toArray(), size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }

        if (!lists[index].add(e)) {
            return false;
        }

        size++;
        modificationsCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        }

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
        boolean isChanged = false;

        for (E element : c) {
            if (add(element)) {
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;

        for (Object object : c) {
            //noinspection SuspiciousMethodCalls
            while (lists[getIndex(remove(object))].contains(object)) {
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean hasChanged = false;

        for (LinkedList<E> list : lists) {
            if (list != null) {
                int listSize = list.size();

                if (list.retainAll(c)) {
                    size -= listSize - list.size();
                    hasChanged = true;
                }
            }
        }

        modificationsCount++;

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
            if (list != null && !list.isEmpty()) {
                stringBuilder.append(list).append(", ");
            }
        }

        return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']').toString();
    }
}