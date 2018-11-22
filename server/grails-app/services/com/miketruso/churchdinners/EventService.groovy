package com.miketruso.churchdinners

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Transactional
class EventService {

    @ReadOnly
    List<Event> upcomingEvents() {
        ZonedDateTime today = ZonedDateTime.now(ZoneId.of('America/Chicago'))

        Event.createCriteria().list() {
            lt('endTime', today) // FIXME returning all or none
            order('startTime', 'asc')
        } as List
    }

    @ReadOnly
    List<Event> search(EventSearchCommand cmd) {
        Event.createCriteria().list() {
            if (cmd.q) {
                ilike('name', "%${cmd.q}%")
            }
            if (cmd.filterDateTimestamp) {
                ZonedDateTime filterDate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(cmd.filterDateTimestamp), ZoneId.of('America/Chicago'))
                ge('startTime', filterDate.with(LocalTime.MIN))
                le('endTime', filterDate.with(LocalTime.MAX))
            }
        } as List<Event>
    }
}
