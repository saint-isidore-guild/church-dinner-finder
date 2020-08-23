package com.miketruso.churchdinners

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import spock.lang.Specification
import wslite.rest.RESTClient
import wslite.rest.RESTClientException

import java.time.ZonedDateTime

@Integration
@Rollback
class EventFunctionalSpec extends Specification {

    protected RESTClient rest
    protected String baseUrl

    void setup() {
        baseUrl = "http://localhost:$serverPort"
        rest = new RESTClient(baseUrl)
    }

    void "event search"() {
        when:
        def response = rest.get(path: "/event/search")

        then:
        response.statusCode == 200
        response.json.events.size() == 3
    }

    void "event name search"() {
        when:
        def response = rest.get(path: "/event/search", query: [q: 'pancake'])

        then:
        response.statusCode == 200
        response.json.events.size() == 1
        response.json.total == 1
    }

    void "event date search"() {
        given:
        Long timestamp = ZonedDateTime.now().toEpochSecond()

        when:
        def response = rest.get(path: "/event/search?", query: [filterDateTimestamp: timestamp])

        then:
        response.statusCode == 200
    }

    void "event bad route"() {
        when:
        def response = rest.get(path: "/event/foo")

        then:
        thrown(RESTClientException)
//        response.statusCode == 400
    }

    void "category search"() {
        given:
        String categoryName = 'Breakfast'

        when:
        def response = rest.get(path: "/event/search", query: [categories: categoryName])

        then:
        response.statusCode == 200
        response.json.events.size() == 1
    }

    void "category multiple"() {
        when:
        def response = rest.get(path: "/event/search?categories=Breakfast&categories=Festival", query: [categories: ['Breakfast', 'Festival']])

        then:
        response.statusCode == 200
        response.json.events.size() == 2
    }

    void "search upcoming"() {
        when:
        def response = rest.get(path: "/event/search", query: [upcoming: true])

        then:
        response.statusCode == 200
        response.json.events.size() == 2
    }

    void "root event endpoint returns upcoming"() {
        when:
        def response = rest.get(path: "/event")

        then:
        response.statusCode == 200
        response.json.events.size() == 2
    }
}
