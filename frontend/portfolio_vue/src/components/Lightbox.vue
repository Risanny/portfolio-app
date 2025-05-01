<template>
  <!-- рендерим только если открыт -->
  <Teleport to="body">
    <transition name="lightbox-fade">
      <div v-if="visible" class="lightbox-overlay" @click.self="close">
        <img :src="src" class="lightbox-img" />
      </div>
    </transition>
  </Teleport>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

/**
 * @prop {boolean} visible — показывать ли окно
 * @prop {string} src — URL картинки
 */
const props = defineProps({
  visible: Boolean,
  src:      String
})
const emit = defineEmits(['close'])

function close() {
  emit('close')
}
</script>

<style scoped>
.lightbox-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.85);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
}

.lightbox-img {
  max-width: 90vw;
  max-height: 90vh;
  border-radius: 4px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.5);
  cursor: zoom-out;
}

/* Анимация появления */
.lightbox-fade-enter-from,
.lightbox-fade-leave-to {
  opacity: 0;
  transform: scale(0.95);
}
.lightbox-fade-enter-active,
.lightbox-fade-leave-active {
  transition: opacity .2s ease, transform .2s ease;
}
</style>
