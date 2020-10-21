import Vue from 'vue'
import VueRouter from 'vue-router'
import ShipAddress from "@/components/user/ShipAddress";
const Login = () => import('../components/Login')
const Home = () => import('../components/Home')
const Welcome = () => import('../components/Welcome')
const User = () => import('../components/user/User')
const History = () => import('../components/user/History')
const Test = () => import('../components/user/Test')
const Feedback = () => import('../components/user/Feedback')
const Area = () => import('../components/mall/Area')
const Brand = () => import('../components/mall/Brand')
const Category = () => import('../components/mall/Category')
Vue.use(VueRouter)

const routes = [
  { path: '/' , redirect:'/login' },
  { path:'/login',component:Login},
  { path:'/home',component:Home,
    redirect: '/dashboard',
    /*子路由*/
    children:[
        /*路径*/
      {path: '/dashboard',component:Welcome},
      {path: '/user/user',component:User},
      {path: '/user/address',component:ShipAddress},
      {path:'/dashboard',component:Welcome},
      {path: '/user/user',component:User},
      {path: '/user/history',component:History},
      {path: '/profile/password',component:Test},
      {path: '/user/feedback',component:Feedback},
      {path: '/mall/brand',component:Brand},
      {path: '/mall/region',component:Area},
      {path: '/mall/category',component:Category}
    ]
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})
/* 挂载路由导航守卫 */
/*router.beforeEach(((to, from, next) => {
  // to 代表要访问的路径
  // from 代表从哪个路径跳转而来
  // next（）代表放行
  // next（‘/’）代表强制跳转到xx路径
  if(to.path === '/login') return next()
  const tokenItem = window.sessionStorage.getItem('xxx')
  if(!tokenItem) return next('/login')
  next()
}))*/
export default router