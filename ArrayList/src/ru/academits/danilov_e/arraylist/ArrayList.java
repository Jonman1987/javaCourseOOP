package ru.academits.danilov_e.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modificationsCount;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(Collection<E> collection) {
        items = (E[]) collection.toArray();
        size = collection.size();
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cant be less than 0. Capacity is "
                    + capacity + ".");
        }

        items = (E[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        if(size == 0){
            items = (E[]) new Object[capacity];
            return;
        }

        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize() {
        if(size == 0){
            items = (E[]) new Object[DEFAULT_CAPACITY];
            return;
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
            return (T[]) Arrays.copyOf((T[]) items, size, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, size);

        a[size] = null;

        return a;
    }

    private void increaseCapacity() {
        if (size == 0) {
            items = Arrays.copyOf(items, DEFAULT_CAPACITY);
            return;
        }

        items = Arrays.copyOf(items, size * 2);
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
        if (size < c.size()) {
            return false;
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

    private static void checkBounds(int index, int maxIndex) {
        if (index < 0 || index > maxIndex) {
            throw new IllegalArgumentException("Index must belong to the range [0; " + (maxIndex - 1) + "]. Index is "
                    + index + ".");
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        // TODO: неправильная логика увеличения вместимости.
        // TODO: Сейчас после увеличения вместимости может не хватить места
        // TODO: не всегда выдает верный boolean
        // TODO: modificationsCount меняется даже если это не нужно
        checkBounds(index, size);

        if (!isEmpty()) {
            System.arraycopy(items, index, items, index + c.size(), size - index);
        }

        size += c.size();

        if (size >= items.length) {
            increaseCapacity();
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
        // TODO: в текущей версии: здесь не нужно использовать indexOf, лучше использовать удаление по значению
        // TODO: лучше сделать примерно как retainAll
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

        Arrays.fill(items, null);

        size = 0;
        modificationsCount++;
    }

    @Override
    public E get(int index) {
        checkBounds(index, size - 1);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkBounds(index, size - 1);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        checkBounds(index, size);

        if (size >= items.length) {
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
        checkBounds(index, size - 1);

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