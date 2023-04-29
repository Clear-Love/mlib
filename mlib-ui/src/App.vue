<script setup>
import { RouterView } from 'vue-router'
import  {get} from '@/utils/request'
import router from "@/router";
import {userStore} from "@/stores";

const store = userStore()

get('api/user/info', (message) => {
    store.auth.config = message
    store.auth.username = message.username
    router.push('/index')
}, () => {
    store.auth.user = null
})
</script>

<template>
  <router-view/>
</template>

<style scoped>

</style>
