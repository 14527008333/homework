var app = new Vue({
    el: '#app',
    data: {
        pageList:'',
        userName:null ,
        email:null,
        status:null,
        createTime:null,
        pageNum:1,
        selectstatus: [{
            value: '0',
            label: '禁用状态的用户'
          }, {
            value: '1',
            label: '启用状态的用户'
          }]
        
    },
    mounted() {
        console.log('view mounted');
        this.getCustomerList();
    },
    methods: {
        getCustomerList() {
            axios.get('http://localhost:8081/customer/list', {
                params: {
                    userName: this.userName,
                    email: this.email,
                    status: this.status,
                    createTime: this.createTime,
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
            this.getCustomerList();
        },
        queryCustomerClick(){
            console.log("query click");
            this.getCustomerList();
        },
        customerShow(customerId){
            console.log("show "+customerId);
            location.href = 'http://127.0.0.1:8080/customerShow.html?customerId=' + customerId;
        }
        
    }
    
})