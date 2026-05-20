import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/stores'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/login', component: () => import('@/views/login/Login.vue') }, // 登录页
    { path: '/register', component: () => import('@/views/login/Login.vue') }, // 注册页
    {
      path: '/',
      component: () => import('@/views/login/Container.vue'),
      redirect: '/article/category',
      children: [
        { path: '/article/category', component: () => import('@/views/article/Category.vue') },
        { path: '/article/get', component: () => import('@/views/article/Article.vue') },
        { path: '/article/edit', component: () => import('@/views/article/ArticleEdit.vue') },
        { path: '/user/profile', component: () => import('@/views/user/UserProfile.vue') },
        { path: '/user/avatar', component: () => import('@/views/user/UserAvatar.vue') },
        { path: '/user/password', component: () => import('@/views/user/UserPassword.vue') }
      ]
    }
  ]
})

// 登录访问拦截 => 默认是直接放行的
// 如果没有token, 且访问的是非登录页或注册页，拦截到登录，其他情况正常放行
router.beforeEach((to) => {
  // 直接从localStorage中获取token，避免初始化顺序问题
  const token = localStorage.getItem('big-user') ? JSON.parse(localStorage.getItem('big-user')).token : ''
  if (!token && to.path !== '/login' && to.path !== '/user/register') return '/login'
})

export default router
