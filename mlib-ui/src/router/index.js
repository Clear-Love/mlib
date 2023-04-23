import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('@/views/home.vue'),
      children: [
        {
          path: '',
          name: 'login-page',
          component: () => import('@/components/home/loginPage.vue'),
        },
        {
          path: 'register',
          name: 'register-page',
          component: () => import('@/components/home/registerPage.vue')
        }
      ]
    }, {
      path: '/index',
      name: 'index',
      component: () => import('@/views/index.vue')
    }
  ]
})

export default router
