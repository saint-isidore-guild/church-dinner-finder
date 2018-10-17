<template>
    <v-container>
        <h1>{{ event.name }}</h1>
        <dl class="list">
            <dt>Description</dt>
            <dd>{{ event.description }}</dd>
            <dt>Website</dt>
            <dd>{{ event.website }}</dd>
            <dt>Phone</dt>
            <dd>{{ event.phone }}</dd>
            <dt>Email</dt>
            <dd>{{ event.email }}</dd>
            <dt>Start Time</dt>
            <dd>{{ event.startTime | formatTimestamp }}</dd>
            <dt>End Time</dt>
            <dd>{{ event.endTime  | formatTimestamp }}</dd>
            <dt>Venue</dt>
            <dd>{{ event.venue }}</dd>
        </dl>
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