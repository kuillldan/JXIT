function updateShopCar(url)
{ 
	var parameters = "";
	var gids = document.getElementsByName("gid");
	for(var i = 0 ; i < gids.length; i++)
	{
		gid = gids[i].value;
		var amount = document.getElementById("amount-" + gid).value;
		console.log(gids[i].value);
		parameters += gid + "=" + amount + "&";
	}
	parameters = parameters.substring(0, parameters.length-1);
	window.location = url + "?" + parameters;
}


function validateAmount(gid)
{
	if(validateRegex("amount-" + gid,/^\d+$/))
	{
		var currentAmount = parseInt(document.getElementById("amount-" + gid).value);
		var price = parseFloat(document.getElementById("price-"+gid).value);
		document.getElementById("totalPrice-" + gid).innerHTML = currentAmount * price;
	}
	else
	{
		return false;
	}
}

function addAmount(gid)
{
	if(validateRegex("amount-" + gid,/^\d+$/))
	{
		var currentAmount = parseInt(document.getElementById("amount-" + gid).value); 
		currentAmount++;
		document.getElementById("amount-" + gid).value = currentAmount; 
		var price = parseFloat(document.getElementById("price-"+gid).value); 
		document.getElementById("totalPrice-" + gid).innerHTML = currentAmount * price;
	}
}

function minusAmount(gid)
{
	if(validateRegex("amount-" + gid,/^\d+$/))
	{
		var currentAmount = parseInt(document.getElementById("amount-" + gid).value);
		if(currentAmount <= 0)
		{ 
			return;
		}
		currentAmount--;
		document.getElementById("amount-" + gid).value = currentAmount ;
		var price = parseFloat(document.getElementById("price-"+gid).value);
		document.getElementById("totalPrice-" + gid).innerHTML = currentAmount * price;
	}
}
 
function validateInsert()
{ 
	var amountResult = validateAmount();
	
	return  amountResult;
}