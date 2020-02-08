import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Layout from '../layout'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home',
    component: Layout,
    children: [
      {
        path: '/home',
        name: 'home',
        component: Home
      },
      {
        path: '/photo',
        name: 'photo',
        component: () => import('../views/photo/photo.vue')
      },
      {
        path: '/map',
        name: 'map',
        component: () => import('../views/map/map.vue')
      }
    ]
  },
  {
    path: '/preview',
    name: 'preview',
    component: () => import('../views/preview/preview.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
