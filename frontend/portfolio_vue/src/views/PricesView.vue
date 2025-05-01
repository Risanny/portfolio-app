
<template>
  <div>
    <h1>Условия и Прайсы</h1>
    <p>Ниже представлены базовые тарифы на фотосессии:</p>
    <ul>
      <li v-for="type in types" :key="type.id">
        {{ type.name }} — от {{ type.price }} ₸
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const types = ref([])
const axios = getCurrentInstance().appContext.config.globalProperties.$axios

onMounted(async () => {
  try {
    // предполагаем, что в SessionTypeDto добавлено поле price;
    // если нет — покажем без цены
    const { data } = await axios.get('/session-types')
    types.value = data.map(t => ({
      id: t.id,
      name: t.name,
      price: t.price ?? 'договорная'
    }))
  } catch (e) {
    console.error('Ошибка при загрузке прайсов', e)
  }
})
</script>

<style scoped>
ul {
  list-style: disc;
  padding-left: 1.5rem;
}
</style>
