var app = new Vue({
    el: '#app',
    data: {
        orderId:null,
        status:null,
        totalPrice:null,
        rewordPoints:null,
        createTimestamp:null,
        updateTimestamp:null,
        shipMethod:null,
        shipAddress:null,
        shipPrice:null,
        payMethod:null,
        invoiceAddress:null,
        invoicePrice:null,
        comment:null,
        orderProducts:[],
        orderHistories:[],
        selectshipMethod:[
            {value:0,label:"顺丰"},
            {value:1,label:"韵达"},
            {value:2,label:"百世"},
            {value:3,label:"申通"},
            {value:4,label:"中通"},
            {value:5,label:"邮政"},
        ],
        selectpayMethod:[
            {value:0,label:"微信"},
            {value:1,label:"支付宝"},
            {value:2,label:"信用卡"},
            {value:3,label:"货到付款"},
        ],
         orderStatus: [{
            value: '0',
            label: '待处理'
          }, {
            value: '1',
            label: '处理中'
          }, {
            value: '2',
            label: '待发货'
          }, {
            value: '3',
            label: '已发货'
          }, {
            value: '4',
            label: '待签收'
          }, {
            value: '5',
            label: '已签收'
          }, {
            value: '6',
            label: '待支付'
          }, {
            value: '7',
            label: '已支付'
          }, {
            value: '8',
            label: '取消'
          }, {
            value: '9',
            label: '拒绝'
          }, {
            value: '10',
            label: '完成'
          }, {
            value: '11',
            label: '待评价'
          }, {
            value: '12',
            label: '已评价'
          }]
    },
    mounted() {
        var url = new URL(location.href);
        var a= url.searchParams.get("orderId");
         this.orderId=a;
        this.getOrderById();
    },
    methods: {
        getOrderById() {
            axios.get('/order/show', {
                params: {
                    orderId:this.orderId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var orderShow= response.data;
                    app.status=orderShow.status,
                    app.totalPrice=orderShow.totalPrice,
                    app.rewordPoints=orderShow.rewordPoints,
                    app.createTimestamp=orderShow.createTimestamp,
                    app.updateTimestamp=orderShow.updateTimestamp,
                    app.shipMethod=orderShow.shipMethod,
                    app.shipAddress=orderShow.shipAddress,
                    app.shipPrice=orderShow.shipPrice,
                    app.payMethod=orderShow.payMethod,
                    app.invoiceAddress=orderShow.invoiceAddress,
                    app.invoicePrice=orderShow.invoicePrice,
                    app.comment=orderShow.comment,
                    app.orderProducts=orderShow.orderProducts,
                    app.orderHistories=orderShow.orderHistories
                    
                })
                .catch(function (error) {
                    console.error(error);
                });
            
        }
        
    }
    
})