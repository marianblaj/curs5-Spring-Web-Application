package ro.fasttrackit.Spring.Web.Application.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ro.fasttrackit.Spring.Web.Application.domain.Country;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.lineSeparator;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.util.stream.Collectors.toList;

@Component
public class CountryReader {

    public List<Country> readCountries() {
        try {
            return Files.lines(Path.of("src/main/resources/countries.txt"))
                    .map(this::readCountry)
                    .collect(toList());
        } catch (IOException e) {
            System.err.println("Could not read from file " + "src/main/resources/countries.txt");
        }
        return List.of();
    }

    private Country readCountry(String line) {
        String[] countryInfo = line.split("\\|");
        return new Country(1,
                countryInfo[0],
                countryInfo[1],
                Long.parseLong(countryInfo[2]),
                Long.parseLong(countryInfo[3]),
                Continent.of(countryInfo[4]).orElse(null),
                countryInfo.length > 5 ? parseNeighbours(countryInfo[5]) : List.of()
        );
    }

    private List<String> parseNeighbours(String neighbours) {
        if (neighbours != null) {
            return Arrays.stream(neighbours.split("~"))
                    .collect(toList());
        } else {
            return List.of();
        }
    }
}
