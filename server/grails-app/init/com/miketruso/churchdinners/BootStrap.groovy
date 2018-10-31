package com.miketruso.churchdinners

import java.time.ZoneId
import java.time.ZonedDateTime

class BootStrap {

    def init = { servletContext ->
        Venue lourdes = new Venue(name: 'Our Lady of Lourdes', address1: '1 Lourdes Pl', city: 'Minneapolis', state: 'MN', zip: '55414').save(flush:true, failOnError:true)
        Venue stMark = new Venue(name: 'Church of St. Mark', address1: '2001 Dayton Ave', city: 'Saint Paul', state: 'MN', zip: '55104').save(flush:true, failOnError:true)
        Venue holyCross = new Venue(name: 'Holy Cross', address1: '1621 University Ave', city: 'Minneapolis', state: 'MN', zip: '55413').save(flush:true, failOnError:true)
        Event lourdesBlockParty = new Event(name: 'Lourdes Block Party', description: "Mass followed by live music, food, raffle, children's activities, Minneapolis Aquatennial fireworks viewing", venue: lourdes, website: 'http://www.lourdesmpls.org', startTime: ZonedDateTime.of(2018, 7, 21, 17, 0, 0, 0, ZoneId.of('America/Chicago')), endTime: ZonedDateTime.of(2018, 7, 21, 22, 0, 0, 0, ZoneId.of('America/Chicago'))).save(failOnError:true, flush:true)
        Event saintMarksFestival = new Event(name: "St. Mark's Festival", description: 'Rides, food, beer, live music, games and more. 5 p.m. outdoor Mass May 19', venue: stMark, website: 'http://www.markerspride.com/index.php/home-festival', hasCost: true, costDescription: 'Admission Free, Food priced a la carte', startTime: ZonedDateTime.of(2018, 5, 18, 17, 0, 0, 0, ZoneId.of('America/Chicago')), endTime: ZonedDateTime.of(2018, 5, 19, 23, 0, 0, 0, ZoneId.of('America/Chicago'))).save(failOnError:true, flush:true)
        Event pancakeBreakfast = new Event(name: 'Pancake Breakfast', description: 'Join us at Holy Cross in Kolbe Hall for the best pancakes in NE MPLS! ', venue: holyCross, website: 'https://www.facebook.com/events/234531420746775/', startTime: ZonedDateTime.of(2018, 10, 21, 8, 30, 0, 0, ZoneId.of('America/Chicago')), endTime: ZonedDateTime.of(2018, 10, 21, 12, 30, 0, 0, ZoneId.of('America/Chicago')), imageUrl: 'https://scontent-ort2-1.xx.fbcdn.net/v/t1.0-9/42570338_1828118650603405_1819132041011134464_n.jpg?_nc_cat=109&_nc_ht=scontent-ort2-1.xx&oh=3896132fabffe64ffe4d373ac2c833c2&oe=5C40288E').save(failOnError:true, flush:true)
        Category breakfast = new Category(name: 'Breakfast').save(failOnError:true, flush:true)
        Category dinner = new Category(name: 'Dinner').save(failOnError:true, flush:true)
        Category party = new Category(name: 'Party').save(failOnError:true, flush:true)
        Category festival = new Category(name: 'Festival').save(failOnError:true, flush:true)
        new EventCategory(event: pancakeBreakfast, category: breakfast).save(failOnError:true, flush:true)
        new EventCategory(event: saintMarksFestival, category: festival).save(failOnError:true, flush:true)
        new EventCategory(event: saintMarksFestival, category: dinner).save(failOnError:true, flush:true)
        new EventCategory(event: lourdesBlockParty, category: dinner).save(failOnError:true, flush:true)
        new EventCategory(event: lourdesBlockParty, category: party).save(failOnError:true, flush:true)
    }

    def destroy = {
    }
}
