package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;

/**
 * The Prism class contains methods to produce any type of three-dimensional
 * prism that can rotate on the x, y, and z axes.
 * 
 * @author Elizabeth Gardner
 * @version 10 April 2020
 */
public class Prism {

    private final List<Polygon3D> faces;
    private final List<Vector> top;
    private final List<Vector> bottom;
    private final Vector vTopCenter;
    private final Vector vBottomCenter;

    /**
     * The Prism constructor creates a new three-dimensional prism with faces
     * with a designated number of sides and radius and a designated height.
     * 
     * @param numberOfSides the number of sides of the prism's two faces
     * @param radius the distance from the center of the face to any of its
     * vertices
     * @param height the distance from a vertex on one face to the corresponding
     * vertex on the other face
     */
    public Prism(int numberOfSides, double radius, double height) {
        this.faces = new ArrayList<>();
        this.top = new ArrayList<>();
        this.bottom = new ArrayList<>();

        double xCenter = 0.0;
        double yCenter = 0.0;
        double zTop = height / 2;
        double zBottom = -height / 2;

        // Construct points at the centers of the polygons
        // on the top and bottom of the prism
        this.vTopCenter = new Vector(xCenter, yCenter, zTop);
        this.vBottomCenter = new Vector(xCenter, yCenter, zBottom);

        // Construct vertices
        for (int i = 0; i < numberOfSides; i++) {
            double fraction = ((double) i) / numberOfSides;
            double angle = fraction * 2.0 * Math.PI;

            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);

            Vector vTop = new Vector(x, y, zTop);
            Vector vBottom = new Vector(x, y, zBottom);

            this.top.add(vTop);
            this.bottom.add(vBottom);
        } // for

        Vector v0;
        Vector v1;
        Vector v2;
        Polygon3D p;

        // Construct triangles that make top of prism
        for (int i = 0; i < numberOfSides - 1; i++) {
            v0 = this.vTopCenter;
            v1 = this.top.get(i);
            v2 = this.top.get(i + 1);
            p = new Polygon3D(v0, v1, v2);
            this.faces.add(p);
        } // for
        v0 = this.vTopCenter;
        v1 = this.top.get(numberOfSides - 1);
        v2 = this.top.get(0);
        p = new Polygon3D(v0, v1, v2);
        this.faces.add(p);

        // Construct triangles that make bottom of prism
        for (int i = 0; i < numberOfSides - 1; i++) {
            v0 = this.vBottomCenter;
            v1 = this.bottom.get(i);
            v2 = this.bottom.get(i + 1);
            p = new Polygon3D(v0, v2, v1);
            this.faces.add(p);
        } // for
        v0 = this.vBottomCenter;
        v1 = this.bottom.get(numberOfSides - 1);
        v2 = this.bottom.get(0);
        p = new Polygon3D(v0, v2, v1);
        this.faces.add(p);

        // Construct triangles that make sides of prism
        for (int i = 0; i < numberOfSides - 1; i++) {
            v0 = this.top.get(i);
            v1 = this.bottom.get(i);
            v2 = this.top.get(i + 1);
            p = new Polygon3D(v0, v1, v2);
            this.faces.add(p);

            v0 = this.top.get(i + 1);
            v1 = this.bottom.get(i);
            v2 = this.bottom.get(i + 1);
            p = new Polygon3D(v0, v1, v2);
            this.faces.add(p);
        } // for
        v0 = this.top.get(numberOfSides - 1);
        v1 = this.bottom.get(numberOfSides - 1);
        v2 = this.top.get(0);
        p = new Polygon3D(v0, v1, v2);
        this.faces.add(p);

        v0 = this.top.get(0);
        v1 = this.bottom.get(numberOfSides - 1);
        v2 = this.bottom.get(0);
        p = new Polygon3D(v0, v1, v2);
        this.faces.add(p);
    } // Prism()

    /**
     * The transform method moves the prism by multiplying the vectors that
     * represent vertices in the prism's faces by a designated matrix.
     * 
     * @param m the matrix to be multiplied by the vectors representing the
     * vertices in the prism's faces
     */
    public void transform(Matrix m) {

        Vector v = this.vTopCenter;
        v.set(m.multiply(v));

        v = this.vBottomCenter;
        v.set(m.multiply(v));

        for (Vector u : this.top) {
            u.set(m.multiply(u));
        } // for 

        for (Vector u : this.bottom) {
            u.set(m.multiply(u));
        } // for 
    } // transform(Matrix)

    /**
     * The getFaces method returns the list of 3D polygons that compose the
     * prism's faces.
     * 
     * @return a list of 3D polygons that compose the prism's faces
     */
    public List<Polygon3D> getFaces() {
        return this.faces;
    } // getFaces()
} // Prism