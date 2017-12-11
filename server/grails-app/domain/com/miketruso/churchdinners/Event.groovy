package com.miketruso.churchdinners

class Event {

    String name
    String description
    String website
    String email
    String phone
    String organizer
    Date startTime
    Date endTime

    Venue venue
    EventCategory category

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
        category nullable: true
    }
}
