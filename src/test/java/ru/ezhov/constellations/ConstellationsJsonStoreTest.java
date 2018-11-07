package ru.ezhov.constellations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ru.ezhov.constellations.domain.Constellation;
import ru.ezhov.constellations.domain.Constellations;
import ru.ezhov.constellations.domain.Star;
import ru.ezhov.constellations.infrastructure.store.ConstellationsJsonStore;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConstellationsJsonStoreTest {
    @Test
    public void save() {
        List<Star> stars = Arrays.<Star>asList(
                new Star("1", 131, 81, 5).setName("Мицар"),
                new Star("2", 193, 78, 5),
                new Star("3", 225, 102, 5),
                new Star("4", 266, 131, 5),
                new Star("5", 357, 136, 5),
                new Star("6", 338, 182, 5),
                new Star("7", 269, 171, 5)
        );
        Constellation constellation = new Constellation("Большая медведица", stars);

        constellation.addStarRelation("1", "2");
        constellation.addStarRelation("2", "3");
        constellation.addStarRelation("3", "4");
        constellation.addStarRelation("4", "5");
        constellation.addStarRelation("4", "7");
        constellation.addStarRelation("5", "6");
        constellation.addStarRelation("6", "7");

        Constellations constellations = new Constellations(Arrays.asList(constellation));

        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(constellations);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAll() throws IOException {
        ConstellationsJsonStore constellationsJsonStore = new ConstellationsJsonStore(new File("constellations.json"));
        Constellations constellations = constellationsJsonStore.getAll();
        System.out.println(constellations);
    }
}
