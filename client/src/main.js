import '@babel/polyfill'
import Vue from 'vue'
import './plugins/vuetify'
import router from './router'
import App from './App.vue'
import {formatTimestamp} from "./util/DateUtil";

Vue.config.productionTip = false
Vue.filter('formatTimestamp', formatTimestamp)
new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
