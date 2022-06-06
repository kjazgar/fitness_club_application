package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.services.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RestController
@Component
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping (value = "/v1/events", consumes = "application/json")
    public ResponseEntity<Object> addEvent(@RequestBody EventRecord event){
        return eventService.addEvent(event);
    }

    @GetMapping("/v1/events/page")
    public Page<EventRecord> getAll(Pageable p){
        return eventService.getPage(p);
    }

    @GetMapping("/v1/events")
    public ResponseEntity<?> printEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/v1/events/{id}")
    public ResponseEntity<?> printEventWithId(@PathVariable int id){
        Optional<EventRecord> event = eventService.getEvent(id);
        if(event.isPresent()){
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/v1/events", params = "coachId")
    public ResponseEntity<?> printAllEventsByTheCoach(@RequestParam("coachId") int coachId){
        List<EventRecord> events = eventService.getEventsByCoach(coachId);
        return ResponseEntity.ok(events);
    }

    @GetMapping(value = "/v1/events", params = "clubId")
    public ResponseEntity<?> printAllEventsByTheClub(@RequestParam("clubId") int clubId){
        List<EventRecord> events = eventService.getEventsByClub(clubId);
        return ResponseEntity.ok(events);
    }

    @PatchMapping(path = "/v1/events/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable int id, @RequestBody EventRecord newEvent) {
        Optional<EventRecord> updatedEvent = eventService.getEvent(id);
        if (updatedEvent.isPresent()) {
            return eventService.updateEvent(id, newEvent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable(value = "id") int id) {
        return eventService.deleteEvent(id);
    }
}
