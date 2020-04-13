package com.eonsahead.swing;

/**
 * Model and perform operations on a 4D vector.
 *
 * @author Elizabeth Gardner
 * @version 10 April 2020
 */
public class Vector {

    private double[] elements = new double[4];

    private double x;
    private double y;
    private double z;
    private double h;

    /**
     * Vector() is a constructor which creates a new 4D vector with all four
     * values set to 0.
     */
    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.h = 0;
    } // Vector()

    /**
     * Vector(double, double, double) is a constructor which creates a new 4D
     * vector with the x, y, and z coordinates set to the designated values and
     * the homogeneous coordinate set to 1.
     *
     * @param x the x coordinate of the new vector
     * @param y the y coordinate of the new vector
     * @param z the z coordinate of the new vector
     */
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.h = 1;
    } // Vector4D(double, double, double)

    /**
     * The get method returns the value of either the x, y, z, or homogeneous
     * coordinate of a four element vector.
     *
     * @param choice either 0 to select the x coordinate, 1 to select the y
     * coordinate, 2 to select the z coordinate, or 3 to select the homogeneous
     * coordinate of the vector
     * @return either the x, y, z, or homogeneous coordinate
     */
    public double get(int choice) {
        double result = -1;
        if (choice == 0) {
            result = this.x;
        } // if
        else if (choice == 1) {
            result = this.y;
        } // else if
        else if (choice == 2) {
            result = this.z;
        } // else if
        else if (choice == 3) {
            result = this.h;
        } // else if
        return result;
    } // get(int)

    /**
     * The getX method finds and returns the value of the first element (x
     * coordinate) in a four element vector.
     *
     * @return the vector's x value
     */
    public double getX() {
        return this.x;
    } // getX()

    /**
     * The getY method finds and returns the value of the second element (y
     * coordinate) in a four element vector.
     *
     * @return the vector's y value
     */
    public double getY() {
        return this.y;
    } // getY()

    /**
     * The getZ method finds and returns the value of the third element (z
     * coordinate) in a four element vector).
     *
     * @return the vector's z value
     */
    public double getZ() {
        return this.z;
    } // getZ()

    /**
     * The getH method finds and returns the value of the fourth element
     * (homogeneous coordinate) in a four element vector.
     *
     * @return the vector's homogeneous coordinate value
     */
    public double getH() {
        return this.h;
    } // getH()

    /**
     * The set(int, double) method changes the value at the designated index in
     * a vector to a designated new value.
     *
     * @param index the position of the value in the vector to be changed
     * @param value the value the designated element is to be changed to
     */
    public void set(int index, double value) {
        this.elements[index] = value;
    } // set(int, double)

    /**
     * The set(Vector) method establishes all four parameters of a four element
     * vector.
     *
     * @param v a four element vector
     */
    public void set(Vector v) {
        this.elements[0] = v.elements[0];
        this.elements[1] = v.elements[1];
        this.elements[2] = v.elements[2];
        this.elements[3] = v.elements[3];
    } // set(Vector)

    /**
     * The dot method calculates the dot product of two vectors.
     *
     * @param v the vector being multiplied
     * @return the double dot product of two vectors
     */
    public double dot(Vector v) {
        double xProduct = this.getX() * v.getX();
        double yProduct = this.getY() * v.getY();
        double zProduct = this.getZ() * v.getZ();
        return xProduct + yProduct + zProduct;
    } // dot(Vector)

    /**
     * The cross method calculates the cross product of two vectors.
     *
     * @param v the vector being multiplied
     * @return the vector cross product of two vectors
     */
    public Vector cross(Vector v) {
        double a1 = v.x;
        double a2 = v.y;
        double a3 = v.z;
        double b1 = this.x;
        double b2 = this.y;
        double b3 = this.z;
        double new1 = (a2 * b3) - (a3 * b2);
        double new2 = (a3 * b1) - (a1 * b3);
        double new3 = (a1 * b2) - (a2 * b1);
        return new Vector(new1, new2, new3);
    } // cross(Vector)

    /**
     * The add method adds two vectors.
     * 
     * @param v the vector being added
     * @return the vector sum of two vectors
     */
    public Vector add(Vector v) {
        double x = this.get(0) + v.get(0);
        double y = this.get(1) + v.get(1);
        double z = this.get(2) + v.get(2);
        return new Vector(x, y, z);
    } // add(Vector)

    /**
     * The subtract method subtracts one vector from another.
     * 
     * @param v the vector being subtracted
     * @return the vector difference of two vectors
     */
    public Vector subtract(Vector v) {
        double x = this.get(0) - v.get(0);
        double y = this.get(1) - v.get(1);
        double z = this.get(2) - v.get(2);
        return new Vector(x, y, z);
    } // subtract(Vector)

    /**
     * The magnitude method calculates the magnitude of a vector using the dot
     * method.
     *
     * @return the double magnitude of the vector
     */
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    } // magnitude()

    /**
     * The normalize method modifies a vector so that it has the same direction
     * but a length (magnitude) equal to one by dividing the x, y, and z
     * coordinates by the magnitude calculated in the magnitude method.
     *
     * @return a vector with a length of one
     */
    public Vector normalize() {
        double newX = this.x / magnitude();
        double newY = this.y / magnitude();
        double newZ = this.z / magnitude();
        return new Vector(newX, newY, newZ);
    } // normalize()

    /**
     * The toString method converts a vector to a string in which the elements
     * are separated by commas and enclosed in parentheses.
     *
     * @return a string representation of a four element vector
     */
    @Override
    public String toString() {
        return "[" + getX() + ", " + getY() + ", " + getZ() + ", " + getH()
                + "]";
    } // toString()    
} // Vector
