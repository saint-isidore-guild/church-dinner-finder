package com.miketruso.churchdinners

import grails.validation.Validateable

class EventSearchCommand implements Validateable {
    String q
    Long filterDateTimestamp
    List<String> categories
    Boolean upcoming

    static constraints = {
        q nullable: true
        filterDateTimestamp nullable: true
        categories nullable: true
        upcoming nullable: true
    }
}
