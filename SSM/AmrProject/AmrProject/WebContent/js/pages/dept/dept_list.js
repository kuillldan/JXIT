$(function()
{
	$("[id*=editBtn-]").each(function()
	{
		var did = this.id.split("-")[1];
		$(this).on("click", function()
		{
			var title = $("#title-" + did).val();
			console.log("***** did = " + did);
			console.log("***** title = " + title);
			
			if (title != "" && title.trim() != "")
			{
				$.post("pages/dept/update.action",
				{
					did : did,
					title : title
				}, function(data)
				{
					console.log("调用结果:" + data);
					operateAlert(data, "部门名称修改成功！", "部门名称修改失败！");
				}, "text");
			} else
			{
				operateAlert(false, "部门名称修改成功！", "部门名称不能为空!");
			}
		});
	})
})