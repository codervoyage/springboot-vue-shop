import Vue from 'vue'
import VueRouter from 'vue-router'
const Login = () => import('../components/Login')
const Home = () => import('../components/Home')

Vue.use(VueRouter)

const routes = [
  { path: '/' , redirect:'/login' },
  { path:'/login',component:Login},
  { path:'/home',component:Home}
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router