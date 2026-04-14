function movejs_menu(num) {
	var path = "";
	
	if(num == 1) {
		path = "/menu/menuList.do";
		js = "/js/menu/menuList.js";
	} else if(num == 2) {
		path = "/code/codeList.do";
		js = "/js/code/codeList.js";
	} else if(num == 3) {
		path = "/user/userList.do";
		js = "/js/user/userList.js";
	} else if(num == 4) {
		path = "/board/boardList.do";
		js = "/js/board/boardList.js";
	}
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main", function() {
		$.getScript(js);
	});
}