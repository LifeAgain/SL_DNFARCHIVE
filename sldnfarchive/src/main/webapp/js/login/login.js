function login() {
	var obj = $("#loginFrm").serializeObject();
	
	$.ajax({
		url: "/login/loginCheckAdm.do"
	  , data: obj
	  , type: "post"
	  , dataType: "json"
	  , async: true
	  , success: function(res) { // 결과 성공 콜백함수
	        if(res.loginCnt == 1) {
	        	console.log("Login Success");
	        	// window.location.href = "/main/main.do";
	        } else {
				Swal.fire({
					icon: "info",
					title: "로그인 실패",
					text: "ID/비밀번호를 확인해주세요."
				});
	        }
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