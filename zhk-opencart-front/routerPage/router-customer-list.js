const customerList = {
     template: `
     <div id="app">
      <el-input placeholder="请输入用户名" v-model="userName"></el-input>
      <el-input placeholder="请输入邮箱" v-model="email" ></el-input>
      <el-input placeholder="请选择创建时间" type="date" v-model="createTime" ></el-input>
      <el-select v-model="status" placeholder="请选择">
        <el-option
          v-for="item in selectstatus"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select><br>
      <el-button type="primary" @click="queryCustomerClick" round>查询</el-button>
        <template>
            <el-table
              :data="pageList.list"
              stripe
              style="width: 100%">
              <el-table-column
                prop="userName"
                label="用户名"
                width="180">
              </el-table-column>
              <el-table-column
                prop="realName"
                label="真实姓名">
              </el-table-column>
              <el-table-column
                prop="email"
                label="邮箱">
              </el-table-column>
              <el-table-column
                prop="status"
                label="状态">
              </el-table-column>
              <el-table-column
                prop="createTime"
                label="创建时间">
              </el-table-column>
              <el-table-column
                fixed="right"
                label="操作">
                <template slot-scope="scope">
                  <el-button  icon="el-icon-view" @click="customerShow(scope.row.customerId)" circle></el-button>
                  <el-button type="primary" @click="updateStatusClick(scope.row.customerId)">修改状态</el-button>
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
          }  
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
                  .then((response)=> {
                      console.log(response);
                      this.pageList = response.data;
                  })
                  .catch((error)=> {
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
              this.$router.push("/customer/show/"+customerId);
          },
          updateStatusClick(customerId){
              console.log("show "+customerId);
              location.href = 'http://127.0.0.1:8080/customerUpdateStatus.html?customerId=' + customerId;
          }
          
      }
    
  
}