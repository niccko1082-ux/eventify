package org.eventify.Repository;

import org.eventify.models.Site;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SiteRepositoryTest {

    @Autowired
    private SiteRepository siteRepository;

    @Test
    public void testSaveAndFindBySiteNameContainingIgnoreCase() {
        // Arrange
        Site site = new Site(null, "Gran Teatro", "Avenida Siempre Viva 123", "Springfield");
        siteRepository.save(site);

        Site site2 = new Site(null, "Teatro Pequeño", "Calle Falsa 456", "Shelbyville");
        siteRepository.save(site2);

        // Act
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Site> result = siteRepository.findBySiteNameContainingIgnoreCase("teatro", pageable);

        // Assert
        assertEquals(2, result.getTotalElements());
        assertTrue(result.getContent().stream().anyMatch(s -> s.getSiteName().equals("Gran Teatro")));
    }
}
