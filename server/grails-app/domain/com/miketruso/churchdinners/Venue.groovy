package com.miketruso.churchdinners

import grails.rest.Resource
import groovy.transform.ToString

@SuppressWarnings('GrailsDomainReservedSqlKeywordName')
@ToString
@Resource(uri='/venue')
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
        name maxSize: 255
        description nullable: true, maxSize: 255
        address1 maxSize: 255
        address2 nullable: true, maxSize: 255
        city maxSize: 255
        state maxSize: 255
        zip maxSize: 255
        latitude nullable: true
        longitude nullable: true
    }

    static mapping = {
        id generator: 'native', params: [sequence: 'seq_venue']
    }
}
