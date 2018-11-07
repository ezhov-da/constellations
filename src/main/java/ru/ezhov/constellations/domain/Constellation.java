package ru.ezhov.constellations.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constellation {
    private String name;
    private Map<String, Star> stars;
    private Map<String, List<String>> starsRelation;
    private String info;

    public Constellation(String name, List<Star> stars) {
        this.name = name;
        this.stars = new HashMap<>();
        this.starsRelation = new HashMap<>();
        stars.forEach(s -> this.stars.put(s.getId(), s));
    }

    public Constellation() {
    }

    public void addStarRelation(String from, String to) {
        if (!starsRelation.containsKey(from)) {
            starsRelation.put(from, new ArrayList<>());
        }
        if (!starsRelation.get(from).contains(to)) {
            starsRelation.get(from).add(to);
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Map<String, Star> getStars() {
        return stars;
    }

    public Star getStar(String identifer) {
        return stars.get(identifer);
    }

    public Map<String, List<String>> getStarsRelation() {
        return starsRelation;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constellation that = (Constellation) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Constellation{" +
                "name='" + name + '\'' +
                ", stars=" + stars +
                ", starsRelation=" + starsRelation +
                ", info='" + info + '\'' +
                '}';
    }
}
