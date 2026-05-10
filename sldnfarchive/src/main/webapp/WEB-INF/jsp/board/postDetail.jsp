<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : postList.jsp
  * @Description : 게시판 모듈
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.04.16	HHP            최초 생성
  *
  * author HHP
  * since 2026.04.16
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
	                		<form:form modelAttribute="postVO" id="detailFrm" name="detailFrm" method="post" onsubmit="javascript: return false;">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>${postInfo.parentNm}</h5></li>
		                            <li class="breadcrumb-item active">${postInfo.boardNm}</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
		                    	<div id="postDetail" class="board-detail">
		                    		<table class="col-12 p-0 m-0">
		                    			<tr>
		                    				<td colspan="2" class="text-center py-3"><strong>${selectPost.title}</strong></td>
		                    			</tr>
		                    			<tr>
		                    				<td colspan="2" class="text-center py-2">
		                    					<ul class="m-0">
		                    						<li><strong>작성자</strong> ${selectPost.userNm}</li>
		                    						<li><strong>작성일자</strong> ${selectPost.fmRegDate}</li>
		                    						<li><strong>조회수</strong> ${selectPost.viewCnt}</li>
		                    					</ul>
		                    				</td>
		                    			</tr>
		                    			<tr>
		                    				<td colspan="2" class="py-5">${selectPost.content}</td>
		                    			</tr>
		                    			<c:forEach var="fileList" items="${fileList}" varStatus="status">
			                    			<tr id="files${fileList.rn}">
			                    				<td class="py-1"><strong>첨부${fileList.rn}</strong></td>
			                    				<td class="py-1">
			                    					<c:if test="${fileList.ext eq 'jpg' or fileList.ext eq 'gif' or fileList.ext eq 'png' or fileList.ext eq 'jpeg' or fileList.ext eq 'bmp' or fileList.ext eq 'tif'}">
			                    						<a href="/images/upload/${fileList.fileNmDtl}" class="text-decoration-none text-black" download>${fileList.fileNm}</a>
			                    					</c:if>
			                    					<c:if test="${not(fileList.ext eq 'jpg' or fileList.ext eq 'gif' or fileList.ext eq 'png' or fileList.ext eq 'jpeg' or fileList.ext eq 'bmp' or fileList.ext eq 'tif')}">
			                    						<a href="/upload/${fileList.fileNmDtl}" class="text-decoration-none text-black" download>${fileList.fileNm}</a>
			                    					</c:if>
			                    				</td>
			                    			</tr>
		                    			</c:forEach>
		                    			<tr>
		                    				<td colspan="2" class="py-2"><strong>댓글(${commentCnt})</strong></td>
		                    			</tr>
		                    			<c:forEach var="commentList" items="${commentList}" varStatus="status">
		                    			<tr id="comment${commentList.commentNo}">
		                    				<td class="py-1">${commentList.userNm}</td>
		                    				<td class="py-1">
		                    					<div class="comment-container row col-12 p-0 m-0">
		                    						<div class="comment-content col-11 ps-0 pe-2 py-0 m-0">${commentList.content}</div>
													<div class="col-1 p-0 m-0">
		                    							<i class="fa-solid fa-pencil ms-2" onclick="javascript:updateComment(this);"></i>
		                    							<i class="fa-solid fa-x ms-2" onclick="javascript:beforeDeleteComment(this);"></i>
		                    						</div>
		                    					</div>
		                    				</td>
		                    			</tr>
		                    			</c:forEach>
		                    			<tr id="comment0">
		                    				<td class="py-1">admin</td>
		                    				<td class="py-1">
		                    					<div class="row col-12 p-0 m-0">
		                    						<div class="col-11 ps-0 pe-2 py-0 m-0">
		                    							<textarea id="content0" name="content0" class="form-control m-0"></textarea>
		                    						</div>
		                    						<div class="col-1 p-0 m-0">
		                    							<div class="row col-12 h-100 p-0 m-0 align-items-center">
		                    								<input type="button" class="btn btn-secondary" onclick="javascript:beforeSaveComment(0);" value="작성" />
		                    							</div>
		                    						</div>
		                    					</div>
		                    				</td>
		                    			</tr>
		                    			<tr>
		                    				<td colspan="2" class="text-end py-2">
		                    					<input type="button" class="btn btn-warning" value="수정" onclick="javascript:goPost();" />
		                    					<input type="button" class="btn btn-danger" value="삭제" onclick="javascript:beforeDeletePost();" />
		                    					<input type="button" class="btn btn-primary" value="목록" onclick="javascript:goList();" />
		                    					<form:hidden path="boardNo" />
		                    					<form:hidden path="postNo" />
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
        <script src="/js/board/postDetail.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>