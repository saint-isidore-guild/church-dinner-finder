import Vue from 'vue'
import Router from 'vue-router'
import EventList from './views/event/List'
import EventShow from './views/event/Show'
import EventEdit from "./views/admin/EventEdit";
import CategoryList from "./components/CategoryList";
import FishFryList from "./views/event/FishFryList";

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'Home',
            component: FishFryList
        },
        {
            path: '/events',
            name: 'EventSearch',
            component: EventList,
            props: true
        },
        {
            path: '/events/:id',
            name: 'EventShow',
            component: EventShow,
            props: true
        },
        {
            path: '/category/:category',
            name: 'CategoryList',
            component: CategoryList,
            props: true
        },
        {
            path: '/admin/events/create',
            name: 'CreateEvent',
            component: EventEdit,
        },
    ]
})