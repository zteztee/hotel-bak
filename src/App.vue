<template>
  <div id="app">
    <!--登入动画-->
    <transition name="rotate-fall">
    <router-view  class="app-router-view"></router-view>
    </transition>
  </div>
</template>

<script>
import 'vue-transition.css'

export default {

  name: 'App',
      //App.vue的created():
    computed:{
          
      },
      
      
  //   created() {
  //       console.log(this.username)
  //   //页面刷新前将vuex里的状态数据保存到sessionStorage
  //   window.addEventListener(
  //   	"beforeunload",()=>{
  //       this.$store.state.user.username = this.username
  //   });
  // }
  //App.vue的created():
  
 //App.vue的created():
 created() {
    // //在页面加载从本地读取状态数据并写入vuex
    // if (sessionStorage.getItem("store")) {
    //   console.log(Object.assign(
    //       {},
    //       this.$store.state,
    //       JSON.parse(sessionStorage.getItem("store"))
    //     ))
    //   this.$store.replaceState(
    //     Object.assign(
    //       {},
    //       this.$store.state,
    //       JSON.parse(sessionStorage.getItem("store"))
    //     )
    //   );
    //   store1.username=this.$store.state.user.username.name
      
    // }

    // //页面刷新前将vuex里的状态数据保存到sessionStorage
    // window.addEventListener(
    // 	"beforeunload",()=>{
    // 	sessionStorage.setItem("store",JSON.stringify(this.$store.state));
    // });
  }


      
}

</script>
<style>
  .app .app-router-view {
    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    overflow: hidden;
    backface-visibility: hidden;
    transform: translate3d(0, 0, 0);
    transform-style: preserve-3d;
    visibility: visible;
  }
</style>
