$(function() {
	editProfile();
});

function editProfile() {
	var obj = $("#myFrm input[type!='button'][type!='file'], #myFrm textarea");
	var len = $(".statY").length;

	for(var i = 0; i < obj.length; i++) {
		var id = $(obj[i]).attr("id");
		var value = $(obj[i]).val();
		
		if($("#" + id).data("orgVal") == null || $("#" + id).data("orgVal") == "undefined") $("#" + id).data("orgVal", value);
	}
	
	if($("#myUserStat").is(":checkbox")) {
		if(len > 0) $("#myUserStat").prop("checked", true);
		else $("#myUserStat").prop("checked", false);
		
		$("#myUserStat").data("orgVal", $("#myUserStat").prop("checked"));
	}
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

function beforeSaveProfile() {
	var cnt = 0;
	var obj = $("#myFrm input[type!='button'][type!='file'], #myFrm textarea");
	
		for(var i = 0; i < obj.length; i++) {
			var id = $(obj[i]).attr("id");
			
			if(!(id == "myUserMail")) {
				if($(obj[i]).data("changeYn") == "Y" || $("#myUploadFile")[0].files.length > 0) cnt++;
			}
		}
		
		if(cnt <= 0) {
			Swal.fire({
				icon: "info",
				title: "변경내용 없음",
				text: "변경된 내용이 없습니다. 다시 확인해주세요."
			});
			
			return;
		}
		
		for(var i = 0; i < obj.length; i++) {
			var id = $(obj[i]).attr("id");
			var nm = $("label[for='" + id + "']").text();
			
			if(!(id == "myUserStat" || id == "myUserNote")) {
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
		}
		
		saveProfile();
}

function saveProfile() {
	var obj = new FormData();
	var org = new FormData($("#myFrm")[0]);
	var file = $("#myUploadFile")[0].files;
	
	for(var pair of org.entries()) {
		var id = "#" + pair[0];
		var val = pair[1];
		var orgId = "u" + pair[0].replace("myU", "");
		
		if(pair[0] != "myUploadFile") obj.append(orgId, val);
  		else if(pair[0] == "myUploadFile") obj.append("uploadFile", val);
  		
  		if($("#myFrm input[type='checkbox']").is(":checked")) obj.set("userStat", "Y");
		else obj.set("userStat", "N");
	}
	
	Swal.fire({
		icon: "question",
		title: "수정 여부",
		text: "프로필을 수정하시겠습니까?",
		showCancelButton: true,
		confirmButtonText: "예",
		cancelButtonText: "아니오"
	}).then((res) => {
		if(res.isConfirmed) {
			$.ajax({
				url: "/main/updateProfile.do"
			  , data: obj
			  , type: "post"
			  , processData: false
			  , contentType: false
			  , success: function(res) { // 결과 성공 콜백함수
			  		for(var pair of org.entries()) {
			  			var id = "#" + pair[0];
			  			var val = pair[1];
			  			
			  			if(pair[0] != "myUploadFile") {
				  			$(id).val(val);
				  			$(id).data("orgVal", val);
				  			$(id).data("changeYn", "N");
				  		} else if(pair[0] == "myUserStat") {
				  			if(val == "Y") $(id).attr("checked", "checked");
				  			else $(id).attr("checked", "");
				  		}
			  		}
			  		
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

function beforeDeleteProfile() {
	Swal.fire({
		icon: "question",
		title: "삭제 여부",
		text: "회원 정보를 삭제하시겠습니까?",
		showCancelButton: true,
		confirmButtonText: "예",
		cancelButtonText: "아니오"
	}).then((res) => {
		if(res.isConfirmed) {
			deleteProfile();
		}
	});
}

function deleteProfile() {
	$.ajax({
		url: "/main/deleteProfile.do"
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	  		closeModal();
	  		
	        Swal.fire({
				icon: "success",
				title: "탈퇴 완료",
				text: "회원 탈퇴를 완료했습니다."
			}).then((res) => {
				window.location.href = "/login/logout.do";
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