<template>
  <div class="session-uploads-container fade-in-up">
    <h2 class="text-center mb-4">Управление фотосессией</h2>

    <!-- Селект и кнопки -->
    <div class="d-flex gap-2 justify-content-center mb-4">
      <select v-model="selectedSessionId" class="form-select w-auto">
        <option disabled value="">— Выберите сессию —</option>
        <option value="__new">+ Новая</option>
        <option
          v-for="s in sessions"
          :key="s.id"
          :value="s.id"
        >{{ s.title }}</option>
      </select>

      <button
        v-if="selectedSessionId==='__new'"
        class="btn btn-outline-primary"
        @click="onCreateSession"
      >Создать</button>

      <button
        v-else-if="selectedSessionId"
        class="btn btn-outline-danger"
        @click="onDeleteSession"
      >Удалить сессию</button>
    </div>

    <!-- Форма для новой сессии -->
    <div
      v-if="selectedSessionId==='__new'"
      class="d-flex gap-2 justify-content-center mb-4"
    >
      <input
        v-model="newSession.title"
        type="text"
        class="form-control w-auto"
        placeholder="Название"
      />
      <input
        v-model="newSession.sessionDate"
        type="date"
        class="form-control w-auto"
      />
      <select
        v-model="newSession.typeId"
        class="form-select w-auto"
      >
        <option disabled value="">— Тип сессии —</option>
        <option
          v-for="t in sessionTypes"
          :key="t.id"
          :value="t.id"
        >{{ t.name }}</option>
      </select>
    </div>

    <!-- Фото-сетка -->
    <div
      v-if="selectedSessionId && selectedSessionId!=='__new'"
      class="row gx-3 gy-3 justify-content-center"
    >
      <div
        v-for="fn in sessionDetails.photoFilenames"
        :key="fn"
        class="col-4 position-relative"
      >
        <img
          :src="`/uploads/${selectedSessionId}/${fn}`"
          class="img-fluid rounded"
        />
        <button
          class="btn btn-sm btn-danger position-absolute top-0 end-0 m-1"
          @click="removePhoto(fn)"
        >✕</button>
      </div>
      <div class="col-4 d-flex align-items-center justify-content-center">
        <button
          class="btn btn-outline-primary btn-lg"
          @click="triggerFileSelect"
        >＋</button>
      </div>
    </div>

    <input
      ref="fileInput"
      type="file"
      multiple
      class="d-none"
      @change="onFileChange"
    />

    <div class="text-center mt-3">
      <button
        v-if="filesToUpload.length"
        class="btn btn-success"
        @click="onUpload"
      >Загрузить {{ filesToUpload.length }}</button>
    </div>

    <p v-if="message" class="text-center mt-2 text-success">{{ message }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import {
  listSessions,
  listSessionTypes,
  createSession,
  getSessionById,
  uploadSessionPhotos,
  deletePhoto,
  deleteSession
} from '@/api.js'

const sessions         = ref([])
const sessionTypes     = ref([])
const selectedSessionId= ref('')
const sessionDetails   = ref({ photoFilenames: [] })

// Для новой сессии
const newSession = ref({
  title: '',
  sessionDate: '',
  typeId: ''
})

const filesToUpload = ref([])
const message       = ref('')

onMounted(async () => {
  sessions.value     = (await listSessions()).data
  sessionTypes.value = (await listSessionTypes()).data
})

watch(selectedSessionId, async id => {
  // Сбрасываем поля при переключении
  filesToUpload.value = []
  message.value       = ''
  if (id && id !== '__new') {
    sessionDetails.value = (await getSessionById(id)).data
  }
})

async function onCreateSession() {
  // Проверяем, что поля заполнены
  if (
    !newSession.value.title ||
    !newSession.value.sessionDate ||
    !newSession.value.typeId
  ) {
    alert('Заполните все поля новой сессии')
    return
  }
  const res = await createSession(newSession.value)
  sessions.value.push(res.data)
  // Переключаемся на созданную сессию
  selectedSessionId.value = res.data.id
  // Сбрасываем форму
  newSession.value = { title: '', sessionDate: '', typeId: '' }
}

async function removePhoto(fn) {
  await deletePhoto(selectedSessionId.value, fn)
  sessionDetails.value = (await getSessionById(selectedSessionId.value)).data
}

const fileInput = ref(null)
function triggerFileSelect() { fileInput.value.click() }
function onFileChange(e) { filesToUpload.value = Array.from(e.target.files) }

async function onUpload() {
  await uploadSessionPhotos(selectedSessionId.value, filesToUpload.value)
  message.value       = 'Успешно!'
  filesToUpload.value = []
  fileInput.value.value = ''
  sessionDetails.value = (await getSessionById(selectedSessionId.value)).data
}

async function onDeleteSession() {
  if (!confirm('Удалить сессию?')) return
  await deleteSession(selectedSessionId.value)
  sessions.value = (await listSessions()).data
  selectedSessionId.value = ''
}
</script>

<style scoped>
.session-uploads-container {
  padding: 2rem 1rem;
}
</style>
