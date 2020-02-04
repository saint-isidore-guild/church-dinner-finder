import Vue from 'vue'
import router from './router'
import App from './App.vue'
import DatetimePicker from 'vuetify-datetime-picker'
import 'vuetify-datetime-picker/src/stylus/main.styl'
import {formatTimestamp} from "./util/DateUtil";
import vuetify from "./plugins/vuetify";

Vue.config.productionTip = false
Vue.filter('formatTimestamp', formatTimestamp)
Vue.use(DatetimePicker)

new Vue({
    router,
    vuetify,
    render: h => h(App)
}).$mount('#app')
