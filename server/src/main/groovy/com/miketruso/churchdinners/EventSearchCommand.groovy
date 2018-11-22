package com.miketruso.churchdinners

import grails.validation.Validateable

class EventSearchCommand implements Validateable {
    String q
    Long filterDateTimestamp

    static constraints = {
        q nullable: true
        filterDateTimestamp nullable: true
    }
}
