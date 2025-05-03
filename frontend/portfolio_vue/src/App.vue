<template>
  <div id="app">
    <!-- Глобальный Navbar показываем только вне /admin -->
    <Navbar v-if="!isAdminRoute" />

    <!-- Здесь попадает либо публичный контент, либо AdminLayout -->
    <router-view />

    <!-- Глобальный Footer показываем только вне /admin -->
    <Footer v-if="!isAdminRoute" />
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { computed } from 'vue'

import Navbar from '@/components/Navbar.vue'
import Footer from '@/components/Footer.vue'

const route = useRoute()
// будем считать, что любые пути, начинающиеся на /admin, — это админка
const isAdminRoute = computed(() => route.path.startsWith('/admin'))
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
