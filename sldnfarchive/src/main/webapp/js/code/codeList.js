var flag = "";

$(function() {
	codeList();
});

function codeList() {
	$.ajax({
		url: "/code/selectCodeList.do"
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	        $("#jstree").jstree({
	        	"core": {
	        		"data": res.codeList
	        	  , "check_callback": true
	        	}
	          , "plugins": ["types", "search"]
	          , "types": {
	          		"file": {
	          			"icon": "jstree-default jstree-file"
	          		  , "max-children": 0
	          	  	  , "max-depth": 0
	          	  	}
	          	}
	        }).on("loaded.jstree", function(e, data) {
	        	$("#jstree").jstree(true).open_all();
	        	$("#jstree").jstree(true).select_node("A00");
	        }).on("select_node.jstree", function(e, data) {
	        	editCode();
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

function schCode() {
	var schCodeNm = $("#schCodeNm").val();
	
	$("#jstree").jstree(true).search(schCodeNm);
}

function insertCode(cat) {
	var baseCd = $("#jstree").jstree(true).get_selected()[0];
	var parent = "";
	var type = "";
	var cdArr = null;
	var maxNum = 0;
	var obj = null;
	var maxId = "";
	var newId = "";
	
	if(cat == "L") {
		parent = "#";
		type = "default";
		
		if(!baseCd) {
			newId = "A00";
		} else {
			cdArr = $("a.jstree-anchor[aria-level=1]");
			maxNum = cdArr.length-1;
			obj = $(cdArr[maxNum]);
			maxId = obj.attr("id").substr(0, 1);
			newId = String.fromCharCode(maxId.charCodeAt(0) + 1) + "00";
		}
	} else if(cat == "S") {
		if(!baseCd) {
			Swal.fire({
				icon: "info",
				title: "상위분류 없음",
				text: "상위분류 코드가 존재하지 않습니다. 다시 확인해주세요."
			});
			
			return;
		} else {
			$("#jstree").jstree(true).open_all();
			
			parent = baseCd.substr(0, 1) + "00";
			type = "file";
			cdArr = $("a.jstree-anchor[id^='" + parent.substr(0, 1) + "']");
			maxNum = cdArr.length-1;
			obj = $(cdArr[maxNum]);
			maxId = parseInt(obj.attr("id").substr(1, 2));
			maxId = maxId + 1;
			newId = parent.substr(0, 1) + String(maxId).padStart(2, '0');
		}
	}
	
	$("#jstree").jstree(true).create_node(parent, {"id": newId, "text": "", "type": type}, "last");
	$("#jstree").jstree(true).deselect_all();
	$("#jstree").jstree(true).select_node(newId);
}

function editCode() {
	var codeCd = $("#jstree").jstree(true).get_selected()[0];
	var codeLcd = codeCd.substr(0, 1);
	var codeScd = codeCd.substr(1, 2);
	
	$.ajax({
		url: "/code/selectCode.do"
	  , data: {"codeLcd": codeLcd, "codeScd": codeScd}
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	  		var obj = res.selectCode;
	  		
	  		if(obj) {
		  		flag = "U";
		  		
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
		  	} else {
		  		flag = "I";
		  		
		  		obj = $("#editFrm input[type!='button'], #editFrm textarea");
		  		
		  		for(var i = 0; i < obj.length; i++) {
		  			var id = $(obj[i]).attr("id");
		  			var val = "";
		  			
		  			if(id == "codeLcd") val = codeLcd;
		  			else if(id == "codeScd") val = codeScd;
		  			else val = "";
		  			
		  			$("#" + id).val(val);
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

function beforeSaveCode() {
	var len = $("#jstree").jstree(true).get_json('#', {flat:true}).length;
	var cnt = 0;
	var obj = $("#editFrm input[type!='button'], #editFrm textarea");
	
	if(len > 0) {
		if(flag == "U") {
			for(var i = 0; i < obj.length; i++) {
				var id = $(obj[i]).attr("id");
				
				if(!(id == "codeLcd" || id == "codeScd")) {
					if($(obj[i]).data("changeYn") == "Y") cnt++;
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
		} else if(flag == "I") {
			for(var i = 0; i < obj.length; i++) {
				var id = $(obj[i]).attr("id");
				var nm = $("label[for='" + id + "']").text();
				
				if(!(id == "useYn" || id == "codeNote")) {
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
		
		saveCode();
	}
}

function saveCode() {
	var url = "";
	var obj = $("#editFrm").serializeObject();
	
	if($("#editFrm input[type='checkbox']").is(":checked")) obj.useYn = 'Y';
	else obj.useYn = 'N';
	
	if(flag == "I") url = "/code/insertCode.do";
	else if(flag == "U") url = "/code/updateCode.do";
	
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
			  		
			  		$("#jstree").jstree(true).rename_node(obj.codeLcd + obj.codeScd, obj.codeNm);
			  		
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

function beforeDeleteCode() {
	var len = $("#jstree").jstree(true).get_json('#', {flat:true}).length;
	
	if(len > 0) {
		Swal.fire({
			icon: "question",
			title: "삭제 여부",
			text: "공통코드를 삭제하시겠습니까? (대분류코드를 삭제할 경우, 소분류코드도 같이 삭제됩니다.)",
			showCancelButton: true,
			confirmButtonText: "예",
			cancelButtonText: "아니오"
		}).then((res) => {
			if(res.isConfirmed) {
				deleteCode();
			}
		});
	}
}

function deleteCode() {
	var obj = $("#editFrm").serializeObject();
	
	$.ajax({
		url: "/code/deleteCode.do"
	  , data: obj
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	        Swal.fire({
				icon: "success",
				title: "삭제 완료",
				text: "메뉴 삭제를 완료했습니다."
			});
			
			$("#jstree").jstree(true).delete_node(obj.codeLcd + obj.codeScd);
			
			var len = $("#jstree").jstree(true).get_json('#', {flat:true}).length;
			
			if(len > 0) $("#jstree").jstree(true).select_node("A00");
			else $("#editFrm input[type!='button'], #editFrm textarea").val("");
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