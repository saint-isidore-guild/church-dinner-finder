<template>
  <div id="map" ref="mapContainer">Map</div>
</template>

<script>
import moment from 'moment'

const apiKey = process.env.GOOGLE_MAPS_API_KEY
export default {
  name: 'EventMap',
  props: {
    events: {
      type: Array,
      default() {
        return []
      },
    },
  },
  data() {
    return {
      map: null,
      markers: [],
    }
  },
  head() {
    return {
      script: [
        { src: `https://maps.googleapis.com/maps/api/js?key=${apiKey}` },
      ],
    }
  },
  computed: {
    eventsByParish() {
      return this.events.reduce((acc, curr) => {
        if (!acc[curr.parishId]) {
          acc[curr.parishId] = [] // If this type wasn't previously stored
        }
        acc[curr.parishId].push(curr)
        return acc
      }, {})
    },
  },
  mounted() {
    this.map = new window.google.maps.Map(this.$refs.mapContainer, {
      center: { lat: 44.9474193, lng: -93.1930733 },
      zoom: 9,
    })

    Object.entries(this.eventsByParish).forEach(([parishId, parishEvents]) => {
      const parish = parishEvents[0].parish
      const marker = new window.google.maps.Marker({
        position: {
          lat: Number(parish.lat),
          lng: Number(parish.lng),
        },
        map: this.map,
        title: parish.name,
      })

      let eventDateString = ''
      parishEvents.forEach((event) => {
        eventDateString += `<li>${moment(event.startDate).format(
          'MMMM d, h:mm a'
        )}</li>`
      })

      const infowindow = new window.google.maps.InfoWindow({
        content: `<h2>${parish.name}</h2>
<hr /><br />
<p class="subtitle-2">${parish.address}, ${parish.city}, ${parish.state} ${parish.zip}</p>
<p class="subtitle-2">Dates:</p>
<ul>${eventDateString}</ul>
`,
      })
      marker.addListener('click', () => {
        infowindow.open({
          anchor: marker,
          map: this.map,
          shouldFocus: false,
        })
      })
      return {
        marker,
      }
    })
  },
}
</script>

<style scoped>
#map {
  height: 50vh;
  width: 100%;
}
</style>
