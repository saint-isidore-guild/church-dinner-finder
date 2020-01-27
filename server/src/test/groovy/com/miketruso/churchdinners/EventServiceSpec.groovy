package com.miketruso.churchdinners

import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest

import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

class EventServiceSpec extends HibernateSpec implements ServiceUnitTest<EventService> {

    Venue venue

    def setup() {
        Category category1 = new Category(name: 'Festival').save(failOnError: true, flush: true)
        Category category2 = new Category(name: 'Breakfast').save(failOnError: true, flush: true)
        Category category3 = new Category(name: 'Fish Fry').save(failOnError: true, flush: true)

        ZonedDateTime today = ZonedDateTime.now()
        ZonedDateTime tomorrow = today.plusDays(1)
        venue = new Venue(name: 'v1', address1: '1 Lourdes Pl', city: 'Minneapolis', state: 'MN', zip: '55414').save(flush: true, failOnError: true)
        Event event1 = new Event(name: 'ev1', startTime: today, endTime: tomorrow, venue: venue).save(failOnError: true, flush: true)
        Event event2 = new Event(name: 'ev2', startTime: today, endTime: tomorrow, venue: venue).save(failOnError: true, flush: true)
        Event event3 = new Event(name: 'ev3', startTime: today, endTime: tomorrow, venue: venue).save(failOnError: true, flush: true)
        Event event4 = new Event(name: 'ev4', startTime: today, endTime: tomorrow, venue: venue).save(failOnError: true, flush: true)

        new EventCategory(event: event1, category: category1).save(failOnError: true, flush: true)
        new EventCategory(event: event1, category: category2).save(failOnError: true, flush: true)
        new EventCategory(event: event2, category: category1).save(failOnError: true, flush: true)
        new EventCategory(event: event3, category: category2).save(failOnError: true, flush: true)
        new EventCategory(event: event4, category: category3).save(failOnError: true, flush: true)
    }

    def "buildCategoryCriteria"() {
        List categories = ['Festival', 'Breakfast']
        when:
        def detachedCriteria = service.buildCategoryCriteria(categories)
        def eventList = detachedCriteria.list()

        then:
        eventList.size() == 3
    }

    def "search - max result count"() {
        given:
        ZonedDateTime today = ZonedDateTime.now()
        ZonedDateTime tomorrow = today.plusDays(1)
        1.upto(101) {
            new Event(name: "event_${it}", startTime: today, endTime: tomorrow, venue: venue).save(failOnError: true, flush: true)
        }
        EventSearchCommand cmd = new EventSearchCommand(max: 101)

        when:
        def res = service.search(cmd)

        then:
        res.size() == 100
        res.totalCount > 100
    }

    def "search - pagination"() {
        given:
        ZonedDateTime today = ZonedDateTime.now()
        ZonedDateTime tomorrow = today.plusDays(1)
        1.upto(40) {
            new Event(name: "event_${it}", startTime: today, endTime: tomorrow, venue: venue).save(failOnError: true, flush: true)
        }

        expect:
        EventSearchCommand cmd = new EventSearchCommand(max: max, offset: offset)
        def res = service.search(cmd)
        numberOfEventResults == res.size()

        where:
        max | offset | numberOfEventResults
        10  | 0      | 10
        10  | 10     | 10
        20  | 10     | 20
        10  | 40     | 4
    }

    def "search - filterDateTimestamp multi day event"() {
        given:
        ZonedDateTime startTime = ZonedDateTime.now(ZoneId.of('UTC')).minusDays(3).with(LocalTime.of(9, 0))
        ZonedDateTime endTime = startTime.plusDays(1)
        Event event = new Event(name: 'multi day event', startTime: startTime, endTime: endTime, venue: venue).save(failOnError: true, flush: true)
        EventSearchCommand cmd = new EventSearchCommand(filterDateTimestamp: startTime.toEpochSecond())

        when:
        def res = service.search(cmd)

        then:
        res.size() == 1
        res == [event]
    }

    def "search - filterDateTimestamp multi day event with search time of midnight of the first day"() {
        given:
        ZonedDateTime startTime = ZonedDateTime.now(ZoneId.of('America/Chicago')).minusDays(3).with(LocalTime.of(9, 0))
        ZonedDateTime endTime = startTime.plusDays(1)
        ZonedDateTime searchTime = ZonedDateTime.now(ZoneId.of('America/Chicago')).minusDays(3).with(LocalTime.MIDNIGHT)
        Event event = new Event(name: 'multi day event', startTime: startTime, endTime: endTime, venue: venue).save(failOnError: true, flush: true)
        EventSearchCommand cmd = new EventSearchCommand(filterDateTimestamp: searchTime.toEpochSecond())

        when:
        def res = service.search(cmd)

        then:
        res.size() == 1
        res == [event]
    }

    def "search - filterDateTimestamp one day event"() {
        given:
        ZonedDateTime startTime = ZonedDateTime.now(ZoneId.of('America/Chicago')).minusDays(3).with(LocalTime.of(9, 0))
        ZonedDateTime endTime = startTime.plusHours(4)
        Event event = new Event(name: 'several hour event', startTime: startTime, endTime: endTime, venue: venue).save(failOnError: true, flush: true)
        EventSearchCommand cmd = new EventSearchCommand(filterDateTimestamp: startTime.with(LocalTime.of(0, 0)).toEpochSecond())

        when:
        def res = service.search(cmd)

        then:
        res.size() == 1
        res == [event]
    }

    void "save - with categories"() {
        given:
        Category cat1 = new Category(name: 'Fish Fry').save(flush: true, failOnError: true)
        Category cat2 = new Category(name: 'Festival').save(flush: true, failOnError: true)
        Venue venue = new Venue(name: 'v1', address1: '1 Church St', city: 'Minneapolis', state: 'MN', zip: '55414').save(flush: true, failOnError: true)
        EventCommand cmd = new EventCommand(name: 'Test Event', venue: venue, startTime: 1, endTime: 2, categories: [cat1, cat2])

        when:
        cmd.validate()
        def res = service.save(cmd)

        then:
        EventCategory.countByEvent(res) == 2
    }

    void "updateCategories - remove categories"() {
        given:
        ZonedDateTime today = ZonedDateTime.now()
        ZonedDateTime tomorrow = today.plusDays(1)
        Event event1 = new Event(name: 'ev1', startTime: today, endTime: tomorrow, venue: venue).save(failOnError: true, flush: true)
        Category cat1 = new Category(name: 'Fish Fry').save(flush: true, failOnError: true)
        new EventCategory(event: event1, category: cat1).save(flush: true, failOnError: true)
        assert EventCategory.countByEvent(event1) == 1

        when:
        service.updateCategories(event1, null)

        then:
        EventCategory.countByEvent(event1) == 0
    }

    void "updateCategories - add categories"() {
        given:
        ZonedDateTime today = ZonedDateTime.now()
        ZonedDateTime tomorrow = today.plusDays(1)
        Event event1 = new Event(name: 'ev1', startTime: today, endTime: tomorrow, venue: venue).save(failOnError: true, flush: true)
        Category cat1 = new Category(name: 'Fish Fry').save(flush: true, failOnError: true)
        assert EventCategory.countByEvent(event1) == 0

        when:
        service.updateCategories(event1, [cat1])

        then:
        EventCategory.countByEvent(event1) == 1
    }

    void "updateCategories - change categories"() {
        given:
        ZonedDateTime today = ZonedDateTime.now()
        ZonedDateTime tomorrow = today.plusDays(1)
        Event event1 = new Event(name: 'ev1', startTime: today, endTime: tomorrow, venue: venue).save(failOnError: true, flush: true)
        Category cat1 = new Category(name: 'Fish Fry').save(flush: true, failOnError: true)
        Category cat2 = new Category(name: 'Breakfast').save(flush: true, failOnError: true)
        new EventCategory(event: event1, category: cat1).save(flush: true, failOnError: true)
        assert EventCategory.countByEvent(event1) == 1

        when:
        service.updateCategories(event1, [cat2])

        then:
        def ec = EventCategory.findAllByEvent(event1)
        ec.size() == 1
        ec[0].category == cat2
    }
}
