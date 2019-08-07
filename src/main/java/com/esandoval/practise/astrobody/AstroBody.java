package com.esandoval.practise.astrobody;

import com.esandoval.practise.utils.Coordinate;

public interface AstroBody {

    String getName();

    void move();

    Coordinate getCoordinate();
}
