var app = new Vue({
    el: '#app',
    data: {
        myShoppingCart:[],
        productId:null,
        productName:null ,
        productCode:null ,
        productAbstract:null ,
        price:null,
        quantity:null,
        status:null,
        imageUrl:null,
        discount:null ,
        description:null ,
        rewordPoints:null ,
        otherImageUrls:[] ,
        selectstatus: [{
            value: 0,
            label: '未审核'
          }, {
            value: 1,
            label: '上架'
          }, {
            value: 2,
            label: '下架'
          }],
    },
    mounted() {
        
    },
    methods: {
        addShoppingCart(){
            var myShoppingCartJson = localStorage['shppingCartJson'];
          this.myShoppingCart=myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
          cartProduct = {
            "productId": 5,
            "productCode": "sfg45",
            "productName": "sfhsdfbg",
            "mainPicUrl": "gnjghn.jpg",
            "unitPrice": 546.5,
            "num": 1
        };
          this.myShoppingCart.push(cartProduct);

          localStorage['shppingCartJson'] = JSON.stringify(this.myShoppingCart);
        this.$message.success('添加购物车成功');
        }
    }
   

    
})