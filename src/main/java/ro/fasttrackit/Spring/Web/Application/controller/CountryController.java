package ro.fasttrackit.Spring.Web.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import ro.fasttrackit.Spring.Web.Application.domain.Country;
import ro.fasttrackit.Spring.Web.Application.service.Continent;
import ro.fasttrackit.Spring.Web.Application.service.CountryReader;
import ro.fasttrackit.Spring.Web.Application.service.CountryService;

import javax.annotation.ManagedBean;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping()
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/countries/names")
    public List<String> getCountriesNames() {
        return countryService.getCountriesNames();
    }

    @GetMapping("/countries/{countryId}/capital")
    public String getCountryCapital(@PathVariable int countryId) {
        return countryService.getCountryCapital(countryId);
    }

    @GetMapping("/countries/{countryId}/population")
    public long getCountryPopulation(@PathVariable int countryId) {
        return countryService.getCountryPopulation(countryId);
    }

    @GetMapping("/continents/{continentName}/countries")
    public List<Country> getContinentCountries(@PathVariable String continentName) {
        return countryService.getContinentCountries(continentName);
    }

    @GetMapping("/countries/{countryId}/neighbours")
    public List<String> getCountryNeighbours(@PathVariable int countryId) {
        return countryService.getCountryNeighbours(countryId);
    }

    @GetMapping(value = "/continents/{continentName}/countries", params = "minPopulation")
    public List<Country> getCountriesPopulationLargerThanMinPopulation(
            @PathVariable String continentName, @RequestParam long minPopulation
    ) {
        return countryService.getCountriesPopulationLargerThanMinPopulation(continentName, minPopulation);
    }

    @GetMapping(value = "/countries", params = {"includeNeighbour", "excludeNeighbour"})
    public List<Country> getCountriesWithIncludeNeighbourWithoutExcludeNeighbour(
            @RequestParam String includeNeighbour, String excludeNeighbour
    ) {
        return countryService.getCountriesWithIncludeNeighbourWithoutExcludeNeighbour(includeNeighbour, excludeNeighbour);
    }

    @GetMapping("/countries/population")
    public Map<String, Long> getCountriesPopulation() {
        return countryService.getCountriesPopulation();
    }

    @GetMapping("/continents/countries")
    public Map<String, List<Country>> getContinentsCountries() {
        return countryService.getContinentsCountries();
    }

    @SessionScope
    @GetMapping("/countries/mine")
    public Optional<Country> getCountry(@RequestHeader("X-Country") String countryName) {
        return countryService.getCountry(countryName);
    }
}
