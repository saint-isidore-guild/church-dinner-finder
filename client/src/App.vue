<template>
    <v-app>
        <v-toolbar
                app
                :clipped-left="clipped"
        >
            <router-link to="/" class="title-link">
                <v-toolbar-title>
                    Church Meal Finder <v-icon>near_me</v-icon>
                </v-toolbar-title>
            </router-link>
            <v-spacer></v-spacer>
            <v-btn icon @click.stop="rightDrawer = !rightDrawer">
                <v-icon>menu</v-icon>
            </v-btn>
        </v-toolbar>
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
                <v-list-tile @click="right = !right">
                    <v-list-tile-action>
                        <v-icon>compare_arrows</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-title>Switch drawer (click me)</v-list-tile-title>
                </v-list-tile>
            </v-list>
        </v-navigation-drawer>
        <v-footer :fixed="fixed" app>
            <span>&copy; 2018 <a href="https://saint-isidore-guild.github.io/">St. Isidore's Guild</a></span>
        </v-footer>
    </v-app>
</template>

<script>

    export default {
        name: 'App',
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
