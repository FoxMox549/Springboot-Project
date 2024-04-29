import VueRouter from 'vue-router';

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import('../components/LoginComponent.vue'),
    },
    {
        path: '/Index',
        name: 'login',
        component: () => import('../components/Index.vue'),
        children:[
            {
                path:'/Home',
                name:'home',
                meta:{
                    title:'Home Page' // second router for the navigator
                },
                component: () => import('../components/HomeComponent.vue'),
            },
            {
                path:'/Admin',
                name:'admin',
                meta:{
                    title:'Admin Manage' // second router for the navigator
                },
                component: () => import('../components/admin/AdminManage.vue'),
            },
            {
                path:'/User',
                name:'user',
                meta:{
                    title:'User Manage' // second router for the navigator
                },
                component: () => import('../components/user/UserManage.vue'),
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

/*export function resetRouter() {
    router.matcher = new VueRouter({
        mode:'history',
        routes: []
    }).matcher
}*/

const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}

export default router;