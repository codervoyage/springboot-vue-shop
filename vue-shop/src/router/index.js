import Vue from 'vue'
import VueRouter from 'vue-router'
const Login = () => import('../components/Login')
const Home = () => import('../components/Home')
const Welcome = () => import('../components/Welcome')
const User = () => import('../components/user/User')
const History = () => import('../components/user/History')
const Test = () => import('../components/user/Test')
Vue.use(VueRouter)

const routes = [
  { path: '/' , redirect:'/login' },
  { path:'/login',component:Login},
  { path:'/home',component:Home,
    redirect: '/dashboard',
    /*子路由*/
    children:[
        /*路径*/
      {path:'/dashboard',component:Welcome},
      {path: '/user/user',component:User},
      {path: '/user/history',component:History},
      {path: '/profile/password',component:Test}
    ]
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router