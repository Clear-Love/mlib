<!--
 * @Author: lmio
 * @Date: 2023-04-13 14:15:29
 * @LastEditTime: 2023-04-22 17:35:44
 * @FilePath: /mlib/mlib-ui/src/views/homeView.vue
 * @Description: 
-->
<template>
    <div class="login-container">
        <div style="flex: 1;">

        </div>
        <div style="width: 350px;">
            <router-view v-slot="{ Component }">
                <transition name="el-fade-in-linear" mode="out-in">
                    <component :is="Component" style="height: 100%"/>
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