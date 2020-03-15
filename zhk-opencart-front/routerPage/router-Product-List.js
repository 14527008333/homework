const productList = {
     template: `
     <div id="app">
      <el-button icon="el-icon-plus"  @click="insertProductClick" circle></el-button>
      <el-input placeholder="请输入商品名称" v-model="productName"></el-input>
      <el-input placeholder="请输入价格" v-model="price" ></el-input>
      <el-input placeholder="请输入库存量" v-model="quantity" ></el-input>
      <el-select v-model="status" placeholder="请选择">
        <el-option
          v-for="item in selectstatus"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select><br>
      <el-button type="primary" @click="queryProductClick" round>查询</el-button>
        <template>
            <el-table
              :data="pageList.list"
              stripe
              style="width: 100%">
              <el-table-column
                prop="mainImageUrl"
                label="图片"
                width="180">
              </el-table-column>
              <el-table-column
                prop="productName"
                label="商品名称"
                width="180">
              </el-table-column>
              <el-table-column
                prop="price"
                label="价格">
              </el-table-column>
              <el-table-column
                prop="quantity"
                label="库存">
              </el-table-column>
              <el-table-column
                prop="status"
                label="状态">
              </el-table-column>
              <el-table-column
                fixed="right"
                label="操作">
                <template slot-scope="scope">
                  <el-button  icon="el-icon-view" @click="productShow(scope.row.productId)" circle></el-button>
                  <el-button  icon="el-icon-edit" @click="productupdate(scope.row.productId)" circle></el-button>
                </template>
              </el-table-column>
            </el-table>
          </template>
          <el-pagination layout="prev, pager, next" :total="pageList.total" :page-size="pageList.pageSize"
            @current-change="pageClick">
        </el-pagination>
    </div>
     `,
     data(){
          return{
               pageList:'',
               productName:null ,
               price:null,
               quantity:null,
               status:null,
               pageNum:1,
               selectstatus: [{
                   value: '0',
                   label: '未审核'
                 }, {
                   value: '1',
                   label: '上架'
                 }, {
                   value: '2',
                   label: '下架'
                 }]
          }
      },
      mounted() {
          console.log('view mounted');
          this.getProductList();
      },
      methods: {
          getProductList() {
              axios.get('http://localhost:8081/product/list', {
                  params: {
                      productName: this.productName,
                      price: this.price,
                      quantity: this.quantity,
                      status: this.status,
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
              this.getProductList();
          },
          queryProductClick(){
              console.log("query click");
              this.getProductList();
          },
          productShow(productId){
              console.log("show "+productId);
              location.href = 'http://127.0.0.1:8080/productShow.html?productId=' + productId;
          },
          productupdate(productId){
              console.log("update "+productId)
              location.href = 'http://127.0.0.1:8080/productUpdate.html?productId=' + productId;
          },
          insertProductClick(){
              console.log("insert product")
              location.href = 'http://127.0.0.1:8080/productCreate.html';
          }
          
      }
}
