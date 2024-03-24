package ru.academits.danilov_e.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    public class HashTableIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int currentListIndex = -1;
        private int countChanger = size;
        private int listCountChanger;

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

    private LinkedList<E>[] items;
    private int size;

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
            for (java.util.Iterator<E> iterator = items[index].iterator(); iterator.hasNext(); ) {
                if (iterator.next().equals(o)) {
                    return true;
                }
            }
        }

        return false;
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
        return null;
    }

    private boolean isTableNeedMoreSize() {

        return false;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Added element is null");
        }

        if (size == items.length) {
            LinkedList<E>[] itemsArray = (LinkedList<E>[]) new LinkedList[items.length * 2];

            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    int index;

                    if (items[i].size() > 1) {
                        for (java.util.Iterator<E> iterator = items[i].iterator(); iterator.hasNext(); ) {
                            E element = iterator.next();
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
                        index = Math.abs(items[i].getFirst().hashCode() % itemsArray.length);

                        if (itemsArray[index] != null) {
                            itemsArray[index].add(items[i].getFirst());
                        } else {
                            itemsArray[index] = items[i];
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
        } else {
            items[index].add(e);
            size++;
        }

        return size != oldSize;
    }

    @Override
    public boolean remove(Object o) {
        int index = Math.abs(o.hashCode() % items.length);

        if (items[index] != null && items[index].size() == 1 && items[index].getFirst().equals(o)) {
            items[index] = null;
            size--;

            return true;
        }

        if (items[index] != null && items[index].size() > 1) {
            int iteratorIndex = 0;

            for (java.util.Iterator<E> iterator = items[index].iterator(); iterator.hasNext(); ) {
                if (iterator.next().equals(o)) {
                    items[index].remove(iteratorIndex);
                    size--;

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
        int matchCount = 0;
        HashTableIterator hashTableIterator = (HashTableIterator) iterator();

        while (true) {
            if (hashTableIterator.hasNext()) {
                E element = hashTableIterator.next();

                if (c.contains(element)) {
                    matchCount++;
                }
            } else {
                break;
            }
        }

        return matchCount == c.size();
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
        int matchCount = 0;

        while (true) {
            if (hashTableIterator.hasNext()) {
                E element = hashTableIterator.next();

                if (c.contains(element)) {
                    array.add(element);
                    matchCount++;
                }
            } else {
                break;
            }
        }

        clear();

        this.addAll(array);

        return matchCount == c.size();
    }

    @Override
    public void clear() {
        items = (LinkedList<E>[]) new LinkedList[100];
        size = 0;
    }
}
