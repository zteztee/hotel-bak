<template>
  <div>
    
    <el-container>
    <el-main>
      <div v-if="model">
        <div>
           <span v-if="logType<=2">用户名:{{ form.onename }} 
           </span> 
          <span v-else-if="logType>=3">团队名称:{{ form.teamname }}
          </span>
        </div>
        <div>
          <span v-if="logType<=2" >用户性别:{{ onesex }}
          </span>
          <span v-else-if="logType>=3">团队邮编:{{ form.teamemil }}
          </span>
        </div>
        <div>
          <span v-if="logType==2">身份证号码:{{ form.oneidentify }}
          </span>
          <span v-else-if="logType==3">团队负责人:{{ form.teamprimary }}
          </span>
        </div>
        <div>
          <span v-if="logType==2">用户年龄:{{ form.oneage }}
          </span>
          <span v-else-if="logType==3">负责人电话号码:{{ form.teamnumber }}
          </span>
        </div>
        <div>
          <span v-if="logType==2">用户账号:{{ form.username }}
          </span>
          <span v-else-if="logType==3">用户账号:{{ form.username }}
          </span>
        </div>
        <div>
          <span v-if="logType==2">注册时间:{{ form.onelogin }}
          </span>
          <span v-else-if="logType==3">注册时间:{{ form.teamlogin }}</span>
        </div>
        <div>
          <span v-if="staycount>0">已入住次数:{{ staycount }}</span>
        </div>
      </div>
      <div v-if=!model>
        <el-form :model="forms">
            <span v-if="logType<=2">
            <el-form-item>
              用户名: <el-input type="text"  maxlength="20" v-model="forms.onename" style="display:inline;width: 100px;"></el-input>
            </el-form-item>
            </span>
            <span v-else-if="logType>=3">
              <el-form-item>
                团队名称: <el-input   maxlength="20" v-model="forms.teamname" style="display:inline;width: 100px;"></el-input>
              </el-form-item>
            </span>
        
            <span v-if="logType<=2" >
              <el-form-item>
                用户性别: <el-input type="text"  maxlength="20"  v-model="forms.onesex" style="display:inline;width: auto;"></el-input>
              </el-form-item>
            </span>
            <span v-else-if="logType>=3">
              <el-form-item>
                团队邮编: <el-input type="text"  maxlength="20" v-model="forms.teamemil" style="display:inline;width: auto;"></el-input>
              </el-form-item>
            </span>
        
            <span v-if="logType<=2">
              <el-form-item>
                身份证号码: <el-input type="text"  maxlength="20" v-model="forms.oneidentify"  style="display:inline;width: auto;"></el-input>
              </el-form-item>
            </span>
            <span v-else-if="logType>=3">
              <el-form-item>
                团队负责人: <el-input type="text"  maxlength="20" v-model="forms.teamprimary"  style="display:inline;width: auto;"></el-input>
              </el-form-item>
            </span>
          
            <span v-if="logType<=2">
              <el-form-item>
                用户年龄: <el-input type="text"  maxlength="20" v-model="forms.oneage" style="display:inline;width: auto;"></el-input>
              </el-form-item>
            </span>
            <span v-else-if="logType>=3">
              <el-form-item>
                负责人电话号码: <el-input type="text"  maxlength="20" v-model="forms.teamnumber"  style="display:inline;width: auto;"></el-input>
              </el-form-item>
            </span>
        
            <span>
              <el-form-item>
                用户账号: <el-input type="text"  maxlength="20"  v-model="forms.username" style="display:inline;width: auto;"></el-input>
              </el-form-item>
            </span>
        </el-form>
        <span>
          <el-button v-if=!model @click="changeModel">取 消</el-button>
          <el-button v-if=!model type="primary" @click="goEdit" >确 定</el-button>
        </span>
      </div>
    </el-main>
      <el-button @click="changeModel">修改信息</el-button>
  </el-container>
</div>
</template>

<script>
import { getInfo } from '@/api/login'
import { getCountByAid } from '@/api/ClientStay'
import data from '../icons/svg-icons/generateIconsView'
import{ editOnes,editTeam } from '@/api/personTable'
import { Message } from 'element-ui'

export default {
  data() {
    return {
      data: {
       username:'',
       password:'',
       logType:'' 
      },
      logType:'',
      model:true,
      onesex:'',
      form:{
        all:{
          id:'',
        }
      },
      forms:{
      },
      staycount:'',
    }
  },
  methods: {
    goEdit(){
      if(this.logType==3){
        editTeam(this.forms).then((res)=>{
          if(res.data.flag==true){
            Message({
              message: '添加成功',
              type: 'success',
              duration: 3 * 1000
            })
            this.form=this.forms
            this.model=!this.model
          }
        })
      }
      if(this.logType==2){
        editOnes(this.forms).then((res)=>{
          if(res.data.flag==true){
            console.log(res)
            Message({
              message: '添加成功',
              type: 'success',
              duration: 3 * 1000
            })
            this.form=this.forms
            this.model=!this.model
            this.checkSex()
          }
        })
      }
      if(this.logType==1){
        console.log(this.logType)
        console.log(this.forms)
      }
    },
    changeModel(){
      this.forms=this.form
      this.model=!this.model
    },
    checkSex(){
      if(this.form.onesex==1){
        this.onesex='男'
      }
      else if(this.form.onesex==0){
        this.onesex='女'
      }
    },
    getLogType(){
      this.logType=this.data.logType
      console.log(this.data)
      console.log(this.logType)
    },
    insertData() {
      console.log(this.$store.state.user.token)
      this.data=JSON.parse(this.$store.state.user.token)
      // this.data=this.$store.state.user.token
      getInfo(this.data).then((res)=>{
        this.form=res.data.data
      })
      this.getLogType()
      
      if(this.form.allid>0){
        getCountByAid(this.form.allid).then((res)=>{
        this.staycount=res.data.data
        console.log(this.form)
        console.log(this.staycount)
        })
      }
    },
  },
  mounted(){
    this.insertData()
    
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
.el-header, .el-footer {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    line-height: 85px;
    /* border-width: 1px; */
    height: 100vh;

  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  .el-form-item__content{
  line-height: 85px;
  text-align: center;
  font-size: 16px;
  
}
.el-form-item__content{
  line-height: 85px;
  text-align: center;
  font-size: 16px;
  
}
   
</style>
<style >

.el-input__inner{
  width: 207px;
    font-size: 16px;
    
  }

</style>

