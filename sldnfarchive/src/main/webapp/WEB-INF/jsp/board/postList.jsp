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
		    <jsp:include page="/main/loginMenuList.do" flush="true" />
		    <div id="layoutSidenav_content">
		        <main>
		            <div class="container-fluid px-4">
		            	<div class="card card-header mt-4">
		            		<form id="schPostFrm" class="row d-block d-md-flex col-12 p-0 m-0 justify-content-between" onsubmit="javascript:return false;">
		            			<div class="row d-block d-md-flex col-12 p-0 m-0 col-md-auto gap-2">
		            				<div class="col-12 col-md-auto p-0 m-0">
			            				<select id="schType" name="schType" class="form-select" onchange="javascript:changeSchType();">
			            					<option value="schKeyword">제목+내용</option>
			            					<option value="schAuthor">작성자</option>
			            				</select>
		            				</div>
		            				<div class="col-12 col-md-auto p-0 mx-0 mb-0 mt-2 mt-md-0">
			            				<input type="text" id="schKeyword" name="schKeyword" class="form-control w-100" value="" onkeyup="javascript:if(event.keyCode == 13) schPost(1);" />
		            				</div>
		            			</div>
		            			<div class="text-end text-md-start w-auto px-0 mt-2 mt-md-0">
		            				<input type="button" class="btn btn-primary col-auto" onclick="javascript:schPost(1);" value="검색" />
		            			</div>
		            		</form>
		            	</div>
		            	
	                	<div class="card my-4">
	                		<form:form modelAttribute="postVO" id="postFrm" name="postFrm" method="post" onsubmit="javascript: return false;">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>${postInfo.parentNm}</h5></li>
		                            <li class="breadcrumb-item active">${postInfo.boardNm}</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
		                    	<c:if test="${postInfo.boardType eq 'A01'}">
		                    		<div id="postList" class="board-list">
				                    	<table class="col-12 p-0 m-0">
				                    		<tr>
				                    			<th class="col-2 col-md-1 text-center py-1">번호</th>
				                    			<th class="col-7 col-md-6 text-center py-1">제목</th>
				                    			<th class="d-none d-md-table-cell col-sm-2 text-center py-1">작성자</th>
				                    			<th class="d-none d-md-table-cell col-sm-2 text-center py-1">작성일자</th>
				                    			<th class="col-3 col-md-1 text-center py-1">조회수</th>
				                    		</tr>
				                    		<c:if test="${totalCnt > 0}">
				                    		<c:forEach var="postList" items="${postList}" varStatus="status">
				                    		<tr>
				                    			<td class="text-center py-2">${postList.rn}</td>
				                    			<td><a id="postTitle${postList.postNo}" href="#" class="text-decoration-none text-black py-2" onclick="javascript:goDetail(${postList.postNo})">${postList.title}</a></td>
				                    			<td class="d-none d-md-table-cell text-center py-2">${postList.userNm}</td>
				                    			<td class="d-none d-md-table-cell text-center py-2">${postList.fmRegDate}</td>
				                    			<td class="text-center py-2">${postList.viewCnt}</td>
				                    		</tr>
				                    		</c:forEach>
				                    		</c:if>
				                    		<c:if test="${totalCnt <= 0}">
			                    			<tr>
			                    				<td colspan="5" class="text-center py-3">게시글이 존재하지 않습니다.</td>
			                    			</tr>
			                    			</c:if>
				                    	</table>
		                    		</div>
		                    	</c:if>
		                    	
		                    	<c:if test="${postInfo.boardType eq 'A02'}">
		                    		<div id="postList" class="board-gallery">
				                    	<table class="col-12 p-0 m-0">
				                    		<c:if test="${totalCnt > 0}">
				                    		<tr class="row col-12 p-0 m-0">
				                    			<c:forEach var="postList" items="${postList}" varStatus="status">
				                    			<td class="px-1">
				                    				<c:if test="${empty postList.fileNmDtl}">
				                    				<div class="text-center py-2">
				                    					<div class="no-post_img" onclick="javascript:goDetail('${postList.postNo}')">
		                    								<img src="/images/img_nouser.png" />
		                    							</div>
	                    							</div>
		                    						</c:if>
		                    						<c:if test="${not empty postList.fileNmDtl}">
		                    						<div class="text-center py-2">
				                    					<div class="post_img" onclick="javascript:goDetail('${postList.postNo}')">
		                    								<img src="/images/upload/${postList.fileNmDtl}" />
		                    							</div>
		                    						</div>
				                    				</c:if>
				                    				<c:if test="${not empty postList.title}">
	                    							<div class="post_title py-1"><strong>${postList.title}</strong></div>
	                    							</c:if>
	                    							<c:if test="${not empty postList.userNm}">
	                    							<div class="post_author py-1">${postList.userNm}</div>
	                    							</c:if>
	                    							<c:if test="${not empty postList.postNo}">
			                    					<div id="postInfo${postList.postNo}" class="post-info row col-12 p-0 m-0 justify-content-between align-items-center">
					                    				<span class="reg-date text-secondary col-auto p-0 my-0 ms-0 me-2">${postList.fmRegDate}</span>
				                    					<span class="view-cnt text-secondary col-auto p-0 m-0"><i class="fa-solid fa-eye"></i> ${postList.viewCnt}</span>
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
			                    </c:if>
			                    
		                    	<div id="postBtn" class="col-12 p-0 mx-0 mb-0 mt-2 text-end">
		                    		<c:if test="${(postInfo.boardNo eq 1 and userIdx eq 1) or postInfo.boardNo ne 1}">
		                    		<input type="button" class="btn btn-primary" value="작성" onclick="javascript:goPost();" />
		                    		</c:if>
		                    	</div>
		                    	<div id="postPager" class="board-pager col-12 p-0 mx-0 mb-0 mt-2 text-center">
		                    		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="schPost" />
		                    		<form:hidden path="boardNo" />
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
        <script src="/js/board/postList.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>