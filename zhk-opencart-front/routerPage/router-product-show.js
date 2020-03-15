const productShow = {
     template: `
          <div id="app">
          <el-input v-model="productName"  :disabled="true" placeholder="请输入商品名称"></el-input>
          <el-input v-model="productCode"  :disabled="true" placeholder="请输入商品代码"></el-input>
          <el-input v-model="price"  :disabled="true" placeholder="请输入商品价格"></el-input>
          <el-input v-model="quantity"  :disabled="true" placeholder="请输入商品库存"></el-input>
          <el-input v-model="discount"  :disabled="true" placeholder="请输入商品折扣"></el-input>
          <el-input v-model="rewordPoints"  :disabled="true" placeholder="请输入购买商品所得积分"></el-input>
          <el-input v-model="productAbstract"  :disabled="true" placeholder="商品简介"></el-input>
          <textarea id='descriptionText'  :disabled="true" >{{description}}</textarea>
          <el-select v-model="status"  :disabled="true" placeholder="请选择">
               <el-option
               v-for="item in selectstatus"
               :key="item.value"
               :label="item.label"
               :value="item.value">
               </el-option>
               </el-select><br>
          
          上传后主图：<br><el-link type="primary">{{imageUrl}}</el-link><br>

          上传后其他图：
          <div v-for="item in otherImageUrls">
               <el-link type="primary">{{item}}</el-link>
          </div><br>
          
     </div>
     `,
     data(){
          return{
               productId:null,
               productName:null ,
               productCode:null ,
               productAbstract:null ,
               price:null,
               quantity:null,
               status:null,
               imageUrl:null,
               discount:null ,
               description:null ,
               rewordPoints:null ,
               otherImageUrls:[] ,
               selectstatus: [{
                   value: 0,
                   label: '未审核'
                 }, {
                   value: 1,
                   label: '上架'
                 }, {
                   value: 2,
                   label: '下架'
                 }]
          }
      },
      mounted() {
          tinymce.init({
              selector: '#descriptionText',
            });
          this.productId=this.$route.params.productId;
          this.getProductById(this.productId);
      },
      methods:{
          getProductById(val){
              axios.get('http://localhost:8081/product/show', {
                  params: {
                      productId: this.productId
                  }
              })
                  .then( (response)=> {
                      console.log(response);
                      this.productName=response.data.productName;
                      this.productCode=response.data.productCode;
                      this.productAbstract=response.data.productAbstract;
                      this.price=response.data.price;
                      this.quantity=response.data.quantity;
                      this.status=response.data.status;
                      this.imageUrl=response.data.imageUrl;
                      this.discount=response.data.discount;
                      this.description=response.data.description;
                      this.rewordPoints=response.data.rewordPoints;
                      this.otherImageUrls=response.data.otherImageUrls;
                  })
                  .catch( ()=> {
                      console.error("error");
                  });
          } 
      }
    
  
}