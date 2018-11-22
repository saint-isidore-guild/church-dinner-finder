package com.miketruso.churchdinners

import groovy.transform.ToString

@ToString
class EventCategory {

    Event event
    Category category

    static constraints = {
        event nullable: false
        category nullable: false
    }
}
