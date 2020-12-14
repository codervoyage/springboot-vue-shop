import Vue from 'vue'
import VueRouter from 'vue-router'
const ShipAddress = () => import('../components/user/ShipAddress.vue')
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
const GoodsShow = () => import('../components/goods/Goods_list')
const GoodsAdd = () => import('../components/goods/Goods_add')
const Comment = () => import('../components/goods/Comment')

Vue.use(VueRouter)

const routes = [
  { path: '/' , redirect:'/login' },
  { path:'/login',component:Login},
  { path:'/home',component:Home,
    redirect: '/dashboard',
    /*子路由*/
    children:[
        /*路径*/
      {path: '/dashboard',component:Welcome,name:'首页'},
      {path: '/user/address',component:ShipAddress,name: '收货地址'},
      {path: '/user/user',component:User,name: '会员管理'},
      {path: '/user/history',component:History,name: '搜索历史'},
      {path: '/profile/password',component:Test,name: '修改信息'},
      {path: '/user/feedback',component:Feedback,name: '意见反馈'},
      {path: '/mall/brand',component:Brand,name: '品牌制造商'},
      {path: '/mall/region',component:Area,name: '行政区域'},
      {path: '/mall/category',component:Category,name: '商品类目'},
      {path: '/goods/list',component:GoodsShow,name: '商品列表'},
      {path: '/goods/create',component:GoodsAdd,name: '商品上架'},
      {path: '/goods/comment',component:Comment,name: '商品评论'}
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