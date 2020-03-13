var app = new Vue({
    el: '#app',
    data: {
        myShoppingCart:[],
        buyQuantity:1,
        productId:null,
        productName:null ,
        productCode:null ,
        productAbstract:null ,
        price:null,
        quantity:null,
        imageUrl:null,
        discount:null ,
        description:null ,
        rewordPoints:null ,
        otherImageUrls:[] 
    },
    mounted() {
      var url = new URL(location.href);
      var a= url.searchParams.get("productId")
      this.productId=a;
      var myShoppingCartJson = localStorage['shppingCartJson'];
      this.myShoppingCart=myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
      this.getProductById();
    },
    methods: {
      getProductById(){
        axios.get('/product/show', {
          params: {
            productId: this.productId
          }
      })
          .then(function (response) {
              var product=response.data;
              app.productId=product.productId;
              app.productName=product.productName;
              app.productCode=product.productCode;

              app.productAbstract=product.productAbstract;
              app.price=product.price;

              app.quantity=product.quantity;
              app.imageUrl=product.imageUrl;
              app.discount=product.discount;
              app.description=product.description;
              app.rewordPoints=product.rewordPoints;
              app.otherImageUrls=product.otherImageUrls;
          })
          .catch(function () {
              console.error("error");
          });
      },
        addShoppingCart(){
           var getshoppingProductById= this.myShoppingCart.find(shop => shop.productId===this.productId)
           if(getshoppingProductById){
            var startNum=parseInt(getshoppingProductById.num);
            var addNum=parseInt(this.buyQuantity);
            getshoppingProductById.num=startNum+addNum;
           }else{
                shoppingCartProduct = {
                  "productId": this.productId,
                  "productCode": this.productCode,
                  "productName": this.productName,
                  "mainPicUrl": this.imageUrl,
                  "unitPrice": this.price,
                  "num": this.buyQuantity
              };
              this.myShoppingCart.push(shoppingCartProduct);
           }
          localStorage['shppingCartJson'] = JSON.stringify(this.myShoppingCart);
        this.$message.success('添加购物车成功');
        }
    }
   

    
})