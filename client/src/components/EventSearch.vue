<template>
    <v-autocomplete
            :loading="loading"
            :items="eventResults"
            item-text="name"
            item-value="id"
            :search-input.sync="q"
            v-model="selectedItem"
            cache-items
            class="mx-3"
            flat
            hide-no-data
            hide-details
            label="Search Events"
            solo
            prepend-icon="search"
            @change="search"
    ></v-autocomplete>
</template>

<script>
    import ApiService from "../services/ApiService";

    export default {
        name: "EventSearch",
        data() {
            return {
                q: '',
                loading: false,
                selectedItem: {},
                eventResults: []
            }
        },
        methods: {
            search() {
                ApiService.get(`/event?q=${this.q}`).then((data) => {
                    this.eventResults =data
                    this.$emit('input', data)
                })
            }
        },
        mounted() {
            this.search()
        },
        watch: {
            selectedItem(val) {
                this.$router.push({path: `/events/${val}`})
            }
        }
    }
</script>

<style scoped>

</style>