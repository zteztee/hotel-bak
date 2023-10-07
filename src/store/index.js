import Vue from 'vue'
import Vuex from 'vuex'
import VueXAlong  from 'vuex-along'
import app from './modules/app'
import user from './modules/user'
import common from './modules/common'
import getters from './getters'
import fullScreen from './modules/fullScreen'
import permission from './modules/permission'
import tagsView from './modules/tagsView'
import errorLog from './modules/errorLog'
Vue.use(Vuex)

const store = new Vuex.Store({
  

  modules: {
    app,
    user,
    common,
    fullScreen,
    permission,
    tagsView,
    errorLog,
  },
//   plugins: [VueXAlong({
//     name: 'store',     //存放在localStroage或者sessionStroage 中的名字
//     local: false,      //是否存放在local中  false 不存放 如果存放按照下面session的配置
//     session: { list: [], isFilter: true } //如果值不为false 那么可以传递对象 其中 当isFilter设置为true时， list 数组中的值就会被过滤调,这些值不会存放在seesion或者local中
// })],
  getters,
})

export default store
