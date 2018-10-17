<template>
    <div class="event-list">
        <v-container grid-list-md text-xs-center>
            <h1>Event List</h1>
            <v-layout row wrap>
                <v-flex v-for="event in events" :key="event.id" xs4>
                    <event-card :event="event"></event-card>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script>
    import EventCard from '../../components/EventCard'
    import ApiService from '../../services/ApiService'

    export default {
        name: "List",
        data() {
            return {
                events: []
            }
        },
        components: {EventCard},
        mounted() {
            ApiService.get('/event').then((data) => {
                this.events = data
            })
        }
    }
</script>

<style scoped>

</style>