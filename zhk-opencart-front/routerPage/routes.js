const routes = [
    { path: '/product/list', component: productList },
    { path: '/customer/list', component: customerList }
  ];

  const router = new VueRouter({
      routes: routes
  });