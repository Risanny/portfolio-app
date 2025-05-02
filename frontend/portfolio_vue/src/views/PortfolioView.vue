<template>
  <div>
    <h1>Портфолио</h1>

    <div v-if="sessions.length === 0">
      Нет сессий для отображения.
    </div>

    <div v-else>
      <div
        v-for="session in sessions"
        :key="session.id"
        class="session"
      >
        <h2>{{ session.title }}</h2>
        <p>{{ session.description }}</p>
        <div class="photos">
          <img
            v-for="filename in session.photoFilenames || []"
            :key="filename"
            :src="`/uploads/${session.id}/${filename}`"
            alt="Фото сессии"
            @click="openLightbox(`/uploads/${session.id}/${filename}`)"
          />
        </div>
      </div>
    </div>

    <!-- Lightbox-компонент -->
    <Lightbox
      :visible="lightboxVisible"
      :src="lightboxSrc"
      @close="closeLightbox"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import Lightbox from '@/components/Lightbox.vue'

/** Список сессий из бэка */
const sessions = ref([])

// Lightbox state
const lightboxVisible = ref(false)
const lightboxSrc     = ref('')

onMounted(async () => {
  try {
    const { data } = await axios.get('/sessions')
    sessions.value = data
  } catch (err) {
    console.error('Ошибка при загрузке сессий', err)
  }
})

function openLightbox(src) {
  lightboxSrc.value = src
  lightboxVisible.value = true
}

function closeLightbox() {
  lightboxVisible.value = false
}
</script>

<style scoped>
.session {
  margin-bottom: 2rem;
}

/* Сетка для миниатюр */
.photos {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1rem;
  margin-top: 1rem;
}

/* Сами превью */
.photos img {
  width: 100%;
  height: auto;
  object-fit: cover;
  border-radius: 4px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.15);
  cursor: zoom-in;
  transition: transform .2s;
}
.photos img:hover {
  transform: scale(1.05);
}
</style>
