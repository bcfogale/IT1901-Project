package rest; //TODO: endre til riktig package CleanE\springboot\restserver\src\restserver

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
I todoeksempel importeres også disse:

import com.fasterxml.jackson.databind.Module; 
import org.springframework.context.annotation.Bean;
import todolist.json.TodoPersistence;

 */

/**
 * Spring-applikasjonen.
 */
@SpringBootApplication
public class CleanEModelApplication {

/* Dette brukes i todoeksempel, men vet ikke hvordan vi skal implementere det enda.
    @Bean
    public Module objectMapperModule() {
      return TodoPersistence.createJacksonModule(false);
    }
 */  

	public static void main(String[] args) {
		SpringApplication.run(CleanEModelApplication.class, args);
	}

}
