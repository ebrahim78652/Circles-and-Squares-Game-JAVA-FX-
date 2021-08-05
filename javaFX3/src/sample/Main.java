package sample;


import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {

    static int countClicks=0;

    @Override
    public void start(Stage stage) throws Exception{
        //first going to make the stage with the beige colour.

        VBox box= new VBox();


        //making some nodes
        Button startButton= new Button("Start");
        TextField sizeOfSymbolsTextField= new TextField();
        sizeOfSymbolsTextField.setText("20.0");
        Label timeLeftLabel= new Label("Time Left: 0");
        Label missedClicksLabel= new Label("Missed Clicks: 0");
        Label placedGraphicsLabel= new Label("Placed Graphics: 0 ");
        Label numberofClickslabel= new Label("Number of clicks: 0");


        //adding them to the VBox
        box.getChildren().add(startButton);
        box.getChildren().add(sizeOfSymbolsTextField);
        box.getChildren().add(timeLeftLabel);
        box.getChildren().add(missedClicksLabel);
        box.getChildren().add(placedGraphicsLabel);
        box.getChildren().add(numberofClickslabel);


        //making the canvas
        Canvas canvas= new Canvas();
        canvas.setHeight(550);
        canvas.setWidth(550);
        canvas.getGraphicsContext2D().setFill(Color.BEIGE);
        canvas.getGraphicsContext2D().fillRect(0,0,550,550);
        //Adding the canvas also to the Vbox.
        box.getChildren().add(canvas);






        //game object
        //saving the canvas that we made in the game class.
        Game game= new Game(canvas);

        //we have a node, and we are basically saying here, that if the following event of Mouse clicked occurs,
        //then do the following.
        AtomicBoolean gameStarted= new AtomicBoolean(false);
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event->{
            game.timing(timeLeftLabel);
            gameStarted.set(true);
        });


        //adding an even handler to canvas.
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            //will only be able to draw a circle if the start button was pressed.
            if(gameStarted.get()==true &&game.getTime()>0){

                game.setSymbolsSize(sizeOfSymbolsTextField.getText());
                countClicks=countClicks+1;
                numberofClickslabel.setText("Number of Clicks: "+ countClicks);


                if(e.getButton()== MouseButton.PRIMARY){
                    game.addCircle(new Point2D(e.getX(),e.getY()));
                }
                else if(e.getButton()==MouseButton.SECONDARY){
                    game.addSquare(new Point2D(e.getX(),e.getY()));
                }
                missedClicksLabel.setText("Number of missclicks: "+ game.getNumberOfMissclicks());
            }
        });


        //setting the stage
        Scene scene= new Scene(box);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

