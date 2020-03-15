const routes = [
    { path: '/product/list', component: productList },
    { path: '/customer/list', component: customerList },
    { path: '/order/list', component: orderList },
    { path: '/product/create', component: productCreate },
    { path: '/product/update/:productId', component: productUpdate },
    { path: '/product/show/:productId', component: productShow },
    { path: '/customer/show/:customerId', component: customerShow }
  ];

  const router = new VueRouter({
      routes: routes
  });