package com.esandoval.practise.astrobody;

import com.esandoval.practise.utils.Coordinate;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Planet implements AstroBody {

    private String name;
    private Integer currentAngle;
    private Integer angVel;
    private Integer radius;
    private Boolean clockwiseDir;

    public Planet(String name, Integer angVel, Integer radius, Boolean clockwiseDir) {
        this.name = name;
        this.angVel = abs(angVel);
        this.radius = abs(radius);
        this.clockwiseDir = clockwiseDir;
        this.currentAngle = 0;
    }

    public void move() {
        Integer nextAngle = currentAngle + angVel;
        currentAngle = nextAngle < 360 ? nextAngle : nextAngle - 360;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(radius * cos(getAngleInRadians()), radius * sin(getAngleInRadians()));
    }

    public String getName() {
        return name;
    }

    private Double getAngleInRadians() {
        return toRadians(clockwiseDir ? -currentAngle : currentAngle);
    }
}
