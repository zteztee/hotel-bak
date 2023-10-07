import axios from 'axios'
import { Message, MessageBox,SingleMessage } from 'element-ui'
import store from '../store'
import { getToken,removeToken, setToken } from '@/utils/auth'
import router from '@/router'



// 创建axios实例
const service = axios.create({
  baseURL: "http://hotelserver:800", // api的base_url
  // baseURL:"http://localhost:801",
  // baseURL :  axios1.devServer.proxy['/api'].pathRewrite,
  timeout: 5000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(config => {
  
  console.log(config)
  // if(location.pathname == '/'){
  //   if (store.getters.token!=null) {
  //     config.headers['X-Token'] = getToken()
      return config // 让每个请求携带自定义token 请根据实际情况自行修改
  //   }else{
      // return Promise.reject(
      //   Message({
      //     message: '登录不成功',
      //     type: 'warning',
      //     duration: 3 * 1000
      //   })
      // ) 
    // }
  // } 

}, error => {
  // Do something with request error
  // console.log(error) // for debug
  return Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  res => {
    console.log(res)

  /**
  * code为非20000是抛错 可结合自己业务进行修改
  */
  if (res.status!=0 && res.status==200 && res.data.data!=null){
    if (location.href === 'http://localhost:9528/#/login')  {
      Message({
      message: "登录成功",
      type: 'success',
      duration: 5 * 1000,      
      })
      return res
    }
    else{
      // console.log(res)
      return res}
  }
  else{
    if(location.href === 'http://localhost:9528/#/login'
    &&res.config.method=="get"){
      return Promise.reject(
        Message({
          message: res.msg || '用户名或密码错误',
          type: 'error',
          duration: 3 * 1000
        })
      ) 
    }
    if(res.status==200 && res.config.method =="put"){
      return res
    }
    if(res.status==200 && res.config.method == "post"){
      return res
    }
    if(res.status==200 && res.config.method =="delete"){
      return res
    }
    else {
      return Promise.resolve(
        Message({
          message: res.msg || '可能获取不到值',
          type: 'warning',
          duration: 3 * 1000
        })
      ) 
    }
    
  }

    
  },
  error => {
    if (error.response) {
      const res = error.response.data
      if (res.status === 401) {
        while (pending.length > 0) {
          pending.pop()('请求中断')
        }
        MessageBox.confirm(
          '您的登录信息已过期，您可以取消以停留在此页，或重新登录',
          '系统提示',
          {
            confirmButtonText: '重新登录',
            cancelButtonText: '取消',
            type: 'warning'
          }
        )
          .then(() => {
            // 定义定时器去主动点击遮罩层让第一个弹窗后的弹窗自己主动关闭
            int = setInterval(() => {
              const ele = document.getElementsByClassName('v-modal')[0]
              // 当检测到document中没有遮罩层的时候 清除计时器
              if (!ele) {
                clearInterval(int)
              } else {
                // 进行点击，触发弹窗的关闭
                ele.click()
              }
            }, 0)
            // 清除浏览器保存的信息
            delSessionStorage()
            // 清除搜索组件保存的信息，防止显示旧数据
            delLocalStorage('Enterprise')
            // 删除路由和 vuex 的store
            delLocalStorage('router')
            delLocalStorage('store')
            router.replace('/login')
          })
          .catch(() => { })
      } else if (res.status === 400) {
        Message({
          message: res.msg || '客户端错误',
          type: 'error',
          duration: 3 * 1000
        })
      } else if (res.status === 403) {
        Message({
          message: res.msg || '您的权限不足',
          type: 'error',
          duration: 3 * 1000
        })
      } else if (res.status === 404) {
        Message({
          message: '网络请求不存在',
          type: 'error',
          duration: 3 * 1000
        })
      } else if (res.status === 500) {
      Message({
          message: res.msg || '服务器异常',
          type: 'error',
          duration: 3 * 1000
        })
      } else if (res.status === 501) {
        Message({
          message: res.msg || '您的操作被取消或不允许提交',
          type: 'warning',
          duration: 3 * 1000
        })
      } else {
        Message({
          message: '服务器正在开小差。。。。',
          type: 'error',
          duration: 3 * 1000
        })
      }
    } else if (error.message.includes('timeout')) {
      Message({
        message: '请求超时，请检查网络连接!',
        type: 'error',
        duration: 3 * 1000
      })
    }

    // console.log(error)
    return Promise.reject(error+'请求端口出错')
    // return new Promise(() => {
    //   console.dir(error, '错误')
    // })
  }
)

export default service
