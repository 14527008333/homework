var app = new Vue({
    el: '#app',
    data: {
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
        tinymce.init({
            selector: '#descriptionText',
          });
        var url = new URL(location.href);
        var a= url.searchParams.get("productId")
        this.productId=a;
        alert(a);
        
        this.getProductById(this.productId);
    },
    methods:{
        getProductById(val){
            axios.get('http://localhost:8081/product/show', {
                params: {
                    productId: this.productId
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.productName=response.data.productName;
                    app.productCode=response.data.productCode;
                    app.productAbstract=response.data.productAbstract;
                    app.price=response.data.price;
                    app.quantity=response.data.quantity;
                    app.status=response.data.status;
                    app.imageUrl=response.data.imageUrl;
                    app.discount=response.data.discount;
                    app.description=response.data.description;
                    app.rewordPoints=response.data.rewordPoints;
                    app.otherImageUrls=response.data.otherImageUrls;
                })
                .catch(function () {
                    console.error("error");
                });
        } 
    }
    
})