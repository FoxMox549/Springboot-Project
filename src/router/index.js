
import VueRouter from 'vue-router';

const routes = [
    {
        path:'/',
        name:'login',
        component:() => import('../components/LoginComponent.vue'),
    },
    {
        path:'/Index',
        name:'login',
        component:() => import('../components/Index.vue'),
    }
]

const router = new VueRouter({
    mode: 'history',
    routes,
})

export default router;