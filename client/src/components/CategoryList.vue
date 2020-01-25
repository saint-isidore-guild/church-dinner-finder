<template>
    <div class="event-category-list">
        <v-container grid-list-md text-xs-center>
            <v-layout row wrap>
                <v-flex v-for="event in events" :key="event.id" xs12>
                    <event-detail :event="event"></event-detail>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script>
    import EventCard from "./EventCard";
    import ApiService from "../services/ApiService";
    import EventDetail from "./EventDetail";

    export default {
        name: "CategoryList",
        components: {EventDetail, EventCard},
        props: ["category"],
        data() {
            return {
                events: []
            }
        },
        methods: {
            searchEvents() {
                let paginationParams = `max=${100}`;
                ApiService.get(`/event/search?${paginationParams}&upcoming=true&categories=${this.category}`).then((data) => {
                    this.events = data.events;
                    this.total = data.total;
                })
            },
        },
        mounted() {
            this.searchEvents()
        }
    }
</script>

<style scoped>

</style>