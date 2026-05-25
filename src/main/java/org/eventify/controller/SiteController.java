package org.eventify.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eventify.Service.SiteService;
import org.eventify.models.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Site", description = "Endpoints para la gestión de sitios (Sites)")
@RestController
@RequestMapping("/api/sites")
public class SiteController {

    public final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @Operation(summary = "Obtener un sitio por ID", description = "Devuelve los detalles de un sitio basándose en su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Sitio encontrado"),
            @ApiResponse(responseCode = "404", description = "Sitio no encontrado", content = @Content)
    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Site> getById(@PathVariable Long id){
        return siteService.getByID(id);
    }

    @Operation(summary = "Obtener todos los sitios", description = "Devuelve una lista paginada con todos los sitios registrados.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Site> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return siteService.getAll(pageable);
    }

    @Operation(summary = "Crear un nuevo sitio", description = "Registra un nuevo sitio en el sistema.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Site create(@RequestBody Site site){
        return siteService.create(site);
    }

    @Operation(summary = "Eliminar un sitio", description = "Elimina un sitio existente utilizando su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Sitio eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Sitio no encontrado", content = @Content)
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@PathVariable Long id){
        return siteService.delete(id);
    }

    @Operation(summary = "Actualizar un sitio", description = "Modifica los datos de un sitio existente.", responses = {
            @ApiResponse(responseCode = "200", description = "Sitio actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Sitio no encontrado", content = @Content)
    })
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Site update(@PathVariable Long id, @RequestBody Site site){
        return siteService.update(id, site);
    }
}
