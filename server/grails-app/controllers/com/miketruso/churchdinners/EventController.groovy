package com.miketruso.churchdinners

import grails.converters.JSON
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import org.springframework.http.HttpStatus

@ReadOnly
class EventController {
    static responseFormats = ['json']

    EventService eventService

    def index() {
        def events = eventService.search(new EventSearchCommand(upcoming: true))
        Integer total = events.totalCount
        render view: 'list', model: [events: events, total: total]
    }

    def show(Event event) {
        if (!event) {
            render status: HttpStatus.BAD_REQUEST
            return
        }
        def categories = EventCategory.findAllByEvent(event)*.category
        [event: event, categories: categories]
    }

    @Transactional
    def save(EventCommand cmd) {
        if (cmd.hasErrors()) {
            render status: HttpStatus.BAD_REQUEST
            return
        }
        Event savedEvent = eventService.save(cmd)
        if (!savedEvent) {
            render status: HttpStatus.BAD_REQUEST
            return
        }
        log.info("New Event Created id=${savedEvent.id}")
        response.status = HttpStatus.CREATED.value()
        render([id: savedEvent.id] as JSON)
    }

    @Transactional
    def delete(Event event) {
        event.delete()
        render status: HttpStatus.NO_CONTENT
    }

    def search(EventSearchCommand cmd) {
        def events = eventService.search(cmd)
        Integer total = events.totalCount
        render view: 'list', model: [events: events, total: total]
    }
}

class EventCommand {
    String name
    String description
    String website
    String email
    String phone
    String organizer
    Long startTime
    Long endTime
    Venue venue
    List<Category> categories

    static constraints = {
        name nullable: false
        description nullable: true
        website nullable: true, url: true
        email nullable: true, email: true
        phone nullable: true
        organizer nullable: true
        startTime nullable: false
        endTime nullable: false
        venue nullable: false
        categories nullable: true
    }
}
