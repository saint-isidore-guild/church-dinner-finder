<template>
  <v-row justify="center" align="center">
    <v-col cols="12">
      <v-card class="logo py-4 d-flex justify-center">
        <EventMap :events="events" />
      </v-card>
      <div v-if="events.length">
        <h2 class="headline my-4">Upcoming</h2>
        <div v-for="(eventList, date) in eventsByDate" :key="date" class="mb-5">
          <h3>{{ date }}</h3>
          <v-divider class="my-2" />
          <EventCard
            v-for="event in eventList"
            :key="event.slug"
            :event="event"
            class="mb-1"
          />
        </div>
      </div>
      <div v-else>
        <h2>No upcoming events</h2>
      </div>
    </v-col>
  </v-row>
</template>

<script>
import moment from 'moment'

import parishes from '@/util/parishes'
export default {
  name: 'IndexPage',
  async asyncData({ $content }) {
    let events = await $content('events/2022')
      .sortBy('startDate', 'asc')
      .where({ categories: { $contains: 'fish' } })
      .fetch()
    // upcoming
    events = events.filter((event) => new Date(event.endDate) > new Date())
    events.forEach((event) => {
      event.parish = parishes.find((p) => p.id === event.parishId)
    })
    return {
      events,
    }
  },
  head() {
    return {
      title: '2022',
    }
  },
  computed: {
    eventsByDate() {
      if (!this.events.length) {
        return {}
      }
      return this.events.reduce((acc, curr) => {
        const shortDate = moment(curr.startDate).format('LL')
        if (!acc[shortDate]) {
          acc[shortDate] = [] // If this type wasn't previously stored
        }
        acc[shortDate].push(curr)
        return acc
      }, {})
    },
  },
}
</script>
