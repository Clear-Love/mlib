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
import vuetify from "@/plugins/vuetify";
import Vue3Lottie from "vue3-lottie";
import MasonryWall from "@yeger/vue-masonry-wall";
import * as process from "process";

const app = createApp(App)
const pinia = createPinia();
pinia.use(piniaPersist);
axios.defaults.baseURL = import.meta.env.VITE_BASE_API
app.use(pinia)
app.use(MasonryWall)
app.use(Vue3Lottie, { name: "LottieAnimation" });
app.use(router)
app.use(vuetify)

app.mount('#app')
