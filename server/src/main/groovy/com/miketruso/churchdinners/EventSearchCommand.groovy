package com.miketruso.churchdinners

import grails.validation.Validateable

class EventSearchCommand implements Validateable {
    String q
    Long filterDateTimestamp
    List<String> categories
    Boolean upcoming
    Long max
    Long offset

    static constraints = {
        q nullable: true
        filterDateTimestamp nullable: true
        categories nullable: true
        upcoming nullable: true
        max nullable: true
        offset nullable: true
    }
}
