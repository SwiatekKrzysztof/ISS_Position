package pl.iss.ISSPosition;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import pl.iss.ISSPosition.position.PossitionThread;
import pl.iss.ISSPosition.position.Properties;
import pl.iss.ISSPosition.position.Reader;
import pl.iss.ISSPosition.satellitePhotos.Snap;

import java.io.FileNotFoundException;

public class App extends Application
{
    public static final Group sp = new Group();
    public static void main( String[] args )
    {
        launch(args);
    }
    public Thread threadPopup = new Thread();
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
        primaryStage.setTitle("ISS Position ~~ CLICK SPACE to get Photo ~~");
        Scene scene = new Scene(sp,image.getWidth(),image.getHeight(),false);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        primaryStage.show();
        //System.out.println(primaryStage.getWidth()+" "+primaryStage.getHeight());
        scene.getWindow().sizeToScene();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case SPACE:
                        Stage secondaryStage = new Stage();
                        initImagePopup(secondaryStage,500,500);
                    break;
                }
            }
        });
    }
    public void initImagePopup(final Stage secondaryStage,double width, double height){
        Properties properties;
        Reader reader = new Reader();
        properties = reader.readISSProperties();
        String lat = Double.toString(properties.getIss_position().getLatitude());
        String lon = Double.toString(properties.getIss_position().getLongitude());

        Snap snap = new Snap(lon,lat,"");
        Image satellitePhoto = snap.satelliteSnap();
        ImageView imageViewSnap = new ImageView(satellitePhoto);
        Pane pane = new Pane();
        pane.getChildren().add(imageViewSnap);
        Scene scene = new Scene(pane,satellitePhoto.getWidth(),satellitePhoto.getHeight());
        //Stage secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.show();
//        initImagePopup(secondaryStage,500,500);
//        threadPopup.start(new Snap(lon,lat,""));

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case SPACE:
                        initImagePopup(secondaryStage,500,500);
                        break;
                }
            }
        });
    }
}
