package pl.iss.ISSPosition.position;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.iss.ISSPosition.position.Properties;

import java.io.IOException;
import java.net.URL;

public class Reader {
    public Properties readISSProperties(){
        Properties properties = new Properties();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            properties = objectMapper.readValue(new URL("http://api.open-notify.org/iss-now.json"),Properties.class);
            //todo crew list
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
