package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the city name:");
        String cityName= sc.nextLine();

        try {
            //call the weather service
            WeatherService weatherService =new WeatherService();
            Weather weather=weatherService.getWeather(cityName);

            if (weather == null) {
                System.out.println("No weather data found for this city");
                return;
            }
            System.out.println("......The weather for city " + weather.getCity() + ".......");
            System.out.println("Temperature: " + weather.getTemp());
            System.out.println("Wind speed: " + weather.getWindSpeed());
        }catch (Exception e){
            System.out.println("Can't fetch weather's data");
        }

        }
    }