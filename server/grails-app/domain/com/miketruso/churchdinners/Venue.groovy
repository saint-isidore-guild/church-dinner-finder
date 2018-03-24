package com.miketruso.churchdinners

import grails.rest.Resource

@Resource(uri="/venue")
class Venue {

    String name
    String description
    String address1
    String address2
    String city
    String state
    String zip
    Double latitude
    Double longitude

    static constraints = {
        description nullable: true
        address2 nullable: true
        latitude nullable: true
        longitude nullable: true
    }
}
