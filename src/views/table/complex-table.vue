<template>
	<section class="app-container">
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters" @submit.native.prevent>
        <span class="topfilter">房间状态：</span>
				<el-form-item>
					<el-select v-model="filters.state" placeholder="房间状态">
            <el-option v-for="item in roomStateList" :key="item.id" :label="item.state" :value="item.state">
            </el-option>
          </el-select>
				</el-form-item>
        <el-form-item :prop="filters.state">
					<el-button type="primary" v-on:click="getRoomsState" >状态查询</el-button>
				</el-form-item>
        <span class="topfilter">房间类型：</span>
				<el-form-item>
					<el-select v-model="filters.type" placeholder="房间类型">
            <el-option v-for="item in roomTypeList" :key="item.id" :label="item.type" :value="item.type"></el-option>
          </el-select>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getRoomsType">类型查询</el-button>
				</el-form-item>
        <el-button type="primary" class="el-icon-plus" style="float: right;" @click="handleAdd" circle></el-button>
        <roomsAddfrom ref="addFrom" :clickz="dialogFormVisibleAdd" :editForms="editForm" @backReturn1="addFormOut" :roomStateList="roomStateList" :roomTypeList="roomTypeList"> </roomsAddfrom>

				<!-- <el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item> -->
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="rooms" highlight-current-row @selection-change="selsChange" style="width: 100%;" size="medium" >
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="roomnum" label="房间号" width="120">
			</el-table-column>
			<el-table-column prop="roomState.state" label="房间状态" width="120" >
			</el-table-column>
			<el-table-column prop="roomType.type" label="房间类型" width="120">
			</el-table-column>
			<el-table-column prop="timemoney" label="元/小时" width="120">
			</el-table-column>
			<el-table-column prop="daymoney" label="元/天" min-width="160">
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <!--编辑界面-->
		          <roomsEditfrom ref="editForm" :clicks="dialogFormVisible" :editForms="editForm" @backReturn="editFormOut" :roomStateList="roomStateList" :roomTypeList="roomTypeList"> </roomsEditfrom>
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
import roomsEditfrom from '@/views/components/rooms/edit.vue'
import roomsAddfrom from '@/views/components/rooms/add.vue'
import {
  getUserListPage,
  getRoomsStates,
  getRoomsTypes,
  removeRooms,
  batchRemoveUser,
  
} from '@/api/roomsTable'

export default {
  components:{
    roomsEditfrom,roomsAddfrom
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
      filters: {
        state: '',
        stateid:0,
        type: '',
        typeid:0
      },
      rooms: [],
      roomStateList:[],
      roomTypeList:[],
      total: 0,
      page: 0,
      pageSize:10,
      sels: [], // 列表选中列
      editFormRules: {
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
      },
      // 编辑界面数据
      editForm: {
        roomnum: '',
        roomState:{ state: '',},
        roomType:{type:''},
        timemoney: 0,
        daymoney: 0,
        id:0,

      },

      addFormVisible: false, // 新增界面是否显示
      addFormRules: {
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
      }
    }
  },
  methods: {
    
    handleCurrentChange(val) {
      this.page = (val-1)*this.pageSize
      this.getUsers()
    },
    // 获取用户列表
    getUsers() {
      const para = {
        page: this.page,
        pageSize: this.pageSize,
      }
      getUserListPage(para).then(res => {
        // this.total = res.data.total
        this.rooms = res.data.data
      })
    },
    getRoomsState() {
      if(this.filters.state!=''){
        for(var i = 0; i < this.roomStateList.length; i++){
          if(this.roomStateList[i].state == this.filters.state){
            this.filters.stateid = this.roomStateList[i].id
            const para = {
              id: this.filters.stateid
            }
            getRoomsStates(para).then(res => {
                    // this.total = res.data.total
              this.rooms = res.data.data
              this.filters.state = ''
            })
          }
        }
      }else{
        const para = {
          id:''
        }
        getRoomsStates(para).then(res => {
                    // this.total = res.data.total
              this.roomStateList = res.data.data
              this.filters.state = ''
            })}
      
    },
    getRoomsType() {
      if(this.filters.type!=''){
        for(var i = 0; i < this.roomTypeList.length; i++){
          if(this.roomTypeList[i].type == this.filters.type){
            this.filters.typeid = this.roomTypeList[i].id
            const para = {
              id: this.filters.typeid
            }
            getRoomsTypes(para).then(res => {
                    // this.total = res.data.total
              this.rooms = res.data.data
              this.filters.type = ''
            })
          }
        }
      }else{
        const para = {
          id:''
        }
        getRoomsTypes(para).then(res => {
           // this.total = res.data.total
          this.roomTypeList = res.data.data
          this.filters.type = ''
        })
      }
    },
    // 删除
    handleDel(index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      })
        .then(() => {
          const para = { id: row.id }
          removeRooms(para).then(res => {
            if(res.data.flag){
              this.$message({
              message: '删除成功',
              type: 'success'
            })
            }
            this.getUsers()
          })
        })
        .catch(() => {})
    },
    // 显示编辑界面
    handleEdit(index, row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.editForm = Object.assign({}, row)
      
    },
    // 显示新增界面
    handleAdd() {
      this.dialogStatus = 'create'
      this.dialogFormVisibleAdd = true
      this.editForm = {
        roomnum: '',
        roomState:{ state: '',},
        roomType:{type:''},
        timemoney: 0,
        daymoney: 0,
        id:0
      }
    },
    // 编辑
    // updateData() {
    //   this.$refs.editForm.validate(valid => {
    //     if (valid) {
    //       this.$confirm('确认提交吗？', '提示', {})
    //         .then(() => {
    //           const para = Object.assign({}, this.editForm)
    //           para.birth =
    //             !para.birth || para.birth === ''
    //               ? ''
    //               : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')
    //           editUser(para).then(res => {
    //             this.$message({
    //               message: '提交成功',
    //               type: 'success'
    //             })
    //             this.$refs['editForm'].resetFields()
    //             this.dialogFormVisible = false
    //             this.getUsers()
    //           })
    //         })
    //         .catch(e => {
    //           // 打印一下错误
    //           console.log(e)
    //         })
    //     }
    //   })
    // },
    // 新增
    // createData: function() {
    //   this.$refs.editForm.validate(valid => {
    //     if (valid) {
    //       this.$confirm('确认提交吗？', '提示', {})
    //         .then(() => {
    //           this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
    //           const para = Object.assign({}, this.editForm)
    //           console.log(para)

    //           para.birth =
    //             !para.birth || para.birth === ''
    //               ? ''
    //               : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')
    //           addUser(para).then(res => {
    //             this.$message({
    //               message: '提交成功',
    //               type: 'success'
    //             })
    //             this.$refs['editForm'].resetFields()
    //             this.dialogFormVisible = false
    //             this.getUsers()
    //           })
    //         })
    //         .catch(e => {
    //           // 打印一下错误
    //           console.log(e)
    //         })
    //     }
    //   })
    // },
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
    addFormOut(v){
      this.dialogFormVisibleAdd=v
    }
  },
  mounted() {
    this.getUsers()
    this.getRoomsState()
    this.getRoomsType()
  }
}
</script>

<style scoped>
.topfilter{
  padding-top: 10px;
    display: inline-block;
}
</style>