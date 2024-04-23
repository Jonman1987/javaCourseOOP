package ru.academits.danilov_e.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modificationsCount;
    private static final int capacity = 10;

    public ArrayList() {
        items = (E[]) new Object[capacity];
    }

    public ArrayList(Collection<E> list) {
        items = (E[]) Arrays.copyOf(list.toArray(), list.size());
        size = list.size();
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0. Capacity is "
                    + capacity + ".");
        }

        items = (E[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= size) {
            throw new IllegalArgumentException("New capacity must be less or equal then current size. New capacity is "
                    + capacity + ". Size is " + size + ".");
        }

        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize() {
        if (items.length == size) {
            throw new IllegalArgumentException("New capacity must be greater then current size. New capacity is " + capacity
                    + ". Size is " + size + ".");
        }

        items = Arrays.copyOf(items, size);
    }

    public int getCapacity() { // Данный метод использовал для отладки, чтобы смотреть, как динамически изменяется
        // размер массива. В дальнейшем его можно удалить.
        return items.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }

        return indexOf(o) != -1;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModificationsCount = modificationsCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (expectedModificationsCount != modificationsCount) {
                throw new ConcurrentModificationException("List has been changed");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("There is no next item");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public ArrayListIterator iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int arraySize = 0;

        for (T t : a) {
            if (t != null) {
                arraySize++;
            }
        }

        if (size + arraySize <= a.length) {
            T[] items = (T[]) toArray();

            for (int i = 0; i < items.length; i++) {
                a[arraySize + i] = items[i];
            }

            return a;
        }

        T[] array = (T[]) new Object[size + arraySize];
        int j = 0;

        for (int i = 0; i < size + arraySize; i++) {
            if (i < arraySize) {
                array[i] = a[i];

                continue;
            }

            array[i] = (T) items[j];
            j++;
        }

        return array;
    }

    private void ensureCapacityTwice() {
        if (items.length == 0) {
            ensureCapacity(capacity);
            return;
        }

        if (items.length == size) {
            ensureCapacity(items.length * 2);
        }
    }

    @Override
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;

        while (true) {
            int index = indexOf(o);

            if (index == -1) {
                break;
            } else {
                result = true;
            }

            remove(index);
        }

        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int matchesCount = 0;

        for (int i = 0; i < c.size(); i++) {
            if (contains(c.toArray()[i])) {
                matchesCount++;
            }
        }

        return matchesCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    private static void checkingBounds(int index, int maxBound, boolean hasIncludeMaxBound) {
        if (hasIncludeMaxBound) {
            if (index < 0 || index >= maxBound) {
                throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (maxBound - 1) + "]. Index is "
                        + index + ".");
            }
        } else {
            if (index < 0 || index > maxBound) {
                throw new IndexOutOfBoundsException("Index must belong to the range [0; " + maxBound + "]. Index is "
                        + index + ".");
            }
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (isEmpty()) {
            return false;
        }

        checkingBounds(index, size, false);

        System.arraycopy(items, index, items, index + c.size(), size - index);

        for (int i = index, j = 0; j < c.size(); i++, j++) {
            items[i] = (E) c.toArray()[j];
            ensureCapacityTwice();
            size++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (isEmpty()) {
            return false;
        }

        boolean hasDeleted = false;

        for (Object object : c) {
            remove(object);
            hasDeleted = true;
        }

        return hasDeleted;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;

        if (isEmpty()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(items[i]);
                isRemoved = true;
                i--;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size != 0) {
            for (E item : items) {
                item = null;
            }

            size = 0;
            modificationsCount++;
        }
    }

    @Override
    public E get(int index) {
        checkingBounds(index, size, true);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkingBounds(index, size, true);

        E oldItem = items[index]; // Бывший oldElement
        items[index] = item;
        modificationsCount++;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        checkingBounds(index, size, false);

        if (items.length == 0) {
            ensureCapacityTwice();
        }

        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = item;
        size++;
        modificationsCount++;
        ensureCapacityTwice();
    }

    @Override
    public E remove(int index) {
        checkingBounds(index, size, true);

        E deletedItem = items[index];

        if (index != size - 1) {
            System.arraycopy(items, index + 1, items, index, size - 1 - index);
        }

        items[size - 1] = null;
        size--;
        modificationsCount++;

        return deletedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i] != null && items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i] != null && items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                stringBuilder.append(items[i]).append(", ");
            } else {
                stringBuilder.append("null").append(", ");
            }
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1)
                .append(']').toString();
    }

    @Override
    public ListIterator<E> listIterator() { // Указано, что данный метод реализовывать не нужно
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) { // Указано, что данный метод реализовывать не нужно
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) { // Указано, что данный метод реализовывать не нужно
        return null;
    }
}
