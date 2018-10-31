package com.miketruso.churchdinners

import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

import java.time.ZonedDateTime

@Integration
@Rollback
class EventFunctionalSpec extends Specification {

    protected RestBuilder rest = new RestBuilder()
    protected String baseUrl

    void setup() {
        baseUrl = "http://localhost:$serverPort"
    }

    void "event search"() {
        when:
        def response = rest.get("${baseUrl}/event/search")

        then:
        response.status == 200
        response.json.size() == 3
    }

    void "event name search"() {
        when:
        def response = rest.get("${baseUrl}/event/search?q=pancake")

        then:
        response.status == 200
        response.json.size() == 1
    }

    void "event date search"() {
        given:
        Long timestamp = ZonedDateTime.now().toEpochSecond()

        when:
        def response = rest.get("${baseUrl}/event/search?filterDateTimestamp=${timestamp}")

        then:
        response.status == 200
    }

    void "event bad route"() {
        when:
        def response = rest.get("${baseUrl}/event/foo")

        then:
        response.status == 400
    }
}
