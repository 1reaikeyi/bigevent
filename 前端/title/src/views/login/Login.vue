<script setup>
import { ref, watch } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { userRegisterService, userLoginService } from '@/api/user.js'

const isRegister = ref(false)
const form = ref()

const formModel = ref({
  userName: '',
  password: '',
})

watch(isRegister, () => {
  formModel.value = {
    userName: '',
    password: ''
  }
})

const register = async () => {
  try {
    await form.value.validate()
    await userRegisterService(formModel.value)
    ElMessage.success('注册成功')
    console.error('注册成功')
    //默认选择登录，不是注册
    isRegister.value = false
  } catch (error) {
    console.error('注册失败:', error)
  }
}

const jwt = useUserStore()
const router = useRouter()
const login = async () => {
  try {
    await form.value.validate()
    const res = await userLoginService(formModel.value)
    jwt.setToken(res.data)
    ElMessage.success('登录成功')
    router.push('/')
    console.log('登录成功')
  } catch (error) {
    console.error('登录失败:', error)
  }
}
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!-- 注册相关表单 -->
      <el-form :model="formModel" ref="form" size="large" autocomplete="off" v-if="isRegister">
        <el-form-item><h1>注册</h1></el-form-item>
        <el-form-item prop="userName">
          <el-input v-model="formModel.userName" :prefix-icon="User" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="formModel.password" :prefix-icon="Lock" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="register" class="button" type="primary" auto-insert-space>注册</el-button>
        </el-form-item>

        <el-form-item class="flex">
          <el-link type="info" underline="never" @click="isRegister = false">← 返回</el-link>
        </el-form-item>
      </el-form>

      <!-- 登录相关表单 -->
      <el-form :model="formModel" ref="form" size="large" autocomplete="off" v-else>
        <el-form-item><h1>登录</h1></el-form-item>
        <el-form-item prop="userName">
          <el-input v-model="formModel.userName" :prefix-icon="User" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="formModel.password" name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item class="flex">
          <div class="flex"><el-link type="primary" underline="never">先注册？</el-link></div>
        </el-form-item>
        <el-form-item>
          <el-button @click="login" class="button" type="primary" auto-insert-space>登录</el-button>
        </el-form-item>

        <el-form-item class="flex">
          <el-link type="info" underline="never" @click="isRegister = true">注册 →</el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
.login-page {
  height: 100vh;
  background-color: #fff;
  .bg {
    background: url('@/assets/logo2.png') no-repeat 60% center / 240px auto,
    url('@/assets/login_bg.jpg') no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }
  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;
    .title {
      margin: 0 auto;
    }
    .button {
      width: 100%;
    }
    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>