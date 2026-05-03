export const parseJWT = (token) => {
  if (!token) return null
  try {
    const base64Payload = token.split('.')[1]
    const payload = atob(base64Payload)
    return JSON.parse(payload)
  } catch (e) {
    console.error('JWT 解析失败:', e)
    return null
  }
}

export const getUserIdFromToken = (token) => {
  const payload = parseJWT(token)
  return payload?.USER_ID || null
}

export const getUserNameFromToken = (token) => {
  const payload = parseJWT(token)
  return payload?.USER_NAME || null
}
