package com.eonsahead.swing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {

    private static final double EPSILON = 1E-8;

    public VectorTest() {
    } // VectorTest()

    @Test
    public void testGet() {
        int index = 0;
        Vector instance = new Vector(99.0, 0.0, 0.0);
        double expResult = 99.0;
        double result = instance.get(index);
        assertEquals(expResult, result, EPSILON);
    } // testGet()

    @Test
    public void testGetX() {
        Vector instance = new Vector(3.3, 2.2, 1.1);
        double expResult = 3.3;
        double result = instance.getX();
        assertEquals(expResult, result, EPSILON);
    } // testGetX()

    @Test
    public void testGetY() {
        Vector instance = new Vector(3.3, 2.2, 1.1);
        double expResult = 2.2;
        double result = instance.getY();
        assertEquals(expResult, result, EPSILON);
    } // testGetY()

    @Test
    public void testGetZ() {
        Vector instance = new Vector(3.3, 2.2, 1.1);
        double expResult = 1.1;
        double result = instance.getZ();
        assertEquals(expResult, result, EPSILON);
    } // testGetZ()

    @Test
    public void testGetH() {
        Vector instance = new Vector();
        double expResult = 0.0;
        double result = instance.getH();
        assertEquals(expResult, result, EPSILON);
    } // testGetH

    @Test
    public void testSet_int_double() {
        int index = 1;
        double value = 2.2;
        Vector instance = new Vector();
        instance.set(index, value);
        double expResult = 2.2;
        double result = instance.getY();
        assertEquals(expResult, result, EPSILON);
    } // testSet_int_double()

    @Test
    public void testSet_Vector() {
        Vector v = new Vector(1.1, 2.2, 3.3);
        Vector instance = new Vector();
        instance.set(v);
        Vector expResult = new Vector(1.1, 2.2, 3.3);
        Vector result = instance;
        assertEquals(expResult, result);
    } // testSet_Vector()

    @Test
    public void testDot() {
        Vector u = new Vector(0, 1, 0);
        Vector v = new Vector(1, 0, 0);
        double expResult = 0.0;
        double result = u.dot(v);
        assertEquals(expResult, result, EPSILON);
    } // testDot()

    @Test
    public void testCross() {
        Vector v = new Vector(1, 2, 3);
        Vector instance = new Vector(4, 5, 6);
        Vector expResult = new Vector(-3, 6, -3);
        Vector result = instance.cross(v);
        assertEquals(expResult, result);
    } // testCross() 

    @Test
    public void testAdd() {
        Vector v = new Vector (1.1, 2.2, 3.3);
        Vector instance = new Vector(4.4, 5.5, 6.6);
        Vector expResult = new Vector(5.5, 7.7, 9.9);
        Vector result = instance.add(v);
        assertEquals(expResult, result);
    } // testAdd()

    @Test
    public void testSubtract() {
        Vector v = new Vector(4.4, 5.5, 6.6);
        Vector instance = new Vector(7.7, 8.8, 9.9);
        Vector expResult = new Vector(3.3, 3.3, 3.3);
        Vector result = instance.subtract(v);
        assertEquals(expResult, result);
    } // testSubtract()

    @Test
    public void testMagnitude() {
        Vector instance = new Vector(3, 4, 12);
        double expResult = 13;
        double result = instance.magnitude();
        assertEquals(expResult, result, EPSILON);
    } // testMagnitude()

    @Test
    public void testNormalize() {
        Vector instance = new Vector(3, 4, 12);
        Vector expResult = new Vector(0.23076923, 0.30769231,
                0.92307692);
        Vector result = instance.normalize();
        assertEquals(expResult, result);
    } // testNormalize()
    
    @Test
    public void testToString() {
        Vector instance = new Vector(1.1, 2.2, 3.3);
        String expResult = "[1.1, 2.2, 3.3, 1.0]";
        String result = instance.toString();
        assertEquals(expResult, result);
    } // testToString()
} // VectorTest
