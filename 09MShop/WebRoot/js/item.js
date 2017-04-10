function validateTitle()
{ 
	return validateEmpty('title');
} 

function updateItem(iid,title)
{  
	validateEmpty("title-" + iid);
	document.getElementById("iid").value = iid;
	document.getElementById("title").value = document.getElementById("title-" + iid).value;
	
//	alert("iid = " + document.getElementById("iid").value);
//	alert("title = 0" + document.getElementById("title").value);
	
	document.getElementById("itemInfoForm").submit();
}

function validateInsert()
{
	var titleResult = validateTitle(); 
	
	return titleResult;
}