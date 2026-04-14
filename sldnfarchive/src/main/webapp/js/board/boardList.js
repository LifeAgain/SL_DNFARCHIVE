var flag = "";

$(function() {
	boardList();
});

function boardList() {
	var obj = $("#schBoardFrm").serializeObject();
	var colNamesArr = ["No.", "종류", "게시판명", "상위메뉴", "사용여부", "댓글여부"]
	var colModelArr = [
		{name: "boardNo", index: "boardNo", align: "center", hidden: true}
	  , {name: "codeNm", index: "codeNm", align: "center", width: "20%"}
	  , {name: "boardNm", index: "boardNm", align: "left", width: "30%"}
	  , {name: "menuNm", index: "menuNm", align: "left", width: "30%"}
	  , {name: "useYn", index: "useYn", align: "center", width: "10%"}
	  , {name: "commentYn", index: "commentYn", align: "center", width: "10%"}
	];
	
	obj.curIdx = 1;
	obj.pageUnit = 10;
	obj.curPage = 1;
	
	$("#boardGrid").clearGridData();
	$("#boardGrid").jqGrid({
		url: "/board/selectBoardList.do"
	  , postData: obj
	  , mtype: "post"
	  , datatype: "json"
	  , colNames: colNamesArr
	  , colModel: colModelArr
	  , jsonReader: {
	  	  root: function(res) {
	  	  	return res.boardList;
	  	  }
	  	, page: "page"
	  	, total: function(res) {
	  		var total = res.totalCnt;
	  		var pageSize = Math.ceil(total / res.boardVO.pageUnit);
	  		
	  		return pageSize;
	  	  }
	  	, records: function(res) {
	  		return res.boardList.length;
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
	  	var records = $("#boardGrid").getGridParam("records");
	  	var arr = $("#boardGrid").jqGrid("getDataIDs");
	  	
	  	if(records > 0) {
	  		$("#boardGrid").setSelection(arr[0]);
	  	} else {
	  		var obj = $("#editFrm select, #editFrm input[type!='button'], #editFrm textarea");
	  		
	  		for(var i = 0; i < obj.length; i++) {
	  			var id = $(obj[i]).attr("id");
	  			
	  			$("#" + id).val("");
			}
	  	}
	  }
	  , onPaging: function(btn) {
	  	var gridPage = $("#boardGrid").getGridParam("page");
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
	  	
	  	$("#boardGrid").clearGridData();
		$("#boardGrid").setGridParam({
			postData: obj
		}).trigger("reloadGrid");
	  }
	  , onSelectRow: function(res) {
	  	editBoard();
	  }
	}).trigger("reloadGrid");
}

function schBoard() {
	var obj = $("#schBoardFrm").serializeObject();
	
	$("#boardGrid").clearGridData();
	$("#boardGrid").setGridParam({
		postData: obj
	}).trigger("reloadGrid");
}

function insertBoard() {
	var arr = $("#boardGrid").jqGrid("getDataIDs");
	
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
	
	$("#boardGrid").addRowData("newRow", {}, "last");
	$("#boardGrid").resetSelection();
	$("#boardGrid").setSelection("newRow");
}

function editBoard() {
	var id = $("#boardGrid").getGridParam("selrow");
	var idx = $("#boardGrid").getRowData(id).boardNo;
	
	if(id != "newRow") {
		flag = "U";
		
		$.ajax({
			url: "/board/selectBoard.do"
		  , data: {"boardNo": idx}
		  , type: "post"
		  , dataType: "json"
		  , async: true
		  , success: function(res) { // 결과 성공 콜백함수
		  		var obj = res.selectBoard;
		  		
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
		var obj = $("#editFrm select, #editFrm input[type!='button'], #editFrm textarea");
			  		
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

function beforeSaveBoard() {
	var len = $("#boardGrid").jqGrid("getDataIDs").length;
	var cnt = 0;
	var obj = $("#editFrm select, #editFrm input[type!='button'], #editFrm textarea");
	
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
		}
		
		for(var i = 0; i < obj.length; i++) {
			var id = $(obj[i]).attr("id");
			var nm = $("label[for='" + id + "']").text();
			
			if(!(id == "useYn" || id == "commentYn" || id == "boardNote")) {
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
		
		saveBoard();
	}
}

function saveBoard() {
	var url = "";
	var obj = $("#editFrm").serializeObject();
	var id = $("#boardGrid").getGridParam("selrow");
	var idx = $("#boardGrid").getRowData(id).boardNo;
	
	if($("#editFrm input[type='checkbox'][id='useYn']").is(":checked")) obj.useYn = 'Y';
	else obj.useYn = 'N';
	
	if($("#editFrm input[type='checkbox'][id='commentYn']").is(":checked")) obj.commentYn = 'Y';
	else obj.commentYn = 'N';
	
	if(flag == "I") url = "/board/insertBoard.do";
	else if(flag == "U") {
		url = "/board/updateBoard.do";
		obj.boardNo = idx;
	}
	
	Swal.fire({
		icon: "question",
		title: "저장 여부",
		text: "게시판 정보를 저장하시겠습니까?",
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
			  		
			  		boardList();
			  		
			        Swal.fire({
						icon: "success",
						title: "저장 완료",
						text: "게시판 정보를 저장했습니다."
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

function beforeDeleteBoard() {
	var len = $("#boardGrid").getGridParam("records");
	
	if(len > 0) {
		Swal.fire({
			icon: "question",
			title: "삭제 여부",
			text: "게시판을 삭제하시겠습니까?",
			showCancelButton: true,
			confirmButtonText: "예",
			cancelButtonText: "아니오"
		}).then((res) => {
			if(res.isConfirmed) {
				deleteBoard();
			}
		});
	}
}

function deleteBoard() {
	var id = $("#boardGrid").getGridParam("selrow");
	var idx = $("#boardGrid").getRowData(id).boardNo;
	
	if(id != "newRow") {
		$.ajax({
			url: "/board/deleteBoard.do"
		  , data: {"boardNo": idx}
		  , type: "post"
		  , dataType: "json"
		  , async: true
		  , success: function(res) { // 결과 성공 콜백함수
		  		boardList();
		  		
		        Swal.fire({
					icon: "success",
					title: "삭제 완료",
					text: "게시판 삭제를 완료했습니다."
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
		var arr = $("#boardGrid").jqGrid("getDataIDs");
		
		$("#boardGrid").delRowData(id);
		$("#boardGrid").setSelection(arr[0]);
	}
}