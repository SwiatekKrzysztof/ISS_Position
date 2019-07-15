package pl.iss.ISSPosition;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static pl.iss.ISSPosition.CoordinatesConverter.EQUATOR;
import static pl.iss.ISSPosition.CoordinatesConverter.MERIDIAN;

public class PossitionThread implements Runnable {
    Circle locationPoint;
    Circle circleAround;
    Reader reader;
    Properties properties;
    double w;
    double h;


    public PossitionThread(Circle locationPoint,Circle circleAround, Reader reader, Properties properties, double width, double height) {
        this.circleAround = circleAround;
        this.locationPoint = locationPoint;
        this.reader = reader;
        this.properties = properties;
        w = width;
        h = height;
    }

    @Override
    public void run() {
        while(true) {

            properties = reader.readISSProperties();
            double x = CoordinatesConverter.longitudeToX(properties.getIss_position().getLongitude()) ;// EQUATOR;
            double y = CoordinatesConverter.latitudeToY(properties.getIss_position().getLatitude()) ;// MERIDIAN;
            System.out.println(properties.getIss_position()+ "\n");

            double widthToEquator = w/EQUATOR;
            double heightToMeridian = h/MERIDIAN;
            locationPoint.setFill(Color.CYAN);
            locationPoint.setRadius(3);
            locationPoint.setCenterX((x * widthToEquator));
            // - below comes from javaFX different possitive and negative values distribution than normal graph
            locationPoint.setCenterY(-(y * heightToMeridian));

            circleAround.setStrokeWidth(2);
            circleAround.setStroke(Color.CYAN);
            circleAround.setFill(Color.TRANSPARENT);
            circleAround.setRadius(15);
            circleAround.setCenterX((x * widthToEquator));
            // - below comes from javaFX different possitive and negative values distribution than normal graph
            circleAround.setCenterY(-(y * heightToMeridian));


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
