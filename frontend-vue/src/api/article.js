import request from '@/utils/request'

// 分类：获取分类列表
export const categoryReadService = () =>
    request.get('/category')

// 分类：获取分类详情
export const categoryGetIdService = (id) =>
    request.get(`/category/${id}`)

// 分类：添加分类
export const categoryAddCategoryService = (data) =>
    request.post('/category', data)

// 分类：编辑分类
export const categoryEditCategoryService = (data) =>
    request.put('/category', data)

// 分类：删除分类
export const categoryDelByIdService = (id) =>
    request.delete(`/category/${id}`)

// 文章：获取文章列表
export const articleReadService = () =>
  request.get('/article/list')

// 文章：获取分页文章列表
export const articleGetPageService = (params) =>
  request.get('/article', { params })

// 文章：获取文章详情
export const articleGetByIdService = (id) =>
  request.get(`/article/${id}`)

// 文章：添加文章
export const articlePublishArticleService = (data) =>
  request.post('/article', data)

// 文章：编辑文章
export const articleEditArticleService = (data) =>
  request.put('/article', data)

// 文章：删除文章
export const articleDelByIdService = (id) =>
  request.delete(`/article/${id}`)


