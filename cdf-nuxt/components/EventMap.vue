<template>
<div id="map" ref="mapContainer">Map</div>
</template>

<script>
const apiKey = process.env.GOOGLE_MAPS_API_KEY;
export default {
  name: "EventMap",
  props: {events: { type: Array, defaultValue: []}},
  data() {
    return {
      map: null,
      markers: []
    }
  },
  head() {
    return {
      script: [
        {src: `https://maps.googleapis.com/maps/api/js?key=${apiKey}`}
      ]
    }
  },
  mounted() {
    this.map = new window.google.maps.Map(this.$refs.mapContainer, {
      center: { lat: 44.9474193, lng: -93.1930733 },
      zoom: 7
    })

    this.markers = this.events.map((event) => {
      const marker = new window.google.maps.Marker({
        position: {lat: Number(event.parish.lat), lng: Number(event.parish.lng)},
        map: this.map,
        title: event.parish.name
      });

      const infowindow = new window.google.maps.InfoWindow({
        content: `<h2>${event.parish.name}</h2>
<hr /><br />
<p>${event.description}</p>
<p>${event.parish.address}, ${event.parish.city}, ${event.parish.state} ${event.parish.zip}</p>
`,
      });
      marker.addListener("click", () => {
        infowindow.open({
          anchor: marker,
          map: this.map,
          shouldFocus: false,
        });
      });
      return {
        marker
      }
    })
  }
}
</script>

<style scoped>
  #map {
    height:50vh;
    width: 100%;
  }
</style>
