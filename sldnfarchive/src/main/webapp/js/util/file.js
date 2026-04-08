function fileCheck(e) {
	var id = $(e).attr("id");
	var ext = $("#" + id).val().split(".").pop().toLowerCase();
	var fileNm = $("#" + id).val().split("\\").pop();
	var regexEng = /^[a-zA-Z0-9_.-]*$/;
	
	if($.inArray(ext, ["jpg", "gif", "png", "jpeg", "bmp", "tif"]) == -1 ) {
		Swal.fire({
			icon: "info",
			title: "확장자 제한",
			text: "이미지 파일 외에는 선택할 수 없습니다."
		});
		
		return false;
	}
	
	if(!regexEng.test(fileNm)) {
		Swal.fire({
			icon: "info",
			title: "파일명 제한",
			text: "파일명은 영문, 숫자, 언더바(_), 하이픈(-)만 가능합니다."
		});
		
		$("#" + id).val("");
		
		return false;
	}
	
	imgChange(id);
}

function imgChange(id) {
	var file = $("#" + id)[0].files;
	var reader = new FileReader();
	
	reader.onload = function(e) {
		$("#profileImg").attr("src", e.target.result);
	}
	
	reader.readAsDataURL(file[0]);
}