const customerShow = {
     template: `
          <div id="app">
          客户用户名：{{userName}} <br>
          客户姓名：{{realName}} <br>
          客户头像：{{avatarUrl}} <br>
          手机：{{mobile}} <br>
          邮箱：{{email}} <br>
          状态：{{statuses[status].label}} <br>
          注册时间：{{(new Date(createTime)).toLocaleString()}} <br>
          订阅新闻：<span v-if="newsSubscribed">是</span><span v-else>否</span> <br>
          积分：{{rewordPoints}} <br>
          默认地址：{{defaultAddress}} <br>
          <br>
          
     </div>
     `,
     data() {
          return{
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
                 }]
          }
      },
      mounted() {
          this.customerId= this.$route.params.customerId;
          this.getCustomerById();
      },
      methods:{
          getCustomerById(){
              axios.get('http://localhost:8081/customer/show', {
                  params: {
                      customerId: this.customerId
                  }
              })
                  .then((response)=> {
                      var customer=response.data;
                      this.userName=customer.userName;
                      this.realName=customer.realName;
                      this.avatarUrl=customer.avatarUrl;
                      this.mobile=customer.mobil;
                      this.email=customer.email;
                      this.status=customer.status;
                      this.newsSubscribed=customer.newsSubscribed;
                      this.rewordPoints=customer.rewordPoints;
                      this.defaultAddress=customer.defaultAddress;
                  })
                  .catch( (error)=> {
                      console.error("error");
                  });
          } 
      }

    
  
}