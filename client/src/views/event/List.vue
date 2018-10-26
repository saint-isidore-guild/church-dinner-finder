<template>
    <div class="event-list">
        <v-container grid-list-md text-xs-center>
            <event-filters v-model="events"></event-filters>
            <h1>Upcoming Events</h1>
            <v-layout row wrap>
                <v-flex v-for="event in events" :key="event.id" md4 xs12>
                    <event-card :event="event"></event-card>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script>
    import EventCard from '../../components/EventCard'
    import ApiService from '../../services/ApiService'
    import EventFilters from "../../components/EventFilters";

    export default {
        name: "List",
        data() {
            return {
                events: []
            }
        },
        components: {EventFilters, EventCard},
        mounted() {
            ApiService.get('/event').then((data) => {
                this.events = data
            })
        }
    }
</script>

<style scoped>

</style>