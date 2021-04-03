package ro.fasttrackit.Spring.Web.Application.service;

import java.util.Optional;
import java.util.stream.Stream;

public enum Continent {

    EUROPE,
    ASIA,
    AFRICA,
    AMERICA,
    AUSTRALIA;

    public static Optional<Continent> of(String continentName) {
        return Stream.of(values())
                .filter(val -> val.name().equalsIgnoreCase(continentName))
                .findFirst();
    }
}


