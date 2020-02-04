<template>
    <v-app>
        <v-app-bar app
                :clipped-left="clipped"
        >
            <router-link to="/" class="title-link">
                <v-toolbar-title>
                    Church Meal Finder <v-icon>near_me</v-icon>
                </v-toolbar-title>
            </router-link>
            <v-spacer></v-spacer>
            <event-search></event-search>
            <v-btn icon @click.stop="rightDrawer = !rightDrawer">
                <v-icon>menu</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
        <v-navigation-drawer
                temporary
                :right="right"
                v-model="rightDrawer"
                fixed
                app
        >
            <v-list>
                <v-list-item @click="right = !right">
                    <v-list-item-action>
                        <v-icon>compare_arrows</v-icon>
                    </v-list-item-action>
                    <v-list-item-title>Switch drawer (click me)</v-list-item-title>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
        <v-footer :fixed="fixed" app>
            <span>&copy; 2018 <a href="https://saint-isidore-guild.github.io/">St. Isidore's Guild</a></span>
        </v-footer>
    </v-app>
</template>

<script>

    import EventSearch from "./components/EventSearch";
    export default {
        name: 'App',
        components: {EventSearch},
        data() {
            return {
                clipped: false,
                drawer: true,
                fixed: false,
                miniVariant: false,
                right: true,
                rightDrawer: false
            }
        }
    }
</script>

<style scoped>
    .title-link {
        text-decoration: none;
        color: inherit;
    }
</style>
