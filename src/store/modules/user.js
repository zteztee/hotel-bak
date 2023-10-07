import { login, logout, getInfo } from '@/api/login'
import { addWeekCount } from '@/api/count'
import { getToken, setToken, removeToken,setTokenKey,getUsername, setUsername } from '@/utils/auth'
import {
  setStore,
  getStore,
  removeStore
} from '@/utils/store'
import { reject } from 'core-js/fn/promise'

const user = {
  state: {
    token: getToken(),
    // token1:'',
    avatar: '',
    roles: [],
    username:{
    name: getUsername()
    },
    TokenKey:'',
    isLock: getStore({
      name: 'isLock'
    }) || false,
    lockPasswd: getStore({
      name: 'lockPasswd'
    }) || '',
    browserHeaderTitle: getStore({
      name: 'browserHeaderTitle'
    }) || 'NxAdmin'
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
      
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_LOCK_PASSWD: (state, lockPasswd) => {
      state.lockPasswd = lockPasswd
      setStore({
        name: 'lockPasswd',
        content: state.lockPasswd,
        type: 'session'
      })
    },
    SET_LOCK: (state, action) => {
      state.isLock = true
      setStore({
        name: 'isLock',
        content: state.isLock,
        type: 'session'
      })
    },
    CLEAR_LOCK: (state, action) => {
      state.isLock = false
      state.lockPasswd = ''
      removeStore({
        name: 'lockPasswd'
      })
      removeStore({
        name: 'isLock'
      })
    },
    SET_BROWSERHEADERTITLE: (state, actions) => {
      console.log('actions'+actions)
      state.browserHeaderTitle = actions
    },
    // getUsername(){
    //   const name=user.name
    //   if(name!=""){
    //     window.localStorage.setItem("usernameList",name)
    //   console.log(window.localStorage.getItem("usernameList"))
    //   if (window.localStorage.getItem("usernameList") ) {
    //     store.replaceState(Object.assign({}, 
    //     store.state,(window.localStorage.getItem("usernameList"))))
    //   } 
    // }
      
  // }
},

  actions: {
    // 登录
    Login({ state,commit }, userInfo) {
      // console.log(userInfo.logType)
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password,userInfo.logType).then(response => {
          const data = response
          state.username.name = userInfo.username
          setUsername(userInfo.username)
          setToken(userInfo)
          getToken(userInfo.username)
          commit('SET_TOKEN', userInfo)
          resolve(data)
          addWeekCount(username)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    async GetInfo({ commit, state }) {
      if((state.username.name)!=null){
      var token =getToken(state.username.name)
        if(token){
          const role = ['admin']
          const role1 = ['editor']
          // console.log(JSON.parse(token))
          if(JSON.parse(token).logType=='1'){
            // role = ['admin']
            commit('SET_ROLES', role)
            return state.roles
          }
          else{
            // role = ['editor']
            commit('SET_ROLES', role1)
            return state.roles
          }        
        } else {
          reject('getInfo:  must be a non-null array !')
        }
      }
      
            // commit('SET_NAME', data.name)
            // commit('SET_AVATAR', data.avata     
    },
    // GetUserInfos({ commit, state }) {
    //   if((state.username.name)!=null){
    //   var token =getToken(state.username.name)
    //   if(token){
    //         return JSON.parse(token)
    //     } else {
    //       reject('获取不到用户信息请查看是否有登录')
    //     }
    //   }
    //   reject('获取不到姓名')
    //         // commit('SET_NAME', data.name)
    //         // commit('SET_AVATAR', data.avata     
    // },

    // 登出
    LogOut({state,commit}) {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('CLEAR_LOCK')
          removeToken()
      // console.log('remove'+state.token)
    },

    // 前端 登出
    FedLogOut({ commit,state }) {
      return new Promise(resolve => {
        // commit('SET_TOKEN', state.username.name)
        removeToken()
        resolve()
      })
    },
    // 动态修改权限
    // ChangeRoles({ commit }, role) {
    //   return new Promise(resolve => {
    //     commit('SET_TOKEN', role)
    //     setToken(role)
    //     getInfo(role).then(response => {
    //       const data = response
    //       commit('SET_ROLES', data.roles)
    //       commit('SET_NAME', data.name)
    //       commit('SET_AVATAR', data.avatar)
    //       resolve()
    //     })
    //   })
    // }
  }
}

export default user
