var app = new Vue({
    el: '#app',
    data: {
        userName:null,
        password:null,
    },
    mounted() {
        console.log('view mounted');
  
    },
    methods:{
        loginClick(){
            axios.get('/customer/login', {
                params: {
                    userName:this.userName,
                    password:this.password,
                }
            })
                .then(function (response) {
                    console.log(response);
                    
                   var token= response.data.token;
                   var expireTimestamp= response.data.expireTimestamp;
                    localStorage["jcartToken"]=token;
                    localStorage["expireTimestamp"]=expireTimestamp;
                    alert("登录成功");
                })
                .catch(function (error) {
                    console.error(error);
                });
        }
    }
})