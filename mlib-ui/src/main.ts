/**
 * main.ts
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'

// Plugins
import axios from "axios";
import {createPinia} from "pinia";
import piniaPersist from "pinia-plugin-persist";
import router from "@/router";
import i18n from "./plugins/i18n";
import vuetify from "@/plugins/vuetify";
import Vue3Lottie from "vue3-lottie";
import MasonryWall from "@yeger/vue-masonry-wall";

const app = createApp(App)
const pinia = createPinia();
pinia.use(piniaPersist)
axios.defaults.baseURL = import.meta.env.VITE_BASE_URL
app.use(pinia)
app.use(MasonryWall)
app.use(i18n)
app.use(Vue3Lottie, { name: "LottieAnimation" });
app.use(router)
app.use(vuetify)
app.mount('#app')
