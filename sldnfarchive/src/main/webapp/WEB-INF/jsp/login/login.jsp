<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : login.jsp
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
                                    <div class="card-header">
                                    	<h3 class="text-center font-weight-light my-4">Login</h3>
                                    </div>
                                    
                                   	<div class="card-body">
                                           <div class="mb-3 text-center">
	                                       		<a class="btn p-0 border-0">
	                                           		<img src="/images/kakao_login_medium_wide.png" />
	                                           	</a>
                                           </div>
                                           <div class="text-center">
                                               <input type="button" class="btn btn-primary btn-login" onclick="javascript:location.href='/login/loginAdm.do'" value="관리자 로그인" />
                                           </div>
                                   	</div>
                                   	
                                    <div class="card-footer text-center py-3">
                                        <div class="small"></div>
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
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>
