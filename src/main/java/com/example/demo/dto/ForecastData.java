package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForecastData {
    private final String phenomenon;
    private final String tempMin;
    private final String tempMax;
    private final String text;
    private List<Place> placeList;
    private List<Wind> windList;
    private String sea;
    private String peipsi;

    public ForecastData(String phenomenon, String tempMin, String tempMax, String text, List<Place> place, List<Wind> wind, String sea, String peipsi) {
        this.phenomenon = phenomenon;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.text = text;
        this.placeList = place;
        this.windList = wind;
        this.sea = sea;
        this.peipsi = peipsi;
    }

    public ForecastData(String phenomenon, String tempMin, String tempMax, String text) {
        this.phenomenon = phenomenon;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.text = text;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getText() {
        return text;
    }

    @Nullable
    public List<Place> getPlaceList() {
        return placeList;
    }

    @Nullable
    public List<Wind> getWindList() {
        return windList;
    }

    @Nullable
    public String getSea() {
        return sea;
    }

    @Nullable
    public String getPeipsi() {
        return peipsi;
    }

    @Override
    public String toString() {
        return "ForecastData{phenomenon='%s%n', tempMin='%s%n', tempMax='%s%n', text='%s%n', placeList=%s%n, windList=%s%n, sea='%s%n', peipsi='%s%n'}"
                .formatted(phenomenon, tempMin, tempMax, text, placeList, windList, sea, peipsi);
    }
}
