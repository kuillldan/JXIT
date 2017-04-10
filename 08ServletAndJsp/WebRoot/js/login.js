
function validateAid()
{
	return validateEmpty("aid");
}

function validatePassword()
{
	return validateEmpty("password");
}

function validateCode()
{
	return validateEmpty("code");
}




function validateLogin()
{
	var aidResult = validateAid();
	var passwordResult = validatePassword();
	var codeResult = validateCode();
	
	return  aidResult && passwordResult && codeResult;    
} 