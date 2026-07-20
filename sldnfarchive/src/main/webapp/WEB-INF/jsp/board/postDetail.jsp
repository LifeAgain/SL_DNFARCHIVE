<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : postDetail.jsp
  * @Description : 게시글 상세 모듈
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
		    <jsp:include page="/main/loginMenuList.do" flush="true" />
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
		                    				<c:if test="${postInfo.boardType eq 'A01'}">
		                    				<td colspan="2" class="py-5">${selectPost.content}</td>
		                    				</c:if>
		                    				<c:if test="${postInfo.boardType eq 'A02'}">
		                    				<td colspan="2" class="py-5">
		                    					<c:forEach var="fileList" items="${fileList}" varStatus="status">
		                    						<div class="text-center mb-4">
		                    							<p id="contentImg${fileList.fileNo}" class="content-img">
		                    								<img src="/images/upload/${fileList.fileNmDtl}" />
		                    								<i class="fa-solid fa-x" onclick="javascript:beforeDeleteMapping(this);"></i>
	                    								</p>
		                    						</div>
		                    					</c:forEach>
		                    					<p class="text-start">${selectPost.content}</p>
		                    				</td>
		                    				</c:if>
		                    			</tr>
		                    			<c:if test="${postInfo.boardType eq 'A01'}">
		                    			<c:forEach var="fileList" items="${fileList}" varStatus="status">
			                    			<tr id="files${fileList.rn}">
			                    				<td class="col-2 py-1"><strong>첨부${fileList.rn}</strong></td>
			                    				<td class="col-10 py-1">
			                    					<c:if test="${fileList.ext eq 'jpg' or fileList.ext eq 'gif' or fileList.ext eq 'png' or fileList.ext eq 'jpeg' or fileList.ext eq 'bmp' or fileList.ext eq 'tif'}">
			                    						<a href="/images/upload/${fileList.fileNmDtl}" class="text-decoration-none text-black" download>${fileList.fileNm}</a>
			                    					</c:if>
			                    					<c:if test="${not(fileList.ext eq 'jpg' or fileList.ext eq 'gif' or fileList.ext eq 'png' or fileList.ext eq 'jpeg' or fileList.ext eq 'bmp' or fileList.ext eq 'tif')}">
			                    						<a href="/upload/${fileList.fileNmDtl}" class="text-decoration-none text-black" download>${fileList.fileNm}</a>
			                    					</c:if>
			                    				</td>
			                    			</tr>
		                    			</c:forEach>
		                    			</c:if>
		                    			<c:if test="${postInfo.commentYn eq 'Y'}">
		                    			<tr>
		                    				<td colspan="2" class="py-2"><strong>댓글(${commentCnt})</strong></td>
		                    			</tr>
		                    			<c:forEach var="commentList" items="${commentList}" varStatus="status">
		                    			<tr id="comment${commentList.commentNo}">
		                    				<td class="col-2 py-1 text-end pe-2">${commentList.userNm}</td>
		                    				<td class="col-10 py-1">
		                    					<div class="comment-container row col-12 p-0 m-0">
		                    						<c:if test="${(postInfo.boardNo eq 1 and userIdx eq 1) or (postInfo.boardNo ne 1 and (userIdx eq 1 or commentList.regNo eq userIdx))}">
		                    						<div class="comment-content col-8 col-sm-10 col-md-11 ps-0 pe-2 py-0 m-0">${commentList.content}</div>
													<div class="col-4 col-sm-2 col-md-1 p-0 m-0">
														<div class="row col-12 h-100 p-0 m-0 gap-2 align-items-center justify-content-end">
                    										<i class="col-auto px-0 fa-solid fa-pencil" onclick="javascript:updateComment(this);"></i>
                    										<i class="col-auto px-0 fa-solid fa-check d-none" onclick="javascript:beforeSaveComment(this);"></i>
                    										<i class="col-auto px-0 fa-solid fa-x" onclick="javascript:beforeDeleteComment(this);"></i>
	                    								</div>
	                    							</div>
	                    							</c:if>
	                    							<c:if test="${(postInfo.boardNo eq 1 and userIdx ne 1) or (postInfo.boardNo ne 1 and not(userIdx eq 1 or commentList.regNo eq userIdx))}">
		                    						<div class="comment-content col-12 ps-0 pe-2 py-0 m-0">${commentList.content}</div>	
	                    							</c:if>
		                    					</div>
		                    				</td>
		                    			</tr>
		                    			</c:forEach>
		                    			<tr id="comment0">
		                    				<td class="col-2 py-1 text-end pe-2">${userNm}</td>
		                    				<td class="col-10 py-1">
		                    					<div class="row col-12 p-0 m-0">
		                    						<div class="col-8 col-sm-10 col-md-11 ps-0 pe-2 py-0 m-0">
		                    							<textarea id="content0" name="content0" class="form-control m-0"></textarea>
		                    						</div>
		                    						<div class="col-4 col-sm-2 col-md-1 p-0 m-0">
		                    							<div class="row col-12 h-100 p-0 m-0 align-items-center">
		                    								<input type="button" class="btn btn-secondary" onclick="javascript:beforeSaveComment(this);" value="작성" />
		                    							</div>
		                    						</div>
		                    					</div>
		                    				</td>
		                    			</tr>
		                    			</c:if>
		                    			<tr>
		                    				<td colspan="2" class="text-end py-2">
		                    					<c:if test="${(postInfo.boardNo eq 1 and userIdx eq 1) or (postInfo.boardNo ne 1 and (userIdx eq 1 or selectPost.regNo eq userIdx))}">
		                    					<input type="button" class="btn btn-warning" value="수정" onclick="javascript:goPost();" />
		                    					<input type="button" class="btn btn-danger" value="삭제" onclick="javascript:beforeDeletePost();" />
		                    					</c:if>
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