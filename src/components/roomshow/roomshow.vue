<template>
  <div class="data-card">
    <el-row :span="24" :gutter="30">
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
					<el-button type="primary" v-on:click="changeRooms">返回</el-button>
          <el-button type="primary" v-if="this.$store.state.user.roles.indexOf('editor')>=0" v-on:click="getRests">推荐</el-button>
				</el-form-item>
			</el-form>
		</el-col>
    
      <el-col :span="4" v-for="(item,index) in rooms" :key="index" :xs="12" :sm="6" :md="4" :lg="4" :xl="1">
        <div class="item">
          <!-- <img :src="item.src" class="item-img" /> --> 
          <div class="checkbox">
            <el-checkbox-group v-if="item.roomState.state=='空房'" v-model="checkbox" @change="allcount(item.daymoney)">
              <el-checkbox :label="item.roomnum" ></el-checkbox>
            </el-checkbox-group>
          </div>
          
          <div ref="room" class="item-text"  :style="roomStateType(item.roomState.state)"  @click="drawer = true,roomsType(item.roomType.type),roomClick(item),roomState(item.roomState.state)"  >
            <el-drawer title="我是标题" :visible.sync="drawer" :with-header="false" :append-to-body="true"   :modal=false>
              <div class="room-image">
                  <el-image style="width: 100%; height: 100%" :src='url' fit="contain"></el-image>
              </div>
              <div>
                <p class="Roomcontent">房间号:{{ room.roomnum }}</p>
                <p class="Roomcontent">{{ state }}</p>
                <p class="Roomcontent">{{ type }}</p>
                <p class="Roomcontent">元/小时:{{ room.timemoney }}</p>
                <p class="Roomcontent">天/小时:{{ room.daymoney }}</p>
                <el-button type="primary" @click="handleEdit()" v-if="stayButton" >入住房间</el-button>
                <roomStay :userinfo="userinfo" :room="room" :type="type" :state="state" :clicks="dialogFormVisible" @backReturn1="editFormOut"> </roomStay>
                <el-button type="primary" @click="handlePre()" v-if="stayButton">预定房间</el-button>
                <roomPre :userinfo="userinfo" :room="room" :type="type" :state="state" :clickp="dialogFormVisible1" @backReturn="preFormOut"> </roomPre>
              </div>
            </el-drawer>
            <p>{{item.roomnum}} {{ item.roomType.type }}</p>
            <p>{{item.roomState.state}} <br> 已入住次数： {{ item.count }}</p>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-button type="danger" round v-bind:disabled="checkbox.length<=0" @click="handleTeamAdd()">多选房间入住</el-button>
    <teamStay :userinfo="userinfo" :checkbox="checkbox" :clickz="dialogFormVisible2" @backReturn1="addTeamFormOut" :countmoney="countmoney"> </teamStay>
  </div>
</template>

<script>
import {getUserListPage,getRoomsStates,
  getRoomsTypes,getRoomsByType} from '@/api/roomsTable'
import { getRest } from '@/api/ClientStay'
import { Container } from 'element-ui'
import roomStay from './roomStay.vue'
import teamStay from './teamStay.vue'
import roomPre from './roomPre.vue'
import {getInfo} from '@/api/login'
import {getUsername,getToken} from '@/utils/auth'
import { reject } from 'lodash'
export default {
  components:{
    roomStay,roomPre,teamStay
  },
  data() {
    return {
      filters: {
        state: '',
        stateid:0,
        type: '',
        typeid:0
      },
      roomStateList:[],
      roomTypeList:[],
      countroom:'',
      userinfo:0,
      rooms: [],
      newrooms:[],
      checkbox:[],
      checkbox1:[],
      state:'',
      type:'',
      roomStateList:[],
      roomTypeList:[],
      total: 0,
      page: 0,
      pageSize:100,
      span: this.option.span || 6,
      data: this.option.data || [],
      color:{
        colorText: '',
        bgText: '',
        borderColor: this.option.borderColor || '#2e323f'
      },
      url:'',
      drawer: false,
      room:{},
      dialogFormVisible: false,
      dialogFormVisible1:false,
      dialogFormVisible2:false,
      stayButton:false,
      countmoney:0,
      rest:'',
    }
  },
  props: {
    option: {
      type: Object,
      default: () => {}
    }
  },
  created() {},
  mounted() {
    this.getRooms()
    this.getInfos()
    this.getRoomsState()
    this.getRoomsType()
    // console.log(getToken('Admin-Token'))
    // this.getRoomsState()
    // this.getRoomsType()
  },
  watch: {},
  computed: {
    
    roomStateType(){
      return (state)=>{
        if(state == '空房'){
          return { 'color' : '#fff','background-color' : '#38f242'}
        }
        if(state == '维修'){
          return { 'color' : '#fff','background-color' : '#3b67a4'}
        }
        if(state == '已入住'){
          return { 'color' : '#fff','background-color' : '#f23838'}
        }if(state == '忙碌'){
          return { 'color' : '#fff','background-color' : '#f2b438'}
        }
        if(state == '已预定'){
          return { 'color' : '#fff','background-color' : '#6a38f2'}
        }
      }
    },
    getCountByRoomnums(roomnum){
      this.getCountByRoomnums(roomnum).then((res)=>{
        this.countroom=res
      })
    },
  },
  methods: {
    getRests(){
      getRest(this.userinfo).then(res=>{
        if(res.data!=null){
          console.log(res.data)
          getRoomsByType(res.data.data).then((res)=>{
            this.rooms=res.data.data
          })
        }
      })
    },
    changeRooms(){
      this.rooms=this.newrooms
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
    
    allcount(param1){
      if(this.checkbox.length>this.checkbox1.length){
        this.checkbox1=this.checkbox
       this.countmoney += param1
       console.log(this.checkbox1.length)
       console.log(this.countmoney)
       console.log(this.checkbox)
      }
      else{
        this.checkbox1=this.checkbox
        this.countmoney -=param1
      }
      
    },
    roomState(value){
      if(value=='空房'||value=='已预定'){this.stayButton=true}
      else{this.stayButton=false}
    },
     // 显示编辑界面
     handleEdit() {
      this.dialogStatus = '入住'
      this.dialogFormVisible = true
      // this.editForm = Object.assign({}, row)
    },
    // 显示编辑界面
    handlePre() {
      this.dialogStatus = '预定'
      this.dialogFormVisible1 = true
      // this.editForm = Object.assign({}, row)
    },
    // 显示编辑界面
    handleTeamAdd() {
      this.dialogStatus = '多房间入住'
      this.dialogFormVisible2 = true
      // this.editForm = Object.assign({}, row)
    },
    getRooms() {
      const para = {
        page: this.page,
        pageSize: this.pageSize,
      }
      getUserListPage(para).then(res => {
        // this.total = res.data.total
        this.rooms = res.data.data
        this.newrooms=res.data.data
        console.log(this.rooms)
      }) 
    },
    editFormOut(val){
      this.dialogFormVisible=val
    },
    preFormOut(val1){
      this.dialogFormVisible1=val1
    },
    addTeamFormOut(val2){
      this.dialogFormVisible2=val2
    },
    // 判断房间类型
    roomsType(type){
        if(type=='大床房'){
         return this.url=require('@/assets/images/rooms/1大床房.jpg')
        }if(type=='单人房'){return this.url=require('@/assets/images/rooms/1-1.jpg')}
        if(type=='双人房'){return this.url=require('@/assets/images/rooms/2-1.jpg')}
        if(type=='情侣房'){return this.url=require('@/assets/images/rooms/3-1.jpg')}
        if(type=='电竞房'){return this.url=require('@/assets/images/rooms/4-1.jpg')}
        if(type=='总统房'){return this.url=require('@/assets/images/rooms/5-1.jpg')}     
    },
    roomClick(room){
      this.room=room
      this.state = room.roomState.state
      this.type = room.roomType.type
    },
    getInfos(){
      if(getUsername()){
        const user = getToken(getUsername())
        getInfo(JSON.parse(user)).then(res=>{
        this.userinfo=res.data.data.allid
      })
      }
      else{
        reject('error')
      }
    }
  }
}
</script>

<style lang="scss">
.el-checkbox{
  color: aliceblue;
}
.item-text{
  text-align: center;
}


.el-drawer.ltr, .el-drawer.rtl, .el-drawer__container{
    top: 0;
    bottom: 0;
    height: 100%;
    box-shadow: none;

}
::v-deep .el-drawer {
  background-color: #ffffff !important;
}
.checkbox{
  top: 0;
}
</style>
<style scoped>
.topfilter{
  padding-top: 10px;
    display: inline-block;
}
</style>