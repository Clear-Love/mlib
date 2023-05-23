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
    animationLink="https://assets2.lottiefiles.com/packages/lf20_8QH3Blu2Xh.json"
    :height="props.size?.toString()"
    :width="props.size?.toString()"
  />
</template>

<style scoped lang="scss"></style>
