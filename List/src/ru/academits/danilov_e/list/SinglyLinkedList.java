package ru.academits.danilov_e.list;

public class SinglyLinkedList <T>{
    private final Node<T> head;
    private int count;

    public SinglyLinkedList(Node<T> head){
        this.head = head;
        count = 1;
    }

    public SinglyLinkedList(Node<T>... head){
        this.head = head[0];
        count = 1;
        this.head.setNext(head[1]);

        int i;
        Node<T> currentElement;

        for(currentElement = this.head.getNext(), i = 2; i < head.length; i++){
            currentElement.setNext(head[i]);
            count++;
            currentElement = currentElement.getNext();
        }
    }

    public int getCount(){
        return count;
    }

    public T getFirst(){
        return head.getData();
    }

    public T getData(int index){
        T data = null;
        int i = 1;
        Node<T> currentElement;

        for(currentElement = head; currentElement != null; currentElement = currentElement.getNext()){
            if(i == index){
                data = currentElement.getData();
            }

            i++;
        }

        return data;
    }

    public T setData(int index, T data){
        T oldValue = null;
        int i = 1;
        Node<T> currentNode;

        for(currentNode = head; currentNode != null; currentNode = currentNode.getNext()){
            if(i == index){
                oldValue = currentNode.getData();
                currentNode.setData(data);
            }

            i++;
        }

        return oldValue;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("[");
        Node<T> currentNode;

        for(currentNode = head; currentNode != null; currentNode = currentNode.getNext()){
            stringBuilder.append(currentNode.getData().toString());
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']');

        return stringBuilder.toString();
    }
}
