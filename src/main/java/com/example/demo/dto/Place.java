package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Place {
    String locationName;
    String phenomenon;
    @Nullable
    String tempmax;
    @Nullable
    String tempmin;

    public Place(String locationName, String phenomenon, @Nullable String tempmax, @Nullable String tempmin) {
        this.locationName = locationName;
        this.phenomenon = phenomenon;
        this.tempmax = tempmax;
        this.tempmin = tempmin;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public String getTempmax() {
        return tempmax;
    }

    @Override
    public String toString() {
        return "Place{" +
                "locationName='" + locationName + '\'' +
                ", phenomenon='" + phenomenon + '\'' +
                ", tempmax='" + tempmax + '\'' +
                ", tempmin='" + tempmin + '\'' +
                '}';
    }
}
