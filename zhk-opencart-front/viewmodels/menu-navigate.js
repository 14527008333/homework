var app = new Vue({
    router: router,
    el: '#app',
    data: {
        menuArray:[
                { name:"客户管理",index:"1",icon:"el-icon-user-solid",
                  submenu:[
                        {name:"客户列表",index:"1-1",route:"/customer/list"}
                    ]
                },
                { name:"商品管理",index:"2",icon:"el-icon-s-shop",
                  submenu:[
                        {name:"商品列表",index:"2-1",route:"/product/list"}
                    ]
                },
                { name:"订单管理",index:"3",icon:"el-icon-s-order",
                  submenu:[
                        {name:"订单列表",index:"3-1",route:"/order/list"}
                    ]
                },
                { name:"退货管理",index:"4",icon:"el-icon-error",
                  submenu:[
                        {name:"退货列表",index:"4-1",route:"/return/list"}
                    ]
                },
                { name:"管理员管理",index:"5",icon:"el-icon-s-custom",
                  submenu:[
                        {name:"管理员列表",index:"5-1",route:"/administrator/list"}
                    ]
                },
        ]   
    },
    mounted() {
        
    },
    methods:{
       
    }
})