var baseUrl = 'http://localhost:8080/pttlAuction';

function loadTeams(){
    
        var apiurl = '/teamlist';
        ongoAjaxRequestAsync("GET",baseUrl+apiurl,'', function(res){
            //console.log(res);
            $.each(res.data, function(idx){
            var team = res.data[idx];
            var child='<div class="col"><h3>'+team.name+'</h3><p>Players - '
            +team.player_count+'/'+team.min_player_count+'</p><p>Amount - '
            +team.balance_amount+'/'+team.base_price+'</p></div>';
            $('#teamlist').append(child);
            
            var litag='<option value="'+team.id+'">'+team.name+'</option>';
            $('#selectteam').append(litag);
      
                });
            });
    }
 function getPlayer(){
       var playerId =  $('#playerId').val();
        var apiurl = '/player?id='+playerId;
        ongoAjaxRequestAsync("GET",baseUrl+apiurl,'', function(res){
            //console.log(res);
            $.each(res.data, function(idx){
            var player = res.data;
            $('#playername').val(player.name)
            $('#mobile').val(player.phone_no)
            $('#auctionprice').val('')
            $('#email').val(player.email)
             $('#baseprice').val('1000000')
      
                });
            });
    }
  function savePlayer(){
    
	var pms = {};
	pms.id = $('#playerId').val()
	pms.email =  $('#email').val()
	pms.phone_no =  $('#mobile').val()
	pms.auction_price =  $('#auctionprice').val()
	pms.baseprice =  $('#baseprice').val()
	pms.name = $('#playername').val()
	pms.team_id =  $('#selectteam').val()
	console.log(pms)
        var apiurl = '/player';
        ongoAjaxRequestAsync("POST",baseUrl+apiurl,pms, function(res){
           alert(res.message)
       
            });
    }
  
  function ongoAjaxRequestAsync(dtype,urlpath, pms, successcb, failcb, asyncP){
	var jsdata = pms != null && pms != '' ? (JSON.stringify(pms)) : ''
	var request = $.ajax({
		  url: urlpath,
		  type: dtype,
		  data: jsdata,
		  dataType: "json",
		  async : asyncP
	});
		 
	request.done(function( msg ) {
			
		successcb( msg );
	});
	 
	request.fail(function( jqXHR, textStatus ) {
		if(failcb){
			failcb(textStatus)
		}else{
			alert('Request failed: '+textStatus);
		}		
	});
}