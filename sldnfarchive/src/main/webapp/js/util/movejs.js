function movejs_menu(path) {
	var js = "/js/" + path.split("/")[1] + "/" + path.split("/")[2].replace(".do", "") + ".js";
	var util = "/js/util/file.js";
	
	if(path.includes("/board/postList.do")) {
		var boardNo = path.split("/board/postList.do?boardNo=")[1];
		js = js.replace("?boardNo=" + boardNo, "");
	}
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
		
		if(path == "/user/userList.do") $.getScript(util);
	});
}