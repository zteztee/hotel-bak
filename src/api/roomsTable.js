import request from '@/utils/request'

export function getUserListPage(params) {
  return request({
    url: '/rooms/'+params.page+'/'+params.pageSize,
    method: 'get',
  })
}

export function getAllRooms(){
  return request({
    url:'/rooms',
    method:'get'
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
export function getRoomsByType(params) {
  return request({
    url: '/rooms/type/',
    method: 'post',
    data:{
      "type":params,
    }
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
export function editRooms(params) {
  return request({
    url: '/rooms',
    method: 'put',
    data: {
      roomnum: params.roomnum,
      roomState :{
        id:params.roomState.id
      } ,
      roomType: {
        id:params.roomType.id,
      },
      timemoney : params.timemoney,
      daymoney : params.daymoney,
      id : params.id
    }
  })
}
export function addRooms(params) {
  return request({
    url: '/rooms',
    method: 'post',
    data: {
      roomnum: params.roomnum,
      roomState :{
        id:params.roomState.state
      } ,
      roomType: {
        id:params.roomType.type,
      },
      timemoney : params.timemoney,
      daymoney : params.daymoney,
    }
  })
}
