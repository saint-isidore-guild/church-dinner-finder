package com.miketruso.churchdinners

import java.time.ZonedDateTime

class Event {

    String name
    String description
    String website
    String email
    String phone
    String organizer
    String costDescription
    boolean hasCost
    ZonedDateTime startTime
    ZonedDateTime endTime

    Venue venue

    static constraints = {
        name nullable: false
        description nullable: true
        website nullable: true, url: true
        email nullable: true, email: true
        phone nullable: true
        organizer nullable: true
        startTime nullable: false
        endTime nullable: false
        venue nullable: false
        hasCost nullable: false
        costDescription nullable: true
    }
}
