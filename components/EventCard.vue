<template>
  <v-card>
    <v-card-title primary-title>
      <div>
        <h3 class="headline mb-0">
          <a :href="website" target="_blank">{{ event.title }}</a>
        </h3>
        <div>{{ event.parish.city }}, {{ event.parish.state }}</div>
        <div>{{ startDate }} - {{ endDate }}</div>
      </div>
    </v-card-title>
    <v-card-text class="subtitle-1">
      {{ event.description }}
    </v-card-text>
    <v-card-actions>
      <v-btn text target="_blank" :href="website">Details</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import moment from 'moment'

export default {
  name: 'EventCard',
  props: {
    event: {
      type: Object,
      default() {
        return {}
      },
    },
  },
  computed: {
    startDate() {
      return moment(this.event.startDate).format('MMMM D, h:mm a')
    },
    endDate() {
      const start = moment(this.event.startDate)
      const end = moment(this.event.endDate)
      if (start.format('L') === end.format('L')) {
        //  same day
        return end.format('h:mm a')
      }
      return moment(this.event.endDate).format('MMMM D, h:mm a')
    },
    website() {
      return this.event.website || this.event.parish.website
    },
  },
  methods: {
    prettyDate(date) {
      return new Date(date).toLocaleString()
    },
  },
}
</script>

<style scoped></style>
