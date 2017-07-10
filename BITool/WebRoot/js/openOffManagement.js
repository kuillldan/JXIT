function checkAutoManagementTime(startHour, startMinute, endHour, endMinute,
		url) {
	var currentStartHour = document.getElementById("currentStartHour").value;
	var currentStartMinute = document.getElementById("currentStartMinute").value;
	var currentEndHour = document.getElementById("currentEndHour").value;
	var currentEndMinute = document.getElementById("currentEndMinute").value;

	if (startHour == currentStartHour && startMinute == currentStartMinute
			&& endHour == currentEndHour && endMinute == currentEndMinute) {
		alert("你没有做任何改变，请选择后再试!");
	} else {
		window.location = url + "?startHour=" + currentStartHour
				+ "&startMinute=" + currentStartMinute + "&endHour="
				+ currentEndHour + "&endMinute=" + currentEndMinute;
	}

}

function changeMode(mode, url) {

	var currentMode = document.getElementById("currentMode").value;
	if (mode == currentMode)
		alert("你没有做任何改变，请选择后再试!");
	else
		window.location = url + "?mode=" + currentMode;
}
