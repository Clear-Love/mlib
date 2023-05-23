export default [
  {
    path: '/auth/login',
    name: 'login',
    component: () => import('@/views/auth/loginPage.vue'),
    meta: {
      layout: "auth",
      title: "login",
    },
  },
  {
    path: '/auth/register',
    name: 'register',
    component: () => import('@/views/auth/registerPage.vue'),
    meta: {
      layout: "auth",
      title: "register",
    },
  },
  {
    path: '/auth/forget',
    component: () => import('@/views/auth/forgetPage.vue'),
    meta: {
      layout: "auth",
      title: "forget",
    },
  },
];
