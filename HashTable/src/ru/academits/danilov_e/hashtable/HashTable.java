package ru.academits.danilov_e.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    public class HashTableIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int currentListIndex = -1;
        int countChanger = size;
        int listChanger;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < items.length;
        }

        @Override
        public T next() { // Возможно я занимаюсь мазохизмом, но мне было интересно до какой глубины я могу написать итератор
            if (size != countChanger) {
                throw new ConcurrentModificationException("Array has been changed");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("The next element is null");
            }

            ++currentIndex;

            while (items[currentIndex] == null){
                ++currentIndex;
            }

            if(items[currentIndex].size() > 1){
                currentListIndex = 0;
            }else {
                currentListIndex = -1;
            }

            listChanger = items[currentIndex].size();

            return items[currentIndex].getFirst();
        }

        public boolean hasNextList() {
            return currentListIndex + 1 < items[currentIndex].size();
        }

        public T nextListElement() {
            if (items[currentIndex].size() != listChanger) {
                throw new ConcurrentModificationException("Array has been changed");
            }

            if(!hasNextList()){
                throw new NoSuchElementException("The next element is null");
            }

            ++currentListIndex;

            return items[currentIndex].get(currentListIndex);
        }
    }

    private LinkedList<T>[] items;
    private int size;

    public HashTable(T object) {
        if (object == null) {
            throw new NullPointerException("Added element is null");
        }

        items = (LinkedList<T>[]) new LinkedList[100];
        size = 0;

        add(object);
    }

    public HashTable(T object, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0. Capacity is "
                    + capacity + ".");
        }

        if (object == null) {
            throw new NullPointerException("Added element is null");
        }

        items = (LinkedList<T>[]) new LinkedList[capacity];
        size = 0;

        add(object);
    }

    public int tableLength() { // Метод отладки используется для контроля размера массива. В дальнейшем можно удалить
        return items.length;
    }

    public int tableIndex(T element) { // Метод отладки используется для контроля изменения индекса при изменении
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
            for (java.util.Iterator<T> iterator = items[index].iterator(); iterator.hasNext(); ) {
                if (iterator.next().equals(o)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        int elementsCount = 0;

        for (LinkedList<T> item : items) {
            if (item != null) {
                elementsCount++;
            }
        }

        Object[] objects = new Object[elementsCount];
        int j = 0;

        for (LinkedList<T> item : items) {
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
    public boolean add(T t) {
        if (t == null) {
            throw new NullPointerException("Added element is null");
        }

        if (size == items.length) {
            LinkedList<T>[] itemsArray = (LinkedList<T>[]) new LinkedList[items.length * 2];

            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    int index;

                    if (items[i].size() > 1) {
                        for (java.util.Iterator<T> iterator = items[i].iterator(); iterator.hasNext(); ) {
                            T element = iterator.next();
                            index = Math.abs(element.hashCode() % itemsArray.length);

                            if (itemsArray[index] != null) {
                                itemsArray[index].add(element);
                            } else {
                                LinkedList<T> list = new LinkedList<>();
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

        int index = Math.abs(t.hashCode() % items.length);
        int oldSize = size;

        if (items[index] == null) {
            LinkedList<T> element = new LinkedList<>();
            element.add(t);
            items[index] = element;
            size++;
        } else {
            items[index].add(t);
            size++;
        }

        return size != oldSize;
    }

    // Насколько понял этот метод не особо имеет смысла для хеш-таблиц, на всякий случай оставил, но если надо удалю
    /*private void trimToSize() {
        if (items.length / size >= 2) {
            LinkedList<T>[] itemsArray = (LinkedList<T>[]) new LinkedList[items.length / 2];

            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    int index;

                    if (items[i].size() > 1) {
                        for (Iterator<T> iterator = items[i].iterator(); iterator.hasNext(); ) {
                            T element = iterator.next();
                            index = Math.abs(element.hashCode() % itemsArray.length);

                            if (itemsArray[index] != null) {
                                itemsArray[index].add(element);
                            } else {
                                LinkedList<T> list = new LinkedList<>();
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
    }*/

    @Override
    public boolean remove(Object o) {
        int index = Math.abs(o.hashCode() % items.length);

        if (items[index] != null && items[index].size() == 1 && items[index].getFirst().equals(o)) {
            items[index] = null;
            size--;
            // trimToSize();

            return true;
        }

        if (items[index] != null && items[index].size() > 1) {
            int iteratorIndex = 0;

            for (java.util.Iterator<T> iterator = items[index].iterator(); iterator.hasNext(); ) {
                if (iterator.next().equals(o)) {
                    items[index].remove(iteratorIndex);
                    size--;
                    // trimToSize();

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

        while (true){
            if(hashTableIterator.hasNext()){
                T element = hashTableIterator.next();

                if(c.contains(element)){
                    matchCount++;

                    while (true){
                        if(hashTableIterator.hasNextList()){
                            T element2 = hashTableIterator.nextListElement();

                            if(c.contains(element2)){
                                matchCount++;
                            }
                        }else {
                            break;
                        }
                    }

                }
            }else {
                break;
            }
        }

        return matchCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        int oldSize = size;

        for (T t : c) {
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
        Iterator<?> iterator = c.iterator();

        while (iterator.hasNext()){

        }


        return false;
    }

    @Override
    public void clear() {
        items = (LinkedList<T>[]) new LinkedList[100];
        size = 0;
    }
}
