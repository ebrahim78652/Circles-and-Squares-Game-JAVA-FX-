package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square {


    double sidelength=60;
    private Point2D centrePoint;


    public Square(Point2D point){
        centrePoint=point;
    }

    public Point2D getCentrePoint() {
        return centrePoint;
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.strokeRect(centrePoint.getX()-sidelength/2, centrePoint.getY()-sidelength/2, sidelength ,sidelength );
        gc.fillRect(centrePoint.getX()-sidelength/2, centrePoint.getY()-sidelength/2, sidelength, sidelength);

        //making the small rectangle in the square.
        gc.setLineWidth(2);
        gc.setStroke(Color.RED);
        gc.strokeRect(centrePoint.getX(), centrePoint.getY(), 1,1);
    }

    public double getSidelength() {
        return sidelength;
    }

    public void setSidelength(double sidelength) {
        this.sidelength = sidelength;
    }

    public void setCentrePoint(Point2D centrePoint) {
        this.centrePoint = centrePoint;
    }
}
