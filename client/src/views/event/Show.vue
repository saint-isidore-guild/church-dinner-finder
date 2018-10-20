<template>
    <v-container fluid grid-list-md>
        <event-detail :event="event"></event-detail>
    </v-container>
</template>

<script>
    import EventDetail from "../../components/EventDetail";
    import ApiService from "../../services/ApiService";
    export default {
        name: "Show",
        components: {EventDetail},
        props: ['id'],
        data() {
            return {
                event: {}
            }
        },
        mounted() {
            ApiService.get(`/event/${this.id}`).then((data) => {
                this.event = data
            })
        }
    }
</script>

<style scoped>

</style>
