package ru.academits.danilov_e.list_draft;

public class ListDraft {

    private static final int arrayDimension = 10;
    private Object[] items = new Object[arrayDimension];
    private int size;

    public ListDraft() {
        size = 0;
    }

    public ListDraft(Object... object){
        while (object.length > items.length){
            items = new Object[arrayDimension + 10];
        }

        System.arraycopy(object, 0, items, 0, object.length);

        size = object.length;
    }

    public int getSize() {
        return size;
    }

    public int getArrayLength(){ // Данный метод использовал для отладки, чтобы смотреть, как динамически изменяется
        // размер массива. В дальнейшем его можно удалить.
        return items.length;
    }

    public void setArrayLength(int size){ // Данный метод использовал для отладки, чтобы задавать ситуации, когда
        // массив сначала разрастается, а затем сильно сокращается и была возможность отследить сокращение массива.
        // В дальнейшем его можно удалить.
        Object[] objects = new Object[size];

        System.arraycopy(items, 0, objects, 0, items.length);

        items = objects;
    }

    public Object getFirst() {
        if (size == 0) {
            throw new NullPointerException("List is empty");
        }

        return items[0];
    }

    public Object get(int index) {
        if (size == 0) {
            throw new NullPointerException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid value of index = " + index + ". List length is " + size + ".");
        }

        return items[index];
    }

    public Object set(int index, Object object) { // Я так понял, что в этом методе я должен возвращать старое значение
        if (size == 0) {
            throw new NullPointerException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid value of index = " + index + ". List length is " + size + ".");
        }

        Object listObject = items[index];
        items[index] = object;

        return listObject;
    }

    public Object remove(int index) {
        if (size == 0) {
            throw new NullPointerException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid value of index = " + index + ". List length is " + size + ".");
        }

        Object removedObject = items[index];

        if (index == items.length - 1) { // Перепроверить
            items[index] = null;
            size--;

            return removedObject;
        }

        for (int i = index; i < items.length - 1; i++) {
            items[i] = items[i + 1];
        }

        items[items.length - 1] = null;
        size--;

        if (size != 0 && (items.length - size >= 10)) {
            Object[] smallerItems = new Object[items.length - 10];
            int j = 0;

            for (Object item : items) {
                if (item != null) {
                    smallerItems[j] = item;
                    j++;
                }
            }

            items = smallerItems;
        }

        return removedObject;
    }

    public void addFirst(Object object) {
        Object[] objectArray;

        if (size == items.length - 1) {
            objectArray = new Object[arrayDimension + 10];
        } else {
            objectArray = new Object[items.length];
        }

        objectArray[0] = object;
        System.arraycopy(items, 0, objectArray, 1, items.length + 1 - 1);
        items = objectArray;
        size++;
    }

    public void add(int index, Object object) {
        if (index < 0 || index >= items.length) {
            throw new IllegalArgumentException("Invalid value of index = " + index + ". List length is " + size + ".");
        }

        Object[] objectArray;

        if (size == items.length - 1) {
            objectArray = new Object[arrayDimension + 10];
        } else {
            objectArray = new Object[items.length];
        }

        objectArray[index] = object;
        for(int i = 0; i < items.length + 1; i++){
            if(i == index){
                objectArray[i] = object;
            }else if(i > index){

            }
        }

        items = objectArray;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("[");

        for (Object item : items) {
            if(item != null){
                stringBuilder.append(item).append(", ");
            }
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1).append(']').toString();
    }
}
