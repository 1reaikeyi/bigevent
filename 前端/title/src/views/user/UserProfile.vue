<script setup>
import { ref, onMounted } from 'vue'
import { useUserStore } from '@/stores'
import { userUpdateUserService } from '@/api/user'
import { ElMessage } from 'element-plus'

const userStore = useUserStore()
const formRef = ref()

const form = ref({
  userName: '',
  nickName: '',
  email: ''
})

const rules = ref({
  nickName: [
    { required: true, message: '请输入用户昵称', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入用户邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱格式', trigger: ['blur', 'change'] }
  ]
})

onMounted(async () => {
  await userStore.getUser()
  form.value = {
    userName: userStore.user?.userName || '',
    nickName: userStore.user?.nickName || '',
    email: userStore.user?.email || ''
  }
})

const submitForm = async () => {
  await formRef.value.validate()
  await userUpdateService(form.value)
  await userStore.getUser()
  ElMessage.success('修改成功')
}
</script>
<template>
  <page-container title="基本资料">
    <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="登录名称">
        <el-input v-model="form.userName" disabled></el-input>
      </el-form-item>
      <el-form-item label="用户昵称" prop="nickName">
        <el-input v-model="form.nickName"></el-input>
      </el-form-item>
      <el-form-item label="用户邮箱" prop="email">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交修改</el-button>
      </el-form-item>
    </el-form>
  </page-container>
</template>
