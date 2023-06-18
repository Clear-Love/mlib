<template>
  <v-card
    class="mx-auto elevation-3"
    width="425"
    max-width="425"
  >
    <v-banner
      lines="one"
      icon="mdi-user-plus"
      color="primary"
    >
      <v-banner-text class="text-h5 font-weight-bold mt-1">
        注册
      </v-banner-text>
    </v-banner>
    <v-container>
      <v-form
        v-model="valid"
        @submit.prevent="register()"
      >
        <v-text-field
          v-model="username"
          color="primary"
          label="用户名"
          placeholder="请输入您的用户名"
          prepend-inner-icon="mdi-user"
          clearable
          :rules="[rules.required, rules.username.size, rules.username.re]"
          variant="outlined"
        ></v-text-field>

        <v-text-field
          v-model="password"
          color="primary"
          label="密码"
          placeholder="请输入你的密码"
          clearable
          :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :type="show1 ? 'text' : 'password'"
          @click:append-inner="show1 = !show1"
          prepend-inner-icon="mdi-lock"
          :rules="[rules.required, rules.password]"
          variant="outlined"
        ></v-text-field>

        <v-text-field
          v-model="re_password"
          color="primary"
          label="确认密码"
          clearable
          :append-inner-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
          @click:append-inner="show2 = !show2"
          :type="show2 ? 'text' : 'password'"
          prepend-inner-icon="mdi-lock"
          :rules="[rules.required, validatePassword]"
          placeholder="请再次输入你的密码"
          variant="outlined"
        ></v-text-field>

        <v-text-field
          ref="form_email"
          v-model="email"
          color="primary"
          label="邮箱"
          placeholder="请输入您的电子邮件地址"
          clearable
          prepend-inner-icon="mdi-email"
          :rules="[rules.required, rules.email]"
          variant="outlined"
        ></v-text-field>

        <v-row>
          <v-col
            md="6"
          >
            <v-text-field
              v-model="code"
              color="primary"
              variant="underlined"
              clearable
              :rules="[rules.required, rules.code]"
              label="验证码"
              placeholder="请输入您接收到的验证码"
            >
            </v-text-field>

          </v-col>
          <v-col
            style="text-align: right"
          >
            <v-btn variant="elevated"
                   class="mt-2 "
                   :disabled="coldTime > 0"
                   size="large"
                   color="primary"
                   @click="validateEmail"
            >
              {{ coldTime > 0 ? '请稍后' + coldTime + '秒' : '获取验证码' }}
            </v-btn>
          </v-col>
        </v-row>
        <v-breadcrumbs-divider/>
        <v-btn block
               color="primary"
               variant="elevated"
               type="submit"
               size="large"
               :loading="loading"
               :disabled="!valid"
        >
          注册
        </v-btn>
      </v-form>
    </v-container>

    <v-divider></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>

      <v-btn
        color="primary"
        @click="router.push('/')"
      >
        已有账号，立即登陆
        <v-icon icon="mdi-chevron-right" end></v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import router from "@/router";
import {post} from "@/utils/request";
import {useSnackbarStore} from "@/stores/sanckbarStore";

const email_pattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/

const username_pattern = /^[a-zA-Z0-9]+$/
export default {
  data: () => ({
    snackbar: useSnackbarStore(),
    router,
    username: '',
    password: '',
    re_password: '',
    email: '',
    code: '',
    loading: false,
    coldTime: 0,
    valid: false,
    valid_email: false,
    interval: 0,
    show1: false,
    show2: false,
    rules: {
      required: (val:string) => !!val || '请输入',
      username: {
        size: (val:string) => val.length >= 3 && val.length <= 10 || '用户长度应该在3-10位',
        re: (val:string) => username_pattern.test(val) || "用户名只能由字母或数组组成",
      },
      password: (val:string) => val.length >= 6 && val.length <= 16 || "密码长度应该在6-16位",
      email: (val:string) => email_pattern.test(val) || '请输入正确的邮箱',
      code: (val:string) => val.length === 6 || "验证码应该为6位"
    }
  }),
  beforeUnmount () {
    clearInterval(this.interval)
  },
  mounted() {
    this.interval = window.setInterval(() => {
      if (this.coldTime < 0) {
        return (this.coldTime = 0)
      }
      this.coldTime--
    }, 1000);
  },
  methods: {
    validatePassword(value:string) {
      return value === this.password || "两次输入密码不一致";
    },
    register() {
      this.loading = true
      console.log(this.loading)
      if (this.valid) {
        post('api/auth/register', {
          username: this.username,
          password: this.password,
          email: this.email,
          code: this.code,
        }, (message) => {
          this.snackbar.showSuccessMessage(message)
          router.push('/')
        }, (message) => {
          this.snackbar.showWarningMessage(message)
        })
      }
      setTimeout(() => (this.loading = false), 500)
    },
    validateEmail() {
      if (this.coldTime <= 0 && email_pattern.test(this.email)) {
        this.coldTime = 60
        post('/api/auth/valid-register-email', {
          email: this.email
        }, (message) => {
          this.snackbar.showSuccessMessage(message)
        }, (message) => {
          this.snackbar.showWarningMessage(message)
          this.coldTime = 0
        },)
      }
    }
  }
}

export {username_pattern, email_pattern}
</script>

<style scoped>

</style>
