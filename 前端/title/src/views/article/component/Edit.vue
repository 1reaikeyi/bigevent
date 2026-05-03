<script setup>
import { ref, onMounted } from 'vue'

import { articlePublishArticleService, articleGetByIdService, articleEditArticleService, categoryReadService } from '@/api/article.js'
import { ElMessage } from 'element-plus'

const visibleDrawer = ref(false)
const categoryList = ref([])

const defaultForm = {
  title: '',
  categoryId: '',
  coverImg: '',
  content: '',
  state: ''
}

const formModel = ref({ ...defaultForm })

const emit = defineEmits(['success'])
const onPublish = async (state) => {
  formModel.value.state = state

  if (formModel.value.id) {
    await articleEditArticleService(formModel.value)
    ElMessage.success('修改成功')
  } else {
    await articlePublishArticleService(formModel.value)
    ElMessage.success('添加成功')
  }
  visibleDrawer.value = false
  emit('success')
}

const open = async (row) => {
  visibleDrawer.value = true
  await getCategoryList()

  if (row.id) {
    const res = await articleGetByIdService(row.id)
    formModel.value = res.data
  } else {
    formModel.value = { ...defaultForm }
  }
}

const getCategoryList = async () => {
  const res = await categoryReadService()
  categoryList.value = res.data
}

defineExpose({ open })
</script>

<template>
  <el-drawer
    v-model="visibleDrawer"
    :title="formModel.id ? '编辑文章' : '添加文章'"
    direction="rtl"
    size="50%"
  >
    <el-form :model="formModel" ref="formRef" label-width="100px">
      <el-form-item label="文章标题" prop="title">
        <el-input v-model="formModel.title" placeholder="请输入标题"></el-input>
      </el-form-item>
      <el-form-item label="文章分类" prop="categoryId">
        <el-select v-model="formModel.categoryId" placeholder="请选择分类" style="width: 100%">
          <el-option v-for="item in categoryList" :key="item.id" :label="item.categoryName" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="文章内容" prop="content">
        <el-input v-model="formModel.content" type="textarea" :rows="10" placeholder="请输入文章内容"/>
      </el-form-item>
      <el-form-item>
        <el-button @click="onPublish('已发布')" type="primary">发布</el-button>
        <el-button @click="onPublish('草稿')" type="info">草稿</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<style lang="scss" scoped>
</style>
