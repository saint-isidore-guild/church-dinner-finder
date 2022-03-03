<template>
  <v-row justify="center" align="center">
    <v-col cols="12">
      <v-card class="logo py-4 d-flex justify-center">
        <EventMap :events="events" />
      </v-card>
      <v-card>
        <v-card-title class="headline"> Upcoming </v-card-title>
        <v-card-text>
          <EventCard v-for="event in events" :key="event.slug" :event="event" />
        </v-card-text>
      </v-card>
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
      .where({ categories: { $contains: 'fish' } }) // , endDate: { $gt: new Date() }
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
      title: "2022"
    };
  },
}
</script>
