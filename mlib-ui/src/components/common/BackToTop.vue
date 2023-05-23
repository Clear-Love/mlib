<template>
  <div class="back-to-top" :class="{ visible: isVisible }" @click="scrollToTop">
    <Icon class="text-white" width="33" icon="icon-park-outline:up" />
  </div>
</template>

<script lang="ts">
import {Icon} from "@iconify/vue";

export default {
  components: {Icon},
  data: () => ({
    isVisible: false,
  }),
  onMounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  onUnmounted() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  methods: {
    handleScroll: () => {
      this.isVisible = window.scrollY > 200;
    },
    scrollToTop: () => {
      window.scrollTo({ top: 0, behavior: "smooth" });
    },
  }
}
</script>

<style lang="scss" scoped>
.back-to-top {
  position: fixed;
  background-color: #344767;
  opacity: 0;
  visibility: hidden;
  bottom: 50px;
  right: 5px;
  width: 50px;
  height: 50px;
  //z-index: 999;
  padding: 0.5rem;
  border-radius: 50%;
  box-shadow: 1px 1px 9px #344767;
  transition: all 0.5s;
  cursor: pointer;
  &:hover {
    box-shadow: 1px 1px 18px #344767;
    transition: all 0.5s;
  }
}

.back-to-top.visible {
  opacity: 1;
  visibility: visible;
}
</style>
