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
        if (capacity < 10 || capacity % 10 != 0) {
            throw new IllegalArgumentException("Capacity must be greater than 10 and a multiple of 10. Capacity is "
                    + capacity + ".");
        }

        items = (E[]) new Object[capacity];
    }

    private void ensureCapacity(int capacity) {
        E[] array = (E[]) new Object[capacity];

        System.arraycopy(items, 0, array, 0, items.length);

        items = array;
    }

    private void trimToSize(int capacity) { // Я не стал делать исключения для capacity так как пользователь напрямую
        // не работает со значением capacity
        if (items.length / size >= 2 && items.length != 10) {
            E[] array = (E[]) new Object[capacity];

            if (size >= 0) System.arraycopy(items, 0, array, 0, size);

            items = array;
        }
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
    public boolean contains(Object o) { // TODO: Не разобрался с "упадет на элементе списка со значением null".
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

    @Override
    public boolean add(E item) {
        if (items.length - 1 == size) {
            ensureCapacity(items.length * 2);
        }

        items[size] = item;
        int oldSize = size;
        size++;
        modificationsCount++;

        return items[size] == item && size - oldSize == 1;
    }

    @Override
    public boolean remove(Object o) {
        E[] array = (E[]) new Object[size];

        boolean isChanged = false;

        while (true) {
            System.arraycopy(items, 0, array, 0, size);

            int index = this.indexOf(o);

            if (index == 0) {
                if (size - 1 >= 0) System.arraycopy(array, 1, items, 0, size - 1);

                items[size - 1] = null;
                isChanged = true;
                size--;
                modificationsCount++;
            } else if (index == size - 1) {
                items[size - 1] = null;
                isChanged = true;
                size--;
                modificationsCount++;
            } else if (index != -1) {
                System.arraycopy(array, 0, items, 0, index);

                if (size - 1 - index >= 0) System.arraycopy(array, index + 1, items, index, size - 1 - index);

                items[size - 1] = null;
                isChanged = true;
                size--;
                modificationsCount++;
            }

            if (index == -1 && isChanged) {
                trimToSize(items.length / 2);

                return true;
            } else if (index == -1) {
                break;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        ArrayListIterator iterator = iterator();

        int matchesCount = 0;

        E item1 = iterator.next(); // Бывший elem1

        while (iterator.hasNext()) {
            Iterator<?> collectionIterator = c.iterator();
            Object item2 = collectionIterator.next(); // Бывший elem2
            iterator.next();

            while (collectionIterator.hasNext()){
                if(item1.equals(item2)){
                    matchesCount++;
                    break;
                }

                collectionIterator.next();
            }
        }

        return matchesCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        ArrayListIterator iterator = (ArrayListIterator) c.iterator();
        int addCount = 0;

        for (int i = 0; i < c.size(); i++) {
            add(i, iterator.next());
            addCount++;
        }

        return size - addCount == c.size();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + size
                    + "]. Index is " + index + ".");
        }

        ArrayListIterator iterator = (ArrayListIterator) c.iterator();
        int addCount = 0;
        int position = index;

        for (int i = 0; i < c.size(); i++) {
            add(position, iterator.next());
            addCount++;
            position++;
        }

        return size - addCount == c.size();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        ArrayListIterator iterator = (ArrayListIterator) c.iterator();
        int removedCount = 0;

        do {
            if (remove(iterator.next())) {
                removedCount++;
            }

        } while (iterator.hasNext());

        return removedCount > 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        ArrayListIterator iterator = (ArrayListIterator) c.iterator();
        boolean isHasRemoved = false;
        ArrayList<E> arrayList = new ArrayList<>();

        do {
            E item = iterator.next();

            if (contains(item)) {
                arrayList.add(item);
                isHasRemoved = true;
            }
        } while (iterator.hasNext());

        if (isHasRemoved) {
            items = arrayList.items;
            size = arrayList.size;
        }

        return isHasRemoved;
    }

    @Override
    public void clear() {
        items = (E[]) new Object[capacity];
        size = 0;
        modificationsCount++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (size - 1)
                    + "]. Index is " + index + ".");
        }

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (size - 1)
                    + "]. Index is " + index + ".");
        }

        E oldItem = items[index]; // Бывший oldElement
        items[index] = item;
        modificationsCount++;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + size
                    + "]. Index is " + index + ".");
        }

        if (items.length - 1 == size) {
            ensureCapacity(items.length * 2);
        }

        E[] array = (E[]) new Object[size];

        System.arraycopy(items, 0, array, 0, size);

        if (index == 0) {
            items[0] = item;

            System.arraycopy(array, 0, items, 1, size);
        } else if (index == size) {
            System.arraycopy(array, 0, items, 0, index);

            items[index] = item;
        } else {
            System.arraycopy(array, 0, items, 0, index);

            items[index] = item;

            System.arraycopy(array, index, items, index + 1, size - index);
        }

        size++;
        modificationsCount++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (size - 1)
                    + "]. Index is " + index + ".");
        }

        E item;
        E[] array = (E[]) new Object[size];
        System.arraycopy(items, 0, array, 0, size);
        item = items[index];

        if (index == 0) {
            System.arraycopy(array, 1, items, 0, size - 1);

            items[size - 1] = null;
            size--;
            modificationsCount++;
        } else if (index == size - 1) {
            items[size - 1] = null;
            size--;
            modificationsCount++;
        } else {
            System.arraycopy(array, 0, items, 0, index);

            if (size - 1 - index >= 0) System.arraycopy(array, index + 1, items, index, size - 1 - index);

            items[size - 1] = null;
            size--;
            modificationsCount++;
        }

        return item;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                index = i;
                break;
            }
        }

        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                index = i;
            }
        }

        return index;
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
