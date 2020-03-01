var app = new Vue({
    el: '#app',
    data: {
        pageList:'',
        productName:null ,
        price:null,
        quantity:null,
        status:null,
        pageNum:1,
        selectstatus: [{
            value: '0',
            label: '未审核'
          }, {
            value: '1',
            label: '上架'
          }, {
            value: '2',
            label: '下架'
          }]
        
    },
    mounted() {
        console.log('view mounted');
        this.getProductList();
    },
    methods: {
        getProductList() {
            axios.get('http://localhost:8081/product/list', {
                params: {
                    productName: this.productName,
                    price: this.price,
                    quantity: this.quantity,
                    status: this.status,
                    pageNum: this.pageNum
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.pageList = response.data;
                })
                .catch(function (error) {
                    console.error(error);
                });
            
        },
        pageClick(val){
            this.pageNum=val;
            this.getProductList();
        },
        queryProductClick(){
            console.log("query click");
            this.getProductList();
        },
        productShow(productId){
            console.log("show "+productId);
            location.href = 'http://127.0.0.1:8080/demo.html?productId=' + productId;
        },
        productupdate(productId){
            console.log("update "+productId)
            location.href = 'http://127.0.0.1:8080/productUpdate.html?productId=' + productId;
        },
        insertProductClick(){
            console.log("insert product")
            location.href = 'http://127.0.0.1:8080/productCreate.html';
        }
        
    }
    
})