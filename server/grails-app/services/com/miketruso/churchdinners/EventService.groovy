package com.miketruso.churchdinners

import grails.gorm.DetachedCriteria
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import grails.gorm.PagedResultList

import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Transactional
class EventService {

    @ReadOnly
    PagedResultList<Event> search(EventSearchCommand cmd) {
        Long max = cmd.max ? Math.min(cmd.max, 100) : 10 // hard limit of 100 results, default 10
        Event.createCriteria().list(max: max, offset: cmd.offset ?: 0) {
            if (cmd.q) {
                ilike('name', "%${cmd.q}%")
            }
            if (cmd.filterDateTimestamp) {
                ZonedDateTime filterDate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(cmd.filterDateTimestamp), ZoneId.of('UTC'))
                or {
                    and {
                        // events that start and stop on the same day
                        ge('startTime', filterDate.with(LocalTime.MIN))
                        le('endTime', filterDate.with(LocalTime.MAX))
                    }
                    and {
                        //events that span multiple days
                        le('startTime', filterDate.with(LocalTime.MAX))
                        ge('endTime', filterDate.with(LocalTime.MIN))
                    }
                }
            } else if (cmd.upcoming) {
                ZonedDateTime now = ZonedDateTime.now(ZoneId.of('UTC'))
                gt('endTime', now)
            }
            if (cmd.categories) {
                'in'('id', buildCategoryCriteria(cmd.categories))
            }
            order('startTime', 'asc')
        } as PagedResultList<Event>
    }

    DetachedCriteria<EventCategory> buildCategoryCriteria(List<String> categories) {
        new DetachedCriteria(EventCategory).build {
            projections {
                property('event.id')
            }
            category {
                or {
                    categories.each {
                        ilike('name', "%${it}%")
                    }
                }
            }
        }
    }
}
