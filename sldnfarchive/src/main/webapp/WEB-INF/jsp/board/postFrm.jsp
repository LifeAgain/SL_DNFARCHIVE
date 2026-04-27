<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : postFrm.jsp
  * @Description : 게시글 작성 폼 모듈
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.04.20	HHP            최초 생성
  *
  * author HHP
  * since 2026.04.20
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
		            				<select id="schType" name="schType" class="form-select w-auto">
		            					<option value="schKeyword">제목+내용</option>
		            					<option value="schWriter">작성자</option>
		            				</select>
			            			<input type="text" id="schKeyword" name="schKeyword" class="form-control w-auto" value="" onkeyup="javascript:if(event.keyCode == 13) postList();" />
		            			</div>
		            			<input type="button" class="btn btn-primary col-auto" onclick="javascript:postList();" value="검색" />
		            		</form>
		            	</div>
		            	
	                	<div class="card my-4">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>커뮤니티</h5></li>
		                            <li class="breadcrumb-item active">공지사항</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
		                    	<div id="postForm" class="board-list">
			                    	<table class="col-12 p-0 m-0">
			                    		<tr>
			                    			<th width="10%" class="text-center">번호</th>
			                    			<th width="45%" class="text-center">제목</th>
			                    			<th width="15%" class="text-center">작성자</th>
			                    			<th width="15%" class="text-center">작성일자</th>
			                    			<th width="15%" class="text-center">조회수</th>
			                    		</tr>
			                    		
			                    			<tr>
			                    				<td colspan="5" class="text-center py-3">게시글이 존재하지 않습니다.</td>
			                    			</tr>
			                    	</table>
		                    	</div>
		                    	<div class="col-12 p-0 mx-0 mb-0 mt-1 text-end">
		                    		<input type="button" class="btn btn-primary" value="작성" onclick="javascript:movejs_menu(9);" />
		                    	</div>
		                    	<div class="col-12 p-0 mx-0 mb-0 mt-2 text-center">
		                    			1 2 3 4
		                    	</div>
		                    </div>
		                    
		                    <hr class="m-0 py-3" />
	                    </div>
                    </div>
                </main>
	            <%@include file="/WEB-INF/jsp/template/innerFooter.jsp" %>
            </div>
        </div>
        <script src="/js/board/boardList.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>