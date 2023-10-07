<template>
  <section class="app-container">
    <div class="top">
      <span v-if="!userType">散客管理</span>
      <span v-if="userType">团体管理</span>
      <el-switch v-model="userType" active-color="#13ce66" inactive-color="#13ce66" @click="changeType"></el-switch>
    </div>
      <span v-if="!userType" style="display: flex;width: fit-content;">
        <el-input v-model="search.one" placeholder="输入散客姓名" clearable size="small" ></el-input>
        <el-button  size="normal" @click="oneSearch" style="height: 34px;padding: revert;">查询</el-button>
      </span>
    
      <el-table v-if="!userType" :span="24" :data="users" highlight-current-row @selection-change="selsChange" style="width: 100%;" :default-sort="{prop:'onelogin', order:'descending'}">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="onename" label="姓名" span="1" sortable>
			</el-table-column>
			<el-table-column prop="onesex" label="性别" span="1" formatSex>
			</el-table-column>
			<el-table-column prop="oneidentify" label="身份证号码" span="2">
			</el-table-column>
			<el-table-column prop="oneage" label="年龄" width="100px">
			</el-table-column>
			<el-table-column prop="onelogin" label="注册时间" min-width="300px" width="300px" sortable>
			</el-table-column>
      <el-table-column prop="username" label="账号" span="1">
			</el-table-column>
      <el-table-column prop="userpwd" label="密码" span="1">
			</el-table-column>
      <el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <!--编辑界面-->
		          <oneUsersEditfrom ref="editForm" :clicks="dialogFormVisible" :editForms="editForm" @backReturn="editFormOut" > </oneUsersEditfrom>
					<!-- <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button> -->
				</template>
			</el-table-column>
    </el-table>

    <span v-if="userType" style="display: flex;width: fit-content;">
        <el-input v-model="search.team" placeholder="输入团体姓名" clearable size="small" ></el-input>
        <el-button  size="normal" @click="teamSearch" style="height: 34px;padding: revert;">查询</el-button>
      </span>
    <el-table v-if="userType" :span="24" :data="teams" highlight-current-row @selection-change="selsChange" style="width: 100%;" :default-sort="{prop:'teamname', order:'descending'}" size="medium ">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="teamname" label="姓名" span="1" sortable>
			</el-table-column>
			<el-table-column prop="teamemil" label="编号" span="1" formatSex>
			</el-table-column>
			<el-table-column prop="teamprimary" label="负责人" span="2">
			</el-table-column>
			<el-table-column prop="teamnumber" label="电话" width="100px">
			</el-table-column>
			<el-table-column prop="teamlogin" label="注册时间" min-width="300px" width="300px" sortable>
			</el-table-column>
      <el-table-column prop="username" label="账号" span="1">
			</el-table-column>
      <el-table-column prop="userpwd" label="密码" span="1">
			</el-table-column>
      <el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button size="small" @click="handleEdit1(scope.$index, scope.row)">编辑</el-button>
          <!--编辑界面-->
		          <teamUsersEditfrom ref="editForm" :clicks="dialogFormVisible1" :editForms="editForm1" @backReturn1="editFormOut1" > </teamUsersEditfrom>
					<!-- <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button> -->
				</template>
			</el-table-column>
    </el-table>

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
import oneUsersEditfrom from '@/views/components/users/one/edit.vue'
import oneUsersAddfrom from '@/views/components/users/one/add.vue'
import teamUsersEditfrom from '@/views/components/users/team/edit.vue'
import {
  getOneListPage,
  getTeamListPage
} from '@/api/personTable'

export default{
    components:{
      oneUsersEditfrom,teamUsersEditfrom
    },
    data(){
      return{
        userType:true,
        search:{
          one:'',
          team:''
        },
        users:[],
        teams:[],
        array:[],
        array1:[],
        sels: [], 
        dialogFormVisible: false,
        dialogFormVisible1: false,
        dialogFormVisibleAdd:false,
        editForm:{
          onename:'',
          onesex:'',
          oneidentify:'',
          oneage:'',
          onelogin:'',
          username:'',
          userpwd:''
        },
        editForm1:{},
        page: 0,
        pageSize:10,
      }
    },
    methods:{
      // 全选单选多选
      selsChange(sels) {
        this.sels = sels
      },
      handleCurrentChange(val) {
      this.page = (val-1)*this.pageSize
      if(this.userType){
        this.getTeam()
      }else{
      this.getOne()
      }
      },
      // 散客姓名查询
      oneSearch(){
        this.users = this.array.filter((item)=>item.onename.includes(this.search.one))
      },
      // 团体姓名查询
      teamSearch(){
        this.teams = this.array1.filter((item1)=>item1.teamname.includes(this.search.team))
      },
    
      // 性别显示转换
      formatSex: function(row, column) {
        return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
      },
      changeType(){
          !userType
      },
      getOne(){
        const para = {
          page: this.page,
          pageSize: this.pageSize,
        }
        getOneListPage(para).then(res => {
        // this.total = res.data.total
        this.users = res.data.data.records
        this.array = res.data.data.records
        })
      },
      getTeam(){
        const para = {
          page: this.page,
          pageSize: this.pageSize,
        }
        getTeamListPage(para).then(res => {
        // this.total = res.data.total
        this.teams = res.data.data.records
        this.array1 = res.data.data.records
        })
      },
        
      // 显示编辑界面
      handleEdit(index, row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.editForm = Object.assign({}, row)
      },
      handleEdit1(index, row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible1 = true
      this.editForm1 = Object.assign({}, row)
      },
      editFormOut(val){
      this.dialogFormVisible=val
      },
      editFormOut1(val1){
      this.dialogFormVisible1=val1
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
    },
    mounted() {
    this.getOne()
    this.getTeam()
    
  }
}

</script>


<style lang="scss" scoped>
span{
  font-weight: 100;
  font-size: small;
  color: green;
}
.top{
  float: right;
}
</style>

