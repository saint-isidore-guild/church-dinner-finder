<template>
    <v-chip  color="green" text-color="white">{{category.name}}</v-chip>
</template>
<script>
    export default {
        name: "CategoryChip",
        props: ['category']
    }
</script>

<style scoped>

</style>
