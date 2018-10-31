package com.miketruso.churchdinners

import groovy.transform.ToString

@ToString
class EventCategory implements Serializable {

    Event event
    Category category

    static mapping = {
        id composite: ['event', 'category']
        version false
    }

    static constraints = {
        event nullable: false
        category nullable: false
    }
}
