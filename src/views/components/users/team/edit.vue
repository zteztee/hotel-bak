<template>
    <div>
  <!-- Form -->
  <el-dialog title="修改" :visible="clicks" @close="back" >
    <el-form :model="editForms">
      <el-form-item label="姓名" >
        <el-input v-model="editForms.teamname" ></el-input>
      </el-form-item>
      <el-form-item label="编号" >
        <el-input v-model="editForms.teamemil" ></el-input>

      </el-form-item>
      <el-form-item label="负责人" >
        <el-input v-model="editForms.teamprimary" ></el-input>

      </el-form-item>
      <el-form-item label="电话" >
        <el-input v-model="editForms.teamnumber" ></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="back">取 消</el-button>
      <el-button type="primary" @click="goEdit" >确 定</el-button>
    </div>
  </el-dialog>
  </div>
  
  </template>
<script>
    import {
    editTeam
  } from '@/api/personTable'
  
    export default {
      props:{
        editForms:Object,
        clicks:Boolean,
       
      },
      data() {
        return { 
          dialogVisible: false,
          roomsEditfrom:'',
        }
      },
      methods:{
        back(){
          this.$emit('backReturn1',this.dialogVisible)
        },
        goEdit(){
          const para = this.editForms
          editTeam(para).then(res=>{
            if(res.data.flag){
            this.$message({
                message: '修改成功',
                type: 'success'
              })
              // location.reload()
              this.back()
          }else{
            this.$message({
                message: '修改失败',
                type: 'error'
              })
              this.back()
          }
          })
        }
      },
      // watch:{
      //   returnFlase(clicks){
      //     this.$emit('backReturn',this.dialogVisible)
      //   }
      // }
    }
  </script>
  