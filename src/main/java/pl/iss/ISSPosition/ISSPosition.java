package pl.iss.ISSPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ISSPosition {
    private double longitude;
    private double latitude;

    @Override
    public String toString() {
        return "ISSPosition{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
