package org.eventify.Repository;

import org.eventify.models.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {
    Page<Site> findBySiteNameContainingIgnoreCase(String name, Pageable pageable);
}
