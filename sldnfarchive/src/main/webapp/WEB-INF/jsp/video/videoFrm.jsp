<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : videoFrm.jsp
  * @Description : 동영상 작성 폼 모듈
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.06.16	HHP            최초 생성
  *
  * author HHP
  * since 2026.06.16
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<%@include file="/WEB-INF/jsp/template/header.jsp" %>
	<body class="sb-nav-fixed">
		<%@include file="/WEB-INF/jsp/template/topnav.jsp" %>
		<div id="layoutSidenav">
		    <%@include file="/WEB-INF/jsp/template/leftnav.jsp" %>
		    <div id="layoutSidenav_content">
		        <main>
		            <div class="container-fluid px-4">
	                	<div class="card my-4">
	                		<form:form modelAttribute="videoVO" id="videoFrm" name="videoFrm" method="post" onsubmit="javascript: return false;">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>추천공략</h5></li>
		                            <li class="breadcrumb-item active">추천공략</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
		                    	<div class="board-frm">
			                    	<table class="col-12 p-0 m-0">
			                    		<tr>
			                    			<td class="text-end pe-2 py-2"><label for="videoUrl"><strong>영상URL</strong></label></td>
			                    			<td class="p-2">
			                    				<c:if test="${flag eq 'I'}">
			                    					<input type="text" id="videoUrl" name="videoUrl" class="form-control m-0" onchange="javascript:chkChangeVal(this);" placeholder="영상URL" value="" />
			                    				</c:if>
			                    				<c:if test="${flag eq 'U'}">
			                    					<input type="text" id="videoUrl" name="videoUrl" class="form-control m-0" onchange="javascript:chkChangeVal(this);" placeholder="영상URL" value="${selectVideo.videoUrl}" />
			                    				</c:if>
			                    			</td>
			                    		</tr>
			                    		<tr>
				                    		<td colspan="2" class="text-end py-2">
				                    			<input type="button" class="btn btn-primary btn-save" onclick="javascript:beforeSaveVideo();" value="작성" />
				                    			<input type="button" class="btn btn-secondary" onclick="javascript:goList();" value="취소" />
				                    			<form:hidden path="videoNo" />
				                    		</td>
				                    	</tr>
			                    	</table>
			                    </div>
		                    </div>
		                    
		                    <hr class="m-0 py-3" />
		                    </form:form>
	                    </div>
                    </div>
                </main>
	            <%@include file="/WEB-INF/jsp/template/innerFooter.jsp" %>
            </div>
        </div>
        <script src="/js/video/videoFrm.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>