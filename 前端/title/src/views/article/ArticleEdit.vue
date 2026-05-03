<script setup>
import { ref, onMounted } from 'vue'
import { Delete, Edit } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  articleReadService,
  articlePublishArticleService,
  articleGetByIdService,
  articleEditArticleService,
  articleDelByIdService,
  categoryReadService
} from '@/api/article'
import ArticleEdit from '@/views/article/component/Edit.vue'

const articleList = ref([])
const categoryList = ref([])
const loading = ref(false)
const articleEditRef = ref()

const getArticleList = async () => {
  loading.value = true
  const res = await articleReadService()
  articleList.value = res.data
  loading.value = false
}

const getCategoryList = async () => {
  const res = await categoryReadService()
  categoryList.value = res.data
}

onMounted(() => {
  getArticleList()
  getCategoryList()
})

const getCategoryName = (categoryId) => {
  const category = categoryList.value.find(c => c.id === categoryId)
  return category ? category.categoryName : '-'
}

const onAddArticle = () => {
  articleEditRef.value.open({})
}

const onEditArticle = (row) => {
  articleEditRef.value.open(row)
}

const onDeleteArticle = async (row) => {
  await ElMessageBox.confirm('确认删除该文章吗？', '提示', {
    type: 'warning',
    confirmButtonText: '确认',
    cancelButtonText: '取消'
  })
  await articleDelByIdService(row.id)
  ElMessage.success('删除成功')
  getArticleList()
}

const onSuccess = () => {
  getArticleList()
}
</script>

<template>
  <page-container title="文章管理">
    <template #extra>
      <el-button type="primary" @click="onAddArticle">添加文章</el-button>
    </template>

    <el-table v-loading="loading" :data="articleList" style="width: 100%">
      <el-table-column type="index" label="序号" width="80"></el-table-column>
      <el-table-column prop="title" label="文章标题">
        <template #default="{ row }">
          <el-link type="primary" :underline="false">{{ row.title }}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="分类" width="120">
        <template #default="{ row }">
          {{ getCategoryName(row.categoryId) }}
        </template>
      </el-table-column>
      <el-table-column prop="state" label="状态" width="100">
        <template #default="{ row }">
          <el-tag :type="row.state === '已发布' ? 'success' : 'info'">{{ row.state }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="onEditArticle(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="onDeleteArticle(row)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="暂无数据"></el-empty>
      </template>
    </el-table>

    <article-edit ref="articleEditRef" @success="onSuccess"></article-edit>
  </page-container>
</template>

<style lang="scss" scoped></style>
