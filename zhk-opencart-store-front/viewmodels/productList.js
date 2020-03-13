var app = new Vue({
    el: '#app',
    data: {
        pageList:'',
        productName:null ,
        price:null,
        pageNum:1
        
    },
    mounted() {
        console.log('view mounted');
        this.getProductList();
    },
    methods: {
        getProductList() {
            axios.get('/product/list', {
                params: {
                    productName: this.productName,
                    price: this.price,
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
            location.href = 'http://127.0.0.1:10010/productShow.html?productId=' + productId;
        }
        
    }
    
})