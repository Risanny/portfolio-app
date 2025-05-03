<template>
  <nav class="navbar">
    <router-link to="/" class="nav-link" exact-active-class="active">Главная</router-link>
    <router-link to="/portfolio" class="nav-link" exact-active-class="active">Портфолио</router-link>
    <router-link to="/prices" class="nav-link" exact-active-class="active">Прайс</router-link>
    <router-link to="/contacts" class="nav-link" exact-active-class="active">Контакты</router-link>
    <!-- ссылка на админку убрана -->
  </nav>
</template>

<script setup>
// Здесь логики нет — навигация через <router-link>
</script>

<style scoped>
.navbar {
  display: flex;
  gap: 1.5rem;
  padding: 0.75rem 1rem;
  background-color: #2c3e50;
}

.nav-link {
  color: #ecf0f1;
  text-decoration: none;
  font-weight: 500;
}

.nav-link:hover {
  text-decoration: underline;
}

.active {
  border-bottom: 2px solid #ecf0f1;
}
</style>
