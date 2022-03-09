<template>
  <div>
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
</template>

<script>
import moment from 'moment'

export default {
  name: 'EventList',
  props: {
    events: {
      type: Array,
      default: () => {
        return []
      },
    },
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
