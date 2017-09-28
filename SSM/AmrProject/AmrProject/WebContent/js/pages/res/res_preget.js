$(function() {
	$("#selectAll").on("click",function(){
		checkboxSelectAll('rid',this.checked) ;
	}) ;
	// 实现整体修改操作的功能
	$(editBtn).on("click",function(){
		// 定义一个数组，保存所有需要被删除的gid数据
		var delGid = new Array() ;
		var foot = 0 ;
		var data = "" ; // 实现最终数据拼凑的字符串
		$("[id*=amount-]").each(function(){
			var gid = this.id.split("-")[1] ;
			var amount = this.value ;
			if (amount != "0") {
				data += gid + ":" + amount + "|" ;
			} else {
				delGid[foot ++] = gid ;
			}
		}) ;
		// 进行ajax异步数据处理操作
			operateAlert(true,"商品数量修改成功！","商品数量修改失败！") ;
			for (var x = 0 ; x < delGid.length ; x ++) {
				$("#sc-" + delGid[x]).remove() ;
			}
	}) ;
	$("#rmBtn").on("click",function(){	// 绑定用户锁定操作
		var data = "" ;
		$(":checked").each(function() {
			if(this.id == "goods.gid") {	// 要删除的内容
				data += this.value + "|" ;
			}
		}) ;
		if (data != "") {
			
		}
		operateAlert(true,"待领商品移除成功！","待领商品移除失败！") ;
	}) ;
	$("button[id*=add-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			$("#amount-" + gid).val(amount+1) ;
		})
	}) ;
	$("button[id*=sub-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			if (amount > 0) {
				$("#amount-" + gid).val(amount - 1) ;
			}
		})
	}) ;
})
