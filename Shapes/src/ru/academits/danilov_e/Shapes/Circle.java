package ru.academits.danilov_e.Shapes;

public class Circle implements Shape, Comparable<Shape> {
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }
    public double getWidth(){
        return 2 * radius;
    }

    public double getHeight(){
        return 2 * radius;
    }

    public double getArea(){
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter(){
        return 2 * Math.PI * radius;
    }

    public int compareTo(Shape figure){
        if(this.getArea() < figure.getArea()){
            return -1;
        }else if(this.getArea() == figure.getArea()){
            return 0;
        }

        return 1;
    }
}
