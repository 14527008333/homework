var app = new Vue({
    el: '#app',
    data: {
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
          }],
    },
    mounted() {
        var url = new URL(location.href);
       var a= url.searchParams.get("productId")
       this.productId=a;
        alert(a);
        tinymce.init({
            selector: '#descriptionText',
          });
          this.getProductById(this.productId);
    },
    methods:{
        getProductById(val){
            axios.get('http://localhost:8081/product/show', {
                params: {
                    productId: this.productId
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.productName=response.data.productName;
                    app.productCode=response.data.productCode;
                    app.productAbstract=response.data.productAbstract;
                    app.price=response.data.price;
                    app.quantity=response.data.quantity;
                    app.status=response.data.status;
                    app.imageUrl=response.data.imageUrl;
                    app.discount=response.data.discount;
                    app.description=response.data.description;
                    app.rewordPoints=response.data.rewordPoints;
                    app.otherImageUrls=response.data.otherImageUrls;
                })
                .catch(function (error) {
                    console.error(error);
                });
        },
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
           }).then(function(response){
                alert("MainImage 上传成功")
                app.imageUrl=response.data;
           }).catch(function(error){
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
            app.otherFiles.forEach(files=>{
                var otherImage= new FormData();
                otherImage.append("multipartFile",files.raw);
                axios.post("http://localhost:8081/image/upload",otherImage,{
                    headers:{
                         'Content-Type': 'multipart/form-data'
                    }
                }).then(function(response){
                     app.otherImageUrls.push(response.data);
                }).catch(function(error){
                  alert("MainImage 上传失败")
                })
            });
            
        },
        productUpdateClick(){
            this.description = tinyMCE.activeEditor.getContent();
            axios.post('http://localhost:8081/product/update', {
                    productId:this.productId,
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
                .then(function () {
                    console.log("修改成功");
                })
                .catch(function () {
                    console.log("修改失败");
                });
        }
    }
    
})