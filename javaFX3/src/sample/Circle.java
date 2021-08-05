package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle {


    private Point2D centrePoint;
    private double radius;
    public Circle(Point2D point){
        centrePoint=point;
    }



    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Point2D getCentrePoint() {
        return centrePoint;
    }

    public void draw(GraphicsContext gc){
        //below here are just the the way to draw the circle
        //The gc is like the pencil. the canvas is the paper
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        //the 4 arguments to the strokeOval: the first 2 are needed to move the circle to position where we clicked
        //the last 2 arguments are needed for setting the width and height of the oval.
        gc.strokeOval(centrePoint.getX()-radius, centrePoint.getY()-radius, radius*2, radius*2);
        gc.fillOval(centrePoint.getX()-radius, centrePoint.getY()-radius, radius*2, radius*2);

        //making the small red rectangle in the circle
        gc.setLineWidth(2);
        gc.setStroke(Color.RED);
        gc.strokeRect(centrePoint.getX(), centrePoint.getY(), 1,1);
    }
}
