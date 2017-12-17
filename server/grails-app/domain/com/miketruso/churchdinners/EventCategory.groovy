package com.miketruso.churchdinners

import grails.rest.Resource

@Resource(uri="/category")
class EventCategory {

    String name

    static constraints = {
        name nullable: false
    }
}
