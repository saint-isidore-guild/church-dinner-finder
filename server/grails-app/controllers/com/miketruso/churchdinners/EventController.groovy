package com.miketruso.churchdinners

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import grails.converters.*
import org.springframework.http.HttpStatus

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@ReadOnly
class EventController {
	static responseFormats = ['json', 'xml']
	
    def index() {
        def events = Event.list().collect {
            [
                    id: it.id,
                    name: it.name,
                    description: it.description,
                    startTime: it.startTime.toEpochSecond(),
                    endTime: it.endTime.toEpochSecond(),
                    website: it.website
            ]
        }
        render events as JSON
    }

    @Transactional
    def save(EventCommand cmd) {
        if (cmd.hasErrors()) {
            render status: HttpStatus.BAD_REQUEST
            return
        }

        Event event = new Event()
        bindData(event, cmd, ['startTime', 'endTime'])
        ZonedDateTime startTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(cmd.startTime), ZoneId.of('America/Chicago'))
        ZonedDateTime endTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(cmd.endTime), ZoneId.of('America/Chicago'))
        event.startTime = startTime
        event.endTime = endTime

        if (!event.save()){
            render status: HttpStatus.BAD_REQUEST
            return
        }
        response.status = HttpStatus.CREATED.value()
        render ([id: event.id] as JSON)
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
    }
}
