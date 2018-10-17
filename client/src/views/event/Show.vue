<template>
    <v-container fluid grid-list-md>
        <event-detail :event="event"></event-detail>
    </v-container>
</template>

<script>
    import axios from 'axios'
    import EventDetail from "../../components/EventDetail";
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
            axios.get(`http://localhost:8080/event/${this.id}`).then((resp) => {
                this.event = resp.data
            })
        }
    }
</script>

<style scoped>

</style>