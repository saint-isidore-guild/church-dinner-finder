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
                        solo
                        multiple
                        @change="search"
                ></v-select>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import ApiService from "../services/ApiService";

    export default {
        name: "EventFilters",
        props: ['value'], //v-model binds
        data() {
            return {
                selectedCategories: [],
                categories: ['Festival', 'Fish Fry', 'Pancake Breakfast', 'Octoberfest'],
                eventResults: []
            }
        },
        methods: {
            search() {
                ApiService.get(`/event?categories=${this.selectedCategories}`).then((data) => {
                    // simulate filtered search
                    const dummyresp = data.splice(1, 1)
                    this.eventResults =dummyresp
                    this.$emit('input', dummyresp)
                    // TODO this.$emit('input', data)
                })
            }
        },
    }
</script>

<style scoped>

</style>