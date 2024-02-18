package shapes_class;

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

    public void setX(double x1, double x2, double x3){
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
    }

    public void setY(double y1, double y2, double y3){
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
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

    public double getPerimeter() {
        double sideA = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)); // Я так понял, что замечание №15 этой части не касалось
        double sideB = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
        double sideC = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));

        return sideA + sideB + sideC;
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

        return this.getWidth() == temp.getWidth() && this.getHeight() == temp.getHeight()
                && this.getArea() == temp.getArea() && this.getPerimeter() == temp.getPerimeter();
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
