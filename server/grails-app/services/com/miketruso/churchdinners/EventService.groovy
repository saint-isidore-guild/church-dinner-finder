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
    List<Event> search(EventSearchCommand cmd) {
        EventCategory.createCriteria().listDistinct {
            projections{
                property('event')
            }
            category {
                if (cmd.categories) {
                    or {
                        cmd.categories.each {
                            ilike('name', "%${it}%")
                        }
                    }
                }
            }
            event {
                if (cmd.q) {
                    ilike('name', "%${cmd.q}%")
                }
                if (cmd.filterDateTimestamp) {
                    ZonedDateTime filterDate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(cmd.filterDateTimestamp), ZoneId.of('UTC'))
                    ge('startTime', filterDate.with(LocalTime.MIN))
                    le('endTime', filterDate.with(LocalTime.MAX))
                } else if (cmd.upcoming) {
                    ZonedDateTime now = ZonedDateTime.now(ZoneId.of('UTC'))
                    gt('endTime', now)
                    order('startTime', 'asc')
                }
            }
        } as List<Event>
    }
}
