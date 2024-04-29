package ru.academits.danilov_e.lambda;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaMain {
    public static void main(String[] args) {
        List<Person> personsList = List.of(new Person[]{new Person("Иван", 18),
                new Person("Анна", 23), new Person("Иван", 33), new Person("Евгений", 12),
                new Person("Анна", 17), new Person("Игорь", 72), new Person("Татьяна", 21)});

        System.out.println("Исходный список имен:");
        personsList
                .forEach(p -> System.out.println(p.getName() + " " + p.getAge())); // Можно было выдать список
        // через System.out.println(personsList), но я оставил лямбду.

        System.out.println("1. Получить список уникальных имен:");
        List<String> uniqueNames = personsList
                .stream()
                .map(Person::getName)
                .distinct()
                .toList();
        System.out.println(uniqueNames);
        System.out.println();

        System.out.println("2. Получить список уникальных имен в формате Имена: Иван, Сергей, Петр:");
        String uniqueNamesLine = personsList
                .stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesLine);
        System.out.println();

        System.out.println("3. Получить список людей младше 18:");
        System.out.println("Список людей:");
        List<Person> peoplesList = personsList
                .stream()
                .filter(p -> (p.getAge() < 18))
                .toList();

        peoplesList.forEach(p -> {
            System.out.println(p.getName() + " " + p.getAge());
        });
        System.out.println();

        System.out.println("Их средний возраст:");
        OptionalDouble averageAge = peoplesList.stream()
                .mapToInt(Person::getAge)
                .average();

        averageAge.stream().forEach(value -> {
            if (averageAge.isPresent()) {
                System.out.println(averageAge.getAsDouble());
            } else {
                System.out.println("Ошибка отображения OptionalDouble.");
            }
        });
        System.out.println();

        System.out.println("4. При помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст:");
        Map<String, List<Person>> peoplesAverageAges = personsList
                .stream()
                .collect(Collectors.groupingBy(Person::getName));

        peoplesAverageAges.forEach((name, g) -> {
            OptionalDouble averageAges = g.stream().mapToInt(Person::getAge).average();

            if (averageAges.isPresent()) {
                System.out.printf("Имя: %s, Возраст: %s%n", name, averageAges.getAsDouble());
            } else {
                System.out.println("Ошибка отображения OptionalDouble.");
            }
        });
        System.out.println();

        System.out.println("5. Получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста:");
        Stream<Person> ageSample = personsList
                .stream()
                .filter(p -> (p.getAge() >= 20 && p.getAge() <= 45));

        ageSample
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
    }
}