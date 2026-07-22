$(function() {
	detailView();
});

function detailView() {
	var boardNo = $("#boardNo").val();
	var boardType = "";
	var content = null;
	var title = $(".board-detail table tr:first-child td strong").text();
	var lenTitle = title.length;
	var lenComm = $("#comment0").length;
	
	if(lenTitle > 25) {
		title = title.substring(0, 24) + "...";
		
		$(".board-detail table tr:first-child td strong").text(title);
	}
	
	if(boardNo == 1) boardType = "A01";
	else boardType = "A02";
	
	if(boardType == "A01") {
		content = $("#postDetail table tr").eq(2).children().eq(0).text();
		content = content.replace(/(?:\r\n|\r|\n)/g, "<br/>");
		
		$("#postDetail table tr").eq(2).children().eq(0).html(content);
	} else if(boardType == "A02") {
		if($(".content-img").length > 0) {
			content = $("#postDetail table tr").eq(2).children().eq(0).children().eq(1).text();
			content = content.replace(/(?:\r\n|\r|\n)/g, "<br/>");	
			
			$("#postDetail table tr").eq(2).children().eq(0).children().eq(1).html(content);
		} else {
			content = $("#postDetail table tr").eq(2).children().eq(0).text();
			content = content.replace(/(?:\r\n|\r|\n)/g, "<br/>");
			var contentArr = content.split("<br/>");
			var newContent = "";
			
			for(var i = 0; i < contentArr.length; i++) {
				if(!(i == 0 || i == 1 || i == (contentArr.length - 1))) newContent = newContent + contentArr[i] + "<br/>";
			}
			
			$("#postDetail table tr").eq(2).children().eq(0).html(newContent);
		}
	}
	
	if(lenComm > 0) {
		if($("#files1").length <= 0 && $("#files2").length <= 0) {
			$(".board-detail table tr").eq(3).css("backgroundColor", "#efefef");
			$(".board-detail table tr").eq(4).css("backgroundColor", "#ffffff");
			$(".board-detail table tr").eq(5).css("backgroundColor", "#ffffff");
		}
		
		if($("#files1").length > 0 || $("#files2").length > 0) {
			$(".board-detail table tr").eq(3).css("backgroundColor", "#ffffff");
			$(".board-detail table tr").eq(4).css("backgroundColor", "#efefef");
			$(".board-detail table tr").eq(5).css("backgroundColor", "#ffffff");
		}
		
		if($("#files1").length > 0 && $("#files2").length > 0) {
			$(".board-detail table tr").eq(3).css("backgroundColor", "#ffffff");
			$(".board-detail table tr").eq(4).css("backgroundColor", "#ffffff");
			$(".board-detail table tr").eq(5).css("backgroundColor", "#efefef");
		}
	} else {
		$(".board-detail table tr").eq(3).css("backgroundColor", "#ffffff");
		$(".board-detail table tr").eq(4).css("backgroundColor", "#ffffff");
		$(".board-detail table tr").eq(5).css("backgroundColor", "#ffffff");
	}
}

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
	var filejs = "/js/util/file.js";
	var boardType = "";
	
	if($(".board-list").length > 0) boardType = "A01";
	else if($(".board-gallery").length > 0) boardType = "A02";
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
		
		if(boardType == "A02") $.getScript(filejs);
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

function beforeDeleteMapping(e) {
	var postNo = $("#postNo").val();
	var fileNo = $(e).closest(".content-img").attr("id").replace("contentImg", "");
	var pLen = postNo.length;
	var fLen = fileNo.length;
	
	if(pLen > 0 && fLen > 0) {
		Swal.fire({
			icon: "question",
			title: "삭제 여부",
			text: "사진을 삭제하시겠습니까?",
			showCancelButton: true,
			confirmButtonText: "예",
			cancelButtonText: "아니오"
		}).then((res) => {
			if(res.isConfirmed) {
				deleteMapping(fileNo);
			}
		});
	}
}

function deleteMapping(fileNo) {
	var obj = $("#detailFrm").serializeObject();
	var postNo = obj.postNo;
	
	$.ajax({
		url: "/board/deleteMapping.do"
	  , data: {"postNo": postNo, "fileNo": fileNo}
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	  		Swal.fire({
				icon: "success",
				title: "삭제 완료",
				text: "게시글 삭제를 완료했습니다."
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

function updateComment(e) {
	var commentId = $(e).closest("tr").attr("id");
	var commentNo = commentId.split("comment")[1];
	var ele = $("#" + commentId).children().eq(1).children().children().eq(0);
	var val = ele.text();
	var btnUp = $("#" + commentId).children().eq(1).children().children().eq(1).children().children().eq(0);
	var btnSave = $("#" + commentId).children().eq(1).children().children().eq(1).children().children().eq(1);
	var otherEle = $("tr[id^='comment'][id!='" + commentId + "'][id!='comment0']");
	
	ele.html("<textarea class='col-12 m-0 form-control'>" + val + "</textarea>");
	btnUp.addClass("d-none");
	btnSave.removeClass("d-none");
	
	for(var i = 0; i < otherEle.length; i++) {
		var otherSubEle = otherEle.eq(i).children().eq(1).children().children().eq(0);
		var otherVal = otherSubEle.text();
		var otherBtnUp = otherEle.eq(i).children().eq(1).children().children().eq(1).children().children().eq(0);
		var otherBtnSave = otherEle.eq(i).children().eq(1).children().children().eq(1).children().children().eq(1);
		
		otherSubEle.html(otherVal);
		otherBtnUp.removeClass("d-none");
		otherBtnSave.addClass("d-none");
	}
}

function beforeSaveComment(e) {
	var commentId = $(e).closest("tr").attr("id");
	var commentNo = commentId.split("comment")[1];
	var flag = "";
	var val = $("#comment" + commentNo + " textarea").val();
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
	obj.content = $("#comment" + commentNo + " textarea").val();
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