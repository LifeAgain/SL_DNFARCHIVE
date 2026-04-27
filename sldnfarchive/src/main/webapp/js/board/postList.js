function changeSchType() {
	var schType = $("#schType").val();
	
	if(schType == "schAuthor") {
		$("#schKeyword").attr("id", "schAuthor");
		$("#schKeyword").attr("name", "schAuthor");
	} else if(schType == "schKeyword") {
		$("#schAuthor").attr("id", "schKeyword");
		$("#schAuthor").attr("name", "schKeyword");
	}
}

function schPost(pageNo) {
	var path = "";
	var key = $("#schType").val();
	var val = "";
	
	if(key == "schKeyword") {
		val = $("#schKeyword").val();
		
		if(val.length > 0) path = "/board/postList.do?schKeyword=" + val + "&curPage=" + pageNo;
		else path = "/board/postList.do?curPage=" + pageNo;
	} else if(key == "schAuthor") {
		val = $("#schAuthor").val();
		
		if(val.length > 0) path = "/board/postList.do?schAuthor=" + val + "&curPage=" + pageNo;
		else path = "/board/postList.do?curPage=" + pageNo;
	}
	
	$(".card-body").children().remove();
	$(".card-body").load(path + " #postList, #postBtn, #postPager");
}

function goDetail() {
	var path = "/board/selectPost.do";
	var js = "/js/board/postList.js";
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
	});
}

function goPost() {
	var path = "/board/postFrm.do";
	var js = "/js/board/postFrm.js";
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
	});
}