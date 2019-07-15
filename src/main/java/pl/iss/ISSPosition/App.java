package pl.iss.ISSPosition;


import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import static pl.iss.ISSPosition.CoordinatesConverter.*;

public class App extends Application
{
    public static final Group sp = new Group();
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

        //CIRCLE MARKING ISS POSITION
        Circle circle = new Circle();
        circle.setLayoutX(width/2.0);
        circle.setLayoutY(height/2.0);

        //CALIBRATION CIRCLE
        //CENTER CIRCLE "NULL ISLAND"
        //Circle centerC = new Circle(2,Color.IVORY);
        //centerC.setLayoutX(width/2.0);

        //CIRCLE AROUND ISS
        Circle circleAround = new Circle();
        circleAround.setLayoutX(width/2.0);
        circleAround.setLayoutY(height/2.0);

        //LINE DESCRIBING PATH OF ISS
        Path path = new Path();
        path.setLayoutX(width/2.0);
        path.setLayoutY(height/2.0);
        properties = reader.readISSProperties();
        new Thread(new PossitionThread(circle,circleAround,path,reader,properties,width,height)).start();

        sp.getChildren().add(path);
        sp.getChildren().add(circleAround);
        //sp.getChildren().add(centerC);
        sp.getChildren().add(circle);

    }
    public void initStage(Stage primaryStage,double width, double height){
        Image image = new Image("http://flatplanet.sourceforge.net/maps/images/PathfinderMap.jpg"
                ,width,height,true,true);
        ImageView imageView = new ImageView(image);
        sp.getChildren().add(imageView);
        Scene scene = new Scene(sp,image.getWidth(),image.getHeight(),false);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        primaryStage.show();
        System.out.println(primaryStage.getWidth()+" "+primaryStage.getHeight());
    }
}
