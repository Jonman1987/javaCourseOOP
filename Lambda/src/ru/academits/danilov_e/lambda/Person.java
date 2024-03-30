package ru.academits.danilov_e.lambda;

public class Person {
    String name;
    int age;

    public Person(String name, int age){
        if(age < 0 || age > 130){
            throw new IllegalArgumentException("Age must be in range [0; 130]. Current age is " + age + ".");
        }

        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        if(age < 0 || age > 130){
            throw new IllegalArgumentException("Age must be in range [0; 130]. Current age is " + age + ".");
        }

        this.age = age;
    }

    @Override
    public String toString(){
        return name + " " + age;
    }
}
