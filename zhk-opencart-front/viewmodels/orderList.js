var app = new Vue({
    el: '#app',
    data: {
        pageList:'',
        customerName:null ,
        status:null,
        totalPrice:null,
        createTime:null,
        updateTime:null,
        pageNum:1,
        selectstatus: [{
            value: '0',
            label: '待处理'
          }, {
            value: '1',
            label: '处理中'
          }, {
            value: '2',
            label: '待发货'
          }, {
            value: '3',
            label: '已发货'
          }, {
            value: '4',
            label: '待签收'
          }, {
            value: '5',
            label: '已签收'
          }, {
            value: '6',
            label: '待支付'
          }, {
            value: '7',
            label: '已支付'
          }, {
            value: '8',
            label: '取消'
          }, {
            value: '9',
            label: '拒绝'
          }, {
            value: '10',
            label: '完成'
          }, {
            value: '11',
            label: '待评价'
          }, {
            value: '12',
            label: '已评价'
          }]
        
    },
    mounted() {
        console.log('view mounted');
        this.getOrderList();
    },
    methods: {
        getOrderList() {
            axios.get('http://localhost:8081/order/list', {
                params: {
                    customerName: this.customerName,
                    status: this.status,
                    totalPrice: this.totalPrice,
                    createTime: this.createTime?this.createTime.getTime():'',
                    updateTime: this.updateTime?this.updateTime.getTime():'',
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
            this.getOrderList();
        },
        queryOrderClick(){
            console.log("query click");
            this.getOrderList();
        },
        orderShow(productId){
            console.log("show "+productId);
            location.href = 'http://127.0.0.1:8080/productShow.html?productId=' + productId;
        }
        
    }
    
})