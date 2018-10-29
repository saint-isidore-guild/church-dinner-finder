<template>
    <v-container
            id="input-usage"
            grid-list-xl
            fluid
    >
        <v-layout wrap>
            <v-flex md6 xs12>
                <v-select
                        v-model="selectedCategories"
                        :items="categories"
                        label="Filter by category"
                        chips
                        deletable-chips
                        clearable
                        prepend-icon="filter_list"
                        multiple
                        @change="search"
                ></v-select>
            </v-flex>
            <v-flex md6 xs12>
                <v-menu
                        v-model="dateMenu"
                        :close-on-content-click="false"
                        full-width
                        max-width="290"
                >
                    <v-text-field
                            slot="activator"
                            :value="computedDateFormatted"
                            clearable
                            label="Filter by Date"
                            prepend-icon="calendar_today"
                            readonly
                    ></v-text-field>
                    <v-date-picker
                            v-model="filterDate"
                            @change="search"
                    ></v-date-picker>
                </v-menu>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import ApiService from "../services/ApiService";
    import {formatDatePickerLabel} from "../util/DateUtil";
    import moment from 'moment'

    export default {
        name: "EventFilters",
        props: ['value'], //v-model binds
        data() {
            return {
                selectedCategories: [],
                categories: ['Festival', 'Fish Fry', 'Pancake Breakfast', 'Octoberfest'],
                eventResults: [],
                filterDate: '',
                dateMenu: false
            }
        },
        methods: {
            search() {
                this.dateMenu = false
                ApiService.get(`/event/search?categories=${this.selectedCategories}&filterDateTimestamp=${moment(this.filterDate).unix().valueOf()}`).then((data) => {
                    this.eventResults = data
                    this.$emit('input', data)
                })
            }
        },
        computed: {
            computedDateFormatted() {
                return this.filterDate ? formatDatePickerLabel(this.filterDate) : ''
            }
        }
    }
</script>

<style scoped>

</style>