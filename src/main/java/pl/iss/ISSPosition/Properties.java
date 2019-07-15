package pl.iss.ISSPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Properties {
    private String message;
    private ISSPosition iss_position;
    private long timestamp;

    @Override
    public String toString() {
        return
                "ISS Position Reading Attempt: " + message + "\n"
                + iss_position + "\n" +
                "Timestamp:  " + timestamp;
    }
}
