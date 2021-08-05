package sample;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private double symbolsSize;
    private Canvas canvas;
    private int numberOfMissclicks=0;
    private List<Circle> circlesList= new ArrayList<>();
    private int clock=10;
    private List<Square> squaresList= new ArrayList<>();
    Timer timer = new Timer();



    public void timing(Label label){

    TimerTask timerTask= new TimerTask() {
        @Override
        public void run() {
            if(clock >0){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        label.setText("Time left: "+ clock);
                    }
                });
                clock=clock-1;
            }
            else
                //once our clock is equal to 0, the timer will be cancelled.
                timer.cancel();
        }
    };

    //our defined timer keeps on going, till infinity, at a period of 1000ms, doing the task defined
    // by the timerTask object
    timer.schedule(timerTask, 0, 1000);
}


    public Game(Canvas canvas){
        this.canvas= canvas;
    }


    public void setSymbolsSize(String string) {
        //we need to do this since we are reading the size from the text field. and that only has strings.
        this.symbolsSize= Double.parseDouble(string);
    }

    public double getSymbolsSize() {
        return symbolsSize;
    }

    public void addCircle(Point2D centrePoint){
        Circle circle= new Circle(centrePoint);

        //only if the clock is 0, we will be able to put a circle
        //can add one more condition below that the start button needs to be pressed first.
         if(clock>0){
             if(circleIsOverlapping(circle)){
                 numberOfMissclicks=numberOfMissclicks+1;
             }
             else{
                 circlesList.add(circle);
                 circle.setRadius(symbolsSize);
                 circle.draw(canvas.getGraphicsContext2D());
                 System.out.println("length of circles list: "+ circlesList.size());
             }
        }
    }


    public void addSquare(Point2D centrePoint){
        Square square= new Square(centrePoint);
        if(squareIsOverlapping(square)){
            numberOfMissclicks=numberOfMissclicks+1;
        }
        else if(clock>0){
            squaresList.add(square);
            square.setSidelength(symbolsSize*1.5);
            square.draw(canvas.getGraphicsContext2D());
            System.out.println("length of squares list: "+ squaresList.size());
        }
    }

    public boolean squareIsOverlapping(Square squarePlaced){
        //checking if overlapping any square
        for (Square square : squaresList) {
            if (squarePlaced.getCentrePoint().distance(square.getCentrePoint()) < square.getSidelength()) {
                return true;
            }
        }

        for(Circle circle: circlesList){
            //checking if overlapping any circle
            if(squarePlaced.getCentrePoint().distance(circle.getCentrePoint())< circle.getRadius()){
                return true;
            }
        }
        return false;

    }

    public boolean circleIsOverlapping(Circle circlePlaced){
        //chekcing if overlapping any circle
        for(Circle circle: circlesList){
            if(circlePlaced.getCentrePoint().distance(circle.getCentrePoint())<circle.getRadius()){
                //above condition checking if radius is being overlapped by other circle
                return true;
            }
        }
        //checking if overlapping any square
        for(Square square: squaresList){
            if(circlePlaced.getCentrePoint().distance(square.getCentrePoint())<square.getSidelength()){
                return true;
            };
        }

        return false;
    }



    public int getNumberOfMissclicks() {
        return numberOfMissclicks;
    }

    public int getTime() {
        return clock;
    }

}
