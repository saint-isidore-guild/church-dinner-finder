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
  name: 'IndexPage',
  async asyncData({ $content }) {
    const events = await $content('events/2022')
      .sortBy('startDate', 'asc')
      .where({
        categories: { $contains: 'fish' },
        endDate: { $gte: new Date().toJSON() }, // upcoming
      })
      .fetch()
    events.forEach((event) => {
      event.parish = parishes.find((p) => p.id === event.parishId)
    })
    return {
      events,
    }
  },
  head() {
    return {
      title: '2022 Fish Fry',
    }
  },
}
</script>
