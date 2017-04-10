function validateEmpno()
{
	return validateRegex("empno", /^\d{4}$/);
} 

function validateEname()
{
	return validateEmpty("ename");
}

function validateJob()
{
	return validateEmpty("job");
}

function validateHiredate()
{
	return validateRegex("hiredate", /^\d{4}-\d{2}-\d{2}$/);
} 

function validateSal()
{
	return validateRegex("sal", /^\d+(\.\d+)?$/);
}

function validateComm()
{
	return validateRegex("comm", /^\d+(\.\d+)?$/);
}

function validateInsert()
{
	var empnoResult = validateEmpno();
	var enameResult = validateEname();
	var jobResult = validateJob();
	var hiredateResult = validateHiredate();
	var salResult = validateSal();
	var commResult = validateComm();
	
	return  empnoResult && enameResult && jobResult && hiredateResult && salResult && commResult;    
} 


function validateUpdate()
{ 
	var enameResult = validateEname();
	var jobResult = validateJob();
	var hiredateResult = validateHiredate();
	var salResult = validateSal();
	var commResult = validateComm();
	
	return   enameResult && jobResult && hiredateResult && salResult && commResult;    
} 