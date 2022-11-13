package com.example.demo.dto;

import java.util.List;

public class Forecast {
    private String date;
    private final ForecastData nightData;
    private final ForecastData dayData;

    public Forecast(String date, ForecastData night, ForecastData day) {
        this.date = date;
        this.nightData = night;
        this.dayData = day;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", nightData=" + nightData.toString() +
                ", dayData=" + dayData.toString() +
                '}';
    }

    public String getDate() {
        return date;
    }

    public ForecastData getNightData() {
        return nightData;
    }

    public ForecastData getDayData() {
        return dayData;
    }
}
