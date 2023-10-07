<template>
    <div>
  <!-- Form -->
  <el-dialog title="修改" :visible="clicks" @close="back" >
    <el-form :model="editForms">
      <el-form-item label="姓名" >
        <el-input v-model="editForms.onename" ></el-input>
      </el-form-item>
      <el-form-item label="性别" >
        <el-input v-model="editForms.onesex" ></el-input>

      </el-form-item>
      <el-form-item label="身份证号码" >
        <el-input v-model="editForms.oneidentify" ></el-input>

      </el-form-item>
      <el-form-item label="年龄" >
        <el-input v-model="editForms.oneage" ></el-input>
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
    editOnes
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
          this.$emit('backReturn',this.dialogVisible)
        },
        goEdit(){
          const para = this.editForms
          editOnes(para).then(res=>{
            if(res.data.flag){
            this.$message({
                message: '修改成功,准备刷新',
                type: 'success',
                duration:3*1000
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
  