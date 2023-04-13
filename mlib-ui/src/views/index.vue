<template>
    <div>
        欢迎来到Mlib
    </div>
    <div>
        <el-button type="primary" @click="logout()">
            退出登录
        </el-button>
        
    </div>
</template>

<script setup>
import {get} from '@/utils/request';
import router from '../router';
import { ElMessage } from 'element-plus';

const logout = () => {
    get('/api/auth/logout', (message) => {
        ElMessage.success(message)
        router.push('/')
    })
}
</script>

<style scoped>

</style>