function loadTeams(){
    //alert(new URL(location.href).searchParams.get("catId"));
    
        var url   
         url = 'pttlAuction/teamlist';
    
        ongoAjaxRequestAsync("GET",url,'', function(res){
            //console.log(res);
            $.each(res.data, function(idx){
            var team = res.data[idx];
            console.log("prod"+team.name);
            var childli = '<li><figure><a class="aa-product-img" href="#"><img  style ="height:235px;width:285px" alt="product image"></a>'+
             '<a class="aa-add-card-btn" id="addToCart" onclick="addToCart('+product.id+')"><span class="fa fa-shopping-cart"></span>Add To Cart</a>'+
             '<figcaption>'+
             '<h4 class="aa-product-title"><a href="#">'+product.name+'</a></h4>'+
              '<span class="aa-product-price">'+product.price+'</span>'+
              '</figcaption>'+
              '</figure> </li>';
              $('#allProductsList').append(childli);
                });
            });
    }