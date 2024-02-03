package ru.academits.danilov_e.Shapes;

public class Rectangle implements Shape, Comparable<Shape>{
    private double width;
    private double height;

    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }
    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public double getArea(){
        return width * height;
    }

    public double getPerimeter(){
        return 2 * (width + height);
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
