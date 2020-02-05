<template>
    <v-app>
        <v-app-bar app
                   :clipped-left="clipped"
        >
            <router-link to="/" class="title-link">
                <v-toolbar-title>
                    Church Meal Finder
                    <v-icon>near_me</v-icon>
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
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title class="title">
                        Fish Fry Finder
                    </v-list-item-title>
                    <v-list-item-subtitle>
                        and other community meals
                    </v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>

            <v-divider></v-divider>

            <v-list>
                <v-list-item
                        v-for="item in items"
                        :key="item.title"
                        :to="`/category/${item.title}`"
                        link
                >
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
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
                fixed: false,
                right: true,
                rightDrawer: false,
                items: [
                    {icon: 'mdi-coffee', title: 'Breakfast'},
                    {icon: 'mdi-pasta', title: 'Festival'},
                    {icon: 'mdi-fish', title: 'Fish Fry'},
                ]
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
