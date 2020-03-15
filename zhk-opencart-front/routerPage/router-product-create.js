const productCreate = {
     template: `
     <div id="app">
        <el-input v-model="productName" placeholder="请输入商品名称"></el-input>
        <el-input v-model="productCode" placeholder="请输入商品代码"></el-input>
        <el-input v-model="price" placeholder="请输入商品价格"></el-input>
        <el-input v-model="quantity" placeholder="请输入商品库存"></el-input>
        <el-input v-model="discount" placeholder="请输入商品折扣"></el-input>
        <el-input v-model="rewordPoints" placeholder="请输入购买商品所得积分"></el-input>
        <el-input v-model="productAbstract" placeholder="商品简介"></el-input>
        <textarea id='descriptionText' >{{description}}</textarea>
        <el-select v-model="status" placeholder="请选择">
            <el-option
              v-for="item in selectstatus"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <el-upload ref="mainUpload" action="" :http-request="mainUploadRequest" :limit="1" accept="image/*"
            :auto-upload="false" :on-change="onMainChange" >
            <el-button slot="trigger" size="small" type="primary">选取主图</el-button>
            <el-button size="small" type="success" @click="uploadMainClick">上传</el-button>
        </el-upload>
        上传后主图：<br><el-link type="primary">{{imageUrl}}</el-link>
        <el-upload ref="otherUpload" multiple action="" :http-request="otherUploadRequest" :limit="10" accept="image/*"
            :auto-upload="false" :on-change="onOtherChange" :on-remove="onOtherRemove">
            <el-button slot="trigger" size="small" type="primary">选取其他图片</el-button>
            <el-button size="small" type="success" @click="uploadOtherClick">上传</el-button>
        </el-upload>

        上传后其他图：
        <div v-for="item in otherImageUrls">
            <el-link type="primary">{{item}}</el-link>
        </div><br>
        
        <el-button type="primary" @click="productCreateClick">发布</el-button>
    </div>
     `,
     data(){
          return{
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
               mainFile:'',
               otherFiles:[],
               otherFileList:[],
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
      },
      methods:{
          onMainChange(val){
              console.log("choose image: "+val);
              this.mainFile=val.raw;
          },
          uploadMainClick(){
              console.log("click upload");
              this.mainUploadRequest();
          },
          mainUploadRequest(){
             var mainImage= new FormData();
             mainImage.append("multipartFile",this.mainFile);
             axios.post("http://localhost:8081/image/upload",mainImage,{
                 headers:{
                      'Content-Type': 'multipart/form-data'
                 }
             }).then((response)=>{
                  alert("MainImage 上传成功")
                  this.imageUrl=response.data;
             }).catch((error)=>{
               alert("MainImage 上传失败")
             })
             
          },
          onOtherChange(file, fileList){
              console.log(fileList);
              this.otherFiles=fileList;
          },
          onOtherRemove(file, fileList){
              console.log("choose image: "+fileList);
              this.otherFiles=fileList;
          },
          uploadOtherClick(){
              console.log("other image upload");
              this.otherUploadRequest();
          },
          otherUploadRequest(){
              this.otherFiles.forEach(files=>{
                  var otherImage= new FormData();
                  otherImage.append("multipartFile",files.raw);
                  axios.post("http://localhost:8081/image/upload",otherImage,{
                      headers:{
                           'Content-Type': 'multipart/form-data'
                      }
                  }).then((response)=>{
                       this.otherImageUrls.push(response.data);
                  }).catch((error)=>{
                    alert("MainImage 上传失败")
                  })
              });
              
          },
          productCreateClick(){
              this.description = tinyMCE.activeEditor.getContent();
              axios.post('http://localhost:8081/product/create', {
                  
                      productName: this.productName,
                      productCode: this.productCode,
                      productAbstract: this.productAbstract,
                      price: this.price,
                      quantity: this.quantity,
                      status: this.status,
                      imageUrl: this.imageUrl,
                      discount: this.discount,
                      description: this.description,
                      rewordPoints: this.rewordPoints,
                      otherImageUrls: this.otherImageUrls                
              })
                  .then( (response)=> {
                      console.log("发布成功");
                  })
                  .catch( (error)=> {
                      console.log("发布失败");
                  });
          }
      }
    
  
}