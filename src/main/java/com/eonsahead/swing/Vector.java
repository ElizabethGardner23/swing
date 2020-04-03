package com.eonsahead.swing;

/**
 * Model and perform operations on a 4D vector.
 * 
 * @author Elizabeth Gardner
 * @version 3 April 2020
 */
public class Vector {
    
    private double x;
    private double y;
    private double z;
    private double h;
    
    public double getX() {
        return this.x;
    } // getX()

    public double getY() {
        return this.y;
    } // getY()

    public double getZ() {
        return this.z;
    } // getZ()

    public double getH() {
        return this.h;
    } // getRGBA()
    
    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.h = 0;
    } // Vector()

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.h = 1;
    } // Vector4D(double, double, double)
    
    public double get(int choice) {
        double result = -1;
        if(choice == 0) {
            result = this.x;
        } // if
        else if(choice == 1) {
            result = this.y;
        } // else if
        else if(choice == 2) {
            result = this.z;
        } // else if
        else if(choice == 3){
            result = this.h;
        } // else if
        return result;
    } // get(int)

    public double dot(Vector v) {
        double xProduct = this.getX() * v.getX();
        double yProduct = this.y * v.getY();
        double zProduct = this.getZ() * v.getZ();
        double hProduct = this.h * v.getH();
        return xProduct + yProduct + zProduct + hProduct;
    } // dot(Vector4D)
    
    public Vector cross(Vector v) {
        double a1 = this.x;
        double a2 = this.y;
        double a3 = this.z;
        double b1 = v.x;
        double b2 = v.y;
        double b3 = v.z;
        double new1 = (a2 * b3) - (a3 * b2);
        double new2 = (a3 * b1) - (a1 * b3);
        double new3 = (a1 * b2) - (a2 * b1);
        return new Vector(new1, new2, new3);
    } // cross(Vector)

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    } // magnitude()
    
    public Vector normalize() {
        double newX = this.x/magnitude();
        double newY = this.y/magnitude();
        double newZ = this.z/magnitude();
        return new Vector(newX, newY, newZ);
    } // normalize()

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ", " + getZ() + ", " + getH()
                + ")";
    } // toString()    
} // Vector
