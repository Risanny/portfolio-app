<!-- frontend/portfolio_vue/src/views/ContactsView.vue -->

<template>
  <div>
    <h1>Контакты</h1>
    <form @submit.prevent="submitForm">
      <div>
        <label>Имя:</label>
        <input v-model="form.name" required />
      </div>
      <div>
        <label>Телефон:</label>
        <input v-model="form.phone" required />
      </div>
      <div>
        <label>E-mail:</label>
        <input v-model="form.email" type="email" required />
      </div>
      <div>
        <label>Тип сессии:</label>
        <select v-model="form.sessionType" required>
          <option disabled value="">Выберите тип</option>
          <option v-for="t in types" :key="t.id" :value="t.name">
            {{ t.name }}
          </option>
        </select>
      </div>
      <div>
        <label>Комментарий:</label>
        <textarea v-model="form.comment"></textarea>
      </div>
      <button type="submit">Отправить</button>
    </form>
    <p v-if="status">{{ status }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const types = ref([])
const form = ref({
  name: '',
  phone: '',
  email: '',
  sessionType: '',
  comment: ''
})
const status = ref('')

onMounted(async () => {
  try {
    const { data } = await axios.get('/session-types')
    types.value = data
  } catch (e) {
    console.error('Не удалось получить виды сессий', e)
  }
})

const submitForm = async () => {
  try {
    await axios.post('/feedback', form.value)
    status.value = 'Заявка отправлена!'
    Object.assign(form.value, { name:'', phone:'', email:'', sessionType:'', comment:'' })
  } catch (e) {
    status.value = 'Ошибка при отправке.'
    console.error(e)
  }
}
</script>

<style scoped>
form > div {
  margin-bottom: 1rem;
}
label {
  display: block;
  margin-bottom: 0.25rem;
}
input, select, textarea {
  width: 100%;
  padding: 0.5rem;
}
button {
  padding: 0.5rem 1rem;
}
</style>
