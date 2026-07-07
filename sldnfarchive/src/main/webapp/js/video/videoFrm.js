$(function() {
	var videoNo = $("#videoNo").val();
	
	if(videoNo <= 0) $(".btn-save").val("작성");
	else $(".btn-save").val("수정");
});

function goList() {
	var path = "/video/videoList.do";
	var js = "/js/video/videoList.js";
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
	});
}

function chkChangeVal(ele) {
	var type = $(ele).attr("type");
	var orgVal = $(ele).data("orgVal");
	var curVal = "";
	
	if(type == "checkbox") curVal = $(ele).prop("checked");
	else curVal = $(ele).val();
	
	if(orgVal == curVal) $(ele).data("changeYn", "N");
	else $(ele).data("changeYn", "Y");
}

function beforeSaveVideo() {
	var videoNo = $("#videoNo").val();
	var cnt = 0;
	var obj = $("#videoFrm input[type!='button'][type!='file'][type!='hidden'], #videoFrm textarea");
	
	if(videoNo > 0) {
		for(var i = 0; i < obj.length; i++) {
			var id = $(obj[i]).attr("id");
			
			if($(obj[i]).data("changeYn") == "Y") cnt++;
		}
		
		if(cnt <= 0) {
			Swal.fire({
				icon: "info",
				title: "변경내용 없음",
				text: "변경된 내용이 없습니다. 다시 확인해주세요."
			});
			
			return;
		}
	}
	
	for(var i = 0; i < obj.length; i++) {
		var id = $(obj[i]).attr("id");
		var nm = $("label[for='" + id + "']").text();
		var num = $(obj[i]).val().length;
		
		if(num <= 0) {
			$(obj[i]).focus();
			
			Swal.fire({
				icon: "info",
				title: "내용 없음",
				text: nm + "을/를 입력해주세요."
			});
			
			return;
		}
	}
	
	saveVideo();
}

function saveVideo() {
	var url = "";
	var txt = "";
	var obj = $("#videoFrm").serializeObject();
	
	if(obj.videoNo <= 0) {
		url = "/video/insertVideo.do";
		txt = "작성";
	} else {
		url = "/video/updateVideo.do";
		txt = "수정";
	}
	
	Swal.fire({
		icon: "question",
		title: "작성 여부",
		text: "내용 " + txt + "을 완료하시겠습니까?",
		showCancelButton: true,
		confirmButtonText: "예",
		cancelButtonText: "아니오"
	}).then((res) => {
		if(res.isConfirmed) {
			$.ajax({
				url: url
			  , data: obj
			  , type: "post"
			  , dataType: "json"
			  , async: true
			  , success: function(res) { // 결과 성공 콜백함수
			  		for(var key in obj) {
			  			var id = "#" + key;
			  			var val = obj[key];
			  			
			  			$(id).val(val);
			  			$(id).data("orgVal", val);
			  			$(id).data("changeYn", "N");
			  		}
			  		
			  		goList();
			  		
			        Swal.fire({
						icon: "success",
						title: "저장 완료",
						text: "회원 정보를 저장했습니다."
					});
			    }
			  , error: function(req, status, err) { // 결과 에러 콜백함수
			        Swal.fire({
						icon: "error",
						title: "에러 발생",
						text: "관리자에게 문의해주세요."
					});
			    }
			});
		}
	});
}