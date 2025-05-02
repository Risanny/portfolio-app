import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'

// Настроим axios так, чтобы все запросы к API шли по относительному пути /api
axios.defaults.baseURL = '/api'

const app = createApp(App)
app.use(router)
app.config.globalProperties.$axios = axios
app.mount('#app')
