export default [
  // data table
  {
    path: "/index",
    name: "home",
    component: () =>
      import(
        /* webpackChunkName: "ui-data-table" */ "@/views/main/Home.vue"
        ),
    meta: {
      requiresAuth: true,
      layout: "main",
      category: "MAIN",
      title: "Home",
    },
  },
  {
    path: "/like",
    name: "like",
    component: () =>
      import(/* webpackChunkName: "ui-colors" */ "@/views/main/Like.vue"),
    meta: {
      requiresAuth: true,
      layout: "main",
      category: "MAIN",
      title: "Like",
    },
  },
  {
    path: "/setting",
    name: "setting",
    component: () =>
      import(
        /* webpackChunkName: "ui-gradient" */ "@/views/main/Setting.vue"
        ),
    meta: {
      requiresAuth: true,
      layout: "main",
      category: "MAIN",
      title: "Setting",
    },
  },
  // lottie Animation
  {
    path: "/bookList",
    name: "bookList",
    component: () =>
      import(
        /* webpackChunkName: "ui-lottie-animation" */ "@/views/main/BookList.vue"
        ),
    meta: {
      requiresAuth: true,
      layout: "main",
      category: "MAIN",
      title: "BookList",
    },
  },
];
