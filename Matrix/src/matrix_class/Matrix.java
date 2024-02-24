package matrix_class;
import ru.academits.danilov_e.vector.Vector;

public class Matrix extends Vector{
    private int n;
    private int m;
    Vector[] vectors;
    public Matrix(int n, int m){
        super(m);
        vectors = new Vector[n];
        this.n = n;
        this.m = m;

        for(int i = 0; i < n; i++){
            vectors[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix){
        super(matrix.m);
        this.n = matrix.n;
        this.m = matrix.m;
        this.vectors = matrix.vectors;
    }

    public Matrix(double[][] doubleArray){
        super(doubleArray[0].length, doubleArray[0]);
        int maxVectorSize = doubleArray[0].length;

        for(int i = 1; i < doubleArray.length; i++){
            if(maxVectorSize < doubleArray[i].length){
                maxVectorSize = doubleArray[i].length;
            }
        }

        this.m = maxVectorSize;
        this.n = doubleArray.length;

        vectors = new Vector[m];

        for(int i = 0; i < doubleArray.length; i++){
            vectors[i] = new Vector(m, doubleArray[i]);
        }
    }

    public Matrix(Vector[] vector){
        super(vector.length);
        this.n = vector.length;
        int maxVectorSize = vector[0].getSize();

        for(int i = 1; i < vector.length; i++){
            if(maxVectorSize < vector[i].getSize()){
                maxVectorSize = vector[i].getSize();
            }
        }

        this.m = maxVectorSize;
        vectors = new Vector[n];

        for(int i = 0; i < vector.length; i++){
            double[] array = new double[m];

            for (int j = 0; j < vector.length; j++){
                array[j] = vector[i].getComponent(j);
            }

            vectors[i] = new Vector(m, array);
        }
    }

    public void print(){
        for (Vector vector : vectors) {
            System.out.println(vector);
        }
    }

    public int getWidth(){
        return m;
    }

    public int getHeight(){
        return n;
    }

    public Vector getVector(int index){
        return vectors[index];
    }

    public void setVector(int index, Vector vector){
        vectors[index] = vector;
    }

    public Vector getColumnVector(int index){
        double[] temp = new double[n];

        for(int i = 0; i < n; i++){
            temp[i] = vectors[i].getComponent(index);
        }

        return new Vector(temp);
    }

    public void transposition(){
        int maxVectorSize = vectors[0].getSize();

        for(int i = 1; i < vectors.length; i++){
            if(maxVectorSize < vectors[i].getSize()){
                maxVectorSize = vectors[i].getSize();
            }
        }

        Vector[] temp = new Vector[maxVectorSize];

        for(int i = 0; i < maxVectorSize; i++){
                temp[i] = this.getColumnVector(i);
        }

        vectors = temp;
    }

    public void makeMultiplication(double number){
        for(int i = 0; i < n; i++){
            vectors[i].multiply(number);
        }
    }

    public double matrixDeterminant(){ // Не доделано
        final double EPSILON = 1.0e-10;

        if(n != m){
            throw new IllegalArgumentException("n must be equal m");
        }

        if(n == 1){
            return vectors[0].getComponent(0);
        }

        if(n == 2){
            return vectors[0].getComponent(0) * vectors[1].getComponent(1)
                    - vectors[0].getComponent(0) * vectors[0].getComponent(1);
        }else{
            double determinant;

        Matrix temp = new Matrix(vectors);

        while (temp.n > 2){

        }

        }
        return 1;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("{");

        for(int i = 0; i < n; i++){
            stringBuilder.append(vectors[i].toString());

            if(i != n - 1){
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void makeMultiplication(Vector vector){

    }
}

