package com.eonsahead.swing;

import com.eonsahead.swing.Matrix;
import com.eonsahead.swing.Vector;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

/**
 * The Polygon3D class contains methods to create a shape that can rotate in
 * three dimensions.
 *
 * @author Elizabeth Gardner
 * @version 10 April 2020
 */
public class Polygon3D {

    private final List<Vector> vertices = new ArrayList<>();

    /**
     * The Polygon3D(Vector, Vector, Vector) constructor creates a triangle
     * where each side is specified by a vector.
     *
     * @param v0 a vector that specifies the first side of the triangle
     * @param v1 a vector that specifies the second side of the triangle
     * @param v2 a vector that specifies the third side of the triangle
     */
    public Polygon3D(Vector v0, Vector v1, Vector v2) {
        this.vertices.add(v0);
        this.vertices.add(v1);
        this.vertices.add(v2);
    } // Polygon3D(Vector, Vector, Vector)

    /**
     * The Polygon3D(int, double) constructor creates a polygon with a
     * designated number of sides and distance from the center of the shape to
     * the vertices.
     *
     * @param numberOfSides the number of sides the shape has
     * @param radius the distance from the center of the shape to the vertices
     */
    public Polygon3D(int numberOfSides, double radius) {
        for (int i = 0; i < numberOfSides; i++) {
            double fraction = ((double) i) / numberOfSides;
            double angle = fraction * 2.0 * Math.PI;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            Vector v = new Vector(x, y, 0.0);
            this.vertices.add(v);
        } // for
    } // Polygon3D(int, double)

    /**
     * The getVertices method returns a list of the polygon's vertices to assist
     * with the makeSleeve method.
     * 
     * @return a list of the polygon's vertices
     */
    public List<Vector> getVertices() {
        return this.vertices;
    } // getVertices()

    /**
     * The makeSleeve method connects two polygons to make a three dimensional
     * prism.
     * 
     * @param otherPolygon the polygon forming the other face of the prism
     * @return a series of rectangles that connect the corresponding sides of
     * the polygons that form the faces of the prism
     */
    public List<Polygon3D> makeSleeve(Polygon3D otherPolygon) {
        // Construct triangles that make sides of prism
        List<Polygon3D> faces = new ArrayList<>();

        int numberOfSides = this.getVertices().size();

        if (numberOfSides == otherPolygon.getVertices().size()) {
            Vector v0;
            Vector v1;
            Vector v2;
            Polygon3D p;

            List<Vector> vertexListA = this.getVertices();
            List<Vector> vertexListB = otherPolygon.getVertices();

            for (int i = 0; i < numberOfSides - 1; i++) {
                v0 = vertexListA.get(i);
                v1 = vertexListB.get(i);
                v2 = vertexListA.get(i + 1);
                p = new Polygon3D(v0, v1, v2);
                faces.add(p);

                v0 = vertexListA.get(i + 1);
                v1 = vertexListB.get(i);
                v2 = vertexListB.get(i + 1);
                p = new Polygon3D(v0, v1, v2);
                faces.add(p);
            } // for

            v0 = vertexListA.get(numberOfSides - 1);
            v1 = vertexListB.get(numberOfSides - 1);
            v2 = vertexListA.get(0);
            p = new Polygon3D(v0, v1, v2);
            faces.add(p);

            v0 = vertexListA.get(0);
            v1 = vertexListB.get(numberOfSides - 1);
            v2 = vertexListB.get(0);
            p = new Polygon3D(v0, v1, v2);
            faces.add(p);
        } // if

        return faces;
    } // makeSleeve(Polygon3D)

    /**
     * The transform method changes the position of the vertices by multiplying
     * the vectors representing the sides of the shape by a designated matrix.
     *
     * @param m the matrix to be multiplied by the vectors indicating the sides
     * of the shape
     */
    public void transform(Matrix m) {
        for (Vector u : this.vertices) {
            u.set(m.multiply(u));
        } // for 
    } // transform(Matrix)

    /**
     * The getNormal method condenses three vectors to a single vector with a
     * length of 1.
     *
     * @return a normalized vector
     */
    public Vector getNormal() {
        Vector p0 = this.vertices.get(0);
        Vector p1 = this.vertices.get(1);
        Vector p2 = this.vertices.get(2);

        Vector v0 = p2.subtract(p1);
        Vector v1 = p0.subtract(p1);

        Vector crossProduct = v0.cross(v1);

        return crossProduct.normalize();
    } // getNormal()

    /**
     * The getShape method creates a shape by connecting individual paths.
     *
     * @return a shape made of a closed chain of paths
     */
    public Shape getShape() {
        GeneralPath path = new GeneralPath();

        Vector v = this.vertices.get(0);
        double x = v.get(0);
        double y = v.get(1);
        path.moveTo(x, y);

        for (int i = 1; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);
            x = v.get(0);
            y = v.get(1);
            path.lineTo(x, y);
        } // for

        path.closePath();

        return path;
    } // getShape()    
} // Polygon3D