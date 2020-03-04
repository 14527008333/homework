var app = new Vue({
    el: '#app',
    data: {
        myShoppingCart: []
    },
    computed:{
            totalPrice(){
                var everyProductTotalPrice = this.myShoppingCart.map(shoppingProduct =>{
                    return shoppingProduct.num*shoppingProduct.unitPrice
                });
                var shoppingCartTotalprice= everyProductTotalPrice.reduce((a,b)=>a+b,0);
               var totalPrice= shoppingCartTotalprice.toFixed(2)
                return totalPrice;
            }
    },
    mounted() {
        console.log('view mounted');

        var myShoppingCartJson = localStorage['shppingCartJson'];
        this.myShoppingCart=myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
      
    },
    methods: {
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