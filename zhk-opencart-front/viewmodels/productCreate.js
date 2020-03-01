var app = new Vue({
    el: '#app',
    data: {
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
                .then(function (response) {
                    console.log("发布成功");
                })
                .catch(function (error) {
                    console.log("发布失败");
                });
        }
    }
    
})