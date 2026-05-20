package org.eventify.Service;

import org.eventify.Repository.SiteRepository;
import org.eventify.models.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Page<Site> getAll(Pageable pageable){
        return siteRepository.findAll(pageable);
    }

    public Optional<Site> getByID(Long id){
        return Optional.ofNullable(siteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sitio no encontrado con ID: " + id)));
    }

    public Site create(Site site){
        if(site == null || site.getSiteName() == null || site.getSiteName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los campos obligatorios deben ser llenados");
        }
        return siteRepository.save(site);
    }

    public boolean delete(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de entrada debe ser válido");
        }
        if(!siteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sitio no encontrado con ID: " + id);
        }
        siteRepository.deleteById(id);
        return true;
    }

    public Site update(Long id, Site site){
        if(id == null || site == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Complete todos los parámetros");
        }
        Site existingSite = siteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sitio no encontrado con ID: " + id));
        
        existingSite.setSiteName(site.getSiteName());
        existingSite.setAddress(site.getAddress());
        existingSite.setCity(site.getCity());
        
        return siteRepository.save(existingSite);
    }
}
