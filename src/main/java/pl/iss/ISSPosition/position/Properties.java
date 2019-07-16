package pl.iss.ISSPosition.position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.iss.ISSPosition.position.ISSPosition;

import java.util.Observable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Properties extends Observable {
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
