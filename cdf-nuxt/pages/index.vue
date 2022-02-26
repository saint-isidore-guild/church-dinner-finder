<template>
  <v-row justify="center" align="center">
    <v-col cols="12" sm="8" md="6">
      <v-card class="logo py-4 d-flex justify-center">
        <NuxtLogo />
        <VuetifyLogo />
      </v-card>
      <v-card>
        <v-card-title class="headline">
          Welcome to the Vuetify + Nuxt.js template
        </v-card-title>
        <v-card-text>
          <EventCard
            v-for="event in events"
            :key="event.slug"
            :event="event"
          />
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn color="primary" nuxt to="/inspire"> Continue </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import parishes from '@/util/parishes'
export default {
  name: 'IndexPage',
  async asyncData ({ $content }) {
    const events = await $content('events/2022')
      .sortBy('startDate', 'asc')
      .where({categories: { $contains: "fish"} })
      .fetch()
    events.forEach((event) => {
      event.parish = parishes.find((p) => p.id === event.parishId)
      console.log(event)
    })

    return {
      events
    }
  }
}
</script>
