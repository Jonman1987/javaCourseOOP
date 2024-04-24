package ru.academits.danilov_e.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private LinkedList<E>[] lists;
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
        int index = Math.abs(o.hashCode() % lists.length);

        if (lists[index] != null && lists[index].size() == 1 && lists[index].getFirst().equals(o)) { // TODO: пересечение с пунктом 2
            return true;
        }

        if (lists[index] != null && lists[index].size() > 1) { // TODO: пересечение с пунктом 2
            for (E e : lists[index]) {
                if (e.equals(o)) {
                    return true;
                }
            }
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
        int elementsCount = 0;

        for (LinkedList<E> item : lists) {
            if (item != null) { // TODO: пересечение с пунктом 2
                elementsCount++;
            }
        }

        Object[] objects = new Object[elementsCount];
        int j = 0;

        for (LinkedList<E> item : lists) {
            if (item != null) { // TODO: пересечение с пунктом 2
                objects[j] = item;
                j++;
            }
        }

        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] array = (T1[]) toArray();

        if (a.length < size) {
            a = (T1[]) Arrays.copyOf(array, size, array.getClass());
        } else {
            a = Arrays.copyOf(array, a.length);
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        if (size == lists.length) {
            LinkedList<E>[] itemsArray = (LinkedList<E>[]) new LinkedList[lists.length * 2];

            for (LinkedList<E> item : lists) {
                if (item != null) { // TODO: пересечение с пунктом 2
                    int index;

                    if (item.size() > 1) {
                        for (E element : item) {
                            index = Math.abs(element.hashCode() % itemsArray.length);

                            if (itemsArray[index] == null) { // TODO: пересечение с пунктом 2
                                itemsArray[index] = new LinkedList<>();
                            }

                            itemsArray[index].add(element);
                        }
                    } else {
                        index = Math.abs(item.getFirst().hashCode() % itemsArray.length);

                        if (itemsArray[index] != null) { // TODO: пересечение с пунктом 2
                            itemsArray[index].add(item.getFirst());
                        } else {
                            itemsArray[index] = item;
                        }
                    }
                }
            }

            lists = itemsArray;
        }

        int index = Math.abs(e.hashCode() % lists.length);

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
        int index = Math.abs(o.hashCode() % lists.length);

        if (lists[index] != null && lists[index].size() == 1 && lists[index].getFirst().equals(o)) { // TODO: пересечение с пунктом 2
            lists[index] = null;
            size--;
            modificationsCount++;

            return true;
        }

        if (lists[index] != null && lists[index].size() > 1) { // TODO: пересечение с пунктом 2
            int iteratorIndex = 0;

            for (E e : lists[index]) {
                if (e.equals(o)) {
                    lists[index].remove(iteratorIndex);
                    size--;
                    modificationsCount++;

                    return true;
                }

                iteratorIndex++;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) { // По идее, наверное более правильно искать коллекцию в таблице, а
        // не наоборот. Но мне тогда не понятно для чего нужен итератор таблицы, если мы будем использовать итератор
        // коллекции. Я реализовал поиск таблицы в коллекции. Могу переделать в обратный вариант.
        int matchesCount = 0;
        HashTableIterator hashTableIterator = (HashTableIterator) iterator();

        while (true) {
            if (hashTableIterator.hasNext()) {
                E element = hashTableIterator.next();

                if (c.contains(element)) {
                    matchesCount++;
                }
            } else {
                break;
            }
        }

        return matchesCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E t : c) {
            add(t);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldSize = size;

        for (Object object : c) {
            remove(object);
        }

        return oldSize - size == c.size();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        HashTableIterator hashTableIterator = (HashTableIterator) iterator();
        LinkedList<E> array = new LinkedList<>();
        int matchesCount = 0;

        while (true) {
            if (hashTableIterator.hasNext()) {
                E element = hashTableIterator.next();

                if (c.contains(element)) {
                    array.add(element);
                    matchesCount++;
                }
            } else {
                break;
            }
        }

        clear();

        addAll(array);

        return matchesCount == c.size();
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (LinkedList<E> item : lists) {
            if(item != null){
                item.clear();
            }
        }

        size = 0;
        modificationsCount++;
    }
}