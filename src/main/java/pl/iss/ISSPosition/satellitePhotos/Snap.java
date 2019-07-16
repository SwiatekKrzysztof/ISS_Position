package pl.iss.ISSPosition.satellitePhotos;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
import lombok.Data;


import java.io.IOException;
import java.net.URL;

@Data
public class Snap implements Runnable{
    private String finalURL = "https://api.nasa.gov/planetary/earth/imagery/?";
    private String lon = "lon=";
    private String lat = "lat=";
    private String dim = "dim=0.1";
    private String key = "api_key=";
    //api_key=
    private Image imageResult;
    public Image satelliteSnap(){
        Image image;
        PhotoProperties photoProperties = new PhotoProperties();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            photoProperties = objectMapper.readValue(new URL(finalURL),PhotoProperties.class);
            image = new Image(photoProperties.getUrl());
        } catch (IOException e) {
            //e.printStackTrace();
            //todo add some funny pics for ocean viewers...
            image = new Image("https://dzetadt.files.wordpress.com/2013/10/ladies-and-gentlemen-the-ocean_o_340469.jpg");
        }
        this.imageResult = image;
        return image;
    }

    public Snap(String lon, String lat, String dim, String key) {
        //https://api.nasa.gov/planetary/earth/imagery/?lon=21.4954&lat=19.0873&dim=0.1&api_key=bRsbl0ECducVFZWLl7sqpPdtEQyHzfcc73nf7OeH
        this.lon = this.lon + lon;
        this.lat = this.lat + lat;
        this.dim = this.dim + dim;
        this.key = this.key + key;
        this.finalURL = this.finalURL + this.lon + "&" + this.lat + "&" + this.dim + "&" + this.key;
    }
    public Snap(String lon, String lat, String dim) {
        //https://api.nasa.gov/planetary/earth/imagery/?lon=21.4954&lat=19.0873&dim=0.1&api_key=bRsbl0ECducVFZWLl7sqpPdtEQyHzfcc73nf7OeH
        this.lon = this.lon + lon;
        this.lat = this.lat + lat;
        this.dim = this.dim + dim;
        this.key = this.key + "DEMO_KEY";
        this.finalURL = this.finalURL + this.lon + "&" + this.lat + "&" + this.dim + "&" + this.key;
    }

    @Override
    public void run() {
        satelliteSnap();
    }
}
