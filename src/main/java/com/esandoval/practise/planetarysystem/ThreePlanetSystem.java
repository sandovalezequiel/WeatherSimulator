package com.esandoval.practise.planetarysystem;

import com.esandoval.practise.astrobody.Planet;
import com.esandoval.practise.utils.Coordinate;
import com.esandoval.practise.utils.Weather;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static com.esandoval.practise.utils.Weather.DROUGHT;
import static com.esandoval.practise.utils.Weather.OPTIMAL;
import static com.esandoval.practise.utils.Weather.RAIN;
import static com.esandoval.practise.utils.Weather.UNKNOWN;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.math3.geometry.partitioning.Region.Location.INSIDE;

public class ThreePlanetSystem extends PlanetarySystem {

    private static final Double hyperplaneThickness = 0.01D;

    public ThreePlanetSystem(Planet planet1, Planet planet2, Planet planet3) {
        super(planet1, planet2, planet3);
        if(astroBodies.stream().map(planet -> new Vector2D(planet.getCoordinate().getX(), planet.getCoordinate().getY())).collect(toSet()).size() < 3){
            throw new RuntimeException("Two or more planets are starting on the same position");
        }
    }

    @Override
    public Weather getCurrentWeather() {
        Coordinate coordP1 = astroBodies.get(0).getCoordinate();
        Coordinate coordP2 = astroBodies.get(1).getCoordinate();
        Coordinate coordP3 = astroBodies.get(2).getCoordinate();

        Vector2D vPlanet1 = new Vector2D(coordP1.getX(), coordP1.getY());
        Vector2D vPlanet2 = new Vector2D(coordP2.getX(), coordP2.getY());
        Vector2D vPlanet3 = new Vector2D(coordP3.getX(), coordP3.getY());
        Vector2D vCenter = new Vector2D(sun.getCoordinate().getX(), sun.getCoordinate().getY());

        Line linePlanet1Planet2 =  new Line(vPlanet1, vPlanet2);

        if(linePlanet1Planet2.contains(vPlanet3)){
            return linePlanet1Planet2.contains(vCenter)? DROUGHT : OPTIMAL;
        }

        PolygonsSet triangle = new PolygonsSet(hyperplaneThickness, vPlanet1, vPlanet2, vPlanet3);

        if(triangle.checkPoint(vCenter).equals(INSIDE)){
            Double actualBoundarySize = triangle.getBoundarySize();
            maxRainBoundarySize =  actualBoundarySize > maxRainBoundarySize ? actualBoundarySize : maxRainBoundarySize;
            return RAIN;
        }

        return UNKNOWN;
    }
}
