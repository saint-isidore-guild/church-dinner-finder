import '@babel/polyfill'
import Vue from 'vue'
import './plugins/vuetify'
import router from './router'
import App from './App.vue'
import moment from 'moment'

Vue.config.productionTip = false
Vue.filter('formatTimestamp', function (value) {
    if (!value) return ''
    return moment.unix(value).format('MM/DD/YY hh:mm A').valueOf()
})
new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
