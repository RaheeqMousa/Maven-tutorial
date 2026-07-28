package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.net.http.HttpClient;

public class WeatherService {

    //this converts the JSON to JAVA objects
    private ObjectMapper mapper = new ObjectMapper();

    private String sendRequest(String url) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            ClassicHttpResponse response = client.execute(request);

            String result = EntityUtils.toString(response.getEntity());

            client.close();

            return result;
        }catch(IOException ex){
            return null;
        }catch (ParseException e){
            return null;
        }
    }

    public Weather getWeather(String cityName){
        try {
            String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + cityName + "&count=1";

            String locResponse = sendRequest(geoUrl);
            JsonNode locationObject = mapper.readTree(locResponse);
            JsonNode cityLocation = locationObject.get("results").get(0);
            double lat = cityLocation.get("latitude").asDouble();
            double lang = cityLocation.get("longitude").asDouble();

            String weatherUrl =
                    "https://api.open-meteo.com/v1/forecast"
                            + "?latitude=" + lat
                            + "&longitude=" + lang
                            + "&current_weather=true";
            String weatherRes = sendRequest(weatherUrl);
            JsonNode weatherObject = mapper.readTree(weatherRes);
            JsonNode weatherData = weatherObject.get("current_weather");
            double temp = weatherData.get("temperature").asDouble();
            double windSpeed = weatherData.get("windspeed").asDouble();

            return new Weather(cityName, temp, windSpeed);
        }catch (JsonProcessingException e) {
                throw new RuntimeException(e);
        }
    }
}
