<template>
    <div>
        <h1>Event List
            <router-link to="/events/create"><v-btn icon><v-icon></v-icon>add</v-btn></router-link>
        </h1>
        <div class="event-list">
            <v-container grid-list-md text-xs-center>
                <v-layout row wrap>
                    <v-flex v-for="event in events" :key="event.id" xs4>
                        <v-card dark color="primary">
                            <v-card-text class="px-0">{{event.name}}</v-card-text>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </div>
    </div>

</template>

<script>
    import axios from 'axios'
    export default {
        name: "Event",
        data() {
            return {
                events: []
            }
        },
        mounted() {
            axios.get('http://localhost:8080/event/').then((resp) => {
                this.events = resp.data
            })
        }
    }
</script>

<style scoped>

</style>