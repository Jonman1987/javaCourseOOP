package ru.academits.danilov_e.list;

public class List<T> {
    private T data;
    private List<T> next;

    public List(T data, List<T> next){
        this.data = data;
        this.next = next;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public List<T> getNext(){
        return next;
    }

    public void setNext(List<T> next){
        this.next = next;
    }
}
