<template>
    <div style="background-color: white;text-align: center;">
        <div style="margin-top: 150px">
            <div style="font-size: 25px;">登录</div>
            <div style="font-size: 14px;color: grey;">输入用户名和密码进行登录</div>
        </div>
        <div style="margin-top: 30px">
            <el-input v-model="form.username" style="width: 300px;" type="text" placeholder="用户名/邮箱">
                <template #prefix>
                    <el-icon>
                        <User/>
                    </el-icon>
                </template>
            </el-input>
            <el-input v-model="form.password" style="width: 300px; margin-top: 10px;" type="password"
                      placeholder="密码">
                <template #prefix>
                    <el-icon>
                        <Lock/>
                    </el-icon>
                </template>
            </el-input>
            <el-row style="margin-top: 5px;" :gutter="20">
                <el-col :span="12" :offset="0" style="text-align: left">
                    <el-checkbox v-model="form.remember" label="记住我"/>
                </el-col>
                <el-col :span="12" :offset="0" style="text-align: right">
                    <el-link type="primary" :underline="true" href="" target="_blank">忘记密码？</el-link>
                </el-col>
            </el-row>

            <div style="margin-top: 20px">
                <el-button style="width: 150px" type="primary" @click="login()">登录</el-button>
            </div>
            <div style="margin-top: 20px">
                <el-button style="width: 150px" type="primary" @click="router.push('/register')">注册账号</el-button>
            </div>
        </div>
    </div>
</template>

<script setup>
import {Lock, User} from '@element-plus/icons-vue';
import {reactive} from 'vue';
import {ElMessage} from 'element-plus';
import {post} from '@/utils/request';
import router from '../../router';

const form = reactive({
    username: '',
    password: '',
    remember: false
})

const login = () => {
    if (!form.username || !form.password) {
        ElMessage.warning('请填写用户名和密码')
    } else {
        post('/api/auth/login', {
            username: form.username,
            password: form.password,
            remember: form.remember
        }, (message) => {
            ElMessage.success(message)
            router.push('/index')
        })
    }
}
</script>

<style scoped></style>