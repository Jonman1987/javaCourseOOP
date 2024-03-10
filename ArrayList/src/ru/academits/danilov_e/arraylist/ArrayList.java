package ru.academits.danilov_e.arraylist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {
    public class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
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
        items = (E[]) new Object[capacity];
        size = 0;
    }

    public void ensureCapacity(int capacity) {
        E[] array = (E[]) new Object[capacity];

        System.arraycopy(items, 0, array, 0, items.length);

        items = array;
    }

    public void trimToSize(int capacity) {
        if(items.length / size >= 2 && items.length != 10){
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

        while (true){
            System.arraycopy(items, 0, array, 0, size);

            int index = this.indexOf(o);

            if(index == 0){
                if (size - 1 >= 0) System.arraycopy(array, 1, items, 0, size - 1);

                items[size - 1] = null;
                isChanged = true;
                size--;
            }else if(index == size - 1){
                items[size - 1] = null;
                isChanged = true;
                size--;
            }else if(index != -1) {
                System.arraycopy(array, 0, items, 0, index);

                if (size - 1 - index >= 0) System.arraycopy(array, index + 1, items, index, size - 1 - index);

                items[size - 1] = null;
                isChanged = true;
                size--;
            }

            if(index == -1 && isChanged){
                trimToSize(items.length / 2);

                return true;
            }else if(index == -1){
                break;
            }
        }


        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
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
        items = (E[]) new Object[10];
        size = 0;
    }

    @Override
    public E get(int index) {
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        // Дописать исключение
        E oldElement = items[index];
        items[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (items.length - 1)
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
        return null;
    }

    @Override
    public int indexOf(Object o) { // Не совсем понял как поступить в случае если элемент не найден
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
    public int lastIndexOf(Object o) { // Не совсем понял как поступить в случае если элемент не найден
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                index = i;
            }
        }

        if (index == -1) {
            System.out.println("Element not found");
        }

        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
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
