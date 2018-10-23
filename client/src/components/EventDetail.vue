<template>
    <v-container>
        <v-layout row wrap>
            <v-flex md8>
                <v-card>
                    <v-img
                            :src="event.imageUrl"
                            aspect-ratio="2.75"
                            v-show="event.imageUrl"
                    ></v-img>
                    <v-card-title primary-title>
                        <div>
                            <h1>{{ event.name }}
                                <v-chip v-show="!event.hasCost">Free</v-chip>
                            </h1>
                            <h2>{{ event.description }}</h2>
                            <div>
                                <h4><v-icon>access_time</v-icon> When</h4>
                                <div>{{ eventDateTime() }}</div>
                            </div>
                            <dl class="list">
                                <dt>Cost of Event</dt>
                                <dd>{{ event.hasCost ? "$$$" : "Free" }}</dd>
                                <dt>Cost Description</dt>
                                <dd>{{ event.costDescription }}</dd>
                            </dl>
                            <v-btn :href="event.website" target="_blank">More Information</v-btn>
                        </div>
                    </v-card-title>
                </v-card>
            </v-flex>
            <v-flex md4>
                <v-card>
                    <v-card-title primary-title>
                        <div>
                            <h3>Contact</h3>
                            <h4>{{ event.venue.name }}</h4>
                            <dl class="list">
                                <dt>Phone</dt>
                                <dd>{{ event.phone }}</dd>
                                <dt>Email</dt>
                                <dd>{{ event.email }}</dd>
                            </dl>
                        </div>
                    </v-card-title>
                </v-card>
            </v-flex>

        </v-layout>
    </v-container>

</template>

<script>
    import {formatStartAndEnd} from "../util/DateUtil";

    export default {
        name: "EventDetail",
        props: ['event'],
        methods: {
            eventDateTime() {
                if (this.event) {
                    return formatStartAndEnd(this.event.startTime, this.event.endTime)
                }
            }
        },
        computed: {

        }
    }
</script>

<style scoped>

</style>