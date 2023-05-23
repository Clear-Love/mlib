<template>
  <v-app id="app" :theme="customizeTheme.darkTheme ? 'dark' : 'light'">
    <component :is="currentLayout" v-if="isRouterLoaded">
      <router-view> </router-view>
    </component>
    <CustomizationMenu />
    <BackToTop />
    <Snackbar />
  </v-app>
</template>

<script setup lang="ts">
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import AuthLayout from "@/layouts/AuthLayout.vue";
import CustomizationMenu from "@/components/CustomizationMenu.vue";
import { useCustomizeThemeStore } from "@/stores/customizeTheme";
import BackToTop from "@/components/common/BackToTop.vue";
import Snackbar from "@/components/common/Snackbar.vue";
import {useRoute} from "vue-router";
import {computed} from "vue";

const customizeTheme = useCustomizeThemeStore();
const route = useRoute();

const isRouterLoaded = computed(() => {
  return route.name !== null;
});

const layouts = {
  default: DefaultLayout,
  auth: AuthLayout,
};

type LayoutName = "default" | "ui" | "landing" | "auth" | "error";

const currentLayout = computed(() => {
  const layoutName = route.meta.layout as LayoutName;
  if (!layoutName) {
    return DefaultLayout;
  }
  return layouts[layoutName];
});
</script>

<style scoped></style>
