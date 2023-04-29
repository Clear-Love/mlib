import {createRouter, createWebHistory} from 'vue-router'
import {userStore} from "@/stores";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'login',
            component: () => import('@/views/homeView.vue'),
            children: [
                {
                    path: '',
                    name: 'login-page',
                    component: () => import('@/components/welcome/loginPage.vue'),
                },
                {
                    path: 'register',
                    name: 'register-page',
                    component: () => import('@/components/welcome/registerPage.vue')
                },
                {
                    path: 'forget',
                    name: 'forget-page',
                    component: () => import('@/components/welcome/forgetPage.vue')
                }
            ]
        }, {
            path: '/index',
            name: 'index',
            component: () => import('@/views/indexView.vue')
        }
    ]
})

router.beforeEach((to, form, next) => {
    const store = userStore()
    if(store.auth.config != null && to.name.startsWith('/login') || to.matched.length === 0) {
        next('/index')
    }else if(store.auth.config == null && to.fullPath.startsWith('/index')) {
        next('/')
    }else {
        next()
    }
})

export default router
