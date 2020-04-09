package com.eonsahead.swing;

/**
 * Model and perform operations on a 4x4 matrix, including rotating, scaling,
 * translating, and multiplying by another matrix and by a vector.
 *
 * @author Elizabeth Gardner
 * @version 7 April 2020
 */
public class Matrix {

    private final double[][] elements;

    /**
     * Matrix() is a constructor which creates a new identity matrix.
     */
    public Matrix() {
        this.elements = new double[4][4];
        this.identity();
    } // Matrix()

    /**
     * The get method returns the element in a designated position (row, column)
     * in a matrix.
     *
     * @param row the row of the matrix in which the desired element is located
     * @param column the column of the matrix in which the desired element is
     * located
     * @return the element in the designated row and column in the matrix
     */
    public double get(int row, int column) {
        return this.elements[row][column];
    } // get(int, int)

    /**
     * The set method sets the element at the designated position (row, column)
     * to the designated new value.
     *
     * @param row the row of the matrix in which the element to be changed is
     * located
     * @param column the column of the matrix in which the element to be changed
     * is located
     * @param value the new value that the element at the position [row][column]
     * in the matrix is to be set to
     */
    public void set(int row, int column, double value) {
        this.elements[row][column] = value;
    } // set(int, int, double)

    /**
     * The identity method produces the numbers needed for an identity matrix (a
     * matrix with all zeros except for ones on the diagonal).
     */
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

    /**
     * The rotationX method rotates a matrix a designated amount about the
     * x-axis.
     *
     * @param angle a measure of how far to rotate the matrix about the x-axis
     */
    public void rotationX(double angle) {
        this.identity();
        this.set(1, 1, Math.cos(angle));
        this.set(1, 2, -Math.sin(angle));
        this.set(2, 1, Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationX(double)

    /**
     * The rotationY method rotates a matrix a designated amount about the
     * y-axis.
     *
     * @param angle a measure of how far to rotate the matrix about the y-axis
     */
    public void rotationY(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 2, Math.sin(angle));
        this.set(2, 0, -Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationY(double)

    /**
     * The rotationZ method rotates a matrix a designated amount about the
     * z-axis.
     *
     * @param angle a measure of how far to rotate the matrix about the z-axis
     */
    public void rotationZ(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 1, -Math.sin(angle));
        this.set(1, 0, Math.sin(angle));
        this.set(1, 1, Math.cos(angle));
    } // rotationZ(double)

    /**
     * The scale method stretches or shrinks a matrix by a designated amount
     * along the x, y, and z axes.
     *
     * @param xFactor the amount that the matrix is stretched or shrunk along
     * the x-axis
     * @param yFactor the amount that the matrix is stretched or shrunk along
     * the y-axis
     * @param zFactor the amount that the matrix is stretched or shrunk along
     * the z-axis
     */
    public void scale(double xFactor, double yFactor, double zFactor) {
        this.elements[0][0] *= xFactor;
        this.elements[1][1] *= yFactor;
        this.elements[2][2] *= zFactor;
    } // scale(double, double, double)

    /**
     * The translate method moves a matrix by a designated amount along the x,
     * y, and z axes.
     *
     * @param xFactor the distance that the matrix is moved along the x-axis
     * @param yFactor the distance that the matrix is moved along the y-axis
     * @param zFactor the distance that the matrix is moved along the z-axis
     */
    public void translate(double xFactor, double yFactor, double zFactor) {
        this.elements[0][3] *= xFactor;
        this.elements[1][3] *= yFactor;
        this.elements[2][3] *= zFactor;
    } // translate(double, double, double)

    /**
     * The multiply(Matrix) method multiplies one matrix by another.
     *
     * @param otherMatrix the matrix being multiplied
     * @return the matrix product of two matrices
     */
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

    /**
     * The multiply(vector) method multiplies a matrix by a vector.
     *
     * @param vector the vector being multiplied
     * @return the vector product of a matrix and vector
     */
    public Vector multiply(Vector v) {
        double x = 0.0;
        for (int i = 0; i < 3; i++) {
            x += this.get(0, i) * v.get(i);
        } // for

        double y = 0.0;
        for (int i = 0; i < 3; i++) {
            y += this.get(1, i) * v.get(i);
        } // for

        double z = 0.0;
        for (int i = 0; i < 3; i++) {
            z += this.get(2, i) * v.get(i);
        } // for

        return new Vector(x, y, z);
    } // multiply(Vector)

    /**
     * The rowToString method turns a row in a matrix to a string to simplify
     * the toString method.
     *
     * @param row a row in a matrix
     * @return a string containing the numbers in the matrix row separated by
     * commas and enclosed in parentheses
     */
    private String rowToString(int row) {
        StringBuilder result = new StringBuilder();
        result.append("( ");
        for (int i = 0; i < 3; i++) {
            result.append(this.get(row, i));
            result.append(", ");
        } // for
        result.append(this.get(row, 3));
        result.append(" )");
        return result.toString();
    } // rowToString(int)

    /**
     * The toString method turns a matrix to a string by using the rowToString
     * method.
     *
     * @return a string containing the numbers in a matrix by row separated by
     * commas and enclosed by brackets
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < 4; i++) {
            result.append(rowToString(i));
        } //
        result.append("]");
        return result.toString();
    } // toString()

    /**
     * The main method tests some of the other methods in this class.
     *
     * @param args
     */
    public static void main(String[] args) {
        Matrix identity = new Matrix();
        System.out.println("identity = " + identity);

        Matrix otherMatrix = new Matrix();
        otherMatrix.elements[0][0] = 1.1;
        otherMatrix.elements[0][1] = 2.2;
        otherMatrix.elements[0][2] = 3.3;
        otherMatrix.elements[0][3] = 4.4;
        otherMatrix.elements[1][0] = 5.5;
        otherMatrix.elements[1][1] = 6.6;
        otherMatrix.elements[1][2] = 7.7;
        otherMatrix.elements[1][3] = 8.8;
        otherMatrix.elements[2][0] = 9.9;
        otherMatrix.elements[2][1] = 10.10;
        otherMatrix.elements[2][2] = 11.11;
        otherMatrix.elements[2][3] = 12.12;
        otherMatrix.elements[3][0] = 13.13;
        otherMatrix.elements[3][1] = 14.14;
        otherMatrix.elements[3][2] = 15.15;
        otherMatrix.elements[3][3] = 16.16;
        System.out.println("other matrix = " + otherMatrix);

        Matrix product = identity.multiply(otherMatrix);
        System.out.println("product = " + product);

        Matrix ccw = new Matrix();
        ccw.rotationZ(Math.PI / 4);
        System.out.println("counter-clockwise rotation = " + ccw);

        Matrix cw = new Matrix();
        cw.rotationZ(-Math.PI / 4);
        System.out.println("clockwise rotation = " + cw);

        Matrix netRotation = ccw.multiply(cw);
        System.out.println("net rotation = " + netRotation);

        Vector vector = new Vector(1, 2, 3);
        System.out.print("vector = " + vector);
    } // main(String [])
} // Matrix
