function validateTitle()
{ 
	return validateEmpty('title');
}

function validatePrice()
{
	return validateRegex("price",/^\d+(\.\d+)?$/);
}

function validateAmount()
{
	return validateRegex("amount",/^\d+$/);
}

function updateStatus(url, paramName, eleName)
{
	deleteAll(url, paramName, eleName);
}

function findAllByItem(url)
{
	window.location = url + "?iid=" + document.getElementById("iid").value;
}

function validateInsert()
{
	var titleResult = validateTitle();
	var priceResult = validatePrice();
	var amountResult = validateAmount();
	
	return titleResult && priceResult && amountResult;
}