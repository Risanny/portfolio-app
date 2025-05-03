<!-- src/components/AdminLayout.vue -->
<template>
  <div class="admin-layout">
    <!-- Собственный админ-навбар -->
    <nav class="admin-navbar">
      <router-link
        to="/admin/session-types"
        class="admin-link"
        exact-active-class="active"
      >
        Типы сессий
      </router-link>
      <router-link
        to="/admin/upload-photos"
        class="admin-link"
        exact-active-class="active"
      >
        Загрузка фото
      </router-link>
    </nav>

    <!-- Основной админ-контент -->
    <main class="admin-content">
      <router-view />
    </main>
  </div>
</template>

<script setup>
// Здесь логики нет
</script>

<style scoped>
.admin-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* Навбар админки */
.admin-navbar {
  display: flex;
  background-color: #2c3e50;
  padding: 0.75rem 1rem;
  gap: 1.5rem;
}

.admin-link {
  color: #ecf0f1;
  text-decoration: none;
  font-weight: 500;
}

.admin-link:hover {
  text-decoration: underline;
}

/* Класс active подсвечивает текущую вкладку */
.active {
  border-bottom: 2px solid #ecf0f1;
}

/* Контент под навбаром */
.admin-content {
  flex: 1;
  padding: 1.5rem;
}
</style>
