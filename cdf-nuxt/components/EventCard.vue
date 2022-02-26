<template>
    <v-card>
        <v-card-title primary-title>
            <div>
                <h3 class="headline mb-0">{{event.title}}</h3>
                <div>{{prettyDate(event.startDate)}}</div>
            </div>
        </v-card-title>
        <v-card-text>
          {{ event.description}}
        </v-card-text>
        <v-card-actions>
            <v-btn
              text
              target="_blank"
              :href="event.parish.website"
            >Details</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        name: "EventCard",
        props: ['event'],
      methods: {
          prettyDate(date) {
            return new Date(date).toLocaleString()
          }
      }
    }
</script>

<style scoped>

</style>
