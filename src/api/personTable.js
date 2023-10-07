import request from '@/utils/request'

export function getOneListPage(params) {
  return request({
    url: '/ones/'+params.page+'/'+params.pageSize,
    method: 'get',
  })
}
export function getTeamListPage(params) {
  return request({
    url: '/teams/'+params.page+'/'+params.pageSize,
    method: 'get',
  })
}
export function getRoomsTypes(params) {
  return request({
    url: '/rooms/t/'+params.id,
    method: 'get',
  })
}
export function removeRooms(params) {
  return request({
    url: '/rooms/'+params.id,
    method: 'delete',
  })
}
export function batchRemoveUser(params) {
  return request({
    url: '/user/batchremove',
    method: 'get',
    params: params
  })
}
export function editOnes(params) {
  return request({
    url: '/ones',
    method: 'put',
    data: {
      onename: params.onename,
      onesex:params.onesex,
      oneidentify:params.oneidentify,
      oneage:params.oneage,
      onelogin:params.onelogin,
      username:params.username,
      userpwd:params.userpwd,
      id : params.id
    }
  })
}
export function addOne(params) {
  return request({
    url: '/logs/p',
    method: 'put',
    data: {
      onename: params.onename,
      onesex:params.onesex,
      oneidentify:params.oneidentify,
      oneage:params.oneage,
      onelogin:params.onelogin,
      username:params.username,
      userpwd:params.userpwd,
    }
  })
}
export function addTeam(params) {
  return request({
    url: '/teams',
    method: 'put',
    data: {
      teamname: params.teamname,
      teamemil:params.teamemil,
      teamprimary:params.teamprimary,
      teamnumber:params.teamnumber,
      teamlogin:params.teamlogin,
      username:params.username,
      userpwd:params.userpwd,
    }
  })
}
export function editTeam(params) {
  return request({
    url: '/teams',
    method: 'put',
    data:{
      teamname: params.teamname,
      teamemil:params.teamemil,
      teamprimary:params.teamprimary,
      teamnumber:params.teamnumber,
      teamlogin:params.teamlogin,
      username:params.username,
      userpwd:params.userpwd,
      id:params.id
    }
    
    
  })
}
