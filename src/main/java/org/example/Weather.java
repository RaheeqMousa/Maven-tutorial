package org.example;

public class Weather {
    private String city;
    private double temp;
    private double windSpeed;

    public Weather() {
    }

    public Weather(String city, double temp, double windSpeed) {
        this.city = city;
        this.temp = temp;
        this.windSpeed=windSpeed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", temp=" + temp +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
