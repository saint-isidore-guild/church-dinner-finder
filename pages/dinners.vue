<template>
  <v-row justify="center" align="center">
    <v-col cols="12">
      <v-card class="logo py-4 d-flex justify-center">
        <EventMap :events="events" />
      </v-card>
      <div v-if="events.length">
        <h2 class="headline my-4">Upcoming</h2>
        <EventList :events="events" />
      </div>
      <div v-else>
        <h2>No upcoming events</h2>
      </div>
    </v-col>
  </v-row>
</template>

<script>
import parishes from '@/util/parishes'

export default {
  name: 'DinnersPage',
  async asyncData({ $content }) {
    let events = await $content('events/2023')
      .sortBy('startDate', 'asc')
      .where({ categories: { $contains: 'dinner' } })
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
      title: '2023 Dinners',
    }
  },
}
</script>
