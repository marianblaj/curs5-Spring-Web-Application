package ro.fasttrackit.Spring.Web.Application.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import ro.fasttrackit.Spring.Web.Application.service.Continent;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

//@Value
//@RequiredArgsConstructor
@Data
public class Country {


    private int id;

    private final String name;
    private final String capital;
    private final long population;
    private final long area;
    private final Continent continent;
    private final List<String> neighbours;

    public Country(long id, String name, String capital, long population, long area, Continent continent, List<String> neighbours) {

        this.id = java.lang.System.identityHashCode(this);
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = neighbours;
    }
}



