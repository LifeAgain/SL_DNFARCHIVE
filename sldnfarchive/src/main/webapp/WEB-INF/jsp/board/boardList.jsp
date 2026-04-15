<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : boardList.jsp
  * @Description : 게시판 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.03.11	HHP            최초 생성
  *
  * author HHP
  * since 2026.03.11
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
		            		<form id="schBoardFrm" class="row col-12 p-0 m-0 justify-content-between" onsubmit="javascript:return false;">
		            			<div class="row col-auto gap-2">
		            				<div class="input-group px-0 w-auto">
			            				<label for="schBoardType" class="input-group-text">종류</label>
			            				<select id="schBoardType" name="schBoardType" class="form-select" onchange="javascript:schBoard();">
			            					<option value="">전체</option>
			            					<c:forEach var="codeList" items="${codeList}" varStatus="status">
			            						<option value="${codeList.codeCd}">${codeList.codeNm}</option>
			            					</c:forEach>
			            				</select>
			            			</div>
			            			<div class="input-group px-0 w-auto">
			            				<label for="schParentCd" class="input-group-text">상위메뉴</label>
			            				<select id="schParentCd" name="schParentCd" class="form-select" onchange="javascript:schBoard();">
			            					<option value="">전체</option>
			            					<c:forEach var="parentMenuList" items="${parentMenuList}" varStatus="status">
			            						<option value="${parentMenuList.parentCd}">${parentMenuList.menuNm}</option>
			            					</c:forEach>
			            				</select>
			            			</div>
		            				<div class="input-group px-0 w-auto">
			            				<label for="schUseYn" class="input-group-text">사용여부</label>
			            				<select id="schUseYn" name="schUseYn" class="form-select" onchange="javascript:schBoard();">
			            					<option value="">전체</option>
			            					<option value="Y">사용</option>
			            					<option value="N">미사용</option>
			            				</select>
			            			</div>
			            			<div class="input-group px-0 w-auto">
			            				<label for="schCommentYn" class="input-group-text">댓글사용여부</label>
			            				<select id="schCommentYn" name="schCommentYn" class="form-select" onchange="javascript:schBoard();">
			            					<option value="">전체</option>
			            					<option value="Y">사용</option>
			            					<option value="N">미사용</option>
			            				</select>
			            			</div>
			            			<div class="input-group px-0 w-auto">
			            				<label for="schBoardNm" class="input-group-text">이름</label>
			            				<input type="text" id="schBoardNm" name="schBoardNm" class="form-control" value="" onkeyup="javascript:if(event.keyCode == 13) schBoard();" />
			            			</div>
			            			<input type="button" class="btn btn-primary col-auto" onclick="javascript:schUser();" value="검색" />
		            			</div>
		            		</form>
		            	</div>
		            	
	                	<div class="card my-4">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>시스템관리</h5></li>
		                            <li class="breadcrumb-item active">게시판관리</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
		                    	 <div class="col-12 m-0 text-end">
		                 			<input type="button" class="btn bg-warning text-bg-warning" onclick="javascript:insertBoard();" value="추가" />
		                 			<input type="button" class="btn bg-danger text-bg-danger" onclick="javascript:beforeDeleteBoard();" value="삭제" />
	                        	</div>
                        		
		                    	<div class="row col-12 p-0 m-0 mt-4">
			                    	<div class="col-6 p-0 m-0">
			                    		<div class="table">
			                    			<table id="boardGrid"></table>
			                    			<div id="gridPager"></div>
			                    		</div>
			                    	</div>
			                    	<div class="col-6 p-0 m-0">
			                    		<form id="editFrm" onsubmit="javascript:return false;">
				                    		<div class="row col-12 p-0 m-0">
					                    		<div class="input-group p-0">
			                              			<label for="boardType" class="col-3 p-2 text-end">종류</label>
			                              			<select id="boardType" name="boardType" class="form-select" onchange="javascript:chkChangeVal(this);">
						            					<option value="">- 선택 -</option>
						            					<c:forEach var="codeList" items="${codeList}" varStatus="status">
						            						<option value="${codeList.codeCd}">${codeList.codeNm}</option>
						            					</c:forEach>
						            				</select>
			                              		</div>
		                              		</div>
		                              		<div class="row col-12 p-0 mx-0 mt-3 mb-0">
		                              			<div class="col-6 p-0 m-0">
			                              			<div class="input-group">
				                              			<label for="boardNm" class="col-6 p-2 text-end">이름</label>
				                              			<input type="text" id="boardNm" name="boardNm" class="form-control" onchange="javascript:chkChangeVal(this);" placeholder="이름" value="" />
				                              		</div>
			                              		</div>
			                              		<div class="col-6 p-0 m-0">
			                              			<div class="input-group">
				                              			<label for="parentCd" class="col-6 p-2 text-end">상위메뉴</label>
				                              			<select id="parentCd" name="parentCd" class="form-select" onchange="javascript:chkChangeVal(this);">
							            					<option value="">- 선택 -</option>
							            					<c:forEach var="parentMenuList" items="${parentMenuList}" varStatus="status">
							            						<option value="${parentMenuList.parentCd}">${parentMenuList.menuNm}</option>
							            					</c:forEach>
							            				</select>
				                              		</div>
			                              		</div>
		                              		</div>
		                              		<div class="row col-12 p-0 mx-0 mt-3 mb-0">
		                              			<div class="col-6 p-0 m-0">
			                              			<div class="input-group">
				                              			<label for="useYn" class="col-6 p-2 text-end">사용여부</label>
			                              				<input type="checkbox" id="useYn" name="useYn" class="form-check" onchange="javascript:chkChangeVal(this);" value="" />
				                              		</div>
			                              		</div>
			                              		<div class="col-6 p-0 m-0">
			                              			<div class="input-group">
				                              			<label for="commentYn" class="col-6 p-2 text-end">댓글기능</label>
			                              				<input type="checkbox" id="commentYn" name="commentYn" class="form-check" onchange="javascript:chkChangeVal(this);" value="" />
				                              		</div>
			                              		</div>
		                              		</div>
		                              		<div class="row col-12 p-0 mx-0 mt-3 mb-0">
				                    			<div class="input-group p-0">
			                              			<label for="boardNote" class="col-3 p-2 text-end">비고</label>
			                              			<textarea id="boardNote" name="boardNote" class="form-control h-auto" onchange="javascript:chkChangeVal(this);"></textarea>
			                              		</div>
		                              		</div>
		                              		<div class="col-12 p-0 mx-0 mt-3 mb-1 text-end">
		                              			<input type="button" class="btn btn-primary" onclick="javascript:beforeSaveBoard();" value="저장" />
		                              		</div>
	                              		</form>
			                    	</div>
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