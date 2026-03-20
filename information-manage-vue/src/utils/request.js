// 可以配置axios的全局默认值 如baseURL timeout 等 便于更改
import axios from 'axios'

//创建axios实例对象
const request = axios.create({
  // baseURL: 'https://m1.apifoxmock.com/m1/7837906-7586570-default',
  baseURL: '/api',
  timeout: 600000
})

//axios的响应 response 拦截器
request.interceptors.response.use(
  (response) => { //成功回调 这样就要不用 response.data.data来获得数据了
    return response.data
  },
  (error) => { //失败回调
    return Promise.reject(error)
  }
)
// axios的请求 request 拦截器
request.interceptors.request.use(
  (config) => {
    // 对请求头做些什么
    return config
  },
  (error) => {
    // 对请求错误做些什么
    return Promise.reject(error)
  }
)

export default request
