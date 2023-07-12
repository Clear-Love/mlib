// Composables
import { createRouter, createWebHistory } from 'vue-router'
import authRoutes from "@/router/auth.routes";
import mainRouters from "@/router/main.routers";

const routes = [
  {
    path: "/",
    redirect: "/auth/login",
    meta: {},
  } as any,
  {
    path: "/:pathMatch(.*)*",
    name: "error",
    component: () =>
      import(/* webpackChunkName: "error" */ "@/views/errors/NotFoundPage.vue"),
  },
  ...authRoutes,
  ...mainRouters,
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { top: 0 };
    }
  },
})

export default router
