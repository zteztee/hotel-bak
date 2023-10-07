import Cookies from 'js-cookie'
import store from '@/store/index'
// export var TokenKey = 'Admin-Token'
var TokenKey = 'getusername'

export function getToken() {
  // return Cookies.remove('adm1232')
  
  // const key = getUsername()
  // if(key){
  return Cookies.get(getUsername())
  }
// }

export function getUsername(){
  return Cookies.get(TokenKey)
}

export function setUsername(username){
  return Cookies.set(TokenKey,username)
}

export function setToken(token) {
  if(getUsername()){
    const username = getUsername()
      return Cookies.set(username, token)
  }
}

export function removeToken() {
  Cookies.remove(getUsername())
  return Cookies.remove(TokenKey)
  
}

// export function setTokenKey(tokenKey){
//   // console.log(getToken('adm456'))
//   // console.log(getToken)
//   return TokenKey=tokenKey
// }