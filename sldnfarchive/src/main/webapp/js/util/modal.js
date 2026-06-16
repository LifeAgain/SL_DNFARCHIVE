function openModal() {
	var path = "/main/profile.do";
	var js = "/js/main/profile.js";
	var util = "/js/util/file.js";
	var util2 = "/js/util/modal.js";
	
	$(".modal").show();
	$(".modal-body").children().remove();
	$(".modal-body").load(path + " .modal-container", function() {
		$.getScript(js);
		$.getScript(util);
		$.getScript(util2);
	});
}

function closeModal() {
	$(".modal").hide();
}