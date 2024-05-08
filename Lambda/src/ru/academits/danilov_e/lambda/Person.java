package ru.academits.danilov_e.lambda;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be greater than 0 or equal.");
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be greater than 0 or equal.");
        }

        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}
