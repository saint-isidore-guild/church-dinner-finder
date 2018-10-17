<template>
    <v-card>
        <v-card-title primary-title>
            <div>
                <h3 class="headline mb-0">{{event.name}}</h3>
                <div>{{event.startTime | formatTimestamp}}</div>
            </div>
        </v-card-title>
        <v-card-actions>
            <v-btn flat :to="`/events/${event.id}`">Details</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        name: "Event",
        props: ['event']
    }
</script>

<style scoped>

</style>