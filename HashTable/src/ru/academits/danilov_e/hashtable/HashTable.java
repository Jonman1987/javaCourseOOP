package ru.academits.danilov_e.hashtable;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<T> implements Collection<T> {
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
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].size() > 1) {
                for (Iterator<T> iterator = items[i].iterator(); iterator.hasNext(); ) {
                    if (iterator.next().equals(o)) {
                        return true;
                    }
                }
            } else if (items[i] != null) {
                if (items[i].equals(o)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
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
                        for (Iterator<T> iterator = items[i].iterator(); iterator.hasNext(); ) {
                            T element = iterator.next();
                            index = Math.abs(element.hashCode() % itemsArray.length);

                            if (itemsArray[index] != null) {
                                itemsArray[index].add(element);
                            } else {
                                // Мне пришлось прибегнуть к такой конструкции
                                LinkedList<T> list = new LinkedList<>();
                                itemsArray[index] = list;
                                itemsArray[index].add(element);
                                // Так как при использовании команды
                                // itemsArray[index] = (LinkedList<T>) iterator.next();
                                // 85 строка в файле HashTableMain давала ошибку ClassCastExсeption и я не смог
                                // разобраться почему у меня получается в рабочем режиме добавить 2 цвета, но
                                // происходит падение при 3м добавлении цвета. При этом на отладке иногда класс
                                // отрабатывает нормально, а иногда падает. Я не смог отловить ошибку.
                            }
                        }
                    } else {
                        index = Math.abs(items[i].hashCode() % itemsArray.length);

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

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        items = (LinkedList<T>[]) new LinkedList[100];
        size = 0;
    }
}
