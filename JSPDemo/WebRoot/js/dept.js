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