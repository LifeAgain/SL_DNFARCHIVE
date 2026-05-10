$(function() {
	if($("#files1").length > 0 || $("#files2").length > 0) {
		$(".board-detail table tr").eq(3).css("backgroundColor", "#ffffff");
		$(".board-detail table tr").eq(5).css("backgroundColor", "#efefef");
	}
});

function goList() {
	var boardNo = $("#boardNo").val();
	var path = "/board/postList.do?boardNo=" + boardNo;
	var js = "/js/board/postList.js";
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
	});
}

function goPost() {
	var boardNo = $("#boardNo").val();
	var postNo = $("#postNo").val();
	var path = "/board/postFrm.do?boardNo=" + boardNo + "&postNo=" + postNo;
	var js = "/js/board/postFrm.js";
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
	});
}

function goDetail(postNo) {
	var boardNo = $("#boardNo").val();
	var path = "/board/selectPost.do?boardNo=" + boardNo + "&postNo=" + postNo;
	var js = "/js/board/postDetail.js";
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
	});
}

function beforeDeletePost() {
	var postNo = $("#postNo").val();
	var len = postNo.length;
	
	if(len > 0) {
		Swal.fire({
			icon: "question",
			title: "삭제 여부",
			text: "게시글을 삭제하시겠습니까?",
			showCancelButton: true,
			confirmButtonText: "예",
			cancelButtonText: "아니오"
		}).then((res) => {
			if(res.isConfirmed) {
				deletePost();
			}
		});
	}
}

function deletePost() {
	var obj = $("#detailFrm").serializeObject();
	var postNo = obj.postNo;
	
	$.ajax({
		url: "/board/deletePost.do"
	  , data: {"postNo": postNo}
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	  		Swal.fire({
				icon: "success",
				title: "삭제 완료",
				text: "게시글 삭제를 완료했습니다."
			});
			
	  		goList();
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

function updateComment(e) {
	var commentNo = $(e).closest("tr").attr("id").split("comment")[1];
	var ele = $(e).closest(".comment-container").children().eq(0);
	var btnUp = $(e).closest(".comment-container").children().eq(1).children().eq(0);
	var btnDel = $(e).closest(".comment-container").children().eq(1).children().eq(1);
	var val = ele.text();
	
	ele.html("<textarea id='content" + commentNo + "' name='content" + commentNo + "' class='form-control m-0'>" + val + "</textarea>");
	
	btnUp.removeClass("fa-pencil");
	btnUp.addClass("fa-check");
	btnUp.attr("onclick", "javascript:beforeSaveComment(" + commentNo + ");");
	btnUp.css("margin-top", "50%");
	btnUp.css("transform", "translateY(-50%)");
	btnDel.css("margin-top", "50%");
	btnDel.css("transform", "translateY(-50%)");
}

function beforeSaveComment(commentNo) {
	var flag = "";
	var val = $("#content" + commentNo).val();
	var len = val.length;
	
	if(len <= 0) {
		Swal.fire({
			icon: "info",
			title: "내용 없음",
			text: "내용을/를 입력해주세요."
		});
		
		return;
	}
	
	saveComment(commentNo);
}

function saveComment(commentNo) {
	var obj = $("#detailFrm").serializeObject();
	obj.commentNo = commentNo;
	obj.content = $("#content" + commentNo).val();
	var url = "";
	var txt = "";
	var postNo = obj.postNo;
	
	if(commentNo > 0) {
		url = "/board/updateComment.do";
		txt = "댓글을 수정하시겠습니까?";
	} else {
		url = "/board/insertComment.do";
		txt = "댓글을 작성하시겠습니까?";
	}
	
	Swal.fire({
		icon: "question",
		title: "작성/수정 여부",
		text: txt,
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
			  		goDetail(postNo);
			  		
			        Swal.fire({
						icon: "success",
						title: "저장 완료",
						text: "댓글 작성을 완료했습니다."
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

function beforeDeleteComment(e) {
	var commentNo = $(e).closest("tr").attr("id").split("comment")[1];
	var len = commentNo.length;
	
	if(len > 0) {
		Swal.fire({
			icon: "question",
			title: "삭제 여부",
			text: "댓글을 삭제하시겠습니까?",
			showCancelButton: true,
			confirmButtonText: "예",
			cancelButtonText: "아니오"
		}).then((res) => {
			if(res.isConfirmed) {
				deleteComment(commentNo);
			}
		});
	}
}

function deleteComment(commentNo) {
	var obj = $("#detailFrm").serializeObject();
	obj.commentNo = commentNo;
	var postNo = obj.postNo;
	
	$.ajax({
		url: "/board/deleteComment.do"
	  , data: obj
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	  		Swal.fire({
				icon: "success",
				title: "삭제 완료",
				text: "댓글 삭제를 완료했습니다."
			});
			
	  		goDetail(postNo);
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