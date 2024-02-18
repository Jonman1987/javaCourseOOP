package ru.academits.danilov_e.shapes;

public class Triangle implements Shape {
    private double x1;
    private double x2;
    private double x3;

    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;

        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public void setX(int xCount, double value){
        if(xCount == 1){
            x1 = value;
        }else if(xCount == 2){
            x2 = value;
        }else if(xCount == 3){
            x3 = value;
        }else {
            System.out.println("Введен не правильный номер координаты Х.");
        }
    }

    public void setY(int yCount, double value){
        if(yCount == 1){
            y1 = value;
        }else if(yCount == 2){
            y2 = value;
        }else if(yCount == 3){
            y3 = value;
        }else {
            System.out.println("Введен не правильный номер координаты Y.");
        }
    }

    public double getX(int xNumber){
        if(xNumber == 1){
            return x1;
        }

        if(xNumber == 2){
            return x2;
        }

        if(xNumber == 3){
            return x3;
        }

        return 0;
    }

    public double getY(int yNumber){
        if(yNumber == 1){
            return y1;
        }

        if(yNumber == 2){
            return y2;
        }

        if(yNumber == 3){
            return y3;
        }

        return 0;
    }

    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    public double getArea() {
        return (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2;
    }

    private double getSideLength(double xFrom, double yFrom, double xTo, double yTo){
        // Я так понял, что замечание №15 этой части не касалось
        return Math.sqrt(Math.pow((xTo - xFrom), 2) + Math.pow((yTo - yFrom), 2));
    }

    public double getPerimeter() {
        double sideALength = getSideLength(x1, y1, x2, y2);
        double sideBLength = getSideLength(x2, y2, x3, y3);
        double sideCLength = getSideLength(x3, y3, x1, y1);

        return sideALength + sideBLength + sideCLength;
    }

    public String getName() {
        return "Треугольник";
    }

    @Override
    public String toString() {
        return "Фигура: " + getName() + ", Площадь: " + getArea() + ", Периметр: " + getPerimeter();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Triangle temp = (Triangle) object;

        return getWidth() == temp.getWidth() && getHeight() == temp.getHeight()
                && getArea() == temp.getArea() && getPerimeter() == temp.getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}
