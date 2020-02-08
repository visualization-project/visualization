import axios from 'axios'
// import config from '@/config'
import { Message } from 'element-ui'
import { baseUrl } from '../../env'
import router from '@/router'
// import { getToken, removeToken } from './auth'

// axios的基础配置
const axiosInstance = axios.create({
  baseURL: baseUrl,
  withCredentials: true,
  timeout: 1000
})

// 设置请求拦截器
// axiosInstance.interceptors.request.use(config => {
//   console.log(config)
//   config.headers['Authorization'] = getToken()
//   return config
// }, error => {
//   return Promise.reject(error)
// })
//
// // 设置响应拦截
// axiosInstance.interceptors.response.use(res => {
//   const { data, status } = res
//   return { data, status }
// }, error => {
//   console.log(error)
//   return Promise.reject(error)
// })

// 对接口返回数据做统一的处理
const pipe = res => {
  return new Promise((resolve, reject) => res.then(x => {
    const data = x.data
    if (data.code === 10000) {
      resolve(data)
    } else if (data.code === 401) { // 登录过期，重新登录
      // 提示并退出登录
      Message.warning({
        content: data.message,
        duration: 1,
        onClose: () => {
          // removeToken()
          router.push({ name: 'login' })
        }
      })
    } else {
      Message({ message: data.message, type: 'error' })
      reject(data)
    }
  }).catch(e => {
    let str = '请求失败'
    if (e.toString().includes('timeout of')) {
      str = '请求超时'
    } else if (e.toString().includes('Network Error')) {
      str = '服务器未响应,请检查网络'
    }
    Message({ message: str, type: 'error' })
    reject(e)
  }))
}

// 登录请求不需要token
const noToken = (url, args) => pipe(axiosInstance.post(url, args))

// post 请求
const post = (url, args = {}) => {
  // const token = getToken()
  // if (!token) {
  //   return
  // }
  return pipe(axiosInstance({
    method: 'post',
    url: url,
    // data: { ...args, Authorization: token },/* 将token放body里面 */
    data: args,
    headers: {
      // 'Authorization': getToken()
    }
  }))
}

// get请求
const get = (url, args = {}) => {
  // const token = getToken()
  // if (!token) {
  //   return
  // }
  return pipe(axiosInstance({
    method: 'get',
    url: url,
    params: args,
    headers: {
      // 'Authorization': getToken()
    }
  }))
}

export {
  noToken,
  post,
  get
}
