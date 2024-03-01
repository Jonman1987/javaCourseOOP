package ru.academits.danilov_e.list_main;


import ru.academits.danilov_e.list.Node;
import ru.academits.danilov_e.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        System.out.println("1. Создаем List:");
        SinglyLinkedList<String> colorsList = new SinglyLinkedList<>(new Node<>("Синий", null), // По моему,
                // тут вы говорили, что нужно сделать unchecked warnings?
                new Node<>("Красный", null), new Node<>("Черный", null));
        System.out.println(colorsList);
        System.out.println();

        System.out.println("2. Получаем значение размера списка:");
        System.out.println("Размер списка: " + colorsList.getCount());
        System.out.println();

        System.out.println("3. Получаем значение узла первого элемента:");
        System.out.println("Значение: " + colorsList.getFirst());
        System.out.println();

        System.out.println("4. Получаем значение узла по номеру index:");
        System.out.println("Значение: " + colorsList.getData(3));
        System.out.println();

        System.out.println("5. Изменяем значение узла по номеру index:");
        System.out.println("Возвращаемое значение старого значения: " + colorsList.setData(2, "Зеленый"));
        System.out.println("Новое значение: " + colorsList.getData(2));
        System.out.println();
    }
}
