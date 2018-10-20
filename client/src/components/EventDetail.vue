<template>
    <v-container>
        <h1>{{ event.name }}</h1>
        <dl class="list">
            <dt>Description</dt>
            <dd>{{ event.description }}</dd>
            <dt>Phone</dt>
            <dd>{{ event.phone }}</dd>
            <dt>Email</dt>
            <dd>{{ event.email }}</dd>
            <dt>Start Time</dt>
            <dd>{{ event.startTime | formatTimestamp }}</dd>
            <dt>End Time</dt>
            <dd>{{ event.endTime  | formatTimestamp }}</dd>
            <dt>Cost of Event</dt>
            <dd>{{ event.hasCost ? "$$$" : "Free" }}</dd>
            <dt>Cost Description</dt>
            <dd>{{ event.costDescription }}</dd>
            <dt>Venue</dt>
            <dd>{{ event.venue.name }}</dd>
            <dt>Categories</dt>
            <dd>{{ event.categories }}</dd>
        </dl>
        <v-btn  :href="event.website" target="_blank">More Information</v-btn>
    </v-container>

</template>

<script>
    export default {
        name: "EventDetail",
        props: ['event']
    }
</script>

<style scoped>

</style>
