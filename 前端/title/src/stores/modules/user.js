import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { userGetByIdService, userLoginService, userRegisterService } from '@/api/user.js'
import { getUserIdFromToken } from '@/stores/modules/jwt.js'

export const useUserStore = defineStore(
  'big-user',
  () => {
    const token = ref('')

    const setToken = (newToken) => {
      token.value = newToken
    }
    const removeToken = () => {
      token.value = ''
    }

    const userId = computed(() => getUserIdFromToken(token.value))

    const user = ref({})

    const login = async (data) => {
      const res = await userLoginService(data)
      return res
    }

    const register = async (data) => {
      const res = await userRegisterService(data)
      return res
    }

    const getUser = async () => {
      if (!userId.value) {
        console.error('无法获取用户ID，token可能无效')
        return
      }
      const res = await userGetByIdService(userId.value)
      user.value = res.data
    }

    const setUser = (obj) => {
      user.value = obj
    }

    return {
      token, setToken, removeToken,
      user, getUser, setUser, userId,
      login, register
    }
  },
  {
    persist: true
  }
)
