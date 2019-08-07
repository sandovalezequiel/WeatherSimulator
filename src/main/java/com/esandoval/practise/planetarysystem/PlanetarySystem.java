package com.esandoval.practise.planetarysystem;

import com.esandoval.practise.astrobody.AstroBody;
import com.esandoval.practise.astrobody.CenterSun;
import com.esandoval.practise.utils.Weather;

import java.util.ArrayList;
import java.util.List;

public abstract class PlanetarySystem {

    protected final AstroBody sun = new CenterSun("Sun");

    protected List<AstroBody> astroBodies;
    protected Double maxRainBoundarySize;

    public PlanetarySystem(AstroBody... astroBodies) {
        this.maxRainBoundarySize = 0D;
        this.astroBodies = new ArrayList();
        for (AstroBody astroBody : astroBodies) {
            this.astroBodies.add(astroBody);
        }
    }

    public void move() {
        astroBodies.stream().forEach(astroBody -> astroBody.move());
    }

    public abstract Weather getCurrentWeather();

    public Double getMaxRainBoundarySize() {
        return maxRainBoundarySize;
    }
}
