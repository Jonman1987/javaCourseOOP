package ru.academits.danilov_e.lambda;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class LambdaMain {
    public static void main(String[] args) {
        List<Person> personsList = List.of(
                new Person("Иван", 18),
                new Person("Анна", 23),
                new Person("Иван", 33),
                new Person("Евгений", 12),
                new Person("Анна", 17),
                new Person("Игорь", 72),
                new Person("Татьяна", 21)
        );

        System.out.println("Исходный список типа Person:");
        personsList.forEach(System.out::println);
        System.out.println();

        System.out.println("1. Получить список уникальных имен:");
        List<String> uniqueNamesList = personsList.stream()
                .map(Person::getName)
                .distinct()
                .toList();
        System.out.println(uniqueNamesList);
        System.out.println();

        System.out.println("2. Вывести список уникальных имен в формате Имена: Иван, Сергей, Петр:");
        String uniqueNamesString = uniqueNamesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesString);
        System.out.println();

        System.out.println("3. Получить список людей младше 18:");
        System.out.println("Список людей:");
        List<Person> minorPersonsList = personsList.stream()
                .filter(p -> p.getAge() < 18)
                .toList();

        minorPersonsList.forEach(System.out::println);

        OptionalDouble minorAverageAge = minorPersonsList.stream()
                .mapToInt(Person::getAge)
                .average();

        if (minorAverageAge.isPresent()) {
            System.out.println("Их средний возраст: " + minorAverageAge.getAsDouble() + ".");
        } else {
            System.out.println("Невозможно вычислить средний возраст, так как в списке отсутствуют несовершеннолетние персоны.");
        }

        System.out.println();

        System.out.println("4. При помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст:");
        Map<String, Double> personsAverageAgesList = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println(personsAverageAgesList);
        System.out.println();

        System.out.println("5. Получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста:");
        personsList.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .forEach(p -> System.out.println(p.getName()));
    }
}