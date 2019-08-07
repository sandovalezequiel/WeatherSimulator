package com.esandoval.practise.astrobody;

import com.esandoval.practise.utils.Coordinate;

public class CenterSun implements AstroBody {

    private Coordinate coordinate;
    private String name;

    public CenterSun(String name){
        this.name = name;
        coordinate = new Coordinate(0D, 0D);
    }

    public String getName() {
        return name;
    }

    public void move() {

    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
