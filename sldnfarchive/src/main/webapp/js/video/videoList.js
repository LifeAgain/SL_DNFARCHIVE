$(function() {
	getVideoData();
});

function getVideoData() {
	var ele = $(".post_img").children();
	
	$.each(ele, function(index, element) {
		var url = $(element).attr("href");
		
		$.getJSON("https://noembed.com/embed/", {format: 'json', url: url}, function(data) {
			var title = data.title;
			var lenTitle = (data.title == null || typeof data.title == "undefined") ? "" : title.length;
			
			if(lenTitle > 20) title = title.substring(0, 19) + "...";
			
			$(".board-gallery tr:nth-child(2) td").eq(index).children().text(title);
			
			if(data.thumbnail_url != null || typeof data.thumbnail_url == "undefined") {
				$(".post_img").eq(index).children().children().attr("src", data.thumbnail_url);
			}
		});
	});
}

function schVideo(pageNo) {
	var path = "/video/videoList.do?curPage=" + pageNo;
		
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