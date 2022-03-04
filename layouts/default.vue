<template>
  <v-app dark>
    <v-app-bar fixed app>
      <v-toolbar-title v-text="title" />
    </v-app-bar>
    <v-main>
      <v-container>
        <Nuxt />
      </v-container>
    </v-main>
    <v-footer :absolute="true" app>
      <span
        >&copy; {{ new Date().getFullYear() }}
        <a href="https://saint-isidore-guild.github.io/"
          >St. Isidore's Guild</a
        ></span
      >
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: 'DefaultLayout',
  data() {
    return {
      title: '2022 Lenten Meals',
    }
  },
}
</script>
