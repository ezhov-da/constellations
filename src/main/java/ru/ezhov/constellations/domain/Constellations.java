package ru.ezhov.constellations.domain;

import java.util.ArrayList;
import java.util.List;

public class Constellations {
    private List<Constellation> constellations;

    public List<Constellation> getConstellations() {
        return constellations;
    }

    public Constellations() {
        constellations = new ArrayList<>();
    }

    public Constellations(List<Constellation> constellations) {
        this.constellations = constellations;
    }

    public void addConstellation(Constellation constellation) {
        if (!constellations.contains(constellation)) {
            constellations.add(constellation);
        }
    }

    @Override
    public String toString() {
        return "Constellations{" +
                "constellations=" + constellations +
                '}';
    }
}
