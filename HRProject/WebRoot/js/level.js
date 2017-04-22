function validateTitle() {
	return validateEmpty("level.title");
}

function validateLosal() {
	return validateRegex("level.losal", /^\d+(\.\d+)?$/);
}

function validateHisal() {
	return validateRegex("level.hisal", /^\d+(\.\d+)?$/);
}

function validateInsert() {
	var titleResult = validateTitle();
	var losalResult = validateLosal();
	var hisalResult = validateHisal();

	return titleResult && losalResult && hisalResult;
}

function validateTitleWhenUpdate(levid) {
	return validateEmpty("level-title-" + levid);
}

function validateLosalWhenUpdate(levid) {
	return validateRegex("level-losal-" + levid, /^\d+(\.\d+)?$/)
}

function validateHisalWhenUpdate(levid) {
	return validateRegex("level-hisal-" + levid, /^\d+(\.\d+)?$/)
}


function updateLevel(levid, url) {
	var titleResult = validateTitleWhenUpdate(levid);
	var losalResult = validateLosalWhenUpdate(levid);
	var hisalResult = validateHisalWhenUpdate(levid);
	
	if (titleResult && losalResult && hisalResult) 
	{
		var title = document.getElementById("level-title-" + levid).value;
		var losal = document.getElementById("level-losal-" + levid).value;
		var hisal = document.getElementById("level-hisal-" + levid).value;
		 
		window.location = url + "?level.levid=" + levid + "&level.title=" + title
				+ "&level.losal=" + losal + "&level.hisal=" + hisal;
	}
}