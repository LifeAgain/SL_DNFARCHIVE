$(function() {
	var title = $(".board-gallery tr:nth-child(2) td strong").text();
	var len = title.length;
	
	if(len > 20) {
		title = title.substring(0, 19) + "...";
		$(".board-gallery tr:nth-child(2) td strong").text(title);
	}
});

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

function schVideo(pageNo) {
	var path = "";
	var key = $("#schType").val();
	var val = "";
	
	if(key == "schKeyword") {
		val = $("#schKeyword").val();
		
		if(val.length > 0) path = "/video/videoList.do?schKeyword=" + val + "&curPage=" + pageNo;
		else path = "/video/videoList.do?curPage=" + pageNo;
	} else if(key == "schAuthor") {
		val = $("#schAuthor").val();
		
		if(val.length > 0) path = "/video/videoList.do?schAuthor=" + val + "&curPage=" + pageNo;
		else path = "/video/videoList.do?curPage=" + pageNo;
	}
	
	$(".card-body").children().remove();
	$(".card-body").load(path + " #videoList, #videoBtn, #videoPager", function() {
		
	});
}

function goVideo(e) {
	var ele = $(e).parent().attr("id");
	var videoNo = 0;
	if(ele != "videoBtn") videoNo = $(e).closest(".post-info").attr("id").split("postInfo")[1];
	var path = "/video/videoFrm.do?videoNo=" + videoNo;
	var js = "/js/video/videoFrm.js";
	
	$("#layoutSidenav_content").children().remove();
	$("#layoutSidenav_content").load(path + " main, footer", function() {
		$.getScript(js);
	});
}

function beforeDeleteVideo(e) {
	var videoNo = $(e).closest(".post-info").attr("id").split("postInfo")[1];
	var len = videoNo.length;
	
	if(len > 0) {
		Swal.fire({
			icon: "question",
			title: "삭제 여부",
			text: "동영상을 삭제하시겠습니까?",
			showCancelButton: true,
			confirmButtonText: "예",
			cancelButtonText: "아니오"
		}).then((res) => {
			if(res.isConfirmed) {
				deleteVideo(videoNo);
			}
		});
	}
}

function deleteVideo(videoNo) {
	$.ajax({
		url: "/video/deleteVideo.do"
	  , data: {"videoNo": videoNo}
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