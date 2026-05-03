<script setup>
import { ref } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  categoryReadService,
  categoryAddCategoryService,
  categoryEditCategoryService,
  categoryDelByIdService
} from '@/api/article'

const channelList = ref([])
const loading = ref(false)
const dialogVisible = ref(false)
const formRef = ref()
const isEdit = ref(false)

const formModel = ref({
  id: null,
  categoryName: '',
  categoryAlias: ''

})

const rules = {
  categoryName: [
    { required: true, message: '请输入分类名称', trigger: 'blur' }
  ],
  categoryAlias: [
    { required: true, message: '请输入分类别名', trigger: 'blur' }
  ]
}

const getChannelList = async () => {
  loading.value = true
  const res = await categoryReadService()
  channelList.value = res.data
  loading.value = false
}
getChannelList()

const onAddChannel = () => {
  isEdit.value = false
  formModel.value = { id: null, categoryName: '', categoryAlias: '' }
  dialogVisible.value = true
}

const onEditChannel = (row) => {
  isEdit.value = true
  formModel.value = { ...row }
  dialogVisible.value = true
}

const onDelChannel = async (row) => {
  await ElMessageBox.confirm('确认删除该分类吗？', '提示', {
    type: 'warning',
    confirmButtonText: '确认',
    cancelButtonText: '取消'
  })
  await categoryDelByIdService(row.id)
  ElMessage.success('删除成功')
  getChannelList()
}

const onSubmit = async () => {
  await formRef.value.validate()
  if (isEdit.value) {
    await categoryEditCategoryService(formModel.value)
    ElMessage.success('编辑成功')
  } else {
    await categoryAddCategoryService(formModel.value)
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
  getChannelList()
}
</script>

<template>
  <page-container title="文章分类">
    <template #extra>
      <el-button type="primary" @click="onAddChannel">添加分类</el-button>
    </template>

    <el-table v-loading="loading" :data="channelList" style="width: 100%">
      <el-table-column type="index" label="序号" width="100"></el-table-column>
      <el-table-column prop="categoryName" label="分类名称"></el-table-column>
      <el-table-column prop="categoryAlias" label="分类别名"></el-table-column>
      <el-table-column label="操作" width="150">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="onEditChannel(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="onDelChannel(row)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="暂无数据"></el-empty>
      </template>
    </el-table>

    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑分类' : '添加分类'" width="30%">
      <el-form ref="formRef" :model="formModel" :rules="rules" label-width="100px">
        <el-form-item label="分类名称" prop="categoryName">
          <el-input v-model="formModel.categoryName" placeholder="请输入分类名称"></el-input>
        </el-form-item>
        <el-form-item label="分类别名" prop="categoryAlias">
          <el-input v-model="formModel.categoryAlias" placeholder="请输入分类别名"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit">确认</el-button>
      </template>
    </el-dialog>
  </page-container>
</template>

<style lang="scss" scoped></style>
