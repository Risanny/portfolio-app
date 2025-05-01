
<template>
  <div>
    <h1>Портфолио</h1>
    <div v-if="sessions.length === 0">Нет сессий для отображения.</div>
    <div v-else>
      <div v-for="session in sessions" :key="session.id" class="session">
        <h2>{{ session.title }}</h2>
        <p>{{ session.description }}</p>
        <div class="photos">
          <img
            v-for="fn in session.photoFilenames"
            :key="fn"
            :src="`http://localhost:8080/uploads/${session.id}/${fn}`"
            alt="Фото сессии"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const sessions = ref([])
const axios = getCurrentInstance().appContext.config.globalProperties.$axios

onMounted(async () => {
  try {
    const { data } = await axios.get('/sessions')
    sessions.value = data
  } catch (e) {
    console.error('Ошибка при загрузке сессий', e)
  }
})
</script>

<style scoped>
.session {
  margin-bottom: 2rem;
}
.photos img {
  max-width: 150px;
  margin-right: 0.5rem;
}
</style>
