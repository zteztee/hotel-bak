<template>
  <div>
    <!-- <p class="warn-content"> -->
      <!-- <a href="https://v-charts.js.org/#/" target="_blank">charts组件参考v-charts
      </a> -->
    <!-- </p> -->
    <ve-histogram :data="chartData"
    :colors="chartColor"
    :legend="legend"
    :loading="loading"
    :data-empty="dataEmpty"
    :extend="extend"
    :settings="chartSettings"
    :title="title"></ve-histogram>
  </div>
</template>

<script>
import { getDaysCount } from '@/api/count'
export default { 
  data() {
    return {
      legend:{
          show: true,
          top:"10%",//与上方的距离 可百分比% 可像素px
      },
      title : {
          text: "近七日用户访问,下单量",
          x: "center",
          textStyle: {
            color: "#303133",//标题颜色
            fontWeight: "bold",//粗体
            fontSize: 24  //字体大小
          }
        },
      chartSettings: {
        xAxisType: 'time',
        area: true,
        yAxisName: ['数量'],
        // axisSite: {right: ['orderAmount']},
        labelMap: {'value1': '访问量', 'value2': '下单量'}
      },
      chartData: {
        columns: ['day', 'value1', 'value2', '下单率'],
        rows: [
          { 日期: '1/1', 访问用户: 1393, 下单用户: 1093, 下单率: 0.32 },
          { 日期: '1/2', 访问用户: 3530, 下单用户: 3230, 下单率: 0.26 },
          { 日期: '1/3', 访问用户: 2923, 下单用户: 2623, 下单率: 0.76 },
          { 日期: '1/4', 访问用户: 1723, 下单用户: 1423, 下单率: 0.49 },
          { 日期: '1/5', 访问用户: 3792, 下单用户: 3492, 下单率: 0.323 },
          { 日期: '1/6', 访问用户: 4593, 下单用户: 4293, 下单率: 0.78 }
        ]
      },
      extend: {
        series: {
          symbolSize: 10,
          label: {
            normal: {
              show: false
            }
          }
        }
      },
      chartColor: ['#89dd47', '#3cabf4'],
      loading: false,
      dataEmpty: false
    }
  },
  methods:{
    getDaysCounts(){
        getDaysCount().then((res)=>{
          var row = []
          res.data.data.forEach(function(item,index,array) { 
              console.log(JSON.parse(item)) 
              row.push(JSON.parse(item))
              // console.log(row)
           })
          this.chartData.rows=row
        })
      }
    },
    mounted(){
        this.getDaysCounts()
    }
}
</script>