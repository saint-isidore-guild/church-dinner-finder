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
    import axios from 'axios'
    import EventCard from '../../components/EventCard'

    export default {
        name: "List",
        data() {
            return {
                events: []
            }
        },
        components: {EventCard},
        mounted() {
            axios.get('http://localhost:8080/event/').then((resp) => {
                this.events = resp.data
            })
        }
    }
</script>

<style scoped>

</style>