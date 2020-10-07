import {createRouter, createWebHistory} from 'vue-router'

const Login = () => import('../views/Login')
const routes = [
    {path: '/', redirect: '/login'},
    {path: '/login', component: Login}
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
