<template>
	<section class="app-container">
		<!--工具条-->
    <span style="display: flex;width: fit-content;">
        <el-input v-model="search" placeholder="请输入订单号" clearable size="small" ></el-input>
        <el-button  size="normal" @click="clientSearch" style="height: 34px;padding: revert;">查询</el-button>
      </span>
		<!--列表-->
		<el-table :data="clients" highlight-current-row @selection-change="selsChange" style="width: 100%;" size="medium" >
			<el-table-column type="selection" width="55">
			</el-table-column>
      <el-table-column prop="id" label="订单记录" width="80">
			</el-table-column>
			<el-table-column prop="all.id" label="用户记录" width="80">
			</el-table-column>
			<el-table-column prop="staytime" label="入住时间" min-width="160" >
			</el-table-column>
			<el-table-column prop="days" label="入住天数" width="100">
			</el-table-column>
			<el-table-column prop="times" label="入住小时数" width="100">
			</el-table-column>
			<el-table-column prop="payneed" label="支付费" min-width="100">
			</el-table-column>
			<el-table-column prop="dateout" label="退房时间" min-width="160">
			</el-table-column>
      <el-table-column prop="roomList" label="已入住房间" min-width="160">
        <template slot-scope="scope">
          <span  v-for="(item,index) in scope.row.roomList" :key="index" style="margin: 0 5px;">{{ item }}
          </span>
        </template>
      </el-table-column>
			<el-table-column label="操作" width="150">
        <template slot-scope="scope">
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
        </template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
			<!-- <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
			</el-pagination> -->
      <el-pagination layout="prev, pager, next" :total="50" @current-change="handleCurrentChange" style="float:right;"></el-pagination>
    </el-col>

		
	</section> 
</template>

<script>
import util from '@/utils/table.js'
import {
  getAllClientBypage,removeClient
} from '@/api/ClientStay'
import { Message } from 'element-ui'

export default {
  components:{
  },
  data() {
    return {
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogFormVisible: false,
      dialogFormVisibleAdd:false,
      search:'',
      clients: [],
      arrays:[],
      total: 0,
      page: 0,
      pageSize:10,
      sels: [], // 列表选中列
      editFormRules: {
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
      },
    }
  },
  methods: {
    
    handleCurrentChange(val) {
      this.page = (val-1)*this.pageSize
      this.getAllClientBypages()
    },
    // 获取用户列表
    getAllClientBypages() {
      const para = {
        page: this.page,
        pageSize: this.pageSize,
      }
      getAllClientBypage(para).then(res => {
        // this.total = res.data.total
        this.clients = res.data.data
        this.arrays=res.data.data
      })
    },
    
    // 删除
    handleDel(index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      })
        .then(() => {
          const para = row.id
          removeClient(para).then(res => {
            console.log(res)
            if(res.data.flag){
              Message({
              message: '删除成功',
              type: 'success',
              duration:3*1000
            })
            }
            else{
              Message({
              message: '删除失败',
              type: 'error',
              duration:3*1000,
            })
            }
          })
        })        
    },
    clientSearch(){
      this.clients = this.arrays.filter((item)=>item.id.toString().includes(this.search))
    },
    
    // 全选单选多选
    selsChange(sels) {
      this.sels = sels
    },
    // 批量删除
    batchRemove() {
      var ids = this.sels.map(item => item.id).toString()
      this.$confirm('确认删除选中记录吗？', '提示', {
        type: 'warning'
      })
        .then(() => {
          const para = { ids: ids }
          batchRemoveUser(para).then(res => {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.getUsers()
          })
        })
        .catch(() => {})
    },
    editFormOut(val){
      this.dialogFormVisible=val
    },
  },
  mounted() {
    this.getAllClientBypages()
    // this.getRoomsState()
    // this.getRoomsType()
  }
}
</script>

<style scoped>

</style>