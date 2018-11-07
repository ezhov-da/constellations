package ru.ezhov.constellations.infrastructure.store;

import ru.ezhov.constellations.domain.Constellations;

import java.io.IOException;

public interface ConstellationsStore {

    Constellations getAll() throws IOException;
}
