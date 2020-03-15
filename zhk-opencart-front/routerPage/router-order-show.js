const orderShow = {
     template: `
     <div id="app">

      订单状态：{{orderStatus[status].label}}<br>
      总价格：{{totalPrice}}<br>
      总积分：{{rewordPoints}}<br>
      下单时间：{{new Date(createTimestamp).toLocaleString()}}<br>
      更新时间{{new Date(updateTimestamp).toLocaleString()}}<br>
      邮寄方式：{{selectshipMethod[shipMethod].label}}<br>
      邮寄地址：{{shipAddress}}<br>
      支付方式：{{selectpayMethod[payMethod].label}}<br>
      发票地址：{{invoiceAddress}}<br>
      备注：{{comment}}<br><br><br>
        订单详情展示：
        <template>
            <el-table
              :data="orderProducts"
              stripe
              style="width: 100%">
              <el-table-column
                prop="productCode"
                label="商品代码">
              </el-table-column>
              <el-table-column
                prop="productName"
                label="商品名称">
              </el-table-column>
              <el-table-column
                prop="quantity"
                label="购买数量">
              </el-table-column>
              <el-table-column
                prop="unitPrice"
                label="单价">
              </el-table-column>
              <el-table-column
                prop="totalPrice"
                label="总价">
              </el-table-column>
              <el-table-column
                prop="unitRewordPoints"
                label="单个积分">
              </el-table-column>
              <el-table-column
                prop="totalRewordPoints"
                label="总积分">
              </el-table-column>
            </el-table>
          </template>
          邮费:{{shipPrice}}<br>
          最终支付：{{totalPrice + shipPrice}}<br><br><br>
        订单历史状态：
        <template>
            <el-table
              :data="orderHistories"
              stripe
              style="width: 100%">
              <el-table-column
                prop="time"
                label="历史订单创建时间">
                  <template slot-scope="scope">
                    {{(new Date(scope.row.time)).toLocaleString()}}
                  </template>
              </el-table-column>
              <el-table-column
                prop="orderStatus"
                label="订单状态">
                  <template slot-scope="scope">
                    {{orderStatus[scope.row.orderStatus].label}}
                  </template>
              </el-table-column>
              <el-table-column
                prop="comment"
                label="备注">
              </el-table-column>
            </el-table>
          </template><br>
          订单历史更新：<br>
          <el-select v-model="orderHistoryStatus" placeholder="请选择订单状态">
            <el-option
              v-for="item in orderStatus"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select><br>
          <el-checkbox v-model="customerNotified">通知客户</el-checkbox><br>
          <el-input v-model="orderHistoryComment" type="textarea" placeholder="请输入备注"></el-input><br>
          <el-button type="primary" @click="submitOrderHistory" round>提交订单历史</el-button>
    </div>
     `,
     data() {
          return{
               orderId:null,
               status:null,
               totalPrice:null,
               rewordPoints:null,
               createTimestamp:null,
               updateTimestamp:null,
               shipMethod:null,
               shipAddress:null,
               shipPrice:null,
               payMethod:null,
               invoiceAddress:null,
               invoicePrice:null,
               comment:null,
               orderProducts:[],
               orderHistories:[],
               selectshipMethod:[
                   {value:0,label:"顺丰"},
                   {value:1,label:"韵达"},
                   {value:2,label:"百世"},
                   {value:3,label:"申通"},
                   {value:4,label:"中通"},
                   {value:5,label:"邮政"},
               ],
               selectpayMethod:[
                   {value:0,label:"微信"},
                   {value:1,label:"支付宝"},
                   {value:2,label:"信用卡"},
                   {value:3,label:"货到付款"},
               ],
                orderStatus: [{
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
                 }],
                 orderHistoryStatus:null,
                 orderHistoryComment:null,
                 customerNotified:false,
          }
      },
      mounted() {
          this.productId=this.$route.params.orderId;
          this.getOrderById();
          this.getOrderHistoryListById();
      },
      methods: {
          getOrderById() {
              axios.get('http://localhost:8081/order/show', {
                  params: {
                      orderId:this.orderId
                  }
              })
                  .then( (response)=> {
                      console.log(response);
                      var orderShow= response.data;
                      this.status=orderShow.status,
                      this.totalPrice=orderShow.totalPrice,
                      this.rewordPoints=orderShow.rewordPoints,
                      this.createTimestamp=orderShow.createTimestamp,
                      this.updateTimestamp=orderShow.updateTimestamp,
                      this.shipMethod=orderShow.shipMethod,
                      this.shipAddress=orderShow.shipAddress,
                      this.shipPrice=orderShow.shipPrice,
                      this.payMethod=orderShow.payMethod,
                      this.invoiceAddress=orderShow.invoiceAddress,
                      this.invoicePrice=orderShow.invoicePrice,
                      this.comment=orderShow.comment,
                      this.orderProducts=orderShow.orderProducts
                      
                  })
                  .catch(function (error) {
                      console.error(error);
                  });
              
          },
          getOrderHistoryListById() {
            axios.get('http://localhost:8081/orderHistory/list', {
                params: {
                    orderId:this.orderId
                }
            })
                .then( (response)=> {
                    console.log(response);
                    var orderHistoryList=response.data;
                    this.orderHistories=orderHistoryList;
                    
                })
                .catch( (error)=> {
                    console.error(error);
                });
            
        },
        submitOrderHistory(){
          console.log("submit orderHistory");
          this.createOrderHistory();
        },
        createOrderHistory() {
          axios.post('http://localhost:8081/orderHistory/create', {
                  orderId:this.orderId, 
                  orderStatus:this.orderHistoryStatus,
                  comment:this.orderHistoryComment,
                  customerNotified:this.customerNotified
          })
              .then( (response)=> {
                  console.log(response);
              })
              .catch( (error)=> {
                  console.error(error);
              });
          
      }
          
      }
    
  
}