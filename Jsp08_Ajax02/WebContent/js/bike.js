/**
 * 
 */

 $(function(){
	 parseJson();
 });
 
 function parseJson(){
	 $.getJSON("json/bike.json",function(data){
		 $.ajax({
			 url:"bike.do?command=datasave",
			 method:"post",
			 data:{"obj":JSON.stringify(data)},
			 success:function(msg){
				 console.log(msg);
				 
				 if(msg){
					 $.each(data,function(key,val){
						 if(key=="DESCRIPTION"){
							 $("table").attr("border", "1");
							 $("thead").append(
								 "<tr>"+
								 	"<th>"+val.ADDR_GU+"</th>"+
								 	"<th>"+val.CONTENT_ID+"</th>"+
								 	"<th>"+val.CONTENT_NM+"</th>"+
								 	"<th>"+val.NEW_ADDR+"</th>"+
								 	"<th>"+val.CRADLE_COUNT+"</th>"+
								 	"<th>"+val.LONGITUDE+"</th>"+
								 	"<th>"+val.LATITUDE+"</th>"+
								 "</tr>"
							 );
						 }else{
							for(let i=0; i<val.length; i++){
								let tmp = val[i];
								
								$("tbody").append(
									"<tr>"+
										"<td>"+tmp.addr_gu+"</td>"+
										"<td>"+tmp.content_id+"</td>"+
										"<td>"+tmp.content_nm+"</td>"+
										"<td>"+tmp.new_addr+"</td>"+
										"<td>"+tmp.cradle_count+"</td>"+
										"<td>"+tmp.longitude+"</td>"+
										"<td>"+tmp.latitude+"</td>"+
									"</tr>"
								);
							} 
						 }
					 });
					 
				 }
			 },
			 error:function(){
				 alert("실패ㅠㅠ");
			 }
		 })
	 });
 }
 