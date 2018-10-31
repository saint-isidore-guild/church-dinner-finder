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
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of('UTC'))
        Event.createCriteria().list {
            gt('endTime', now)
            order('startTime', 'asc')
        } as List
    }

    @ReadOnly
    List<Event> search(EventSearchCommand cmd) {
        Event.createCriteria().list {
            if (cmd.q) {
                ilike('name', "%${cmd.q}%")
            }
            if (cmd.filterDateTimestamp) {
                ZonedDateTime filterDate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(cmd.filterDateTimestamp), ZoneId.of('UTC'))
                ge('startTime', filterDate.with(LocalTime.MIN))
                le('endTime', filterDate.with(LocalTime.MAX))
            }
        } as List<Event>
    }
}
