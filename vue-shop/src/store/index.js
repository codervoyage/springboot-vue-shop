import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //保存所有打开的路由
    // {path:'/xxx',name:xxx}
    openTabs: [],
    //激活状态
    activeIndex: '/dashboard'
  },
  mutations: {
    //添加tabs
    add_tabs (state, data) {
      state.openTabs.push(data)
    },
    //删除tabs
    delete_tabs (state, path) {
      let index = 0
      for (let option of state.openTabs) {
        if (option.path == path) {
          break
        }
        index++
      }
      state.openTabs.splice(index, 1)
    },
    //设置当前激活的tabs
    set_active_tabs (state, index) {
      this.state.activeIndex = index
    }
  },
  actions: {}
})