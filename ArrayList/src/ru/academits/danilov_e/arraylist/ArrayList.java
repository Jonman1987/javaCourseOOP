package ru.academits.danilov_e.arraylist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {
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

    public void trimToSize() {

    }

    public int getArrayLength(){ // Данный метод использовал для отладки, чтобы смотреть, как динамически изменяется
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
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E element) {
        if(items.length - 1 == size){
            ensureCapacity(items.length * 2);
        }

        items[size] = element;
        int oldSize = size;
        size++;

        return items[size] == element && size - oldSize == 1;
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

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element){
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException("Index must belong to the range [0; " + (items.length - 1)
                    + "]. Index is " + index + ".");
        }

        if(items.length - 1 == size){
            ensureCapacity(items.length * 2);
        }

        E[] array = items;

        if(index == 0){
            items[0] = element;

            for (int i = 0; i < size; i++){
                items[i + 1] = array[i];
            }
        }else if(index == size){
            for (int i = 0; i < index; i++){
                items[i + 1] = array[i];
            }

            items[index] = element;
        }else {
            for (int i = 0; i < index; i++){
                items[i + 1] = array[i];
            }

            items[index] = element;

            for (int i = index; i < size; i++){
                items[i + 1] = array[i];
            }
        }

        size++;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
    public String toString(){
        if(size == 0){
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (Object item : items) {
            if(item != null){
                stringBuilder.append(item).append(", ");
            }
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1).append(']').toString();
    }
}
