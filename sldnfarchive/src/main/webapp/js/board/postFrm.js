function goList() {
	var boardNo = $("#boardNo").val();
	var path = "/board/postList.do?boardNo=" + boardNo;
	var js = "/js/board/postList.js";
	
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

function beforeSavePost() {
	var postNo = $("#postNo").val();
	var cnt = 0;
	var obj = $("#postForm input[type!='button'][type!='file'], #postForm textarea");
	
	if(postNo > 0) {
		for(var i = 0; i < obj.length; i++) {
			var id = $(obj[i]).attr("id");
			
			if($(obj[i]).data("changeYn") == "Y" || $("#uploadFile1")[0].files.length > 0 || $("#uploadFile2")[0].files.length > 0) cnt++;
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
	
	savePost();
}

function savePost() {
	var url = "";
	var obj = new FormData($("#postForm")[0]);
	var file1 = $("#uploadFile1")[0].files;
	var file2 = $("#uploadFile2")[0].files;
	var postNo = $("#postNo").val();
	
	if(postNo <= 0) url = "/board/insertPost.do";
	else url = "/board/updatePost.do";
	
	for(var pair of obj.entries()) {
		var id = "#" + pair[0];
		var val = pair[1];
	}
	
	Swal.fire({
		icon: "question",
		title: "작성 여부",
		text: "게시글 작성을 완료하시겠습니까?",
		showCancelButton: true,
		confirmButtonText: "예",
		cancelButtonText: "아니오"
	}).then((res) => {
		if(res.isConfirmed) {
			$.ajax({
				url: url
			  , data: obj
			  , type: "post"
			  , processData: false
			  , contentType: false
			  , success: function(res) { // 결과 성공 콜백함수
			  		for(var pair of obj.entries()) {
			  			var id = "#" + pair[0];
			  			var val = pair[1];
			  			
			  			if(!(pair[0] == "uploadFile1" || pair[0] == "uploadFile2")) {
				  			$(id).val(val);
				  			$(id).data("orgVal", val);
				  			$(id).data("changeYn", "N");
				  		}
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