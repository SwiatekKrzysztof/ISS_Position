package pl.iss.ISSPosition.satellitePhotos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoProperties {
    private double cloud_score;
    private String date;
    private String id;
    private PhotoResources resource;
    private String service_version;
    private String url;
}
