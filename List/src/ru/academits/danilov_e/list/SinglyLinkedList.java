package ru.academits.danilov_e.list;

public class SinglyLinkedList <T>{
    private List<T> head;
    private int count;

    public SinglyLinkedList(List<T> head){
        this.head = head;
        count = 1;
    }

    public int getCount(){
        return count;
    }

    public List<T> getFirst(){
        return head;
    }
}
