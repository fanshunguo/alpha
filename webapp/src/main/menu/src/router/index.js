import VueRouter from 'vue-router'
import Page1 from '../pages/Page1.vue'
import Page2 from '../pages/Page2.vue'
import Page4 from '../pages/Page4.vue'
import baseSearch from '../pages/base_search.vue'
import accountDetail from '../components/AccountDetail.vue'

const routes = [
  {
    path: '/base_search',
    name: 'base_search',
    component: baseSearch,
    meta: {
      keepAlive: true
    }
  },
  {
    path: '/',
    name: 'page1',
    component: Page1
  },
  {
    path: '/page2',
    name: 'page2',
    component: Page2
  },
  {
    path: '/page4',
    name: 'page4',
    component: Page4
  },
  {
    path: '/accountDetail',
    name: 'accountDetail',
    component: accountDetail
  }
]

const router = new VueRouter({
  routes, // short for routes: routes
  linkActiveClass: 'active'
})

export {
  router
}
