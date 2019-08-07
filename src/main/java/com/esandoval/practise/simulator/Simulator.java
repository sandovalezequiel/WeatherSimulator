package com.esandoval.practise.simulator;

import com.esandoval.practise.planetarysystem.PlanetarySystem;
import com.esandoval.practise.utils.Weather;

import java.util.HashMap;
import java.util.Map;

import static com.esandoval.practise.utils.Weather.DROUGHT;
import static com.esandoval.practise.utils.Weather.OPTIMAL;
import static com.esandoval.practise.utils.Weather.RAIN;
import static com.esandoval.practise.utils.Weather.UNKNOWN;
import static java.lang.String.format;
import static java.util.Arrays.stream;

public class Simulator {

    private static final Integer daysInAYear = 365;

    private Integer daysOfSimulation;
    private Integer maxRainDay;
    private PlanetarySystem planetarySystem;
    private Map<Weather, Integer> periods;

    public Simulator(Integer yearsOfSimulation, PlanetarySystem system) {
        this.daysOfSimulation = yearsOfSimulation * daysInAYear;
        this.maxRainDay = -1;
        this.planetarySystem = system;
        this.periods = new HashMap<>();
        stream(Weather.values()).forEach(weather -> periods.put(weather, 0));
    }

    public void simulate() {
        Double simulationMaxRainBoundary = 0D;
        for (int currentDayInSimulation = 0; currentDayInSimulation < daysOfSimulation; currentDayInSimulation++, planetarySystem.move()) {
            Weather weather = planetarySystem.getCurrentWeather();
            Double maxPlanetarySystemRainBoundary = planetarySystem.getMaxRainBoundarySize();

            if (weather.equals(RAIN) && (simulationMaxRainBoundary < maxPlanetarySystemRainBoundary)) {
                maxRainDay = currentDayInSimulation;
                simulationMaxRainBoundary = maxPlanetarySystemRainBoundary;
            }

            periods.put(weather, periods.get(weather) + 1);
        }

        System.out.println("======================================================================");
        System.out.println(format("Drought periods: %s \nRain periods: %s\nOptimal periods: %s\nOther perioods: %s\n\nMax rain day in simulation:%s\n",
                periods.get(DROUGHT),
                periods.get(RAIN),
                periods.get(OPTIMAL),
                periods.get(UNKNOWN),
                maxRainDay > -1 ? maxRainDay : " No rainy periods on this years"));
    }

    public void clearResults(){
        this.maxRainDay = -1;
        this.periods = new HashMap<>();
        stream(Weather.values()).forEach(weather -> periods.put(weather, 0));
    }
}
