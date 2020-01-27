package com.miketruso.churchdinners

import grails.test.hibernate.HibernateSpec
import grails.testing.web.controllers.ControllerUnitTest

class EventControllerSpec extends HibernateSpec implements ControllerUnitTest<EventController> {

    EventService eventService

    def setup() {
        eventService = Mock(EventService)
        controller.eventService = eventService
    }

    def "save"() {
        given:
        Venue venue = new Venue(name: 'v1', address1: '1 Church St', city: 'Minneapolis', state: 'MN', zip: '55414').save(flush:true, failOnError:true)
        EventCommand cmd = new EventCommand(name: 'Test Event', venue: venue, startTime: 1, endTime: 2)

        when:
        cmd.validate()
        controller.save(cmd)

        then:
        1 * eventService.save(cmd) >> new Event(id: 1)
        response.status == 201
    }

    def "save - bad request"() {
        given:
        EventCommand cmd = new EventCommand()

        when:
        cmd.validate()
        controller.save(cmd)

        then:
        response.status == 400
    }

    def "save - error during save"() {
        given:
        Venue venue = new Venue(name: 'v1', address1: '1 Church St', city: 'Minneapolis', state: 'MN', zip: '55414').save(flush:true, failOnError:true)
        EventCommand cmd = new EventCommand(name: 'Test Event', venue: venue, startTime: 1, endTime: 2)

        when:
        cmd.validate()
        controller.save(cmd)

        then:
        1 * eventService.save(cmd) >> null
        response.status == 400
    }
}
