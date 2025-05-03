<template>
  <div class="session-types-container">
    <h2 class="title">Управление типами фотосессий</h2>

    <!-- Сообщение об ошибке -->
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>

    <!-- Форма добавления нового типа -->
    <div class="form-add">
      <input
        v-model="newName"
        class="input-new"
        type="text"
        placeholder="Название"
      />
      <input
        v-model="newPrice"
        class="input-new price-input"
        type="text"
        placeholder="Цена (напр., 40000/час)"
      />
      <textarea
        v-model="newDescription"
        class="input-new desc-input"
        placeholder="Описание"
      ></textarea>
      <button
        type="button"
        class="btn-add"
        @click="onCreate"
      >
        Добавить
      </button>
    </div>

    <!-- Список типов -->
    <ul class="sessions-list">
      <li
        v-for="(type, idx) in types"
        :key="type.id"
        class="session-item"
      >
        <div class="session-data">
          <template v-if="editingId === type.id">
            <input
              v-model="editingName"
              class="input-edit"
              type="text"
              placeholder="Название"
            />
            <input
              v-model="editingPrice"
              class="input-edit price-edit"
              type="text"
              placeholder="Цена"
            />
            <textarea
              v-model="editingDescription"
              class="input-edit desc-edit"
              placeholder="Описание"
            ></textarea>
          </template>
          <template v-else>
            <div class="session-view">
              <span class="session-name">{{ type.name }}</span>
              <span class="session-price">{{ type.price }}</span>
              <p class="session-desc">{{ type.description }}</p>
            </div>
          </template>
        </div>

        <div class="actions">
          <button
            v-if="editingId !== type.id"
            @click="startEdit(type)"
            class="btn-action"
          >✏️</button>
          <button
            v-else
            @click="confirmEdit(type.id)"
            class="btn-action"
          >✔️</button>
          <button
            v-if="editingId === type.id"
            @click="cancelEdit"
            class="btn-action"
          >❌</button>
          <button
            @click="onDelete(type.id)"
            class="btn-action"
          >🗑️</button>
        </div>
      </li>

      <li
        v-if="types.length === 0"
        class="session-item empty"
      >
        Список пуст — добавьте первый тип.
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import {
  listSessionTypes,
  createSessionType,
  updateSessionType,
  deleteSessionType
} from '@/api.js'

const types = ref([])
const newName = ref('')
const newPrice = ref('')
const newDescription = ref('')
const editingId = ref(null)
const editingName = ref('')
const editingPrice = ref('')
const editingDescription = ref('')
const errorMessage = ref('')

async function fetchTypes() {
  types.value = (await listSessionTypes()).data
  errorMessage.value = ''
}

function onCreate() {
  const name = newName.value.trim()
  const price = newPrice.value.trim()
  const description = newDescription.value.trim()
  if (!name || !price) return

  createSessionType({ name, price, description })
    .then(() => {
      newName.value = ''
      newPrice.value = ''
      newDescription.value = ''
      fetchTypes()
    })
    .catch(err => {
      console.error(err)
      errorMessage.value = 'Ошибка при создании типа.'
    })
}

function startEdit(type) {
  editingId.value = type.id
  editingName.value = type.name
  editingPrice.value = type.price
  editingDescription.value = type.description
}

function confirmEdit(id) {
  const name = editingName.value.trim()
  const price = editingPrice.value.trim()
  const description = editingDescription.value.trim()
  if (!name || !price) return

  updateSessionType(id, { name, price, description })
    .then(() => {
      editingId.value = null
      editingName.value = ''
      editingPrice.value = ''
      editingDescription.value = ''
      fetchTypes()
    })
    .catch(err => {
      console.error(err)
      errorMessage.value = 'Ошибка при сохранении изменений.'
    })
}

function cancelEdit() {
  editingId.value = null
  editingName.value = ''
  editingPrice.value = ''
  editingDescription.value = ''
  errorMessage.value = ''
}

function onDelete(id) {
  if (!confirm('Удалить этот тип?')) return

  deleteSessionType(id)
    .then(fetchTypes)
    .catch(err => {
      console.error(err)
      // если сервер вернул текст ошибки — возьмём его
      if (err.response && err.response.status === 409) {
        errorMessage.value = err.response.data
      } else {
        errorMessage.value = 'Не удалось удалить тип.'
      }
    })
}

onMounted(fetchTypes)
</script>

<style scoped>
.session-types-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 2rem 1rem;
  font-family: Arial, sans-serif;
}

.title {
  text-align: center;
  margin-bottom: 1.5rem;
}

/* Ошибки */
.error-message {
  color: #e74c3c;
  text-align: center;
  margin-bottom: 1rem;
}

.form-add {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  justify-content: center;
  margin-bottom: 2rem;
}

.input-new {
  flex: 1 1 150px;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.price-input {
  max-width: 120px;
}

.desc-input {
  flex: 1 1 100%;
  min-height: 60px;
  resize: vertical;
}

.btn-add {
  padding: 0.5rem 1rem;
  border: 1px solid #007bff;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  border-radius: 4px;
}

.sessions-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.session-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0.75rem 0;
  border-top: 1px solid #ddd;
}

.session-item:first-child {
  border-top: none;
}

.session-data {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
}

.session-view {
  display: flex;
  flex-direction: column;
}

.session-name {
  font-weight: bold;
  font-size: 1rem;
}

.session-price {
  color: #555;
  font-size: 0.9rem;
}

.session-desc {
  margin: 0;
  color: #777;
  font-size: 0.85rem;
}

.input-edit {
  flex: 1;
  padding: 0.4rem;
  border: 1px solid #aaa;
  border-radius: 4px;
}

.price-edit {
  max-width: 120px;
}

.desc-edit {
  min-height: 50px;
  resize: vertical;
}

.actions {
  display: flex;
  gap: 0.5rem;
  margin-left: 1rem;
}

.btn-action {
  border: 1px solid #ccc;
  background: transparent;
  padding: 0.25rem 0.5rem;
  cursor: pointer;
  border-radius: 4px;
}

.session-item.empty {
  justify-content: center;
  color: #888;
  font-style: italic;
}
</style>
