package com.miketruso.churchdinners

import grails.gorm.DetachedCriteria
import grails.gorm.PagedResultList
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import grails.web.databinding.DataBindingUtils

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
                ZonedDateTime filterDate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(cmd.filterDateTimestamp), ZoneId.of('America/Chicago'))
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

    Event save(EventCommand cmd, Event event = null) {
        if (!event) {
            event = new Event()
        }
        DataBindingUtils.bindObjectToInstance(event, cmd, null, ['startTime', 'endTime', 'categories'], null)
        ZonedDateTime startTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(cmd.startTime), ZoneId.of('UTC'))
        ZonedDateTime endTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(cmd.endTime), ZoneId.of('UTC'))
        event.startTime = startTime
        event.endTime = endTime
        event.hasCost = event.costDescription ? true : false
        if (!event.save()) {
            log.warn(event.errors)
            return null
        }
        updateCategories(event, cmd.categories)
        event
    }

    void updateCategories(Event event, List<Category> categories) {
        if (!categories) {
            EventCategory.executeUpdate('delete from EventCategory ec where ec.event = :event', [event: event])
        } else {
            categories.each { category ->
                EventCategory.findOrCreateWhere(event: event, category: category).save(flush: true)
            }
            EventCategory.executeUpdate('delete from EventCategory ec where ec.event = :event and ec.category not in(:categoryList)', [event: event, categoryList: categories])
        }
    }
}
