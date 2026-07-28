import org.example.Weather;
import org.example.WeatherService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherTest {

    @Test
    public void testGetWeather(){
        WeatherService service= new WeatherService();

        Weather weather=service.getWeather("Ramallah");
        assertNotNull(weather);

        assertEquals("Ramallah", weather.getCity());

    }
}
