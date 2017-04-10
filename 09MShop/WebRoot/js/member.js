function validateMid()
{ 
	return validateEmpty('mid');
}

function validatePassword()
{
	return validateEmpty('password');
}

function validateCode()
{
	return validateEmpty('code');
}

function validateRegist()
{
	var midResult = validateMid();
	var passwordResult = validatePassword();
	
	
	return midResult && passwordResult ;
}

function activeAllMembers(url, paramName, eleName)
{
	deleteAll(url, paramName, eleName)
}

function disableAllMembers(url, paramName, eleName)
{
	deleteAll(url, paramName, eleName)
}

function validateLogin()
{
	var midResult = validateMid();
	var passwordResult = validatePassword();
	var codeResult = validateCode();
	
	return midResult && passwordResult && codeResult;
}