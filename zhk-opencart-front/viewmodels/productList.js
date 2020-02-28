var app = new Vue({
    el: '#app',
    data: {
        pageList:'',
        productName:null ,
        price:null,
        quantity:null,
        status:null,
        pageNum:1
    },
    mounted() {
        console.log('view mounted');
        this.getProductList();
    },
    methods: {
        getProductList() {
            axios.get('http://localhost:8080/product/list', {
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
        }
        
    }
    
})