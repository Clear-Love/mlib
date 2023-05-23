<script setup lang="ts">
import { Vue3Lottie } from "vue3-lottie";
const props = defineProps({
  size: {
    type: Number,
    default: 400,
  },
});
</script>

<template>
  <Vue3Lottie
    animationLink="https://assets6.lottiefiles.com/packages/lf20_ofa3xwo7.json"
    :height="props.size?.toString()"
    :width="props.size?.toString()"
  />
</template>

<style scoped lang="scss"></style>
