package com.miketruso.churchdinners

import java.time.ZoneId
import java.time.ZonedDateTime

class BootStrap {

    def init = { servletContext ->

        Venue lourdes = new Venue(name: 'Our Lady of Lourdes', address1: '1 Lourdes Pl', city: 'Minneapolis', state: 'MN', zip: '55414').save(flush:true, failOnError:true)
        Venue stMark = new Venue(name: 'Church of St. Mark', address1: '2001 Dayton Ave', city: 'Saint Paul', state: 'MN', zip: '55104').save(flush:true, failOnError:true)

        new Event(name: 'Lourdes Block Party', description: 'Mass followed by live music, food, raffle, children’s activities, Minneapolis Aquatennial fireworks viewing', venue: lourdes, website: 'http://www.lourdesmpls.org', startTime: ZonedDateTime.of(2018, 7,21,17, 0, 0, 0, ZoneId.of('America/Chicago')), endTime: ZonedDateTime.of(2018, 7,21,22, 0, 0, 0, ZoneId.of('America/Chicago'))).save(failOnError:true, flush:true)
        new Event(name: "St. Mark’s Festival", description: 'Rides, food, beer, live music, games and more. 5 p.m. outdoor Mass May 19', venue: stMark, website: 'http://www.markerspride.com/index.php/home-festival', startTime: ZonedDateTime.of(2018, 5,18,17, 0, 0, 0, ZoneId.of('America/Chicago')), endTime: ZonedDateTime.of(2018, 5,19,23, 0, 0, 0, ZoneId.of('America/Chicago'))).save(failOnError:true, flush:true)

    }
    def destroy = {
    }
}
