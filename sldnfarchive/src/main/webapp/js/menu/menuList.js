var flag = "";
var maxLcd = "";
var maxScd = [];

$(function() {
	menuList();
});

function menuList() {
	var data = $("#schUseYn").val();
	
	$.ajax({
		url: "/menu/selectMenuList.do"
	  , data: {schUseYn: data}
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	  		maxLcd = res.maxLcd;
	  		maxScd = res.maxScd;
	  		
	        $("#jstree").jstree({
	        	"core": {
	        		"data": res.menuList
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
	        	var len = $("#jstree").jstree(true).get_json('#', {flat:true}).length;
			
				if(len > 0) {
					$("#jstree").jstree(true).open_all();
		        	$("#jstree").jstree(true).select_node("A00");
				}
				else $("#editFrm input[type!='button'], #editFrm textarea").val("");
	        }).on("select_node.jstree", function(e, data) {
	        	editMenu();
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

function schMenu() {
	var schMenuNm = $("#schMenuNm").val();
	
	$("#jstree").jstree(true).search(schMenuNm);
}

function schMenuUseYn() {
	$("#jstree").jstree(true).destroy();
	menuList();
}

function insertMenu(cat) {
	var baseCd = $("#jstree").jstree(true).get_selected()[0];
	var newBaseCd = "";
	var parent = "";
	var type = "";
	var newId = "";
	var nodeExists1 = false;
	var nodeExists2 = false;
	
	if(cat == "L") {
		parent = "#";
		type = "default";
		
		if(baseCd) {
			for(var i = 0; i < maxScd.length; i++) {
				if(baseCd.substr(0, 1) == maxScd[i].menuLcd) {
					newBaseCd = maxScd[i].menuLcd + String(Number(maxScd[i].maxScd) + 1).padStart(2, '0')
				}
			}
		}
		
		if(!maxLcd) {
			newId = "A00";
		} else {
			newId = String.fromCharCode(maxLcd.charCodeAt(0) + 1) + "00";
			nodeExists1 = $("#jstree").jstree(true).get_node(newId);
			nodeExists2 = $("#jstree").jstree(true).get_node(newBaseCd);
		}
	} else if(cat == "S") {
		if(!baseCd) {
			Swal.fire({
				icon: "info",
				title: "상위메뉴 없음",
				text: "상위메뉴가 존재하지 않습니다. 다시 확인해주세요."
			});
			
			return;
		} else {
			parent = baseCd.substr(0, 1) + "00";
			type = "file";
			newBaseCd = String.fromCharCode(maxLcd.charCodeAt(0) + 1) + "00";
			
			for(var i = 0; i < maxScd.length; i++) {
				if(baseCd.substr(0, 1) == maxScd[i].menuLcd) {
					newId = maxScd[i].menuLcd + String(Number(maxScd[i].maxScd) + 1).padStart(2, '0')
				}
			}
			
			nodeExists1 = $("#jstree").jstree(true).get_node(newBaseCd);
			nodeExists2 = $("#jstree").jstree(true).get_node(newId);
		}
	}
	
	if(nodeExists1 || nodeExists2) {
		Swal.fire({
			icon: "info",
			title: "알림",
			text: "기존 입력 중인 정보가 존재합니다."
		});
		
		return false;
	}
	
	$("#jstree").jstree(true).create_node(parent, {"id": newId, "text": "", "type": type}, "last");
	$("#jstree").jstree(true).deselect_all();
	$("#jstree").jstree(true).select_node(newId);
}

function editMenu() {
	var menuCd = $("#jstree").jstree(true).get_selected()[0];
	var menuLcd = menuCd.substr(0, 1);
	var menuScd = menuCd.substr(1, 2);
	
	$.ajax({
		url: "/menu/selectMenu.do"
	  , data: {"menuLcd": menuLcd, "menuScd": menuScd}
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	  		var obj = res.selectMenu;
	  		
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
		  			
		  			if(id == "menuLcd") val = menuLcd;
		  			else if(id == "menuScd") val = menuScd;
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

function beforeSaveMenu() {
	var len = $("#jstree").jstree(true).get_json('#', {flat:true}).length;
	var cnt = 0;
	var obj = $("#editFrm input[type!='button'], #editFrm textarea");
	
	if(len > 0) {
		if(flag == "U") {
			for(var i = 0; i < obj.length; i++) {
				var id = $(obj[i]).attr("id");
				
				if(!(id == "menuLcd" || id == "menuScd")) {
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
		}
		
		for(var i = 0; i < obj.length; i++) {
			var id = $(obj[i]).attr("id");
			var nm = $("label[for='" + id + "']").text();
			
			if(!(id == "useYn" || id == "menuNote")) {
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
		
		saveMenu();
	}
}

function saveMenu() {
	var url = "";
	var obj = $("#editFrm").serializeObject();
	
	if($("#editFrm input[type='checkbox']").is(":checked")) obj.useYn = 'Y';
	else obj.useYn = 'N';
	
	if(flag == "I") url = "/menu/insertMenu.do";
	else if(flag == "U") url = "/menu/updateMenu.do";
	
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
			  		
			        Swal.fire({
						icon: "success",
						title: "저장 완료",
						text: "메뉴 정보를 저장했습니다."
					});
					
					$("#jstree").jstree(true).destroy();
					menuList();
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

function beforeDeleteMenu() {
	var len = $("#jstree").jstree(true).get_json('#', {flat:true}).length;
			
	if(len > 0) {
		Swal.fire({
			icon: "question",
			title: "삭제 여부",
			text: "메뉴를 삭제하시겠습니까?",
			showCancelButton: true,
			confirmButtonText: "예",
			cancelButtonText: "아니오"
		}).then((res) => {
			if(res.isConfirmed) {
				deleteMenu();
			}
		});
	}
}

function deleteMenu() {
	var obj = $("#editFrm").serializeObject();
	
	$.ajax({
		url: "/menu/deleteMenu.do"
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
			
			$("#jstree").jstree(true).destroy();
			menuList();
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