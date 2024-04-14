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

    public int compare(Integer age) {
        if(getAge() < age){
            return -1;
        }else if(getAge() > age){
            return 1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Data equals method");
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        return this.age == ((Person) obj).age && this.name.equals(((Person) obj).name);
    }
}
