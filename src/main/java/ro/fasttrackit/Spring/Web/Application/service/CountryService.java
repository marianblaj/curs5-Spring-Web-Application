package ro.fasttrackit.Spring.Web.Application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fasttrackit.Spring.Web.Application.domain.Country;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.springframework.boot.system.SystemProperties.get;

@Service
public class CountryService {

    private final List<Country> countries;


    public CountryService(CountryReader countryReader) {
        countries = countryReader.readCountries();
    }

    public List<Country> getAllCountries() {
        return new ArrayList<>(countries);
    }

    public List<String> getCountriesNames() {
        return countries.stream()
                .map(Country::getName)
                .collect(Collectors.toList());
    }

    public String getCountryCapital(int countryId) {
        Optional<String> capital = countries.stream()
                .filter(element -> element.getId() == countryId)
                .findFirst()
                .map(Country::getCapital);
        return capital.orElse("No such country id.");
    }

    public long getCountryPopulation(int countryId) {
        Optional<Long> population = countries.stream()
                .filter(element -> element.getId() == countryId)
                .findFirst()
                .map(Country::getPopulation);
        return population.orElse(0L);
    }

    public List<Country> getContinentCountries(String continentName) {
        return countries.stream()
                .filter(value -> value.getContinent() != null)
                .filter(element -> element.getContinent().toString().equalsIgnoreCase(continentName))
                .collect(Collectors.toList());
    }

    public List<String> getCountryNeighbours(int countryId) {
        return countries.stream()
                .filter(element -> element.getId() == countryId)
                .map(Country::getNeighbours)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public List<Country> getCountriesPopulationLargerThanMinPopulation(String continentName, long minPopulation) {
        List<Country> result = countries.stream()
                .filter(value -> value.getContinent() != null)
                .filter(element -> element.getContinent().toString().equalsIgnoreCase(continentName))
                .filter(element -> element.getPopulation() > minPopulation)
                .collect(Collectors.toList());
        if (!result.isEmpty())
            return result;
        else
            return List.of();
    }

    public List<Country> getCountriesWithIncludeNeighbourWithoutExcludeNeighbour(String includeNeighbour, String excludeNeighbour) {
        List<Country> result = countries.stream()
                .filter(country -> country.getNeighbours()
                        .stream()
                        .anyMatch(element -> element.equals(includeNeighbour)))
                .filter(country -> country.getNeighbours()
                        .stream()
                        .anyMatch(element -> !element.equalsIgnoreCase(excludeNeighbour)))
                .collect(Collectors.toList());

        if (!result.isEmpty())
            return result;
        else
            return List.of();
    }


    public Map<String, Long> getCountriesPopulation() {

        return countries.stream()
                .collect(Collectors.toMap(Country::getName, Country::getPopulation));

    }

    public Map<String, List<Country>> getContinentsCountries() {

        return countries.stream()
                .filter(value -> value.getContinent() != null)
                .collect(Collectors.groupingBy(country -> country.getContinent().toString()));
    }

    public Optional<Country> getCountry(String countryName) {
        var result = countries.stream()
                .filter(element -> element.getName().equalsIgnoreCase(countryName))
                .findFirst();
        return result;
    }
}
