package ru.academits.danilov_e.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modificationsCount;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(Collection<E> collection) {
        //noinspection unchecked
        items = (E[]) collection.toArray();
        size = collection.size();
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity can't be less than 0. Capacity is "
                    + capacity + ".");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    // Данный метод использовал для отладки, чтобы смотреть, как динамически изменяется
    // размер массива. В дальнейшем его можно удалить.
    public int getCapacity() {
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
        if (size > a.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            items = Arrays.copyOf(items, DEFAULT_CAPACITY);
            return;
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return true;
        }

        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    private static void checkIndex(int index, int maxIndex) {
        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + maxIndex + "]. Index is "
                    + index + ".");
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index, size);

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity((size + c.size()));

        if (index != size) {
            System.arraycopy(items, index, items, index + c.size(), size - index);
        }

        int i = index;

        for (E element : c) {
            items[i] = element;
            i++;
        }

        size += c.size();
        modificationsCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (isEmpty() || c.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (isEmpty()) {
            return false;
        }

        if (c.isEmpty()) {
            clear();
            return true;
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

        Arrays.fill(items, 0, size, null);

        size = 0;
        modificationsCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index, size - 1);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index, size - 1);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        checkIndex(index, size);

        if (size == items.length) {
            increaseCapacity();
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
        checkIndex(index, size - 1);

        E removedItem = items[index];

        if (index != size - 1) {
            System.arraycopy(items, index + 1, items, index, size - 1 - index);
        }

        items[size - 1] = null;
        size--;
        modificationsCount++;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
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

        return stringBuilder
                .delete(stringBuilder.length() - 2, stringBuilder.length())
                .append(']')
                .toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        //noinspection unchecked
        ArrayList<E> arrayList = (ArrayList<E>) object;

        if (size != arrayList.size) {
            return false;
        }

        boolean hasEqual = true;

        for (int i = 0; i < size; i++) {
            if (!items[i].equals(arrayList.get(i))) {
                return false;
            }
        }

        return hasEqual;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (E item : items) {
            hash += prime * hash + Double.hashCode((Double) item);
        }

        return hash;
    }

    // Указано, что данный метод реализовывать не нужно
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    // Указано, что данный метод реализовывать не нужно
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    // Указано, что данный метод реализовывать не нужно
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}