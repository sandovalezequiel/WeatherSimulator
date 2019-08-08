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

    private Integer maxRainDay;
    private Map<Weather, Integer> periods;
    private Map<Integer, String> history;

    public Simulator(Integer yearsOfSimulation, PlanetarySystem planetarySystem) {
        this.maxRainDay = -1;
        this.periods = new HashMap<>();
        this.history = new HashMap<>();
        initialize(yearsOfSimulation, planetarySystem);
    }

    private void initialize(Integer yearsOfSimulation, PlanetarySystem planetarySystem){
        Double simulationMaxRainBoundary = 0D;
        Integer daysOfSimulation = yearsOfSimulation * daysInAYear;
        stream(Weather.values()).forEach(weather -> periods.put(weather, 0));

        for (int currentDayInSimulation = 0; currentDayInSimulation < daysOfSimulation; currentDayInSimulation++, planetarySystem.move()) {
            Weather weather = planetarySystem.getCurrentWeather();
            Double maxPlanetarySystemRainBoundary = planetarySystem.getMaxRainBoundarySize();

            if (weather.equals(RAIN) && (simulationMaxRainBoundary < maxPlanetarySystemRainBoundary)) {
                maxRainDay = currentDayInSimulation;
                simulationMaxRainBoundary = maxPlanetarySystemRainBoundary;
            }
            history.put(currentDayInSimulation, weather.toString());
            periods.put(weather, periods.get(weather) + 1);
        }
    }

    public void printResults() {
        System.out.println("======================================================================");
        System.out.println(format("Drought periods: %s \nRain periods: %s\nOptimal periods: %s\nOther perioods: %s\n\nMax rain day in simulation:%s\n",
                periods.get(DROUGHT),
                periods.get(RAIN),
                periods.get(OPTIMAL),
                periods.get(UNKNOWN),
                maxRainDay > -1 ? maxRainDay : " No rainy periods on this years"));
    }

    public Map<Integer, String> getHistory(){
        return history;
    }
}
