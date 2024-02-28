package ru.academits.danilov_e.list_main;


import ru.academits.danilov_e.list.List;
import ru.academits.danilov_e.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        System.out.println("1. Создаем пустой List:");
        SinglyLinkedList<String> stringList = new SinglyLinkedList<>(new List<>("Привет", null));
    }
}
