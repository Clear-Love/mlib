<template>
  <v-card
    class="mx-auto mt-15 elevation-5"
    width="344"
    max-width="344"
  >
    <v-banner
      lines="one"
      :icon="icon"
      color="primary"
    >
      <v-banner-text class="text-h5 font-weight-bold mt-1">
        {{ text }}
      </v-banner-text>
    </v-banner>
    <v-breadcrumbs-divider/>
    <v-container>
      <v-form
        v-if="step == 1"
        v-model="valid1"
        @submit.prevent="valid_code()"
      >
        <v-text-field
          ref="form_email"
          v-model="email"
          color="primary"
          label="邮箱"
          clearable
          prepend-inner-icon="mdi-email"
          :rules="[rules.required, rules.email]"
          placeholder=""
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
              label="验证码">
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
        <v-btn block
               color="primary"
               variant="elevated"
               type="submit"
               size="large"
               :loading="loading"
               :disabled="!valid1">立即重置密码
        </v-btn>
      </v-form>
      <v-form
        v-if="step == 2"
        v-model="valid2"
        @submit.prevent="reset()"
      >
        <v-text-field
          v-model="password"
          color="primary"
          label="密码"
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
        <v-breadcrumbs-divider/>
        <v-btn block
               color="primary"
               variant="elevated"
               type="submit"
               size="large"
               :loading="loading"
               :disabled="!valid2">重置密码
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
        返回登陆页面
        <v-icon icon="mdi-chevron-right" end></v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">

import {email_pattern, username_pattern} from './registerPage.vue'
import {post} from "@/utils/request";
import router from "@/router";
import {useSnackbarStore} from "@/stores/sanckbarStore";


export default {
  data: () => ({
    router,
    snackbar: useSnackbarStore(),
    interval: 0,
    text: '',
    step: 1,
    icon: 'mdi-email',
    password: '',
    re_password: '',
    email: '',
    code: '',
    coldTime: 0,
    show1: false,
    show2: false,
    valid1: false,
    valid2: false,
    loading: false,
    rules: {
      required: (val:string) => !!val || '请输入',
      username: {
        size: (val:string) => val.length >= 3 && val.length <= 10 || '用户长度应该在3-10位',
        re: (val:string) => username_pattern.test(val) || "用户名只能由字母或数组组成",
      },
      password: (val:string) => val.length >= 6 && val.length <= 16 || "密码长度应该在6-16位",
      email: (val:string) => email_pattern.test(val) || '请输入正确的邮箱',
      code: (val:string) => val.length == 6 || "验证码应该为6位"
    }
  }),
  beforeUnmount() {
    clearInterval(this.interval)
  },
  mounted() {
    this.text = '验证邮箱'
    this.interval = window.setInterval(() => {
      if (this.coldTime < 0) {
        return (this.coldTime = 0)
      }
      this.coldTime--
    }, 1000)
  },
  methods: {
    validatePassword(value:string) {
      return value === this.password || "两次输入密码不一致";
    },
    valid_code() {
      this.loading = true
        post('api/auth/start-reset', {
          email: this.email,
          code: this.code,
        }, (message) => {
          this.snackbar.showSuccessMessage(message)
          this.icon = 'mdi-lock'
          this.step = 2
        }, (message) => {
          this.snackbar.showWarningMessage(message)
        })
      this.loading = false
    },
    reset() {
      this.loading = true
      post('api/auth/do-reset', {
        password: this.password,
      }, (message) => {
        this.snackbar.showSuccessMessage(message)
        }, (message) => {
        this.snackbar.showErrorMessage(message)
      })
      this.loading = false
    },
    async validateEmail() {
      let valid = await this?.$refs.form_email?.validate();
      if (this.coldTime <= 0 && valid.length === 0) {
        post('/api/auth/valid-reset-email', {
          email: this.email
        }, (message) => {
          this.snackbar.showSuccessMessage(message)
          this.coldTime = 60
        }, (message) => {
          this.snackbar.showWarningMessage(message)
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
