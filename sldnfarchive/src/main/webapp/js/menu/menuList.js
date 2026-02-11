$(function() {
	menuList();
});

function menuList() {
	$.ajax({
		url: "/menu/selectMenuList.do"
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	  		console.log(res);
	        $("#jstree").jstree({
	        	"core": {
	        		"data": res.menuList
	        	}
	          , "plugins": ["types", "search"]
	          , "types": {
	          		"default": {
	          			"icon": "fa fa-folder text-primary"
	          		}
	          	  , "file": {
	          	  		"icon": ".jstree-themeicon-custom.ico-file"
	          	  	}
	          	}
	        }).on("loaded.jstree", function(e, data) {
	        	$("#jstree").jstree(true).open_all();
	        	$("#jstree").jstree(true).select_node("A00");
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