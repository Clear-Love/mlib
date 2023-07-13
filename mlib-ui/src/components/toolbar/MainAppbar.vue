<!--
* @Component:
* @Maintainer: lmio
* @Description:
-->
<script setup lang="ts">
import { useDisplay } from "vuetify";
import { useCustomizeThemeStore } from "@/stores/customizeTheme";
import ToolbarLanguage from "@/components/toolbar/ToolbarLanguage.vue";
import ToolbarNotifications from "./ToolbarNotifications.vue";
import ToolbarUser from "./ToolbarUser.vue";
import {onBeforeUnmount, onMounted, ref} from "vue";
import eventBus from "@/utils/eventBus";
const { mdAndUp } = useDisplay();
const customizeTheme = useCustomizeThemeStore();
const showMobileSearch = ref(false);
const searchInput = ref<HTMLElement | null>();
const searchText = ref('');
const handleKeyDown = (event: KeyboardEvent) => {
  if (event.key === '/') {
    event.preventDefault()
    searchInput.value?.focus()
  }
};

const search = () => {
  if (searchInput.value && searchText.value) {
    // Perform search logic here
    eventBus.emit('search', searchText)
  }
};

const handleEnter = (event: KeyboardEvent) => {
  if (event.key === 'Enter') {
    event.preventDefault();
    search();
  }
};
onMounted(() => {
  document.addEventListener('keydown', handleKeyDown)
})

onBeforeUnmount(() => {
  document.removeEventListener('keydown', handleKeyDown)
})


</script>

<template>
  <!-- ---------------------------------------------- -->
  <!--App Bar -->
  <!-- ---------------------------------------------- -->
  <v-app-bar :density="mdAndUp ? 'default' : 'compact'">
    <!-- ---------------------------------------------- -->
    <!-- search input mobil -->
    <!-- ---------------------------------------------- -->
    <div class="d-flex flex-fill align-center" v-if="showMobileSearch">
      <v-text-field
        color="primary"
        variant="solo"
        prepend-inner-icon="mdi-magnify"
        append-inner-icon="mdi-close"
        @click:append-inner="showMobileSearch = false"
        hide-details
        placeholder="Search"
      ></v-text-field>
    </div>
    <div v-else class="px-2 d-flex align-center justify-space-between w-100">
      <!-- ---------------------------------------------- -->
      <!-- NavIcon -->
      <!-- ---------------------------------------------- -->
      <v-app-bar-nav-icon
        @click="customizeTheme.mainSidebar = !customizeTheme.mainSidebar"
      ></v-app-bar-nav-icon>
      <v-spacer></v-spacer>
      <div class="align-center">
        <v-text-field
          v-if="mdAndUp"
          class="ml-2"
          style="width: 400px"
          color="primary"
          variant="solo"
          density="compact"
          hide-details
          placeholder="Search"
          ref="searchInput"
          v-model="searchText"
          @keydown.enter="handleEnter"
        ></v-text-field>
      </div>
      <div>
        <v-btn v-if="mdAndUp" icon="mdi-magnify" @click="search" id="search-button"></v-btn>
      </div>

      <v-spacer></v-spacer>

      <div class="d-flex">
        <v-btn v-if="!mdAndUp" icon @click="showMobileSearch = true">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
        <!-- search input desktop -->

        <v-btn v-if="mdAndUp" icon>
          <v-badge dot color="success">
            <v-icon>mdi-account-multiple-outline</v-icon>
          </v-badge>
        </v-btn>
        <ToolbarNotifications />
        <v-divider vertical thickness="2" inset class="ml-5 mr-1"></v-divider>

        <ToolbarLanguage />
        <ToolbarUser />
      </div>
    </div>
  </v-app-bar>
</template>

<style scoped lang="scss"></style>
