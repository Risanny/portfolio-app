import { createRouter, createWebHistory } from 'vue-router'

import HomeView            from '../views/HomeView.vue'
import PortfolioView       from '../views/PortfolioView.vue'
import PricesView          from '../views/PricesView.vue'
import ContactsView        from '../views/ContactsView.vue'
import AdminLayout         from '../components/AdminLayout.vue'
import AdminSessionTypes   from '../views/admin/AdminSessionTypes.vue'
import AdminPhotoUploads   from '../views/admin/AdminPhotoUploads.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/portfolio',
    name: 'portfolio',
    component: PortfolioView
  },
  {
    path: '/prices',
    name: 'prices',
    component: PricesView
  },
  {
    path: '/contacts',
    name: 'contacts',
    component: ContactsView
  },
  {
    path: '/admin',
    component: AdminLayout,
    children: [
      { path: '', redirect: 'session-types' },
      { path: 'session-types', component: AdminSessionTypes },
      { path: 'upload-photos', component: AdminPhotoUploads }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
