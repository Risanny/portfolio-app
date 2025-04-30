// src/main.js

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'

// Устанавливаем базовый URL для всех запросов к бэку
axios.defaults.baseURL = 'http://localhost:8080/api'

// Делаем axios доступным в компонентах через this.$axios или import
const app = createApp(App)
app.config.globalProperties.$axios = axios

app.use(router)
app.mount('#app')
