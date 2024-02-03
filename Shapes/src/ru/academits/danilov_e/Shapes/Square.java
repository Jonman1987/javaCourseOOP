package ru.academits.danilov_e.Shapes;

public class Square implements Shape, Comparable<Shape>{
    private double sideSize;

    public Square(double sideSize){
        this.sideSize = sideSize;
    }

    public double getWidth(){
        return sideSize;
    }

    public double getHeight(){
        return sideSize;
    }

    public double getArea(){
        return sideSize * sideSize;
    }

    public double getPerimeter(){
        return 4 * sideSize;
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
