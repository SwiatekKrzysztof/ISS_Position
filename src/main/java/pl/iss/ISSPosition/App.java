package pl.iss.ISSPosition;


import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static pl.iss.ISSPosition.CoordinatesConverter.*;

public class App extends Application
{
    public static final Pane sp = new Pane();
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{
        double width = 1024.0;
        double height = 512.0;

        Reader reader = new Reader();

        Properties properties = new Properties();

        initStage(primaryStage,width,height);
        Circle circle = new Circle(3,Color.RED);
        circle.setLayoutX(30.0);
        circle.setLayoutY(30.0);
        new Thread(new PossitionThread(sp,circle,reader,properties,width,height)).start();
        sp.getChildren().add(circle);


            //PossitionThread possitionThread = new PossitionThread(circle,reader,properties,width,height);



    }
    public void initStage(Stage primaryStage,double width, double height){


        Image image = new Image("http://flatplanet.sourceforge.net/maps/images/PathfinderMap.jpg"
                ,width,height,true,true);
        //https://upload.wikimedia.org/wikipedia/commons/7/74/Mercator-projection.jpg
        ImageView imageView = new ImageView(image);
        //imageView.setOpacity(0.1);
        sp.getChildren().add(imageView);
        Scene scene = new Scene(sp,image.getWidth(),image.getHeight(),false);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
