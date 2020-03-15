const routes = [
    { path: '/product/list', component: productList },
    { path: '/customer/list', component: customerList },
    { path: '/order/list', component: orderList }
  ];

  const router = new VueRouter({
      routes: routes
  });