package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Wind {
    String locationName;
    String direction;
    String speedMin;
    String speedMax;
    String gustSpeed;

    public Wind(String locationName, String direction, String speedMin, String speedMax, String gustSpeed) {
        this.locationName = locationName;
        this.direction = direction;
        this.speedMin = speedMin;
        this.speedMax = speedMax;
        this.gustSpeed = gustSpeed;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getDirection() {
        return direction;
    }

    public String getSpeedMin() {
        return speedMin;
    }

    public String getSpeedMax() {
        return speedMax;
    }

    public String getGustSpeed() {
        return gustSpeed;
    }

}
