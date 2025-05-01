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

        <!-- лайтбокс -->
        <Lightbox
          :visible="lightboxVisible"
          :src="lightboxSrc"
          @close="closeLightbox"
        />
        </div>
      </div>
    </div>
</template>

<script setup>
// Импортируем из Vue и Axios
import { ref, onMounted } from 'vue'
import axios from 'axios'

import Lightbox from '@/components/Lightbox.vue'

const sessions = ref([])

// Для лайтбокса
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

// Открыть лайтбокс
function openLightbox(src) {
  lightboxSrc.value = src
  lightboxVisible.value = true
}

// Закрыть лайтбокс
function closeLightbox() {
  lightboxVisible.value = false
}

/**
 * @typedef {Object} Session
 * @property {number} id
 * @property {string} title
 * @property {string} description
 * @property {string} sessionDate
 * @property {number} typeId
 * @property {string[]} photoFilenames
 */

onMounted(async () => {
  try {
    const { data } = await axios.get('/sessions')
    sessions.value = data
  } catch (err) {
    console.error('Ошибка при загрузке сессий', err)
  }
})
</script>

<style scoped>
.session {
  margin-bottom: 2rem;
}

/* Обёртка галереи: flex или grid на ваш вкус */
.photos {
  display: grid;
  /* 3 фото в ряд, каждое минимум 200px, максимум растягивается равномерно */
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1rem;             /* расстояние между картинками */
  margin-top: 1rem;
}

/* Сами картинки */
.photos img {
  width: 100%;           /* растягиваем под ячейку grid */
  height: auto;          /* сохраняем пропорции */
  object-fit: cover;     /* обрезает лишнее, если необходимо */
  border-radius: 4px;    /* чуть скруглим углы */
  box-shadow: 0 2px 6px rgba(0,0,0,0.15);
  transition: transform .2s;
}

/* При наведении сделаем «zoom» */
.photos img:hover {
  transform: scale(1.05);
}
</style>

