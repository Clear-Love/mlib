// Composables
import { createRouter, createWebHistory } from 'vue-router'
import authRoutes from "@/router/auth.routes";

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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
