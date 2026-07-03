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
		    <%@include file="/WEB-INF/jsp/template/leftnav.jsp" %>
		    <div id="layoutSidenav_content">
		        <main>
		            <div class="container-fluid px-4">
		            	<div class="card card-header mt-4">
		            		<form id="schVideoFrm" class="row col-12 p-0 m-0 justify-content-between" onsubmit="javascript:return false;">
		            			<div class="row col-auto gap-2">
		            				<select id="schType" name="schType" class="form-select w-auto" onchange="javascript:changeSchType();">
		            					<option value="schKeyword">제목+내용</option>
		            					<option value="schAuthor">작성자</option>
		            				</select>
			            			<input type="text" id="schKeyword" name="schKeyword" class="form-control w-auto" value="" onkeyup="javascript:if(event.keyCode == 13) schVideo(1);" />
		            			</div>
		            			<input type="button" class="btn btn-primary col-auto" onclick="javascript:schVideo(1);" value="검색" />
		            		</form>
		            	</div>
		            	
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
		                    			<c:forEach var="i" begin="0" end="1">
		                    			<c:if test="${not empty videoList[(i * 5)]}">
		                    			<c:forEach var="j" begin="0" end="3">
		                    			<tr>
		                    				<c:forEach var="k" begin="0" end="4">
		                    				<c:if test="${j eq 0 and not empty videoList[((i*5) + k)]}">
		                    				<td class="text-center py-2">
		                    					<div class="post_img">
		                    						<a href="${videoList[((i*5) + k)].videoUrl}" target="_blank"><img src="http://img.youtube.com/vi/${fn:substring(videoList[((i*5) + k)].videoUrl, 33, fn:length(videoList[((i*5) + k)].videoUrl))}/0.jpg" /></a>
		                    					</div>
		                    				</td>
		                    				</c:if>
		                    				
		                    				<c:if test="${j eq 1}">
		                    				<td><c:if test="${not empty videoList[((i*5) + k)].title}"><strong>${videoList[((i*5) + k)].title}</strong></c:if></td>
		                    				</c:if>
		                    				
		                    				<c:if test="${j eq 2}">
		                    				<td><c:if test="${not empty videoList[((i*5) + k)].userNm}">${videoList[((i*5) + k)].userNm}</c:if></td>
		                    				</c:if>
		                    				
		                    				<c:if test="${j eq 3}">
		                    				<td>
		                    					<c:if test="${not empty videoList[((i*5) + k)].videoNo}">
			                    					<div id="postInfo${videoList[((i*5) + k)].videoNo}" class="post-info row col-12 p-0 m-0 justify-content-between align-items-center">
					                    				<span class="reg-date text-secondary col-auto p-0 my-0 ms-0 me-3">${videoList[((i*5) + k)].fmRegDate}</span>
				                    					<span class="view-cnt text-secondary col-auto p-0 m-0"><i class="fa-solid fa-eye"></i></span>
				                    				</div>
			                    				</c:if>
		                    				</td>
		                    				</c:if>
		                    				</c:forEach>	
		                    			</tr>
		                    			</c:forEach>
		                    			</c:if>
		                    			</c:forEach>
			                    		</c:if>
			                    		
			                    		<c:if test="${totalCnt <= 0}">
		                    			<tr>
		                    				<td colspan="5" class="text-center py-3">게시글이 존재하지 않습니다.</td>
		                    			</tr>
		                    			</c:if>
			                    	</table>
			                    </div>
			                    
		                    	<div id="videoBtn" class="col-12 p-0 mx-0 mb-0 mt-2 text-end">
		                    		<input type="button" class="btn btn-primary" value="작성" onclick="javascript:goVideo();" />
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