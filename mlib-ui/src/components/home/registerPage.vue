<template>
	<div style="background-color: white;text-align: center;">
		<div style="margin-top: 150px">
			<div style="font-size: 25px;">账号注册</div>
		</div>
		<div style="margin-top: 30px">
			<el-form :model="form" :rules="rules" ref="formRef"
			label-position="left" label-width="80px"
			size="default" @validate="onValidate">
				<el-form-item label="用户名" prop="username">
					<el-input v-model="form.username" type="text" placeholder="用户名">
						<template #prefix>
							<el-icon>
								<User />
							</el-icon>
						</template>
					</el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password">
					<el-input v-model="form.password" type="password" placeholder="密码">
						<template #prefix>
							<el-icon>
								<Lock />
							</el-icon>
						</template>
					</el-input>
				</el-form-item>
				<el-form-item label="重复密码" prop="re_password">
					<el-input v-model="form.re_password" type="password" placeholder="重复密码">
						<template #prefix>
							<el-icon>
								<Lock />
							</el-icon>
						</template>
					</el-input>
				</el-form-item>
				<el-form-item label="电子邮件" prop="email">
					<el-input v-model="form.email" style="width: 300px;" type="email" placeholder="电子邮件地址">
						<template #prefix>
							<el-icon>
								<Message />
							</el-icon>
						</template>
					</el-input>
				</el-form-item>
			</el-form>
			<div style="margin-top: 10px;">
				<el-row :gutter="10" style="width: 100%;">
					<el-col :span="12" :offset="0">
						<el-input v-model="form.code" type="text" placeholder="请输入验证码">
							<template #prefix>
								<el-icon>
									<EditPen />
								</el-icon>
							</template>
						</el-input>
					</el-col>
					<el-col :span="12" :offset="0">
						<el-button type="primary" :disabled="!isEmailValid" @click="validateEmail">获取验证码</el-button>
					</el-col>
				</el-row>
			</div>
			<div style="margin-top: 20px">
				<el-button style="width: 150px" type="primary" @click="register">注册账号</el-button>
			</div>
			<div style="margin-top: 10px;">
				<span style="font-size: 14px; line-height: 15px;color: gray;">已有账号 </span>
				<el-link type="primary" style="translate: 0 -2px;" @click="router.push('/')">立即登陆</el-link>
			</div>
		</div>
	</div>
</template>

<script setup>
import { EditPen, Lock, Message, User } from '@element-plus/icons-vue';
import { reactive, ref } from 'vue';
import router from '../../router';
import { ElMessage } from 'element-plus';
import { post } from '@/utils/request';

const form = reactive({
	username: '',
	password: '',
	re_password: '',
	email: '',
	code: '',
})

const validateUsername = (rule, value, callback) => {
	if (value === '') {
		callback(new Error('请输入用户名'))
	} else if (!/^[a-zA-Z0-9]+$/.test(value)) {
		callback(new Error('用户名不能包含特殊字符，只能是中文或英文！'))
	}else {
		callback()
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
	username: [
		{ validator: validateUsername, trigger: 'blur' },
		{ min: 3, max: 10, message: '用户名长度应该为3到10个字符', trigger: ['blur', 'change'] },
	],
	password: [
		{ required: true, message: '密码不能为空', trigger: 'blur' },
		{ min: 6, max: 16, message: '密码的长度应该在6到16个字符之间', trigger: ['blur', 'change'] }
	],
	re_password: [
		{ validator: validatePassword, trigger: ['blur', 'change'] },
	],
	email: [
		{ required: true, message: '请填写电子邮件地址', trigger: 'blur' },
		{ type: 'email', message: '请输入正确的电子邮件地址', trigger: ['blur', 'change'] },
	],
	code: [
		{ required: true, message: '请输入获取的验证码', trigger: 'blur' },
	],
}

const formRef = ref()
const isEmailValid = ref(false)

const onValidate = (prop, isValid) => {
	if (prop === 'email') {
		isEmailValid.value = isValid
	}
}

const register = () => {
	formRef.value.validate((isValid) => {
		if(isValid) {

		}else {
			ElMessage.warning('您还未填写完成注册信息')
		}
	})
}

const validateEmail = () => {
	post('api/auth/valid-email', {
		email : form.email
	}, (message) => {
		ElMessage.success(message)
	})
}
</script>

<style></style>
