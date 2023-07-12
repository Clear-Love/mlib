<template>
  <v-card
    class="ma-3 pa-3"
    elevation="3"
    width="344"
    max-width="344"
  >
    <v-banner
      lines="one"
      icon="mdi-login-variant"
      color="primary"
    >
      <v-banner-text class="text-h5 font-weight-bold mt-1">
        登陆
      </v-banner-text>
    </v-banner>
    <v-breadcrumbs-divider/>
    <v-container>
      <v-form
        v-model="valid"
        @submit.prevent="login()"
      >
        <v-text-field
          v-model="username"
          label="用户名/邮箱"
          variant="outlined"
          placeholder="请输入用户名或者邮件地址"
          :rules="[rules.required, rules.username]"
          clearable
          prepend-inner-icon="mdi-account"
          type="text"
          required>
        </v-text-field>

        <v-text-field
          v-model="password"
          variant="outlined"
          label="密码"
          placeholder="请输入密码"
          clearable
          :append-inner-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.password]"
          prepend-inner-icon="mdi-lock"
          :type="show ? 'text' : 'password'"
          @click:append-inner="show = !show"
          required>
        </v-text-field>

        <v-row>
          <v-col
            md="5"
          >
            <v-checkbox
              v-model="remember"
              color="primary"
              density="compact"
              class="mt-2"
              label="记住密码">
            </v-checkbox>

          </v-col>
          <v-col
            md="4"
            class="ms-auto"
          >
            <v-btn variant="text"
                   class="mt-3"
                   color="primary"
                   @click="router.push('/auth/forget')"
            >
              忘记密码？
            </v-btn>
          </v-col>
        </v-row>


        <v-btn block
               color="primary"
               variant="elevated"
               type="submit"
               size="large"
               :loading="loading"
               :disabled="!valid">登录
        </v-btn>

      </v-form>
    </v-container>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="primary"
        @click="router.push('/auth/register')"
      >
        没有账号？前往注册
        <v-icon icon="mdi-chevron-right" end></v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script lang="ts">
import router from "@/router";
import {useSnackbarStore} from "@/stores/sanckbarStore";
import {post} from "@/utils/request";


export default {
  data: () => ({
    router,
    snackbar: useSnackbarStore(),
    username: '',
    password: '',
    valid: false,
    loading: false,
    remember: false,
    show: false,
    rules: {
      required: (val:string) => !!val || '请输入',
      password: (val:string) => val.length >= 6 && val.length <= 16 || '密码长度应该在6到16位',
      username: (val:string) => val.length >= 3 && val.length <= 10 || '用户名长度应该在3-10位',
    },
  }),
  methods: {
    login() {
      this.loading = true
      post('/api/auth/login', {
        username: this.username,
        password: this.password,
        remember: this.remember,
      }, (message) => {
        this.snackbar.showSuccessMessage(message)
        router.push('/index')
      }, (message) => {
        this.snackbar.showErrorMessage(message)
      })
      setTimeout(() => (this.loading = false), 200)
    }
  }
}

</script>

<style scoped>

</style>
