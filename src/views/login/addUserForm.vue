<template>
    <el-dialog title="用户注册" :visible="addUserForm" @close="back" >
        <div >
            <span v-if="!userType">散客</span>
            <span v-if="userType">团体</span>
            <el-switch v-model="userType" active-color="#13ce66" inactive-color="#13ce66"></el-switch>
        </div>
        <div v-if="!userType" style="height: 200%;">
            <el-form :model="oneform" :rules="rule1" ref="oneform">
                <el-form-item label="散客姓名" :label-width="formLabelWidth" prop="onename">
                    <el-input v-model="oneform.onename" ></el-input>
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth" prop="onesex">
                    <el-radio v-model="oneform.onesex" label="1">男</el-radio>
                    <el-radio v-model="oneform.onesex" label="0">女</el-radio>
                </el-form-item>
                <el-form-item label="身份信息" :label-width="formLabelWidth" prop="oneidentify">
                    <el-input v-model="oneform.oneidentify" ></el-input>
                </el-form-item>
                <el-form-item label="年龄" :label-width="formLabelWidth" prop="oneage">
                    <el-input v-model="oneform.oneage" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="注册账号" :label-width="formLabelWidth" prop="username">
                    <el-input v-model="oneform.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="注册密码" :label-width="formLabelWidth" prop="userpwd"> 
                    <el-input v-model="oneform.userpwd" autocomplete="off"></el-input>
                </el-form-item>   
             </el-form>
             <br>
            <span  class="dialog-footer" style="float: right;">
                    <el-button @click="back">取 消</el-button>
                    <el-button type="primary" @click="submitForm('oneform')">确 定</el-button>
            </span>
        </div>
        <div v-if="userType" style="height: 150%;">
            <el-form :model="teamform" ref="teamform" :rules="rule2">
                <el-form-item label="团队姓名" :label-width="formLabelWidth" prop="teamname">
                    <el-input v-model="teamform.teamname" ></el-input>
                </el-form-item>
                <el-form-item label="团队邮编" :label-width="formLabelWidth" prop="teamemil">
                    <el-input v-model="teamform.teamemil" ></el-input>
                </el-form-item>
                <el-form-item label="负责人" :label-width="formLabelWidth" prop="teamprimary">
                    <el-input v-model="teamform.teamprimary" ></el-input>
                </el-form-item>
                <el-form-item label="负责人电话" :label-width="formLabelWidth" prop="teamnumber">
                    <el-input v-model="teamform.teamnumber" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="注册账号" :label-width="formLabelWidth" prop="username">
                    <el-input v-model="teamform.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="注册密码" :label-width="formLabelWidth" prop="userpwd">
                    <el-input v-model="teamform.userpwd" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <span style="float: right;">
                    <el-button @click="back" >取 消</el-button>
                    <el-button type="primary" @click="submitForm1('teamform')">确 定</el-button>
                </span>
            <br>
                
        </div>
    </el-dialog>
</template>

<script>
import { addOne,addTeam } from '@/api/personTable';
import { Message } from 'element-ui';


  export default {
    props:{
        addUserForm:Boolean,
    },
    
    data() {
      return {
        userType:false,
        dialogTableVisible: false,
        dialogFormVisible: false,
        oneform: {
            onename:'',
            onesex:'',
            oneidentify:'',
            oneage:'',
            username:'',
            userpwd:'',
            onelogin:'',
        },
        rule1:{
            onename:[
                {required:true,message:'请输入散客姓名',trigger:'blur'},
                {min:2,max:5,message:'输入2-5位数',trigger:'blur'}
            ],
            oneidentify:[{required:true,message:'输入身份证号',trigger:'blur'}],
            username:[{required:true,message:'输入账号',trigger:'blur'}],
            userpwd:[{required:true,message:'输入密码',trigger:'blur'}],
        },
        teamform: {
            teamname:'',
            teamemil:'',
            teamprimary:'',
            teamnumber:'',
            username:'',
            userpwd:'',
            teamlogin:'',
        },
        rule2:{
            teamname:[
                {required:true,message:'输入团队姓名',trigger:'blur'},
            ],
            teamprimary:[{required:true,message:'输入负责人',trigger:'blur'}],
            teamnumber:[{required:true,message:'输入负责人号码',trigger:'blur'}],
            username:[{required:true,message:'输入账号',trigger:'blur'}],
            userpwd:[{required:true,message:'输入密码',trigger:'blur'}],
        },
        formLabelWidth: '120px',
        gettime:'',
      };
    },
    methods:{
        formatSex: function(row, column) {
        return row.onesex === 1 ? '男' : row.onesex === 0 ? '女' : '未知'
        },
        back(){
            this.$emit('backReturn',this.dialogFormVisible)
        },
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {
            if (valid) {
                this.addOnes()
            } else {
                return this.resetForm(formName);
            }
            });
        },
        submitForm1(formName) {
        this.$refs[formName].validate((valid) => {
            if (valid) {
                this.addTeams()
            } else {
                return this.resetForm(formName);
            }
            });
        },
        resetForm(formName) {
        this.$refs[formName].resetFields();
        },
        addOnes(){
            this.oneform.onelogin=this.gettime
          addOne(this.oneform).then((res)=>{
          if(res.flag=true){
            Message({
              message: '添加成功',
              type: 'success',
              duration: 3 * 1000
            })
            this.resetForm('oneform')
            this.back()
          }
        }).catch(()=>{
          Message({
              message: '添加失败',
              type: 'error',
              duration: 3 * 1000
            })
            this.resetForm('oneform')
            this.back()
        })
        },
        addTeams(){
            this.teamform.teamlogin=this.gettime
          addTeam(this.teamform).then((res)=>{
            if(res.flag=true){
                Message({
                message: '添加成功',
                type: 'success',
                duration: 3 * 1000
                })
                this.resetForm('teamform')
                this.back()
            }
            }).catch(()=>{
            Message({
                message: '添加失败',
                type: 'error',
                duration: 3 * 1000
                })
                this.resetForm('teamform')
                this.back()
            })
        },
        gettimes() {
		      let yy = new Date().getFullYear();
		      let mm = new Date().getMonth()+1;
		      let dd = new Date().getDate();
		      let hh = new Date().getHours();
		      let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
		      let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
		      this.gettime = yy+'-'+mm+'-'+dd+' '+hh+':'+mf+':'+ss;
		      console.log(this.gettime)
        } 

    },
    mounted(){
        this.gettimes()
    }
        
  };
</script>
<style>
.el-dialog__body{
    height: 520px;
}

</style>