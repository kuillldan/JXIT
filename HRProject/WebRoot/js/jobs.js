function validateTitle() {
	return validateEmpty("jobs.title");
}

function validateTitleWhenUpdate(jid)
{
	console.log("[debug] jid: " + jid);
	return validateEmpty("jobs-title-" + jid);
}

function updateJobs(id, url) {
	if (validateTitleWhenUpdate(id)) {
		var title = document.getElementById("jobs-title-" + id).value;
		var note = document.getElementById("jobs-note-" + id).value;
		window.location = url + "?jobs.jid=" + id + "&jobs.title=" + title + "&jobs.note=" + note;
	}
}

function validateDnameWhenUpdate(did) {
	validateEmpty("dept-" + did);
}