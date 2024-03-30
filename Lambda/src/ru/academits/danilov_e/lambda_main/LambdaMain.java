package ru.academits.danilov_e.lambda_main;

import ru.academits.danilov_e.lambda.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LambdaMain {
    public static void main(String[] args) {
        Person person1 = new Person("Иван", 18);
        Person person2 = new Person("Анна", 23);
        Person person3 = new Person("Сергей", 44);
        Person person4 = new Person("Петр", 15);
        Person person5 = new Person("Ирина", 28);
        Person person6 = new Person("Иван", 24);
        Person person7 = new Person("Евгений", 28);
        Person person8 = new Person("Сергей", 28);
        Person person9 = new Person("Анастасия", 56);
        Person person10 = new Person("Георгий", 20);
        Person person11 = new Person("Анна", 20);
        Person person12 = new Person("Иван", 33);
        Person person13 = new Person("Евгений", 37);
        Person person14 = new Person("Татьяна", 18);
        Person person15 = new Person("Татьяна", 21);
        Person person16 = new Person("Игорь", 72);
        Person person17 = new Person("Ева", 5);
        Person person18 = new Person("Дмитрий", 33);
        Person person19 = new Person("Иван", 40);
        Person person20 = new Person("Анна", 17);

        Person[] personArray = {person1, person2, person3, person4, person5, person6, person7, person8, person9, person10,
                person11, person12, person13, person14, person15, person16, person17, person18, person19, person20};

        List<Person> personList = new ArrayList<>(List.of(personArray));

        personList.forEach(person -> System.out.println(person.getName() + " " + person.getAge()));
        System.out.println();

        System.out.println("Список людей младше 18:");
        List<Person> sor = personList.stream().filter(person -> (person.getAge() < 18)).toList();
        sor.forEach(person -> System.out.println(person.getName() + " " + person.getAge()));
        System.out.println();

        System.out.println("Их средний возраст:");

    }
}