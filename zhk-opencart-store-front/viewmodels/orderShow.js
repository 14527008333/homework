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
        orderHistories:[]
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