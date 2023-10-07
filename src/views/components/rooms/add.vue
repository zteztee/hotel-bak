<template>
    <div>
  <!-- Form -->
  <el-dialog title="增加房间" :visible="clickz" @close="back" >
    <el-form :model="editForms" :rules="rules" ref="ruleFrom"> 
      <el-form-item label="房间号" prop="roomnum">
        <el-input v-model="editForms.roomnum"></el-input>
      </el-form-item>
      <el-form-item label="房间状态" prop="roomstate">
        <el-select v-model="editForms.roomState.state" placeholder="房间状态">
            <el-option v-for="item in roomStateList" :key="item.id" :label="item.state" :value="item.id">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="房间类型" prop="roomtype">
        <el-select v-model="editForms.roomType.type" placeholder="房间类型">
            <el-option v-for="item in roomTypeList" :key="item.id" :label="item.type" :value="item.id">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="元/小时" prop="timemoney">
        <el-input  v-model="editForms.timemoney"></el-input>
      </el-form-item>
      <el-form-item label="元/天" prop="daymoney">
        <el-input v-model="editForms.daymoney"> </el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="back">取 消</el-button>
      <el-button type="primary" @click="goEdit"  >确 定</el-button>
    </div>
  </el-dialog>
  </div>
  
  </template>
  <script>
    import {
      addRooms
  } from '@/api/roomsTable'
  
    export default {
      props:{
        editForms:Object,
        clickz:Boolean,
        roomStateList:Array,
        roomTypeList:Array
      },
      data() {
        return { 
          dialogVisible: false,
          roomsAddfrom:'',
          rules:{
            roomnum:[
                {required:true,message:'请输入房间号',trigger:'blur'},
                {min:4,max:4,message:'输入4位数',trigger:'blur'}
            ],
            roomState:[{required:true,message:'请选择房间状态',trigger:'blur'}],
            roomtype:[{required:true,message:'请选择房间类型',trigger:'blur'}],
            timemoney:[{required:true,message:'输入小时费用',trigger:'blur'}],
            daymoney:[{required:true,message:'输入天费用',trigger:'blur'}],
          },
          ruleForm: {
          roomnum: '',
          roomstate: '',
          roomtype: '',
          timemoney: '',
          daymoney: '',
        },
        }
      },
      methods:{
        back(){
          this.$emit('backReturn1',this.dialogVisible)
        },
        goEdit(){
          this.ruleForm = this.editForms
          console.log(this.editForms)
          addRooms(this.ruleForm).then(res=>{
            if(res.data.flag){
            this.$message({
                message: '添加成功',
                type: 'success'
              })
              this.back()
          }else{
            this.$message({
                message: '添加失败',
                type: 'error'
              })
              this.back()
          }
          })
        },
      //   submitForm(formName) {
      //   this.$refs.formName.validate((valid) => {
      //     if (valid) {
      //       this.resetForm()
      //     } else {
      //       return false;
      //     }
      //   });
      // },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
      },
      // watch:{
      //   returnFlase(clicks){
      //     this.$emit('backReturn',this.dialogVisible)
      //   }
      // }
    }
  </script>
  