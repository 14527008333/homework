var app = new Vue({
    el: '#app',
    data: {
        myShoppingCart: [],
        shipMethod:null,
        shipAddressId:null,
        invoiceAddressId:null,
        payMethod:null,
        comment:null,
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
        addresses:[]
    },
    computed:{
            totalPrice(){
                var everyProductTotalPrice = this.myShoppingCart.map(shoppingProduct =>{
                    return shoppingProduct.num*shoppingProduct.unitPrice
                });
                var shoppingCartTotalprice= everyProductTotalPrice.reduce((a,b)=>a+b,0);
               var totalPrice= shoppingCartTotalprice.toFixed(2)
                return totalPrice;
            },
            orderProducts(){
               var orderProduct= this.myShoppingCart.map(shoppingCart=>{
                    var object= new Object();
                    object.productId=shoppingCart.productId;
                    object.quantity=shoppingCart.num;
                    return object;
                })
                return orderProduct;
            }
    },
    mounted() {
        console.log('view mounted');

        var myShoppingCartJson = localStorage['shppingCartJson'];
        this.myShoppingCart=myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
      this.getaddressByCustomerId();
    },
    
    methods: {
        getaddressByCustomerId(){
            axios.get('/address/list', {
                params: {
                  productId: this.productId
                }
            })
                .then(function (response) {
                    var address=response.data;
                    app.addresses=address;
                })
                .catch(function () {
                    console.error("error");
                });
        },
        submitOrder(){
            axios.post('/order/checkout', {
                shipMethod: this.shipMethod,
                shipAddressId: this.shipAddressId,
                invoiceAddressId: this.invoiceAddressId,
                payMethod: this.payMethod,
                comment: this.comment,
                orderProducts: this.orderProducts,            
        })
            .then(function (response) {
                console.log("订单提交成功");
            })
            .catch(function (error) {
                console.log("订单提交失败");
            });
        },
        deleteShopping(val){
            if (confirm('确定删除？')) {
                this.myShoppingCart.splice(val, 1);
                localStorage['shppingCartJson'] = JSON.stringify(this.myShoppingCart);
                this.$message.success('删除购物车成功');
            }
        },
        updateNum(index,upDateNum){

            app.myShoppingCart[index].num=upDateNum;
            localStorage['shppingCartJson'] = JSON.stringify(this.myShoppingCart);
            this.$message.success('修改成功');

        }
    }
})