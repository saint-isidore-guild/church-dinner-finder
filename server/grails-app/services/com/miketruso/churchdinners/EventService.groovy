package com.miketruso.churchdinners

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

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
}
