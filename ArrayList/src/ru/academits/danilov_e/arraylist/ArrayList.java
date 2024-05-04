package ru.academits.danilov_e.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modificationsCount;

    private static final int CAPACITY = 10;

    public ArrayList() {
        items = (E[]) new Object[CAPACITY];
    }

    public ArrayList(Collection<E> collection) {
        items = (E[]) collection.toArray();
        size = collection.size();
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException("Capacity must be greater than 0. Capacity is "
                    + capacity + ".");
        }

        items = (E[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize() {
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
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (size <= a.length) {
            System.arraycopy(items, 0, a, 0, size);

            for (int i = size; i < a.length; i++) { // Логика зануления массива
                a[i] = null;
            }
        } else {
            a = (T[]) Arrays.copyOf((T[]) items, size, a.getClass());
        }

        return a;
    }

    private void increaseCapacity(int currentCapacity) {
        if (currentCapacity == 0) {
            items = Arrays.copyOf(items, CAPACITY);
            return;
        }

        items = Arrays.copyOf(items, currentCapacity * 2);
    }

    @Override
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;
        int index = indexOf(o);

        if (index != -1) {
            result = true;
            remove(index);
        }

        return result;
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
        return addAll(size, c);
    }

    private static void checkingBounds(int index, int maxBound) {
        if (index < 0 || index > maxBound) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (maxBound - 1) + "]. Index is "
                    + index + ".");
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkingBounds(index, size);

        if (!isEmpty()) {
            System.arraycopy(items, index, items, index + c.size(), size - index);
        }

        size = size + c.size();

        if (size >= items.length) {
            increaseCapacity(size);
        }

        int i = index;

        for (E object : c) {
            items[i] = object;
            i++;
        }

        modificationsCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (Object object : c) {
            int index = indexOf(object);

            while (index != -1) {
                remove(index);
                isRemoved = true;
                index = indexOf(object);
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
        modificationsCount++;
    }

    @Override
    public E get(int index) {
        checkingBounds(index, size);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkingBounds(index, size);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        checkingBounds(index, size);

        if (size + 1 >= items.length) {
            increaseCapacity(size);
        }

        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = item;
        size++;
        modificationsCount++;
    }

    @Override
    public E remove(int index) {
        checkingBounds(index, size);

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
            } else if (items[i] == null) {
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
            } else if (items[i] == null) {
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
            stringBuilder.append(items[i]).append(", ");
        }

        return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']').toString();
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