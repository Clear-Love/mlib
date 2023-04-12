<script setup>
import { RouterLink, RouterView } from 'vue-router'
import {User, Lock} from '@element-plus/icons-vue'
import { ref } from 'vue'
const input = ref('')
const pwd = ref('')
</script>

<template>
  <div style="width:100vw;height: 100vh;overflow: hidden;display: flex;" >
    <div style="flex: 1">
      <el-image style="width: 100%; height: 100%" fit="cover"
      src="https://png.pngtree.com/thumb_back/fh260/background/20210316/pngtree-pink-yellow-clouds-cute-baby-background-image_587296.jpg"/>
    </div>
    <div style="width: 500px;background-color: white;text-align: center">
      <div style="margin-top: 150px">
        <div style="font-size: 25px;">登录</div>
        <div style="font-size: 14px;color: grey;">输入用户名和密码进行登录</div>
      </div>
      <div style="margin-top: 30px">
        <el-input v-model="input" style="width: 300px;" type="text" placeholder="用户名/邮箱">
          <template #prefix>
            <el-icon><User/></el-icon>
          </template>
        </el-input>
        <el-input v-model="pwd" style="width: 300px; margin-top: 10px;" type="password" placeholder="密码">
          <template #prefix>
            <el-icon><Lock/></el-icon>
          </template>
        </el-input>
        <el-row style="margin-top: 5px;" :gutter="20">
          <el-col :span="12" :offset="0">
            <el-checkbox style="text-align: left" v-model="checked1" label="记住我" :indeterminate="false"
            @change="">记住我</el-checkbox>
          </el-col>
          <el-col :span="12" :offset="0" >
            <el-link style="text-align: right" type="primary" :underline="true" href="" target="_blank">忘记密码？</el-link>
            
          </el-col>
        </el-row>
        
        <div style="margin-top: 20px">
          <el-button style="width: 150px" plain="Primary" @click="">登录</el-button>
        </div>
        <div style="margin-top: 20px">
          <el-button style="width: 150px" plain="Primary" @click="">注册账号</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
