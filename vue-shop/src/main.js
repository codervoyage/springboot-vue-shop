import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element-ui'
import './assets/css/global.css'
import Axios from 'axios'
Axios.defaults.baseURL = 'http://localhost:8081/'
Vue.prototype.$http = Axios
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
