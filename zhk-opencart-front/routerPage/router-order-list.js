const orderList = {
     template: `
     <div id="app">
      <el-input placeholder="请输入用户名称" v-model="customerName"></el-input>
      <el-input placeholder="请输入订单总价" v-model="totalPrice" ></el-input>
      <el-input placeholder="请选择订单创建时间" v-model="createTime" type="date" ></el-input>
      <el-input placeholder="请选择订单修改时间" v-model="updateTime" type="date" ></el-input>
      <el-select v-model="status" placeholder="请选择订单状态">
        <el-option
          v-for="item in selectstatus"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select><br>
      <el-button type="primary" @click="queryOrderClick" round>查询</el-button>
        <template>
            <el-table
              :data="pageList.list"
              stripe
              style="width: 100%">
              <el-table-column
                prop="customerName"
                label="客户名称"
                width="180">
              </el-table-column>
              <el-table-column
                prop="status"
                label="订单状态"
                width="180">
              </el-table-column>
              <el-table-column
                prop="totalPrice"
                label="总价">
              </el-table-column>
              <el-table-column
                prop="rewordPoints"
                label="所得积分">
              </el-table-column>
              <el-table-column
                prop=""
                label="创建时间">
                <template slot-scope="scope">
                  {{(new Date(scope.row.createTimetamp)).toLocaleString()}}
                </template>
              </el-table-column>
              <el-table-column
                prop=""
                label="修改时间">
                <template slot-scope="scope">
                  {{(new Date(scope.row.updateTimetamp)).toLocaleString()}}
                </template>
              </el-table-column>
              <el-table-column
                fixed="right"
                label="操作">
                <template slot-scope="scope">
                  <el-button  icon="el-icon-view" @click="orderShow(scope.row.orderId)" circle></el-button>
                </template>
              </el-table-column>
            </el-table>
          </template>
          <el-pagination layout="prev, pager, next" :total="pageList.total" :page-size="pageList.pageSize"
            @current-change="pageClick">
        </el-pagination>
    </div>
     `,
     data() {
          return{
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
          }       
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
                  .then( (response)=> {
                      console.log(response);
                      this.pageList = response.data;
                  })
                  .catch( (error)=> {
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
          orderShow(orderId){
              console.log("show "+orderId);
              location.href = 'http://127.0.0.1:8080/orderShow.html?orderId=' + orderId;
          }
          
      }
    
  
}