var app = new Vue({
    el: '#app',
    data: {
        myShoppingCart: []
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
        }
    }
})