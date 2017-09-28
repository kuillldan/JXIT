$(function(){
	$("[id*=editBtn-]").each(function(){
		var tid = this.id.split("-")[1] ;
		$(this).on("click",function(){
			console.log("***** tid = " + tid) ; 
			operateAlert(true , "办公用品分类信息修改成功！","办公用品分类信息修改失败！") ;
		}) ;
	})
})