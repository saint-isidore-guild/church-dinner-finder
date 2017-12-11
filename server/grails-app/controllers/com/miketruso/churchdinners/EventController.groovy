package com.miketruso.churchdinners


import grails.rest.*
import grails.converters.*

class EventController {
	static responseFormats = ['json', 'xml']
	
    def index() { }

    def listUpcoming(){
        def results = Event.withCriteria {
            ge('startTime', new Date())
        }
    }

    def search(){

        def results = Event.withCriteria {
            if(params.category){
                eventCategory {
                    eq('id', params.category)
                }
            }
//            if(params.startDate){
//                between()
//            }
//            if(params.zip){
//                //search nearby zip
//            }
            or {
                ilike('name', "%${params.q}%")
                ilike('description', "%${params.q}%")
                venue {
                    ilike('name', "%${params.q}%")
                    ilike('description', "%${params.q}%")
                }
            }

        }
    }
}
