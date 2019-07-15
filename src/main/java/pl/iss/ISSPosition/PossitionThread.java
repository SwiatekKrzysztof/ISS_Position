package pl.iss.ISSPosition;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static pl.iss.ISSPosition.CoordinatesConverter.EQUATOR;
import static pl.iss.ISSPosition.CoordinatesConverter.MERIDIAN;

public class PossitionThread implements Runnable {
    Pane pane;
    Circle locationPoint;
    Reader reader;
    Properties properties;
    double w;
    double h;


    public PossitionThread(Pane pane, Circle locationPoint, Reader reader, Properties properties, double width, double height) {
        this.pane = pane;
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
            double x = CoordinatesConverter.longitudeToX(properties.getIss_position().getLongitude()) / EQUATOR;
            double y = CoordinatesConverter.latitudeToY(properties.getIss_position().getLatitude()) / MERIDIAN;
            System.out.println("Longitude-X= " + x + "  Latitude-Y= " + y);
            locationPoint.setFill(Color.RED);
            locationPoint.setRadius(3);
            locationPoint.setCenterX(Math.abs(x * w));
            locationPoint.setCenterY(Math.abs(y * h));
            new Circle(Math.abs(x*w),Math.abs(y*h),5,Color.CYAN);
            //Klasa anonimowa
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //locationPoint = new Circle(3,x*w,y*h,Color.RED);
                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
