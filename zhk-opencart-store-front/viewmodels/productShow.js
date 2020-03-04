var app = new Vue({
    el: '#app',
    data: {
        myShoppingCart:[],
        quantity:1,
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
      var myShoppingCartJson = localStorage['shppingCartJson'];
      this.myShoppingCart=myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
    },
    methods: {
        addShoppingCart(){
           var getshoppingProductById= this.myShoppingCart.find(shop => shop.productId===5)
           if(getshoppingProductById){
            var startNum=parseInt(getshoppingProductById.num);
            var addNum=parseInt(this.quantity);
            getshoppingProductById.num=startNum+addNum;
           }else{
                shoppingCartProduct = {
                  "productId": 5,
                  "productCode": "sfg45",
                  "productName": "sfhsdfbg",
                  "mainPicUrl": "gnjghn.jpg",
                  "unitPrice": 546.5,
                  "num": this.quantity
              };
              this.myShoppingCart.push(shoppingCartProduct);
           }
          localStorage['shppingCartJson'] = JSON.stringify(this.myShoppingCart);
        this.$message.success('添加购物车成功');
        }
    }
   

    
})