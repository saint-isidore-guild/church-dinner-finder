package com.miketruso.churchdinners

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

import java.time.ZonedDateTime

@Transactional
class EventService {

    @ReadOnly
    List<Event> upcomingEvents() {
        ZonedDateTime today = ZonedDateTime.now()
        Event.createCriteria().list {
            le('startTime', today)
            order('startTime', 'asc')
        } as List
    }
}
