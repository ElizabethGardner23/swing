package com.eonsahead.swing;

/**
 * Model and perform operations on a 4x4 matrix.
 *
 * @author Elizabeth Gardner
 * @version 3 April 2020
 */
public class Matrix {

    private final double[][] elements;

    public Matrix() {
        this.elements = new double[4][4];
        this.identity();
    } // Matrix()

    public double get(int row, int column) {
        return this.elements[row][column];
    } // get(int, int)

    public void set(int row, int column, double value) {
        this.elements[row][column] = value;
    } // set(int, int, double)

    public final void identity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    this.set(i, j, 1.0);
                } // if
                else {
                    this.set(i, j, 0.0);
                } // else
            } // for
        } // for
    } // identity()

    public void rotationX(double angle) {
        this.identity();
        this.set(1, 1, Math.cos(angle));
        this.set(1, 2, -Math.sin(angle));
        this.set(2, 1, Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationX(double)
    
    public void rotationY(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 2, Math.sin(angle));
        this.set(2, 0, -Math.sin(angle));
        this.set(2, 2, Math.cos(angle));   
    } // rotationY(double)
    
    public void rotationZ(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 1, -Math.sin(angle));
        this.set(1, 0, Math.sin(angle));
        this.set(1, 1, Math.cos(angle));
    } // rotationZ(double)
    
    public void scale(double xFactor, double yFactor, double zFactor) {
        this.elements[0][0] *= xFactor;
        this.elements[1][1] *= yFactor;
        this.elements[2][2] *= zFactor;
    } // scale(double, double, double)
    
    public void translate(double xFactor, double yFactor, double zFactor) {
        this.elements[0][3] *= xFactor;
        this.elements[1][3] *= yFactor;
        this.elements[2][3] *= zFactor;
    } // translate(double, double, double)

    public Matrix multiply(Matrix otherMatrix) {
        Matrix product = new Matrix();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                double sum = 0.0;
                for (int k = 0; k < 4; k++) {
                    sum += this.elements[row][k]
                            * otherMatrix.elements[k][column];
                } // for
                product.set(row, column, sum);
            } // for
        } // for
        return product;
    } // multiply(Matrix)
    
        public String multiply(Vector vector) {
        double matrix00 = this.elements[0][0];
        double matrix01 = this.elements[0][1];
        double matrix02 = this.elements[0][2];
        double matrix03 = this.elements[0][3];
        double matrix10 = this.elements[1][0];
        double matrix11 = this.elements[1][1];
        double matrix12 = this.elements[1][2];
        double matrix13 = this.elements[1][3];
        double matrix20 = this.elements[2][0];
        double matrix21 = this.elements[2][1];
        double matrix22 = this.elements[2][2];
        double matrix23 = this.elements[2][3];
        double matrix30 = this.elements[3][0];
        double matrix31 = this.elements[3][1];
        double matrix32 = this.elements[3][2];
        double matrix33 = this.elements[3][3];
        double vector0 = vector.getX();
        double vector1 = vector.getY();
        double vector2 = vector.getZ();
        double vector3 = vector.getH();
        double p0 = (matrix00 * vector0) + (matrix01 * vector1) + 
                (matrix02 * vector2) + (matrix03 * vector3);
        double p1 = (matrix10 * vector0) + (matrix11 * vector1) + 
                (matrix12 * vector2) + (matrix13 * vector3);
        double p2 = (matrix20 * vector0) + (matrix21 * vector1) + 
                (matrix22 * vector2) + (matrix23 * vector3);
        double p3 = (matrix30 * vector0) + (matrix31 * vector1) + 
                (matrix32 * vector2) + (matrix33 * vector3);
        return "[(" + p0 + "), (" + p1 + "), (" + p2 + "), (" + p3 + ")]";
    } // multiply(Vector4D)

    private String rowToString(int row) {
        StringBuilder result = new StringBuilder();
        result.append("( ");
        for (int i = 0; i < 3; i++) {
            result.append(this.get( row, i));
            result.append(",");
        } // for
        result.append(this.get( row, 3 ));
        result.append(" )");
        return result.toString();
    } // rowToString(int)

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for (int i = 0; i < 4; i++) {
            result.append(rowToString(i));
        } //
        result.append(" ]");
        return result.toString();
    } // toString()

    public static void main(String[] args) {
        Matrix identity = new Matrix();
        System.out.println("identity = " + identity);

        Matrix product = identity.multiply(identity);
        System.out.println("product = " + product);

        Matrix ccw = new Matrix();
        ccw.rotationZ(Math.PI / 4);
        System.out.println("counter-clockwise rotation = " + ccw);

        Matrix cw = new Matrix();
        cw.rotationZ(-Math.PI / 4);
        System.out.println("clockwise rotation = " + cw);

        Matrix netRotation = ccw.multiply(cw);
        System.out.println("net rotation = " + netRotation);
    } // main(String [])
} // Matrix
