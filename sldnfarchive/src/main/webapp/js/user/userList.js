var flag = "";
var orgFileNm = "";

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
	
	obj.curIdx = 1;
	obj.pageUnit = 10;
	obj.curPage = 1;
	
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
	  	, page: "page"
	  	, total: function(res) {
	  		var total = res.totalCnt;
	  		var pageSize = Math.ceil(total / res.userVO.pageUnit);
	  		
	  		return pageSize;
	  	  }
	  	, records: function(res) {
	  		return res.userList.length;
	  	  }
	  	, repeatitems: false
	  }
	  , rowNum: obj.pageUnit
	  , autowidth: true
	  , shrinkToFit: true
	  , height: "auto"
	  , rownumbers: true
	  , pager: "#gridPager"
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
	  , onPaging: function(btn) {
	  	var gridPage = $("#userGrid").getGridParam("page");
	  	var totalPage = $("#sp_1_gridPager").text();
	  	
	  	if(btn == "next") {
	  		if(gridPage < totalPage) gridPage = gridPage + 1;
	  		else gridPage = totalPage;
	  	} else if(btn == "prev") {
	  		if(gridPage > 1) gridPage -= 1;
	  		else gridPage = 1;
	  	} else if(btn == "first") gridPage = 1;
	  	else if(btn == "last") gridPage = totalPage;
	  	else if(btn == "user") {
	  		var inptPage = Number($("#gridPager .ui-pg-input").val());
	  		
	  		if(inptPage > 0 && inptPage <= totalPage) gridPage = inptPage;
	  		else {
	  			gridPage = 1;
	  			$("#gridPager .ui-pg-input").val(gridPage);
	  		}
	  	} else if(btn == "records") gridPage = 1;
	  	
		obj.curPage = gridPage;
	  	
	  	$("#userGrid").clearGridData();
		$("#userGrid").setGridParam({
			postData: obj
		}).trigger("reloadGrid");
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
		  		orgFileNm = obj.fileNmDtl;
		  		
		  		for(var key in obj) {
		  			var id = "#" + key;
		  			var val = obj[key];
		  			
		  			if(key == "userMail") {
		  				$(id).addClass("bg-secondary");
		  				$(id).attr("readonly", true);
		  			}
		  			
		  			if($(id).is(":checkbox")) {
		  				if(val == "Y") $(id).prop("checked", true);
		  				else $(id).prop("checked", false);
		  				$(id).data("orgVal", $(id).prop("checked"));
		  			} else {
		  				$(id).val(val);
		  				$(id).data("orgVal", val);
		  			}
		  		}
		  		
		  		if(orgFileNm) $("#profileImg").attr("src", "/images/upload/" + encodeURIComponent(orgFileNm));
		  		else $("#profileImg").attr("src", "/images/img_nouser.png");
		  		
		  		$("#uploadFile").val("");
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
		
		$("#profileImg").attr("src", "/images/img_nouser.png");
  		for(var i = 0; i < obj.length; i++) {
  			var id = $(obj[i]).attr("id");
  			
  			if(id == "userMail") {
  				$("#" + id).removeClass("bg-secondary");
  				$("#" + id).attr("readonly", false);
  			}
  			
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
	var arr = $("#userGrid").jqGrid("getDataIDs");
	var len = arr.length;
	var cnt = 0;
	var obj = $("#editFrm input[type!='button'][type!='file'], #editFrm textarea");
	
	if(len > 0) {
		if(flag == "U") {
			for(var i = 0; i < obj.length; i++) {
				var id = $(obj[i]).attr("id");
				
				if(!(id == "userMail")) {
					if($(obj[i]).data("changeYn") == "Y" || $("#uploadFile")[0].files.length > 0) cnt++;
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
		}
		
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
		
		for(var i = 0; i < (len - 1); i++) {
			var inputUserMailVal = $("#userMail").val();
			var userMail = $("#userGrid").getRowData(arr[i]).userMail;
			
			if(inputUserMailVal == userMail) {
				$("#userMail").focus();
					
				Swal.fire({
					icon: "info",
					title: "아이디 중복",
					text: "이미 존재하는 아이디입니다."
				});
				
				return;
			}
		}
		
		saveUser();
	}
}

function saveUser() {
	var url = "";
	var obj = new FormData($("#editFrm")[0]);
	var file = $("#uploadFile")[0].files;
	var id = $("#userGrid").getGridParam("selrow");
	var idx = $("#userGrid").getRowData(id).userIdx;
	
	if(flag == "I") url = "/user/insertUser.do";
	else if(flag == "U") {
		url = "/user/updateUser.do";
		obj.append("userIdx", idx);
	}
	
	if($("#editFrm input[type='checkbox']").is(":checked")) obj.set("userStat", "Y");
	else obj.set("userStat", "N");
	
	Swal.fire({
		icon: "question",
		title: "저장 여부",
		text: "회원 정보를 저장하시겠습니까?",
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
			  			
			  			if(pair[0] != "uploadFile") {
				  			$(id).val(val);
				  			$(id).data("orgVal", val);
				  			$(id).data("changeYn", "N");
				  		}
			  		}
			  		
			  		userList();
			  		
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
		  		userList();
		  		
		        Swal.fire({
					icon: "success",
					title: "삭제 완료",
					text: "회원 정보 삭제를 완료했습니다."
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
	} else {
		var arr = $("#userGrid").jqGrid("getDataIDs");
		
		$("#userGrid").delRowData(id);
		$("#userGrid").setSelection(arr[0]);
	}
}