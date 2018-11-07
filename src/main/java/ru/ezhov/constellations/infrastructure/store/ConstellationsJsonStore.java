package ru.ezhov.constellations.infrastructure.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ezhov.constellations.domain.Constellations;

import java.io.File;
import java.io.IOException;

public class ConstellationsJsonStore implements ConstellationsStore {

    private File file;

    public ConstellationsJsonStore(File file) {
        this.file = file;
    }

    @Override
    public Constellations getAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Constellations constellations = mapper.readValue(file, Constellations.class);
        return constellations;
    }
}
