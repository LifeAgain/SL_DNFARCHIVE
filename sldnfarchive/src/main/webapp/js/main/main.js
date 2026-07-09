$(function() {
	getVideoData();
	// loginMenuList();
});

function loginMenuList() {
	$.ajax({
		url: "/main/loginMenuList.do"
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	        console.log(res);
	        $("a.collapsed")
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

function goDetail(e) {
	var postNo = $(e).closest("td").attr("id").split("curPost")[1];
	var path = "/board/selectPost.do?boardNo=1"+ "&postNo=" + postNo;
	var js = "/js/board/postDetail.js";
	var val = $(e).closest("tr").children().eq(1).text();
	
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

function getVideoData() {
	var ele = $(".curPostList").eq(1).children().children().not(":first");
	
	$.each(ele, function(index, element) {
		var url = $(element).children().children().attr("href");
		
		$.getJSON("https://noembed.com/embed/", {format: 'json', url: url}, function(data) {
			var title = data.title;
			var lenTitle = (data.title == null || typeof data.title == "undefined") ? "" : title.length;
			
			if(lenTitle > 30) title = title.substring(0, 29) + "...";
			
			$(".curPostList").eq(1).children().children().not(":first").eq(index).children().children().text(title);
		});
	});
}
