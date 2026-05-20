package org.eventify.Service;

import org.eventify.Repository.EventRepository;
import org.eventify.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Page<Event> getAll(Pageable pageable){
        return eventRepository.findAll(pageable);
    }

    public Optional<Event> getByID(Long id){
        return Optional.ofNullable(eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado con ID: " + id)));
    }

    public Event create(Event event){
        if(event == null || event.getName().isEmpty() || event.getType().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los campos obligatorios deben ser llenados");
        }
        return eventRepository.save(event);
    }

    public boolean delete (Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de entrada debe ser válido");
        }
        if(!eventRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado con ID: " + id);
        }
        eventRepository.deleteById(id);
        return true;
    }

    public Event update(Long id, Event event){
        if(id == null || event == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Complete todos los parámetros");
        }
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado con ID: " + id));
        
        existingEvent.setName(event.getName());
        existingEvent.setType(event.getType());
        
        return eventRepository.save(existingEvent);
    }
}
