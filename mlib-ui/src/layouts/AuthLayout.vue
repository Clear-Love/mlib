<template>
  <div class="d-flex text-center flex-grow-1">
    <v-sheet
      class="layout-side d-none d-md-flex flex-column justify-space-between"
    >
      <div class="mt-3 mt-md-10 pa-2">
        <div class="text-primary text-h2 font-weight-bold">
          <v-row>
            <v-col class="text-right" md="5">
              <Icon  icon="ion:book-sharp"></Icon>
            </v-col>
            <v-col class="text-left">
              Mlib
            </v-col>
          </v-row>
        </div>
        <div class="title my-2 mt-n7">
          welcome to mlib
        </div>
      </div>
    </v-sheet>
    <v-card
      variant="text"
      class="flex-grow-1 d-flex align-center justify-center flex-column"
    >
      <div class="layout-content ma-auto w-full">
        <slot></slot>
      </div>
      <div class="pa-5">Vue 3.2 & Vuetify 3.1.13 & Vite 4.2</div>
    </v-card>
  </div>
</template>

<style lang="scss" scoped>
.layout-side {
  width: 420px;
}

.layout-content {
  max-width: 480px;
}
</style>
<script lang="ts" setup>
import {Icon} from "@iconify/vue";
</script>
