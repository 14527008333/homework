const routes = [
    { path: '/product/list', component: productList },
    { path: '/customer/list', component: customerList },
    { path: '/order/list', component: orderList },
    { path: '/product/create', component: productCreate },
    { path: '/product/update/:productId', component: productUpdate }
  ];

  const router = new VueRouter({
      routes: routes
  });