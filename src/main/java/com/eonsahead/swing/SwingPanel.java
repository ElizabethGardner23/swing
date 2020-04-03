package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SwingPanel extends JPanel implements ActionListener {

    private double centerX = 0.0;
    private double centerY = 0.0;
    private double radius = 0.5;
    private double deltaY = 0.02;
    private Color color = Color.red;

    public SwingPanel() {
        Timer timer = new Timer(50, this);
        timer.start();
    } // SwingPanel()

    public double getCenterX() {
        return this.centerX;
    } // getCenterX()

    public void setCenterX(double x) {
        this.centerX = x;
    } // setCenterX( double )

    public double getCenterY() {
        return this.centerY;
    } // getCenterY()

    public void setCenterY(double y) {
        this.centerY = y;
    } // setCenterY( double )

    public double getRadius() {
        return this.radius;
    } // getRadius()

    public void setRadius(double r) {
        this.radius = r;
    } // setRadius( double )

    public Color getColor() {
        return this.color;
    } // getColor()

    public void setColor(Color c) {
        this.color = c;
    } // setColor( Color )

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();

        AffineTransform transform = new AffineTransform();
        AffineTransform scaling = new AffineTransform();
        scaling.setToScale(w / 2, h / 2);
        AffineTransform translation = new AffineTransform();
        translation.setToTranslation(1.0, 1.0);

        transform.concatenate(scaling);
        transform.concatenate(translation);

        // Replace this block of code that creates
        // an ellipse with your own code that draws
        // something else
        // Make sure that all geometry fits in a square
        // whose corners are (-1, -1) and (+1, +1)
        
        double d = 2 * this.radius;
        double ulx = this.centerX - this.radius;
        double uly = this.centerY - this.radius;
//        Ellipse2D.Double circle = new Ellipse2D.Double(ulx, uly, d, d);

        Rectangle2D.Double rectangle = new Rectangle2D.Double(ulx, uly, d, d);

        Shape shape = transform.createTransformedShape(rectangle);
        g2D.setColor(this.getColor());
        g2D.fill(shape);
    } // paintComponent( Graphics )

    @Override
    public void actionPerformed(ActionEvent event) {
        // You might also like to try what happens
        // in each step of the animation
        // Move? In which direction? How much?
        // Make bigger? Or make smaller?
        // Rotate? (There's an AffineTransform for that, too.)
        // Change color?

        if (this.centerY > 0.5) {
            this.deltaY = -this.deltaY;
        } // if
        else if (this.centerY < -0.5) {
            this.deltaY = -this.deltaY;
        } // else if
        this.centerY += this.deltaY;

        this.repaint();
    } // actionPerformed( ActionEvent )

} // SwingPanel
