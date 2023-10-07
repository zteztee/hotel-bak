<template>
    <div>
  <!-- Form -->
  <el-dialog title="房间入住" :visible="clicks" @close="back" :modal=false>
            <el-row :gutter="20">
                <el-col :span="6">
                    房间号:{{ room.roomnum }}
                </el-col>
                <el-col :span="6">
                  房间状态:{{ state }}
                </el-col>
                <el-col :span="6">
                  房间类型:{{ type }}
                </el-col>
            </el-row>
        <el-form :model="ruleForm" :rules="rules" ref="ruleFrom"> 
            <el-row :gutter="40">
                <el-col :span="6">
                    <el-form-item label="小时" prop="times">
                        <el-input  v-model="ruleForm.times" @blur=getCount @change="dateType"></el-input>
                    </el-form-item>
                  
                </el-col>
                <el-col :span="6">
                    <el-form-item label="天数" prop="days">
                        <el-input v-model="ruleForm.days" @blur=getCount @change="dateType"> </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
                    <div class="block">
                    <el-form-item label="选择入住时间" prop="date">
                        <p>
                            <el-date-picker v-model="date" type="datetime" placeholder="选择日期时间" default-time="12:00:00" @change="dateType">
                            </el-date-picker>
                        </p>
                    </el-form-item>
                    </div>
                    <div class="block">
                      <el-form-item label="截止时间" prop="dateout">
                        <el-date-picker v-model="ruleForm.dateout" type="datetime" default-time="12:00:00" readonly>
                        </el-date-picker>
                      </el-form-item>
                    <br>
                  </div>
                    <span>所需付款:{{ ruleForm.payneed }}</span>

            </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('ruleFrom'),back()">取 消</el-button>
        <el-button type="primary" @click.native="submitForm('ruleFrom')">确 定</el-button>
      </div>
  </el-dialog>
  </div>
  
  </template>
<script>
    import {
      addClient
  } from '@/api/ClientStay'
  import { Message } from 'element-ui'
    export default {
      props:{
        room:Object,
        clicks:Boolean,
        type:String,
        state:String,
        userinfo:Number,
      },
      data() {
        return { 
          date:'',
          dialogVisible: false,
          roomsAddfrom:'',
          rules:{
            days:[{required:true,message:'输入天数',trigger:'blur'}],
            times:[{message:'输入小时数',trigger:'blur'}]
          },
          roomnum: '',
          ruleForm: {
            days:'',
            times:'',
            payneed:0,
            all:{
              id:''
            },
            date:'',
            dateout:'',
            roomlist:[]
            },          
        }
      },
      computed:{
        
      },
      methods:{
        dateType(){
          this.ruleForm.date = this.date
          const d=new Date(this.date)
          let t = new Date(this.date);
          this.ruleForm.date=d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate()+' '+d.getHours()+':'+d.getMinutes()+':'+d.getSeconds()
          t=Date.parse(new Date(t))/1000;
          t+= (86400) * this.ruleForm.days+ (3600) * this.ruleForm.times;
          let newDate = new Date(parseInt(t) * 1000);
          this.ruleForm.dateout = newDate.getFullYear() + '-'+ (newDate.getMonth()+1) + '-'+ newDate.getDate() + ' '+newDate.getHours()+':'+newDate.getMinutes()+':'+newDate.getSeconds()
        },
        getCount(){
            this.ruleForm.payneed=this.ruleForm.days*this.room.daymoney+this.ruleForm.times*this.room.timemoney
        },
        back(){
          this.$emit('backReturn1',this.dialogVisible)
        },
        
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.addClients()
          } else {
            console.log('提交出现了问题!')
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      addClients(){
        console.log(this.room.roomnum)
        this.ruleForm.roomlist=this.room.roomnum.toString().trim().split(" ")
        this.ruleForm.all.id=this.userinfo
        addClient(this.ruleForm).then((res)=>{
          if(res.flag=true){
            Message({
              message: '添加成功',
              type: 'success',
              duration: 3 * 1000
            })
            this.resetForm('ruleFrom')
            this.back()
          }
        }).catch(()=>{
          Message({
              message: '添加失败',
              type: 'error',
              duration: 3 * 1000
            })
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
.el-form-item__content {
    line-height: 43px;
    text-align: left;
    font-size: 9px;
    width: 270px;
}
</style>