//Vue全局组件
import Vue from 'vue'
import App from './App.vue'
//导入路由
import router from './router'
//加载elementui.js文件
import './plugins/ui'
//加载全局css文件
import './assets/css/global.css'
//导入axios
import Axios from 'axios'
//导入加载动画NProgress插件和引入css
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
Vue.use(NProgress)
//导入vuex
import store from './store/index'
// 在全局定义了一个时间过滤器组件
Vue.filter('dateFormat', timeStamp => {
  if (!timeStamp) return ''
  const time = new Date(timeStamp)
  const y = time.getFullYear()
  const m = (time.getMonth() + 1 + '').padStart(2, '0')
  const d = (time.getDate() + '').padStart(2, '0')
  const hh = (time.getHours() + '').padStart(2, '0')
  const mm = (time.getMinutes() + '').padStart(2, '0')
  const ss = (time.getSeconds() + '').padStart(2, '0')
  return `${y}年${m}月${d}日`
})
Axios.defaults.baseURL = 'http://localhost:8081/'
Axios.interceptors.request.use(config =>{
  NProgress.start()
  return config
})
Axios.interceptors.response.use(config =>{
  NProgress.done()
  return config
})
Vue.prototype.$http = Axios
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
