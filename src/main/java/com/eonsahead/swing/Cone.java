package com.eonsahead.swing;

import com.eonsahead.swing.Polygon3D;
import java.util.ArrayList;
import java.util.List;

/**
 * The Cone class creates a 3D cone.
 * 
 * @author Elizabeth Gardner
 * @version 12 April 2020
 */
public class Cone {
    
//    The idea for the Cone class is that it basically turns a prism into a cone
//    by using a polygon with 360 sides (so it looks like a circle) as the base
//    and a polygon with 360 sides and a radius of 0 (so it acts as a point) as
//    the second "face." I haven't had much time to develop this, so the cone is
//    likely incomplete/not properly adapted, but the basic premise is there.    
    
    private final List<Vector> vertices = new ArrayList<>();

    /**
     * The Cone constructor makes a 3D cone by making a prism with one face a
     * 3D polygon with 360 sides (so it looks like a circle) and another face
     * with 360 sides and a radius of 0 (so it acts as a point) and making the
     * sleeve just like in the Polygon3D class.
     * 
     * @param radius the radius of the circle forming the base of the cone
     */
    public Cone(double radius) {
        Polygon3D base = new Polygon3D(360, radius);
        Polygon3D point = new Polygon3D(360, 0);
        base.makeSleeve(point);
    } // Cone(double)

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
} // Cone