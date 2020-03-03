var app = new Vue({
    el: '#app',
    data: {},
    mounted() {
        console.log('view mounted');

        var url = new URL(location.href);
       var a= url.searchParams.get("productId")
        alert(a);


        
    }
})