<template>
    <div style="text-align: center; margin: 0 20px">
        <div style="margin-top: 150px">
            <el-steps :active="active" finish-status="success" align-center>
                <el-step title="验证电子邮件"/>
                <el-step title="重新设定密码"/>
            </el-steps>
        </div>

        <div style="margin-top: 30px" v-if="active === 0">
            <el-form :model="form" :rules="rules" ref="formRef"
                     label-width="0px"
                     size="large" @validate="onValidate">
                <el-form-item prop="email">
                    <el-input v-model="form.email" type="email" placeholder="电子邮件地址">
                        <template #prefix>
                            <el-icon>
                                <Message/>
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="code" style="width: 100%">
                    <el-row :gutter="10">
                        <el-col :span="12" :offset="0">
                            <el-input v-model="form.code" :maxlength="6" type="text" placeholder="请输入验证码">
                                <template #prefix>
                                    <el-icon>
                                        <EditPen/>
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-col>
                        <el-col :span="12" :offset="0" style="text-align: right">
                            <el-button type="primary" :disabled="!isEmailValid || coldTime > 0" @click="validateEmail">
                                {{ coldTime > 0 ? '请稍后' + coldTime + '秒' : '获取验证码' }}
                            </el-button>
                        </el-col>
                    </el-row>
                </el-form-item>
            </el-form>
            <div style="margin-top: 80px">
                <el-button type="danger" size="large" @click="startReset" style="width: 175px">重置密码</el-button>
            </div>
        </div>

        <div style="margin-top: 30px; height: 100%" v-if="active === 1">
            <div style="margin-top: 20px">
                <div style="font-size: 25px; font-weight: bold">重置密码</div>
            </div>
            <div style="margin-top: 25px">
                <el-form size="large" :model="form" :rules="rules" @validate="onValidate" ref="formRef">
                    <el-form-item prop="password">
                        <el-input v-model="form.password" :maxlength="16" type="password" placeholder="密码">
                            <template #prefix>
                                <el-icon>
                                    <Lock/>
                                </el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="re_password">
                        <el-input v-model="form.re_password" :maxlength="16" type="password" placeholder="重复密码">
                            <template #prefix>
                                <el-icon>
                                    <Lock/>
                                </el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                </el-form>
            </div>
            <div style="margin-top: 50px">
                <el-button type="danger" size="large" style="width: 175px" @click="resetPassword">立即重置密码</el-button>
            </div>
        </div>

    </div>
</template>

<script setup>
import {EditPen, Lock, Message} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {post} from "@/utils/request";
import {ElMessage} from "element-plus";
import router from "@/router";

const formRef = ref()
const active = ref(0)
const isEmailValid = ref(false)
const coldTime = ref(0)

const form = reactive({
    email: '',
    code: '',
    password: '',
    re_password: '',
})

const onValidate = (prop, isValid) => {
    if (prop === 'email') {
        isEmailValid.value = isValid
    }
}

const validatePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入你的密码'))
    } else if (value !== form.password) {
        callback(new Error("两次输入的密码不一致"))
    } else {
        callback()
    }
}

const rules = {
    password: [
        {required: true, message: '密码不能为空', trigger: 'blur'},
        {min: 6, max: 16, message: '密码的长度应该在6到16个字符之间', trigger: ['blur', 'change']}
    ],
    re_password: [
        {validator: validatePassword, trigger: ['blur', 'change']},
    ],
    email: [
        {required: true, message: '请填写电子邮件地址', trigger: 'blur'},
        {type: 'email', message: '请输入正确的电子邮件地址', trigger: ['blur', 'change']},
    ],
    code: [
        {required: true, message: '请输入获取的验证码', trigger: 'blur'},
    ],
}

const intervalId = ref(0)

const validateEmail = () => {
    coldTime.value = 60
    clearInterval(intervalId.value)
    post('api/auth/valid-reset-email', {
        email: form.email
    }, (message) => {
        ElMessage.success(message)
    }, (message) => {
        ElMessage.warning(message)
        coldTime.value = 1
    })
    intervalId.value = setInterval(() => coldTime.value--, 1000)
}

const startReset = () => {
    formRef.value.validate((isValid) => {
        if (isValid) {
            post('api/auth/start-reset', {
                email: form.email,
                code: form.code,
            }, (message) => {
                active.value++
                clearInterval(intervalId.value)
                ElMessage.success(message)
            })
        }else {
            ElMessage.warning("请填写电子邮件和验证码")
        }
    })
}

const resetPassword = () => {
    formRef.value.validate((isValid) => {
        if (isValid) {
            post('api/auth/do-reset', {
                password: form.password,
            }, (message) => {
                ElMessage.success(message)
                router.push('/')
            })
        }else {
            ElMessage.warning("请填写新的密码")
        }
    })
}
</script>

<style scoped>

</style>