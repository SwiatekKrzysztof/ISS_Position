package pl.iss.ISSPosition.position;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import static pl.iss.ISSPosition.position.CoordinatesConverter.EQUATOR;
import static pl.iss.ISSPosition.position.CoordinatesConverter.MERIDIAN;

public class PossitionThread implements Runnable {
    Circle locationPoint;
    Circle circleAround;
    Path path;
    Reader reader;
    Properties properties;
    Properties prevProperties;
    double w;
    double h;


    public PossitionThread(Circle locationPoint, Circle circleAround, Path path, Reader reader, Properties properties, double width, double height) {
        this.circleAround = circleAround;
        this.locationPoint = locationPoint;
        this.path = path;
        this.reader = reader;
        this.properties = properties;
        w = width;
        h = height;
    }

    @Override
    public void run() {
        double widthToEquator = w/EQUATOR;
        double heightToMeridian = h/MERIDIAN;
        prevProperties = properties;
        path.getElements().add(new MoveTo(
                (CoordinatesConverter.longitudeToX(prevProperties.getIss_position().getLongitude()))*widthToEquator,
                -(CoordinatesConverter.latitudeToY(prevProperties.getIss_position().getLatitude()))*heightToMeridian));
        for (int i = 0; true; i++){

            properties = reader.readISSProperties();
            double x = CoordinatesConverter.longitudeToX(properties.getIss_position().getLongitude()) ;
            double y = CoordinatesConverter.latitudeToY(properties.getIss_position().getLatitude()) ;
            System.out.println(properties.getIss_position()+ "\n");


            locationPoint.setFill(Color.CYAN);
            locationPoint.setRadius(3);
            locationPoint.setCenterX((x * widthToEquator));
            // - below comes from javaFX different possitive and negative values distribution than normal graph
            locationPoint.setCenterY(-(y * heightToMeridian));

            circleAround.setStrokeWidth(2);
            circleAround.setStroke(Color.CYAN);
            circleAround.setFill(Color.TRANSPARENT);
            circleAround.setRadius(15);
            circleAround.setCenterX(locationPoint.getCenterX());
            circleAround.setCenterY(locationPoint.getCenterY());

            paintPath();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void paintPath(){
        path.setStrokeWidth(4);
        path.setStroke(Color.CYAN);
        path.getElements().add(new MoveTo(locationPoint.getCenterX(),locationPoint.getCenterY()));
        path.getElements().add(new LineTo(locationPoint.getCenterX(),locationPoint.getCenterY()));



    }
}
