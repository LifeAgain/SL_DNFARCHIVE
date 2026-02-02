<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : loginAdm.jsp
  * @Description : 로그인화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.01.16	HHP            최초 생성
  *
  * author HHP
  * since 2026.01.16
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@include file="/WEB-INF/jsp/template/header.jsp" %>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Login</h3></div>
                                    <div class="card-body">
                                        <form id="loginFrm" onsubmit="javascript:return false;">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="userMail" name="userMail" type="text" placeholder="name" />
                                                <label for="userMail">ID</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="userPw" name="userPw" type="password" placeholder="Password" onkeyup="javascript:if(event.keyCode==13) login();" />
                                                <label for="userPw">PW</label>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mb-3">
	                                            <div class="form-check">
	                                                <input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" />
	                                                <label class="form-check-label" for="inputRememberPassword">비밀번호 기억</label>
	                                            </div>
	                                            <div>
	                                            	<a class="btn btn-primary" href="#" onclick="javascript:login();">Login</a>
	                                            </div>
	                                        </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <%@include file="/WEB-INF/jsp/template/innerFooter.jsp" %>
            </div>
        </div>
        <script src="/js/login/login.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>
