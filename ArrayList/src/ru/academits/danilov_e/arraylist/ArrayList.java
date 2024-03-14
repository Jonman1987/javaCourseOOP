package ru.academits.danilov_e.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    public class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        int countChanger = size();

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (size() != countChanger) {
                throw new ConcurrentModificationException("Array has been changed");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("The next element is null");
            }

            ++currentIndex;
            return items[currentIndex];
        }

        public E current() { // Я так понимаю можно обойтись без этого метода, я написал его, чтобы отслеживать перемещение итератора
            return items[currentIndex];
        }
    }

    private E[] items;
    private int size;

    public ArrayList() {
        items = (E[]) new Object[10];
        size = 0;
    }

    public ArrayList(ArrayList<E> arrayList) {
        items = arrayList.items;
        size = arrayList.size;
    }

    public ArrayList(int capacity) {
        if (capacity < 10 || capacity % 10 != 0) {
            throw new IllegalArgumentException("Capacity must be greater than 10 and a multiple of 10. Capacity is "
                    + capacity + ".");
        }

        items = (E[]) new Object[capacity];
        size = 0;
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

    public int getArrayLength() { // Данный метод использовал для отладки, чтобы смотреть, как динамически изменяется
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
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public ArrayListIterator iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() { // Если честно я пытался делать cast в E[] и преобразовывать
        // Object[] в String[], но у меня не получилось. Если можно поясните как работать в этим методом public Object[] toArray()
        Object[] objects = new Object[size];

        System.arraycopy(items, 0, objects, 0, size);

        return objects;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] array;

        if(size + a.length < items.length){
            array = (T[]) new Object[items.length];
        }else if(size + a.length < a.length ){
            array = (T[]) new Object[a.length];
        }else {
            array = (T[]) new Object[a.length + items.length];
        }

        for(int i = 0; i < size + array.length; i++){
            if(i < size){
                array[i] = (T) items[i];
            }else{

            }
        }

        return a;
    }

    @Override
    public boolean add(E element) {
        if (items.length - 1 == size) {
            ensureCapacity(items.length * 2);
        }

        items[size] = element;
        int oldSize = size;
        size++;

        return items[size] == element && size - oldSize == 1;
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
            } else if (index == size - 1) {
                items[size - 1] = null;
                isChanged = true;
                size--;
            } else if (index != -1) {
                System.arraycopy(array, 0, items, 0, index);

                if (size - 1 - index >= 0) System.arraycopy(array, index + 1, items, index, size - 1 - index);

                items[size - 1] = null;
                isChanged = true;
                size--;
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
        int matchCount = 0;

        while (true) {
            if (c.contains(iterator.next())) {
                matchCount++;
            } else {
                if (!iterator.hasNext()) {
                    break;
                }
            }
        }

        return matchCount == c.size();
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
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (size)
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
            E element = iterator.next();

            if (contains(element)) {
                arrayList.add(element);
                isHasRemoved = true;
            }
        } while (iterator.hasNext());

        if(isHasRemoved){
            items = arrayList.items;
            size = arrayList.size;
        }

        return isHasRemoved;
    }

    @Override
    public void clear() {
        items = (E[]) new Object[10];
        size = 0;
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
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (size - 1)
                    + "]. Index is " + index + ".");
        }

        E oldElement = items[index];
        items[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (size)
                    + "]. Index is " + index + ".");
        }

        if (items.length - 1 == size) {
            ensureCapacity(items.length * 2);
        }

        E[] array = (E[]) new Object[size];

        System.arraycopy(items, 0, array, 0, size);

        if (index == 0) {
            items[0] = element;

            System.arraycopy(array, 0, items, 1, size);
        } else if (index == size) {
            System.arraycopy(array, 0, items, 0, index);

            items[index] = element;
        } else {
            System.arraycopy(array, 0, items, 0, index);

            items[index] = element;

            System.arraycopy(array, index, items, index + 1, size - index);
        }

        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (size - 1)
                    + "]. Index is " + index + ".");
        }

        E element;
        E[] array = (E[]) new Object[size];
        System.arraycopy(items, 0, array, 0, size);
        element = items[index];

        if (index == 0) {
            System.arraycopy(array, 1, items, 0, size - 1);

            items[size - 1] = null;
            size--;
        } else if (index == size - 1) {
            items[size - 1] = null;
            size--;
        } else {
            System.arraycopy(array, 0, items, 0, index);

            if (size - 1 - index >= 0) System.arraycopy(array, index + 1, items, index, size - 1 - index);

            items[size - 1] = null;
            size--;
        }

        return element;
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

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (Object item : items) {
            if (item != null) {
                stringBuilder.append(item).append(", ");
            }
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1).append(']').toString();
    }
}
