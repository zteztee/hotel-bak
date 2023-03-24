import request from '@/utils/request'

export function getUserListPage(params) {
  return request({
    url: '/rooms/'+params.page+'/'+params.pageSize,
    method: 'get',
  })
}
export function getRoomsStates(params) {
  return request({
    url: '/rooms/s/'+params.id,
    method: 'get',
  })
}
export function getRoomsTypes(params) {
  return request({
    url: '/rooms/t/'+params.id,
    method: 'get',
  })
}
export function removeUser(params) {
  return request({
    url: '/user/remove',
    method: 'get',
    params: params
  })
}
export function batchRemoveUser(params) {
  return request({
    url: '/user/batchremove',
    method: 'get',
    params: params
  })
}
export function editUser(params) {
  return request({
    url: '/user/edit',
    method: 'get',
    params: params
  })
}
export function addUser(params) {
  return request({
    url: '/user/add',
    method: 'get',
    params: params
  })
}
