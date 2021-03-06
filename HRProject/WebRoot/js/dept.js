function validateDname() {
	return validateEmpty("dept.dname");
}

function updateDept(id, url) {
	if (validateDnameWhenUpdate(id)) {
		var dname = document.getElementById("dept-" + id).value;
		window.location = url + "?dept.did=" + id + "&dept.dname=" + dname;
	}
}

function validateDnameWhenUpdate(did) {
	validateEmpty("dept-" + did);
}