package org.eventify.configuration;

import org.eventify.Repository.EventRepository;
import org.eventify.Repository.SiteRepository;
import org.eventify.models.Event;
import org.eventify.models.Site;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    CommandLineRunner loadInitialRunData(EventRepository eventRepository, SiteRepository siteRepository) {
        return args -> {
            // Cargar eventos iniciales
            eventRepository.save(new Event(null, "Concierto Rock", "Música"));
            eventRepository.save(new Event(null, "Feria de Tecnología", "Tecnología"));

            // Cargar sitios iniciales
            siteRepository.save(new Site(null, "Estadio Nacional", "Av. Principal 123", "Ciudad de México"));
            siteRepository.save(new Site(null, "Centro de Convenciones", "Calle Falsa 123", "Buenos Aires"));
            
            System.out.println("Datos iniciales de Eventify cargados correctamente en memoria.");
        };
    }
}
