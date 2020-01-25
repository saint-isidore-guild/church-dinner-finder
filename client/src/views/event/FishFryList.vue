<template>
    <div class="event-category-list">
        <v-container grid-list-md text-xs-center>
            <h1>Upcoming Fish Fries</h1>
            <category-list category="Fish Fry"></category-list>
        </v-container>
    </div>
</template>

<script>
    import CategoryList from "../../components/CategoryList";
    export default {
        name: "FishFryList",
        components: {CategoryList},
    }
</script>

<style scoped>

</style>