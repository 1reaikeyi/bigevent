import request from '@/utils/request'

// 用户注册
export const userRegisterService = ({ userName, password, repassword }) => {
  const params = new URLSearchParams()
  params.append('userName', userName)
  params.append('password', password)
  params.append('repassword', repassword)
  return request.post('/user/register', params)
}

// 用户登录
export const userLoginService = ({ userName, password }) => {
  const params = new URLSearchParams()
  params.append('userName', userName)
  params.append('password', password)
  return request.post('/user/login', params)
}

// 获取用户信息
export const userGetByIdService = (id) =>
  request.get(`/user/${id}`)

// 更新用户信息
export const userUpdateUserService = (data) =>
  request.put('/user', data)


// 更新用户密码
export const userUpdatePwdService = (data) =>
  request.patch('/user/updatePwd', data)