<template>
    <div>
  <!-- Form -->
  <el-dialog title="房间预定" :visible="clickp" @close="back" :modal=false>

            <el-row :gutter="20" >
                <el-col :span="8">
                    房间号:{{ room.roomnum }}
                </el-col>
                <el-col :span="8">
                    房间状态:{{ state }}
                </el-col>
                <el-col :span="8">
                    房间类型:{{ type }}
                </el-col>
            </el-row>
            <br>
    <template>
  <el-form :model="preForm" :rules="rules" ref="preForm" label-width="150px">
      <el-row style="height:max-content">           
                <el-form-item label="人数" prop="preper">
                    <el-input v-model="preForm.preper" style="width: 71.6px; height: 31.6px;"> </el-input>
                </el-form-item>
        </el-row>
        <el-form-item label="选择预定时间" prop="cometime">
          <el-date-picker type="datetime" placeholder="选择日期" value-format="timestamp" :picker-options="pickerOptions" v-model="date" @change="dateType"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('preForm')">确定</el-button>
          <el-button @click="resetForm('preForm'),back()">取消</el-button>
        </el-form-item>
      </el-form>
    </template>
  </el-dialog>
  </div>
  
  </template>
<script>
    import {
      addClientPre
  } from '@/api/ClientStay'
  import { Message } from 'element-ui';
  
    export default {
      props:{
        room:Object,
        clickp:Boolean,
        type:String,
        state:String,
        userinfo:Number,
      },
      data() {
        var validateTime = (rule, value, callback) => {
          const curTime = new Date()
          const now =curTime.setHours(curTime.getHours() + 1);
          if (value <= now) {
            callback(new Error('选择时间必须大于当前时间一小时'));
          } else {
            callback();
          }
        };
        return { 
          dialogVisible1: false,
          roomsAddfrom:'',
          rules:{
            preper:[{required:true,message:'输入人数',trigger:'blur'}],
            cometime:[{ required: true, message: '请选择时间', trigger: 'change' },
            { validator: validateTime, trigger: 'blur' },
            ]
          },
          date:'',
          preForm: {
            roomlist: '',
            preper:'',
            cometime:'',
            all:{
              id:''
            },
            },  
          pickerOptions: {
          // 限制收货时间不让选择今天以前的
            disabledDate(time) {
              return time.getTime() < Date.now() - 8.64e7;
            },
          },     
        };             
      },
      computed:{
        
      },
      methods:{
        submitForm(formName) {
          this.$refs[formName].validate(valid => {
            if (valid) {
              this.addPres()
            } else {
              console.log('提交出现问题');
              return false;
            }
          });
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        },
    

        dateType(){
          
          const d=new Date(this.date)
          this.preForm.cometime=d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate()+' '+d.getHours()+':'+d.getMinutes()+':'+d.getSeconds()
          console.log(this.preForm.cometime)
        },
        back(){
          this.$emit('backReturn',this.dialogVisible1)
        },
        
      addPres(){
        this.preForm.roomlist=this.room.roomnum.toString().trim().split(" ")
        this.preForm.all.id=this.userinfo
        console.log(this.preForm)
        addClientPre(this.preForm).then((res)=>{
          if(res.flag=true){
            Message({
              message: '添加成功',
              type: 'success',
              duration: 3 * 1000
            })
            this.resetForm('preForm')
            this.back()
          }
        }).catch(()=>{
          Message({
              message: '添加失败',
              type: 'error',
              duration: 3 * 1000
            })
            this.resetForm('preForm')
            this.back()
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
<style scoped>
.demonstration{
    top: 50px;
}
</style>