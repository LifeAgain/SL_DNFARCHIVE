<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : videoList.jsp
  * @Description : 추천공략
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
		    <jsp:include page="/main/loginMenuList.do" flush="true" />
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
	                    		<div id="videoList" class="board-gallery">
			                    	<table class="col-12 p-0 m-0">
			                    		<c:if test="${totalCnt > 0}">
			                    		<tr class="row col-12 p-0 m-0">
			                    			<c:forEach var="videoList" items="${videoList}" varStatus="status">
			                    			<td class="px-1">
			                    				<div class="text-center py-2">
			                    					<div class="post_img">
	                    								<a href="${videoList.videoUrl}" target="_blank"><img src="/images/img_nouser.png" /></a>
	                    							</div>
	                    						</div>
                    							<div class="post_title py-1"><strong></strong></div>
                    							<c:if test="${not empty videoList.userNm}">
                    							<div class="post_author py-1">${videoList.userNm}</div>
                    							</c:if>
                    							<c:if test="${not empty videoList.videoNo}">
                    							<div id="postInfo${videoList.videoNo}" class="post-info row col-12 p-0 m-0 justify-content-between align-items-center">
				                    				<span class="reg-date text-secondary col-auto p-0 my-0 ms-0 me-2">${videoList.fmRegDate}</span>
			                    					<div class="video-btn row col-auto p-0 m-0 gap-2">
			                    						<span class="text-secondary col-auto p-0 m-0"><i class="fa-solid fa-pencil" onclick="javascript:goVideo(this);"></i></span>
			                    						<span class="text-secondary col-auto p-0 m-0"><i class="fa-solid fa-x" onclick="javascript:beforeDeleteVideo(this);"></i></span>
			                    					</div>
			                    				</div>
                    							</c:if>
			                    			</td>
			                    			</c:forEach>
			                    		</tr>
		                    			</c:if>
		                    			
			                    		<c:if test="${totalCnt <= 0}">
		                    			<tr>
		                    				<td colspan="5" class="text-center py-3">게시글이 존재하지 않습니다.</td>
		                    			</tr>
		                    			</c:if>
			                    	</table>
			                    </div>
			                    
		                    	<div id="videoBtn" class="col-12 p-0 mx-0 mb-0 mt-2 text-end">
		                    		<input type="button" class="btn btn-primary" value="작성" onclick="javascript:goVideo(this);" />
		                    	</div>
		                    	<div id="videoPager" class="board-pager col-12 p-0 mx-0 mb-0 mt-2 text-center">
		                    		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="schVideo" />
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
        <script src="/js/board/videoList.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>