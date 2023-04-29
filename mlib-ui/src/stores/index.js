import {ref, computed, reactive} from 'vue'
import { defineStore } from 'pinia'

export const userStore = defineStore('store', () => {
    const auth = reactive({
        config: null,
        email: null,
        username: null,
    })
    return {auth}
})
