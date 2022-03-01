<template>
  <v-form ref="createForm">
    <v-text-field v-model="event.title" label="title" outlined></v-text-field>
    <v-textarea
      v-model="event.description"
      label="description"
      outlined
    ></v-textarea>
    <v-select
      v-model="event.categories"
      label="categories"
      :items="['fish', 'dinner', 'festival']"
      multiple
      outlined
    ></v-select>
    <v-autocomplete
      v-model="event.parishId"
      :items="parishList"
      item-text="name"
      item-value="id"
      label="Parish"
      outlined
    ></v-autocomplete>

    <v-row>
      <v-col>
        <v-date-picker v-model="event.startDate"></v-date-picker>
      </v-col>
      <v-col>
        <v-time-picker
          v-model="event.startTime"
          label="starTime"
        ></v-time-picker>
      </v-col>
      <v-col>
        <v-time-picker v-model="event.endTime" label="endDate"></v-time-picker>
      </v-col>
    </v-row>

    <pre>{{ eventData }},</pre>
    <v-btn @click="$refs.createForm.reset()">Clear</v-btn>
  </v-form>
</template>

<script>
import parishes from '@/util/parishes'

export default {
  name: 'CreateEvent',
  data() {
    return {
      event: {
        title: '',
        description: '',
        startDate: null,
        startTime: null,
        endTime: null,
        categories: ['fish'],
        parishId: null,
      },
    }
  },
  computed: {
    eventData() {
      const springAhead = new Date('2022-03-14')
      return {
        title: this.event.title,
        description: this.event.description,
        slug: this.event.title
          ?.replaceAll(' ', '-')
          ?.replaceAll('.', '')
          ?.toLowerCase(),
        parishId: this.event.parishId,
        categories: this.event.categories,
        startDate: `${this.event.startDate}T${this.event.startTime}:00-0${
          new Date(this.event.startDate) >= springAhead ? 5 : 6
        }:00`,
        endDate: `${this.event.startDate}T${this.event.endTime}:00-0${
          new Date(this.event.startDate) >= springAhead ? 5 : 6
        }:00`,
      }
    },
    parishList() {
      return parishes
    },
  },
}
</script>

<style scoped></style>
