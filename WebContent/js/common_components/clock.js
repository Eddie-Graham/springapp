function updateClock(id) {
	
	var date = new Date();
	
	var hoursNum = date.getHours();
	var hoursStr = hoursNum.toString();
	
	var minutesNum = date.getMinutes();
	var minutesStr = minutesNum.toString();
	
	var secondsNum = date.getSeconds();
	var secondsStr = secondsNum.toString();
	
	var hours = timeFormatting(hoursStr);
	var minutes = timeFormatting(minutesStr);
	var seconds = timeFormatting(secondsStr);
	
	var string = hours + ":" + minutes + ":" + seconds;

	$("#" + id).html(string);

	// call this function again in 1000ms
	setTimeout(updateClock, 1000, id);
}

function timeFormatting(element){
	
	if(element.length == 1){
		return "0" + element;
	}
	
	return element;
}

updateClock("time");