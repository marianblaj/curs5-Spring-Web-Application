package ro.fasttrackit.Spring.Web.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.fasttrackit.Spring.Web.Application.service.CountryReader;

@SpringBootApplication
public class SpringWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringWebApplication.class, args);
	}

}
