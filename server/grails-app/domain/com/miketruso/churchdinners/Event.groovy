package com.miketruso.churchdinners

import groovy.transform.ToString

import java.time.ZonedDateTime

@ToString
class Event {

    String name
    String description
    String website
    String email
    String phone
    String organizer
    String costDescription
    String imageUrl
    boolean hasCost
    ZonedDateTime startTime
    ZonedDateTime endTime

    Venue venue

    static constraints = {
        name nullable: false, maxSize: 255
        description nullable: true, maxSize: 255
        website nullable: true, url: true, maxSize: 255
        email nullable: true, email: true, maxSize: 255
        phone nullable: true, maxSize: 255
        organizer nullable: true, maxSize: 255
        startTime nullable: false
        endTime nullable: false
        venue nullable: false
        hasCost nullable: false
        costDescription nullable: true, maxSize: 255
        imageUrl nullable: true, maxSize: 1000
    }

    static mapping = {
        id generator: 'native', params: [sequence: 'seq_event']
    }
}
