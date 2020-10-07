import { createApp } from 'vue'
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Axios from 'axios'
createApp(App).use(router).mount('#app')

Axios.defaults.baseURL = 'http://localhost:8080/'

Vue.prototype.$http = Axios

