<template>
  <div>
  <div style="width: 49%;display: block;position: absolute;">
    <ve-pie :data="chartData"
    :legend="legend"
    :title="title"
    ></ve-pie>
    </div>
    <div style="width: 49%;display: block;position: absolute;left: 51%;">
      <ve-pie :legend="legend" :title="title1" :data="chartData1" ></ve-pie>
    </div>  
  </div>
</template>

<script>
import { getRoomtypeCountList,getCountLastThreeMonthMoney } from '@/api/count';

export default {
    data() {
      return {
        legend:{
          show: true,
          top:"10%",//与上方的距离 可百分比% 可像素px
      },
      title : {
          text: "用户入住类型比例",
          x: "center",
          textStyle: {
            color: "#303133",//标题颜色
            fontWeight: "bold",//粗体
            fontSize: 24  //字体大小
          }
        },
        title1 : {
          text: "近三月收入情况",
          x: "center",
          textStyle: {
            color: "#303133",//标题颜色
            fontWeight: "bold",//粗体
            fontSize: 24  //字体大小
          }
        },
        chartData: {
          columns: ['roomtype', 'count1'],
          rows: [            
          ]
        },
        chartData1: {
          columns: ['month', 'value3'],
          rows: [            
          ]
        },
        rows:[],
      }
    },
    methods:{
      getRoomtypeCountLists(){
        console.log(this.rows)
        getRoomtypeCountList().then((res)=>{
          var row = []
          res.data.data.forEach(function(item,index,array) { 
              // console.log(JSON.parse(item)) 
              row.push(JSON.parse(item))
              // console.log(row)
           })
          this.chartData.rows=row
        })
      },
      getCountLastThreeMonthMoneys(){
        getCountLastThreeMonthMoney().then((res)=>{
          var row = []
          res.data.data.forEach(function(item,index,array) { 
              // console.log(JSON.parse(item)) 
              row.push(JSON.parse(item))
              // console.log(row)
           })
          this.chartData1.rows=row
        })
      }
    },
    mounted(){
        this.getRoomtypeCountLists(),
        this.getCountLastThreeMonthMoneys()
    }
  };
</script>