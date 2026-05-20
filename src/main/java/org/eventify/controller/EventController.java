package org.eventify.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eventify.Service.EventService;
import org.eventify.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Event", description = "Endpoints para la gestión de eventos (Events)")
@RestController
@RequestMapping("/event/")
public class EventController {

    public final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Obtener un evento por ID", description = "Devuelve los detalles de un evento basándose en su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado", content = @Content)
    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Event> getById(@PathVariable Long id){
        return eventService.getByID(id);
    }

    @Operation(summary = "Obtener todos los eventos", description = "Devuelve una lista paginada con todos los eventos registrados.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Event> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return eventService.getAll(pageable);
    }

    @Operation(summary = "Crear un nuevo evento", description = "Registra un nuevo evento en el sistema.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@RequestBody Event event){
        return eventService.create(event);
    }

    @Operation(summary = "Eliminar un evento", description = "Elimina un evento existente utilizando su ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Evento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado", content = @Content)
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@PathVariable Long id){
        return eventService.delete(id);
    }

    @Operation(summary = "Actualizar un evento", description = "Modifica los datos de un evento existente.", responses = {
            @ApiResponse(responseCode = "200", description = "Evento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado", content = @Content)
    })
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event update(@PathVariable Long id, @RequestBody Event event){
        return eventService.update(id, event);
    }
}
