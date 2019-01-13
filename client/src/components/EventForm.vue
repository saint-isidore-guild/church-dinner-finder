<template>
    <v-container grid-list-md text-xs-center>
        <v-layout row wrap>
            <v-flex xs12>
                <v-card>
                    <v-card-title primary-title>
                        <v-form
                                ref="form"
                                v-model="valid"
                                lazy-validation
                                xs12
                        >
                            <v-layout row wrap>
                                <v-flex xs12>
                                    <v-text-field
                                            v-model="event.name"
                                            label="Name"
                                            required
                                    ></v-text-field>
                                </v-flex>

                                <v-flex xs12>
                                    <v-textarea
                                            v-model="event.description"
                                            label="Description"
                                            required
                                    ></v-textarea>
                                </v-flex>

                                <v-flex xs12>
                                    <v-text-field
                                            v-model="event.website"
                                            label="Website"
                                            required
                                    ></v-text-field>
                                </v-flex>

                                <v-flex xs12>
                                    <v-text-field
                                            v-model="event.email"
                                            label="Email"
                                            type="email"
                                            required
                                    ></v-text-field>
                                </v-flex>

                                <v-flex xs12>
                                    <v-text-field
                                            v-model="event.phone"
                                            label="Phone"
                                            type="phone"
                                            mask="phone"
                                            required
                                    ></v-text-field>
                                </v-flex>

                                <v-flex xs12>
                                    <v-text-field
                                            v-model="event.organizer"
                                            label="Organizer"
                                            required
                                    ></v-text-field>
                                </v-flex>

                                <v-flex xs12>
                                    <v-text-field
                                            v-model="event.costDescription"
                                            label="Cost"
                                            prefix="$"
                                    ></v-text-field>
                                </v-flex>

                                <v-flex xs12>
                                    <v-select
                                            v-model="event.venue"
                                            :items="venues"
                                            item-value="id"
                                            item-text="name"
                                            label="Venue"
                                            solo
                                    ></v-select>
                                </v-flex>

                                <v-flex xs12 md6>
                                    <v-datetime-picker
                                            label="Start Time"
                                            format="MM/DD/YY hh:mm A"
                                            v-model="startTime">
                                    </v-datetime-picker>
                                </v-flex>

                                <v-flex xs12 md6>
                                    <v-datetime-picker
                                            label="End Time"
                                            format="MM/DD/YY hh:mm A"
                                            v-model="endTime">
                                    </v-datetime-picker>
                                </v-flex>

                                <v-btn
                                        color="success"
                                        @click="submit"
                                >
                                    Save
                                </v-btn>

                            </v-layout>
                        </v-form>
                    </v-card-title>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {formatDatePickerLabel} from "../util/DateUtil";
    import ApiService from "../services/ApiService";
    import moment from 'moment';

    export default {
        name: "EventForm",
        data() {
            return {
                valid: false,
                event: {},
                venues: [],
                startTime: '',
                endTime: ''
            }
        },
        methods: {
            submit() {
                this.event.startTime = moment(this.startTime).utc().unix().valueOf();
                this.event.endTime = moment(this.endTime).utc().unix().valueOf();
                ApiService.post("event/", this.event).then(() => {

                })
            },
        },
        computed: {
            computedDateFormatted() {
                return this.filterDate ? formatDatePickerLabel(this.filterDate) : ''
            }
        },
        mounted() {
            ApiService.get('venue').then((resp) => {
                this.venues = resp;
            })
        }
    }
</script>

<style scoped>

</style>