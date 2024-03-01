package ru.academits.danilov_e.list_main;


import ru.academits.danilov_e.list.Node;
import ru.academits.danilov_e.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        System.out.println("1. Создаем List:");
        System.out.println("Конструктор на массиве:");
        // По моему, тут вы говорили, что нужно сделать unchecked warnings?
        SinglyLinkedList<String> colorsList1 = new SinglyLinkedList<>(new Node[]{new Node<>("Синий", null),
                new Node<>("Красный", null), new Node<>("Черный", null)});
        System.out.println(colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();
        System.out.println("Одиночный конструктор:");
        SinglyLinkedList<String> colorsList2 = new SinglyLinkedList<>(new Node<>("Бирюзовый", null));
        System.out.println(colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println();

        System.out.println("2. Получаем значение размера списка:");
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println();

        System.out.println("3. Получаем значение узла первого элемента:");
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Значение: " + colorsList1.getFirst());
        System.out.println();
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Значение: " + colorsList2.getFirst());
        System.out.println();

        System.out.println("4. Получаем значение узла по номеру index:");
        int index1 = 3;
        int index2 = 2;
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Индекс узла: " + index1);
        System.out.println("Значение: " + colorsList1.getData(index1));
        System.out.println("Индекс узла: " + index2);
        System.out.println("Значение: " + colorsList1.getData(index2));
        System.out.println();

        System.out.println("5. Изменяем значение узла по номеру index:");
        int index3 = 2;
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println("Индекс узла: " + index3);
        System.out.println("Возвращаемое значение старого узла: " + colorsList1.setData(index3, "Зеленый"));
        System.out.println("Новое значение: " + colorsList1.getData(index3));
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();

        System.out.println("6. Удаление узла по номеру index:");
        int index4 = 2;
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Индекс узла: " + index4);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println("Возвращаемое значение удаляемого узла: " + colorsList1.delete(index4));
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();

        System.out.println("7. Вставка элемента в начало:");
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        colorsList1.inputFirst(new Node<>("Пурпурный", null));
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();

        System.out.println("8. Вставка элемента по номеру index:");
        int index5 = 2;
        Node<String> newNode = new Node<>("Бордовый", null);
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Индекс узла: " + index5);
        System.out.println("Размер списка: " + colorsList1.getCount());
        colorsList1.input(index5, newNode);
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();

        System.out.println("9. Удаление узла по значению:");
        String string1 = "Синий";
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println("Искомое удаляемое значение: " + string1);
        System.out.println("Результат: " + colorsList1.deleteData(string1));
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();

        System.out.println("10. Удаление первого узла:");
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println("Возвращаемое значение удаляемого узла: " + colorsList1.deleteFirst());
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();
    }
}
