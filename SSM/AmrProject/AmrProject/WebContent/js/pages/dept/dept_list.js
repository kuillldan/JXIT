$(function(){
	$("[id*=editBtn-]").each(function(){
		var did = this.id.split("-")[1] ;
		$(this).on("click",function(){
			console.log("***** did = " + did) ;
			operateAlert(true , "部门名称修改成功！","部门名称修改失败！") ;
		}) ;
	})
})