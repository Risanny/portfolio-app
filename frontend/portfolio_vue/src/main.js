import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import './assets/custom.css'



// Настроим axios так, чтобы все запросы к API шли по относительному пути /api
axios.defaults.baseURL = '/api'

const app = createApp(App)
app.use(router)
app.config.globalProperties.$axios = axios
app.mount('#app')
