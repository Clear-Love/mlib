<!--
 * @Author: lmio
 * @Date: 2023-04-13 14:15:29
 * @LastEditTime: 2023-04-22 17:35:44
 * @FilePath: /mlib/mlib-ui/src/views/home.vue
 * @Description: 
-->
<template>
    <div class="login-container">
        <div style="flex: 1;">
            
        </div>
        <div style="width: 350px;">
            <router-view v-slot="{ Component }">
                <transition>
                    <component :is="Component" />
                </transition>
            </router-view>
        </div>
        <div style="flex: 1;">
            
        </div>
    </div>
</template>

<script>

</script>

<style scoped>
.login-container {
    display: flex;
    width: 100vw;
    height: 100vh;
}
</style>