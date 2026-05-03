import request from '@/utils/request'

// 文件上传
export const commonUploadService = (data) =>
  request.post('/common/upload', data)

// 文件下载
export const commonDownloadService = (params) =>
  request.get('/common/download', { params })
