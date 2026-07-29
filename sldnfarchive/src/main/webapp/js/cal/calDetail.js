function calList() {
	
}

function tabChange(e) {
	var ele = $(e);
	var tabNo = ele.attr("id").split("tab")[1];
	
	ele.addClass("active");
	ele.siblings().removeClass("active");
	
	$("#box" + tabNo).addClass("d-block");
	$("#box" + tabNo).removeClass("d-none");
	$("#box" + tabNo).addClass("active");
	
	$("#box" + tabNo).siblings().removeClass("d-block");
	$("#box" + tabNo).siblings().addClass("d-none");
	$("#box" + tabNo).siblings().removeClass("active");
}