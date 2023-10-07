import request from '@/utils/request'

export function login(username,userpwd,logType) {
  if(logType==2){
    return request({
      url: '/ones/u/'+username+'/'+userpwd,
      method: 'get',
      // data: {
      //   username,
      //   userpwd
      // }
    })
  }
  if(logType==3){
    return request({
      url: '/teams/u/'+username+'/'+userpwd,
      method: 'get',
      // data: {
      //   username,
      //   userpwd
      // }
    })
  }
  if(logType==1){
    return request({
      url: '/logs/'+username+'/'+userpwd,
      method: 'get',
      // data: {
      //   username,
      //   userpwd
      // }
    })
  }
  
}

export function getInfo(token) {
  if(token.logType==2){
    return request({
      url: '/ones/u/'+token.username+'/'+token.password,
      method: 'get',
      // data: {
      //   username,
      //   userpwd
      // }
    })
  }
  if(token.logType==3){
    return request({
      url: '/teams/u/'+token.username+'/'+token.password,
      method: 'get',
      // data: {
      //   username,
      //   userpwd
      // }
    })
  }
  if(token.logType==1){
    return request({
      url: '/logs/'+token.username+'/'+token.password,
      method: 'get',
      // data: {
      //   username,
      //   userpwd
      // }
    })
  }
}

// export function logout() {
//   return request({
//     url: '/user/logout',
//     method: 'post'
//   })
// }
