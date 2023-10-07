import request from '@/utils/request'

export function addClient(params) {
  return request({
    url: '/clients', 
    method: 'put',
    data: {
      payneed: params.payneed,
      all:params.all,
      staytime:params.date,
      days:params.days,
      times:params.times,
      paytype_id:params.paytype_id,
      dateout:params.dateout,
      roomList:params.roomlist
    }
  })
}
export function getAllClientBypage(params) {
  return request({
    url: '/clients/'+params.page+'/'+params.pageSize, // 假地址 自行替换
    method: 'get'
  })
}
export function removeClient(param) {
  return request({
    url: '/clients/'+param, // 假地址 自行替换
    method: 'delete'
  })
}

export function addClientPre(params) {
  return request({
    url: '/pres', 
    method: 'put',
    data: {
      all:params.all,
      staytime:params.preper,
      cometime:params.cometime,
      roomList:params.roomlist
    }
  })
}

export function getAllCount(){
  return request({
    url:'/count',
    method:'get'
  })
}

export function  getCountByAid(params){
  return request({
    url:'/count/aid/'+params,
    method:'get'
  })
}

export function getRest(params){
  return request({
    url:'count/a/'+params,
    method:'get'
  })
}

export function  getCountByRoomnum(params){
  return request({
    url:'/count/roomnum/'+params,
    method:'get'
  })
}


export function  getCountByRoomtype(params){
  return request({
    url:'/count/roomtype/'+params,
    method:'get'
  })
}