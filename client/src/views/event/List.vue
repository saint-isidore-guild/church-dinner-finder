<template>
    <div class="event-list">
        <v-container grid-list-md text-xs-center>
            <event-filters v-model="filters"></event-filters>
            <h1>Upcoming Events</h1>
            <v-layout row wrap>
                <v-flex v-for="event in events" :key="event.id" md4 xs12>
                    <event-card :event="event"></event-card>
                </v-flex>
            </v-layout>
            <v-layout row>
                <v-flex>
                    <div class="text-xs-left">
                        <h4>{{resultText()}}</h4>
                        <v-pagination
                                v-show="pages() > 1"
                                v-model="currentPage"
                                :length="pages()"
                        ></v-pagination>
                    </div>
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
                events: [],
                total: 0,
                currentPage: 1,
                filters: {},
                resultsPerPage: 10
            }
        },
        components: {EventFilters, EventCard},
        methods: {
            searchEvents() {
                let filterString = Object.entries(this.filters).map(([key, val]) => {
                    if (Array.isArray(val)) {
                        return val.map((val2) => `${key}=${val2}`).join('&')
                    } else {
                        return `${key}=${val}`
                    }
                }).join('&');
                let paginationParams = `max=${this.resultsPerPage}&offset=${this.currentPage * this.resultsPerPage - this.resultsPerPage}`;
                ApiService.get(`/event/search?${paginationParams}&upcoming=true&${filterString}`).then((data) => {
                    this.events = data.events
                    this.total = data.total
                })
            },
            pages() {
                return Math.floor(this.total/this.resultsPerPage) + 1
            },
            resultText() {
                if (this.total == 0) {
                    return 'No results found'
                }
                let minResult = this.currentPage * this.resultsPerPage - this.resultsPerPage +1
                let maxResult = Math.min(this.currentPage * this.resultsPerPage, this.total)
                return `Showing ${minResult} to ${maxResult} of ${this.total}`
            }
        },
        watch: {
            filters() {
                this.searchEvents()
            },
            currentPage() {
                this.searchEvents()
            }
        },
        mounted() {
            this.searchEvents()
        }
    }
</script>

<style scoped>

</style>