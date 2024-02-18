package shapes_class;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideSize) {
        this.sideLength = sideSize;
    }

    public void setSideLength(double sideLength){
        this.sideLength = sideLength;
    }

    public double getSideLength(){
        return sideLength;
    }

    public double getWidth() {
        return sideLength;
    }

    public double getHeight() {
        return sideLength;
    }

    public double getArea() {
        return sideLength * sideLength;
    }

    public double getPerimeter() {
        return 4 * sideLength;
    }

    public String getName() {
        return "Квадрат";
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

        Square temp = (Square) object;

        return this.getWidth() == temp.getWidth() && this.getHeight() == temp.getHeight()
                && this.getArea() == temp.getArea() && this.getPerimeter() == temp.getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }
}
