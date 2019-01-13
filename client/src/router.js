import Vue from 'vue'
import Router from 'vue-router'
import EventList from './views/event/List'
import EventShow from './views/event/Show'
import EventEdit from "./views/admin/EventEdit";

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'EventList',
            component: EventList
        },
        {
            path: '/events/:id',
            name: 'EventShow',
            component: EventShow,
            props: true
        },
        {
            path: '/admin/events/create',
            name: 'CreateEvent',
            component: EventEdit,
        },
    ]
})