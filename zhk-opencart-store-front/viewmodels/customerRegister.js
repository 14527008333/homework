var app = new Vue({
    el: '#app',
    data: {
        username:null ,
        realName:null ,
        email:null ,
        mobile:null,
        password:null,
        repassword:null,
        newsSubscribed:null,
    },
    methods:{
        customerRegisterClick(){

            if(this.password!=this.repassword){
                alert("密码不一致");
                return;
            }
            axios.post('/customer/register', {
                
                username: this.productName,
                realName: this.productCode,
                email: this.productAbstract,
                mobile: this.price,
                password: this.quantity,
                newsSubscribed: this.status              
            })
                .then(function (response) {
                    console.log("发布成功");
                })
                .catch(function (error) {
                    console.log("发布失败");
                });
        }
    }
    
})