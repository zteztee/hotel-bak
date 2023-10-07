import request from '@/utils/request'

export function getRoomtypeCountList(){
    return request({
        url: '/count/roomtype1',
        method:'get',
    })
}

export function getCountLastThreeMonthMoney(){
    return request({
        url:'/count/threemonthmoney',
        method:'get'
    })
}

export function addWeekCount(params){
    return request({
        url:'/count/visits/'+params,
        method:'get',
        
    })
}

export function getDaysCount(){
    return request({
        url:'/count/dayscount',
        method:'get'
    })
}