<script setup>
import { ref, onMounted } from 'vue'
import { articleGetPageService, categoryReadService } from '@/api/article'

const articleList = ref([])
const categoryList = ref([])
const loading = ref(false)
const total = ref(0)

const params = ref({
  pageNum: 1,
  pageSize: 10
})

const getArticlePage = async () => {
  loading.value = true
  const res = await articleGetPageService(params.value)
  articleList.value = res.data.records
  total.value = res.data.total
  loading.value = false
}

const getCategoryList = async () => {
  const res = await categoryReadService()
  categoryList.value = res.data
}

onMounted(() => {
  getArticlePage()
  getCategoryList()
})

const getCategoryName = (categoryId) => {
  const category = categoryList.value.find(c => c.id === categoryId)
  return category ? category.categoryName : '-'
}
const getCreateUser = (categoryId) => {
  const CreatUser = categoryList.value.find(c => c.id === categoryId )
  return CreatUser ? CreatUser.createUser : '-'
}
const getUpdateUser = (categoryId) => {
  const UpdateUser = categoryList.value.find(c => c.id === categoryId)
  return UpdateUser ? UpdateUser.updateUser : '-'
}

const onSizeChange = (size) => {
  params.value.pageNum = 1
  params.value.pageSize = size
  getArticlePage()
}

const onCurrentChange = (page) => {
  params.value.pageNum = page
  getArticlePage()
}
</script>

<template>
  <page-container title="文章列表">
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
      <el-table-column label="创建人" width="120">
        <template #default="{ row }">
          {{ getCreateUser(row.createUser) }}
        </template>
      </el-table-column>
      <el-table-column label="更新人" width="120">
        <template #default="{ row }">
          {{ getUpdateUser(row.updateUser) }}
        </template>
      </el-table-column>
      <el-table-column prop="state" label="状态" width="100">
        <template #default="{ row }">
          <el-tag :type="row.state === '已发布' ? 'success' : 'info'">{{ row.state }}</el-tag>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="暂无数据"></el-empty>
      </template>
    </el-table>

    <el-pagination
      v-model:current-page="params.pageNum"
      v-model:page-size="params.pageSize"
      :page-sizes="[5, 10, 20, 50]" :background="true" layout="jumper, total, sizes, prev, pager, next" :total="total"
      @size-change="onSizeChange"
      @current-change="onCurrentChange"
      style="margin-top: 20px; justify-content: flex-end"
    />
  </page-container>
</template>

<style lang="scss" scoped></style>
