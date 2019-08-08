package com.esandoval.practise;

import com.esandoval.practise.astrobody.Planet;
import com.esandoval.practise.planetarysystem.ThreePlanetSystem;
import com.esandoval.practise.simulator.Simulator;

public class App {
    public static void main(String[] args) {
        new Simulator(10,
                new ThreePlanetSystem(
                        new Planet("Ferengi", 1, 500, true),
                        new Planet("Betasoide", 3, 2000, true),
                        new Planet("Vulcano", 5, 1000, false))
        ).printResults();
    }
}
