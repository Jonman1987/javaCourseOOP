package ru.academits.danilov_e.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private LinkedList<E>[] items;
    private int size;
    private int modificationsCount = 0;

    public HashTable(E object) {
        if (object == null) {
            throw new NullPointerException("Added element is null");
        }

        items = (LinkedList<E>[]) new LinkedList[100];
        size = 0;

        add(object);
    }

    public HashTable(E object, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0. Capacity is "
                    + capacity + ".");
        }

        if (object == null) {
            throw new NullPointerException("Added element is null");
        }

        items = (LinkedList<E>[]) new LinkedList[capacity];
        size = 0;

        add(object);
    }

    public int tableLength() { // Метод отладки используется для контроля размера массива. В дальнейшем можно удалить
        return items.length;
    }

    public int tableIndex(E element) { // Метод отладки используется для контроля изменения индекса при изменении
        // размера таблицы. В дальнейшем можно удалить
        int index = -1;

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].contains(element)) {
                index = i;
            }
        }

        return index;
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
        if (o == null) {
            throw new NullPointerException("Searched element is null");
        }

        int index = Math.abs(o.hashCode() % items.length);

        if (items[index] != null && items[index].size() == 1 && items[index].getFirst().equals(o)) {
            return true;
        }

        if (items[index] != null && items[index].size() > 1) {
            for (E e : items[index]) {
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
            if(currentListIndex != -1){
                if(currentListIndex + 1 < items[currentIndex].size()){
                    return true;
                }else {
                    currentListIndex = -1;
                }
            }

            int index = currentIndex + 1;

            if(index < items.length && items[index] == null){
                while (index < items.length){
                    if(items[index] != null){
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

            if(currentListIndex != -1){
                if(currentListIndex + 1 < items[currentIndex].size()){
                    currentListIndex++;
                    return items[currentIndex].get(currentListIndex);
                }else {
                    currentListIndex = -1;
                }
            }

            currentIndex++;

            if(items[currentIndex] == null){
                while (currentIndex < items.length){
                    if(items[currentIndex] != null){
                        if(items[currentIndex].size() > 1){
                            currentListIndex++;
                            return items[currentIndex].get(currentListIndex);
                        }

                        return items[currentIndex].getFirst();
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

        for (LinkedList<E> item : items) {
            if (item != null) {
                elementsCount++;
            }
        }

        Object[] objects = new Object[elementsCount];
        int j = 0;

        for (LinkedList<E> item : items) {
            if (item != null) {
                objects[j] = item;
                j++;
            }
        }

        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] array = (T1[]) toArray();
        if(a.length < size){
            a = (T1[]) Arrays.copyOf(array, size, array.getClass());
        }else {
            a = Arrays.copyOf(array, a.length);
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Added element is null");
        }

        if (size == items.length) {
            LinkedList<E>[] itemsArray = (LinkedList<E>[]) new LinkedList[items.length * 2];

            for (LinkedList<E> item : items) {
                if (item != null) {
                    int index;

                    if (item.size() > 1) {
                        for (E element : item) {
                            index = Math.abs(element.hashCode() % itemsArray.length);

                            if (itemsArray[index] != null) {
                                itemsArray[index].add(element);
                            } else {
                                LinkedList<E> list = new LinkedList<>();
                                itemsArray[index] = list;
                                itemsArray[index].add(element);
                            }
                        }
                    } else {
                        index = Math.abs(item.getFirst().hashCode() % itemsArray.length);

                        if (itemsArray[index] != null) {
                            itemsArray[index].add(item.getFirst());
                        } else {
                            itemsArray[index] = item;
                        }
                    }
                }
            }

            items = itemsArray;
        }

        int index = Math.abs(e.hashCode() % items.length);
        int oldSize = size;

        if (items[index] == null) {
            LinkedList<E> element = new LinkedList<>();
            element.add(e);
            items[index] = element;
            size++;
            modificationsCount++;
        } else {
            items[index].add(e);
            size++;
            modificationsCount++;
        }

        return size != oldSize;
    }

    @Override
    public boolean remove(Object o) {
        int index = Math.abs(o.hashCode() % items.length);

        if (items[index] != null && items[index].size() == 1 && items[index].getFirst().equals(o)) {
            items[index] = null;
            size--;
            modificationsCount++;

            return true;
        }

        if (items[index] != null && items[index].size() > 1) {
            int iteratorIndex = 0;

            for (E e : items[index]) {
                if (e.equals(o)) {
                    items[index].remove(iteratorIndex);
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
        int oldSize = size;

        for (E t : c) {
            add(t);
        }

        return size - oldSize == c.size();
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

        this.addAll(array);

        return matchesCount == c.size();
    }

    @Override
    public void clear() {
        if(size != 0){
            for(int i = 0; i < items.length; i++){
                if(items[i] != null){
                    items[i] = null;
                }
            }

            size = 0;
            modificationsCount++;
        }
    }
}
