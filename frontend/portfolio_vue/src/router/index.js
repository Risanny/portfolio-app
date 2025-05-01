// frontend/portfolio_vue/src/router/index.js

import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import PortfolioView from '../views/PortfolioView.vue'
import PricesView from '../views/PricesView.vue'
import ContactsView from '../views/ContactsView.vue'

const routes = [
  { path: '/',       component: HomeView },
  { path: '/portfolio', component: PortfolioView },
  { path: '/prices',    component: PricesView },
  { path: '/contacts',   component: ContactsView },
]


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
