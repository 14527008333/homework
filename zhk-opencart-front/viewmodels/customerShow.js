var app = new Vue({
    el: '#app',
    data: {
        customerId:null,
        userName:null,
        realName:null ,
        avatarUrl:null ,
        mobile:null ,
        email:null,
        status:null,
        createTime:null,
        newsSubscribed:null,
        rewordPoints:null,
        defaultAddress:null ,
        statuses: [{
            value: 0,
            label: '禁用'
          }, {
            value: 1,
            label: '启用'
          }],
    },
    mounted() {
        var url = new URL(location.href);
        var a= url.searchParams.get("customerId")
        this.customerId=a;
        alert(this.customerId);
        this.getCustomerById();
    },
    methods:{
        getCustomerById(){
            axios.get('http://localhost:8081/customer/show', {
                params: {
                    customerId: this.customerId
                }
            })
                .then(function (response) {
                    var customer=response.data;
                    app.userName=customer.userName;
                    app.realName=customer.realName;
                    app.avatarUrl=customer.avatarUrl;
                    app.mobile=customer.mobil;
                    app.email=customer.email;
                    app.status=customer.status;
                    app.newsSubscribed=customer.newsSubscribed;
                    app.rewordPoints=customer.rewordPoints;
                    app.defaultAddress=customer.defaultAddress;
                })
                .catch(function () {
                    console.error("error");
                });
        } 
    }
    
})