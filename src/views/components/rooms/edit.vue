<template>
  <div>
<!-- Form -->
<el-dialog title="修改" :visible="clicks" @close="back" >
  <el-form :model="editForms">
    <el-form-item label="房间号" >
      <el-input v-model="editForms.roomnum" ></el-input>
    </el-form-item>
    <el-form-item label="房间状态" >
      <el-select v-model="editForms.roomState.id" placeholder="房间状态">
          <el-option v-for="item in roomStateList" :key="item.id" :label="item.state" :value="item.id">
          </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="房间类型" >
      <el-select v-model="editForms.roomType.id" placeholder="房间类型">
          <el-option v-for="item in roomTypeList" :key="item.id" :label="item.type" :value="item.id">
          </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="元/小时" >
      <el-input v-model="editForms.timemoney" ></el-input>
    </el-form-item>
    <el-form-item label="元/天" >
      <el-input v-model="editForms.daymoney" ></el-input>
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
    editRooms
} from '@/api/roomsTable'

  export default {
    props:{
      editForms:Object,
      clicks:Boolean,
      roomStateList:Array,
      roomTypeList:Array
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
        this.roomsEditfrom = this.editForms
        console.log(this.roomsEditfrom)
        editRooms(this.roomsEditfrom).then(res=>{
          if(res.data.flag){
          this.$message({
              message: '修改成功',
              type: 'success'
            })
            // location.reload()
            // this.back()
        }else{
          this.$message({
              message: '修改失败',
              type: 'error'
            })
            // this.back()
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
