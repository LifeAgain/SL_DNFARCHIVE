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
		            	<div class="card card-header mt-4">
		            		<form id="schPostFrm" class="row col-12 p-0 m-0 justify-content-between" onsubmit="javascript:return false;">
		            			<div class="row col-auto gap-2">
		            				<select id="schType" name="schType" class="form-select w-auto" onchange="javascript:changeSchType();">
		            					<option value="schKeyword">제목+내용</option>
		            					<option value="schAuthor">작성자</option>
		            				</select>
			            			<input type="text" id="schKeyword" name="schKeyword" class="form-control w-auto" value="" onkeyup="javascript:if(event.keyCode == 13) schPost(1);" />
		            			</div>
		            			<input type="button" class="btn btn-primary col-auto" onclick="javascript:schPost(1);" value="검색" />
		            		</form>
		            	</div>
		            	
	                	<div class="card my-4">
	                		<form:form modelAttribute="postVO" id="postFrm" name="postFrm" method="post" onsubmit="javascript: return false;">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>커뮤니티</h5></li>
		                            <li class="breadcrumb-item active">공지사항</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
		                    	<div id="postList" class="board-list">
			                    	<table class="col-12 p-0 m-0">
			                    		<tr>
			                    			<th width="10%" class="text-center py-1">번호</th>
			                    			<th width="45%" class="text-center py-1">제목</th>
			                    			<th width="15%" class="text-center py-1">작성자</th>
			                    			<th width="15%" class="text-center py-1">작성일자</th>
			                    			<th width="15%" class="text-center py-1">조회수</th>
			                    		</tr>
			                    		<c:if test="${totalCnt > 0}">
			                    		<c:forEach var="postList" items="${postList}" varStatus="status">
			                    		<tr>
			                    			<td class="text-center py-2">${postList.rn}</td>
			                    			<td><a href="#" class="text-decoration-none text-black py-2" onclick="javascript:goDetail()">${postList.title}</a></td>
			                    			<td class="text-center py-2">${postList.userNm}</td>
			                    			<td class="text-center py-2">${postList.fmRegDate}</td>
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
		                    	<div id="postBtn" class="col-12 p-0 mx-0 mb-0 mt-1 text-end">
		                    		<input type="button" class="btn btn-primary" value="작성" onclick="javascript:goPost();" />
		                    	</div>
		                    	<div id="postPager" class="board-pager col-12 p-0 mx-0 mb-0 mt-2 text-center">
		                    		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="schPost" />
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