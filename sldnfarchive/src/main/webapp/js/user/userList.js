var flag = "";

$(function() {
	userList();
});

function userList() {
	var obj = $("#schUserFrm").serializeObject();
	var colNamesArr = ["No.", "아이디(이메일)", "이름", "휴면여부"]
	var colModelArr = [
		{name: "userIdx", index: "userIdx", align: "center", hidden: true}
	  , {name: "userMail", index: "userMail", align: "left", width: "50%"}
	  , {name: "userNm", index: "userNm", align: "left", width: "30%"}
	  , {name: "userStat", index: "userStat", align: "center", width: "20%"}
	];
	
	$("#userGrid").clearGridData();
	$("#userGrid").jqGrid({
		url: "/user/selectUserList.do"
	  , postData: obj
	  , mtype: "post"
	  , datatype: "json"
	  , colNames: colNamesArr
	  , colModel: colModelArr
	  , jsonReader: {
	  	  root: function(res) {
	  	  	return res.userList;
	  	  }
	  	, records: function(res) {
	  		return res.userList.length;
	  	  }
	  	, repeatitems: false
	  }
	  , rowNum: 10
	  , autowidth: true
	  , shrinkToFit: true
	  , height: "auto"
	  , rownumbers: true
	  , loadComplete: function(res) {
	  	var records = $("#userGrid").getGridParam("records");
	  	var arr = $("#userGrid").jqGrid("getDataIDs");
	  	
	  	if(records > 0) {
	  		$("#userGrid").setSelection(arr[0]);
	  	} else {
	  		var obj = $("#editFrm input[type!='button'], #editFrm textarea");
	  		
	  		for(var i = 0; i < obj.length; i++) {
	  			var id = $(obj[i]).attr("id");
	  			
	  			$("#" + id).val("");
			}
	  	}
	  }
	  , onSelectRow: function(res) {
	  	editUser();
	  }
	}).trigger("reloadGrid");
}

function schUser() {
	var obj = $("#schUserFrm").serializeObject();
	
	$("#userGrid").clearGridData();
	$("#userGrid").setGridParam({
		postData: obj
	}).trigger("reloadGrid");
}

function insertUser() {
	var arr = $("#userGrid").jqGrid("getDataIDs");
	
	for(var i = 0; i < arr.length; i++) {
		if(arr[i] == "newRow") {
			Swal.fire({
				icon: "info",
				title: "알림",
				text: "기존 입력 중인 정보가 존재합니다."
			});
			
			return false;
		}
	}
	
	$("#userGrid").addRowData("newRow", {}, "last");
	$("#userGrid").resetSelection();
	$("#userGrid").setSelection("newRow");
}

function editUser() {
	var id = $("#userGrid").getGridParam("selrow");
	var idx = $("#userGrid").getRowData(id).userIdx;
	
	if(id != "newRow") {
		flag = "U";
		
		$.ajax({
			url: "/user/selectUser.do"
		  , data: {"userIdx": idx}
		  , type: "post"
		  , dataType: "json"
		  , async: true
		  , success: function(res) { // 결과 성공 콜백함수
		  		var obj = res.selectUser;
		  		
		  		for(var key in obj) {
		  			var id = "#" + key;
		  			var val = obj[key];
		  			
		  			if($(id).is(":checkbox")) {
		  				if(val == "Y") $(id).prop("checked", true);
		  				else $(id).prop("checked", false);
		  				$(id).data("orgVal", $(id).prop("checked"));
		  			} else {
		  				$(id).val(val);
		  				$(id).data("orgVal", val);
		  			}
		  		}
		    }
		  , error: function(req, status, err) { // 결과 에러 콜백함수
		        Swal.fire({
					icon: "error",
					title: "에러 발생",
					text: "관리자에게 문의해주세요."
				});
		    }
		});
	} else {
		flag = "I";
		var obj = $("#editFrm input[type!='button'], #editFrm textarea");
			  		
  		for(var i = 0; i < obj.length; i++) {
  			var id = $(obj[i]).attr("id");
  			
  			$("#" + id).val("");
  		}
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

function beforeSaveUser() {
	var len = $("#userGrid").jqGrid("getDataIDs").length;
	var cnt = 0;
	var obj = $("#editFrm input[type!='button'], #editFrm textarea");
	
	if(len > 0) {
		if(flag == "U") {
			for(var i = 0; i < obj.length; i++) {
				cnt++;
			}
			
			if(cnt <= 0) {
				Swal.fire({
					icon: "info",
					title: "변경내용 없음",
					text: "변경된 내용이 없습니다. 다시 확인해주세요."
				});
				
				return;
			}
		} else if(flag == "I") {
			for(var i = 0; i < obj.length; i++) {
				var id = $(obj[i]).attr("id");
				var nm = $("label[for='" + id + "']").text();
				
				if(!(id == "userStat" || id == "userNote")) {
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
		}
		
		saveUser();
	}
}

function saveUser() {
	var url = "";
	var obj = $("#editFrm").serializeObject();
	var id = $("#userGrid").getGridParam("selrow");
	var idx = $("#userGrid").getRowData(id).userIdx;
	
	if($("#editFrm input[type='checkbox']").is(":checked")) obj.userStat = 'Y';
	else obj.userStat = 'N';
	
	if(flag == "I") url = "/user/insertUser.do";
	else if(flag == "U") {
		url = "/user/updateUser.do";
		obj.userIdx = idx;
	}
	
	Swal.fire({
		icon: "question",
		title: "저장 여부",
		text: "메뉴 정보를 저장하시겠습니까?",
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
			  		
			  		userList();
			  		
			        Swal.fire({
						icon: "success",
						title: "저장 완료",
						text: "메뉴 정보를 저장했습니다."
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

function beforeDeleteUser() {
	var len = $("#userGrid").getGridParam("records");
	
	if(len > 0) {
		Swal.fire({
			icon: "question",
			title: "삭제 여부",
			text: "회원 정보를 삭제하시겠습니까?",
			showCancelButton: true,
			confirmButtonText: "예",
			cancelButtonText: "아니오"
		}).then((res) => {
			if(res.isConfirmed) {
				deleteUser();
			}
		});
	}
}

function deleteUser() {
	var id = $("#userGrid").getGridParam("selrow");
	var idx = $("#userGrid").getRowData(id).userIdx;
	
	if(id != "newRow") {
		$.ajax({
			url: "/user/deleteUser.do"
		  , data: {"userIdx": idx}
		  , type: "post"
		  , dataType: "json"
		  , async: true
		  , success: function(res) { // 결과 성공 콜백함수
		        Swal.fire({
					icon: "success",
					title: "삭제 완료",
					text: "메뉴 삭제를 완료했습니다."
				});
				
				userList();
		    }
		  , error: function(req, status, err) { // 결과 에러 콜백함수
		        Swal.fire({
					icon: "error",
					title: "에러 발생",
					text: "관리자에게 문의해주세요."
				});
		    }
		});
	} else {
		var arr = $("#userGrid").jqGrid("getDataIDs");
		
		$("#userGrid").delRowData(id);
		$("#userGrid").setSelection(arr[0]);
	}
}