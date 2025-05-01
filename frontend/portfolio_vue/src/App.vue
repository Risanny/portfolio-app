<template>
  <div id="app">
    <header>
      <nav>
        <router-link to="/">Главная</router-link>
        <router-link to="/portfolio">Портфолио</router-link>
        <router-link to="/prices">Условия и прайсы</router-link>
        <router-link to="/contacts">Контакты</router-link>
      </nav>
    </header>

    <main>
      <!-- Router-view рендерит то, что описано в src/router/index.js -->
      <router-view/>
    </main>

    <footer>
      © 2025 Photographer Portfolio
    </footer>
  </div>
</template>

<script setup>
// здесь больше ничего не должно быть
</script>

<style>
#app {
  font-family: Arial, sans-serif;
  max-width: 960px;
  margin: 0 auto;
  padding: 1rem;
}

nav {
  display: flex;
  gap: 1rem;
  margin-bottom: 2rem;
}

nav a {
  text-decoration: none;
  color: #333;
}

nav a.router-link-active {
  font-weight: bold;
}

footer {
  margin-top: 2rem;
  text-align: center;
  color: #666;
}
</style>
