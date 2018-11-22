package com.miketruso.churchdinners

import groovy.transform.ToString

@ToString
class Category {

    String name

    static constraints = {
        name nullable: false, maxSize: 255
    }

    static mapping = {
        id generator: 'native', params: [sequence: 'seq_category']
    }
}
