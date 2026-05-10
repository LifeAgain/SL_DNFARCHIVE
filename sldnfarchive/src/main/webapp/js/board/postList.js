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
	var boardNo = $("#boardNo").val();
	var path = "";
	var key = $("#schType").val();
	var val = "";
	
	if(key == "schKeyword") {
		val = $("#schKeyword").val();
		
		if(val.length > 0) path = "/board/postList.do?boardNo=" + boardNo + "&schKeyword=" + val + "&curPage=" + pageNo;
		else path = "/board/postList.do?boardNo=" + boardNo + "&curPage=" + pageNo;
	} else if(key == "schAuthor") {
		val = $("#schAuthor").val();
		
		if(val.length > 0) path = "/board/postList.do?boardNo=" + boardNo + "&schAuthor=" + val + "&curPage=" + pageNo;
		else path = "/board/postList.do?boardNo=" + boardNo + "&curPage=" + pageNo;
	}
	
	$(".card-body").children().remove();
	$(".card-body").load(path + " #postList, #postBtn, #postPager", function() {
		
	});
}

function goDetail(postNo) {
	var boardNo = $("#boardNo").val();
	var path = "/board/selectPost.do?boardNo=" + boardNo + "&postNo=" + postNo;
	var js = "/js/board/postDetail.js";
	var val = $("#postTitle" + postNo).closest("tr").children().eq(4).text();
	
	$.ajax({
		url: "/board/updateViewCnt.do"
	  , data: {
	  		"viewCnt": val
	  	  , "postNo": postNo
	  	}
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	        $("#layoutSidenav_content").children().remove();
			$("#layoutSidenav_content").load(path + " main, footer", function() {
				$.getScript(js);
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

function goPost() {
	var boardNo = $("#boardNo").val();
	var path = "/board/postFrm.do?boardNo=" + boardNo + "&postNo=0";
	var js = "/js/board/postFrm.js";
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
	});
}