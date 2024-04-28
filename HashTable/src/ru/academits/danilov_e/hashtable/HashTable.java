package ru.academits.danilov_e.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final LinkedList<E>[] lists;
    private int size;
    private int modificationsCount;

    public HashTable() {
        lists = (LinkedList<E>[]) new LinkedList[100];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0. Capacity is "
                    + capacity + ".");
        }

        lists = (LinkedList<E>[]) new LinkedList[capacity];
    }

    public int getTableCapacity() { // Метод отладки используется для контроля размера массива. В дальнейшем можно удалить
        return lists.length;
    }

    private int getArrayIndex(Object o, LinkedList<E>[] listsArray) {
        return Math.abs(o.hashCode() % listsArray.length);
    }

    public int getElementIndex(E element) { // Метод отладки используется для контроля изменения индекса при изменении
        // размера таблицы. В дальнейшем можно удалить
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null && lists[i].contains(element)) { // TODO: пересечение с пунктом 2
                return i;
            }
        }

        return -1;
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
        int index = getArrayIndex(o, lists);

        if (lists[index] != null && !lists[index].isEmpty()) { // TODO: пересечение с пунктом 2
            return lists[index].contains(o);
        }

        return false;
    }

    private class HashTableIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int currentListIndex = -1;
        private final int expectedModificationsCount = modificationsCount;

        @Override
        public boolean hasNext() {
            if (currentListIndex != -1) {
                if (currentListIndex + 1 < lists[currentIndex].size()) {
                    return true;
                }

                currentListIndex = -1;
            }

            int index = currentIndex + 1;

            if (index < lists.length && lists[index] == null) { // TODO: пересечение с пунктом 2
                while (index < lists.length) {
                    if (lists[index] != null) { // TODO: пересечение с пунктом 2
                        return true;
                    }

                    index++;
                }
            }

            return false;
        }

        @Override
        public E next() {
            if (expectedModificationsCount != modificationsCount) {
                throw new ConcurrentModificationException("HashTable has been changed");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("HashTable has not next element");
            }

            if (currentListIndex != -1) {
                if (currentListIndex + 1 < lists[currentIndex].size()) {
                    currentListIndex++;
                    return lists[currentIndex].get(currentListIndex);
                }

                currentListIndex = -1;
            }

            currentIndex++;

            if (lists[currentIndex] == null) {
                while (currentIndex < lists.length) {
                    if (lists[currentIndex] != null) {
                        if (lists[currentIndex].size() > 1) {
                            currentListIndex++;
                            return lists[currentIndex].get(currentListIndex);
                        }

                        return lists[currentIndex].getFirst();
                    }

                    currentIndex++;
                }
            }

            return null;
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
        int j = 0;

        for (LinkedList<E> list : lists) {
            if (list != null) { // TODO: пересечение с пунктом 2
                while (i < list.size()){
                    objects[j] = list.get(i);
                    i++;
                    j++;
                }

                i = 0;
            }
        }

        return objects;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] array = (T[]) toArray();

        if (a.length <= size) {
            a = (T[]) Arrays.copyOf(array, size, array.getClass());
        } else {
            a = Arrays.copyOf(array, a.length);
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int index = getArrayIndex(e, lists);

        if (lists[index] == null) {
            LinkedList<E> list = new LinkedList<>(); // Это бывшая переменная element
            list.add(e);
            lists[index] = list;
        } else {
            lists[index].add(e);
        }

        size++;
        modificationsCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getArrayIndex(o, lists);

        if (lists[index] != null && lists[index].size() == 1 && lists[index].getFirst().equals(o)) { // TODO: пересечение с пунктом 2
            lists[index].clear();
            size--;
            modificationsCount++;

            return true;
        }

        if (lists[index] != null && lists[index].size() > 1 && lists[index].contains((E) o)) { // TODO: пересечение с пунктом 2
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
        boolean hasChanged = false;

        for (E t : c) {
            add(t);
            hasChanged = true;
        }

        return hasChanged;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean hasChanged = false;

        for (Object object : c) {
            remove(object);
            hasChanged = true;
        }

        return hasChanged;
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
    public String toString(){

        return "";
    }
}