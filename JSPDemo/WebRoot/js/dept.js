function validateDeptno()
{ 
	return validateEmpty("deptno");
}

function validateDname()
{
	return validateEmpty("dname");
}

function validateLoc()
{
	return validateEmpty("loc");
}

function validateInsert()
{
	var deptnoResult = validateDeptno();
	var dnameResult = validateDname();
	var locResult = validateLoc();
	
	return deptnoResult && dnameResult && locResult;
}


function gotoPage(cp,url)
{
	if(cp <= 0)
	{
		cp = 1;
	}
	var ls = document.getElementById("selectLineSize").value;
	window.location =  url + "?cp=" + cp + "&ls=" + ls;
}

function clearContent()
{
//	alert("XXX");
//	alert(document.getElementById("redirectToPage").value);
	document.getElementById("redirectToPage").value = "";
}

function resetContent(currentPage,pageSize)
{
	var val = document.getElementById("redirectToPage").value;
	if(val == "" )
	{
		document.getElementById("redirectToPage").value = currentPage +"/" + pageSize;
	}
}

function redirectTo(url)
{
	var val = document.getElementById("redirectToPage").value;
	if(val != "")
	{
		gotoPage(val,url);
	}
}