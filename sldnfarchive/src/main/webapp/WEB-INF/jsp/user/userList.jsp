<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : userList.jsp
  * @Description : 회원 관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.02.19	HHP            최초 생성
  *
  * author HHP
  * since 2026.02.19
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
		            		<form id="schUserFrm" class="row col-12 p-0 m-0 justify-content-between" onsubmit="javascript:return false;">
		            			<div class="row col-auto gap-2">
			            			<div class="input-group px-0 w-auto">
			            				<label for="schUserStat" class="input-group-text">휴면여부</label>
			            				<select id="schUserStat" name="schUserStat" class="form-select">
			            					<option value="">전체</option>
			            					<option value="Y">정상</option>
			            					<option value="N">휴면</option>
			            				</select>
			            			</div>
			            			<div class="input-group px-0 w-auto">
			            				<label for="schUserNm" class="input-group-text">이름</label>
			            				<input type="text" id="schUserNm" name="schUserNm" class="form-control" value="" onkeyup="javascript:if(event.keyCode == 13) schCode();" />
			            			</div>
		            			</div>
		            			<input type="button" class="btn btn-primary col-auto" onclick="javascript:schCode();" value="검색" />
		            		</form>
		            	</div>
		            	
	                	<div class="card my-4">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>시스템관리</h5></li>
		                            <li class="breadcrumb-item active">회원관리</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
		                    	 <div class="col-12 m-0 text-end">
		                 			<input type="button" class="btn bg-warning text-bg-warning" onclick="javascript:insertUser();" value="추가" />
		                 			<input type="button" class="btn bg-danger text-bg-danger" onclick="javascript:beforeDeleteUser();" value="삭제" />
	                        	</div>
                        		
		                    	<div class="row col-12 p-0 m-0 mt-4">
			                    	<div class="col-6 p-0 m-0">
			                    		<div id="jstree"></div>
			                    	</div>
			                    	<div class="col-6 p-0 m-0">
			                    		<form id="editFrm" onsubmit="javascript:return false;">
				                    		<div class="row col-12 p-0 m-0">
					                    		<div class="input-group p-0">
			                              			<label for="userMail" class="col-2 p-2 text-end">ID</label>
			                              			<input type="text" id="userMail" name="userMail" class="form-control" placeholder="메일주소" value="" />
			                              		</div>
		                              		</div>
		                              		<div class="row col-12 p-0 mx-0 mt-3 mb-0">
		                              			<div class="col-8 p-0 m-0">
						                    		<div class="input-group">
				                              			<label for="userNm" class="col-3 p-2 text-end">이름</label>
				                              			<input type="text" id="userNm" name="userNm" class="form-control" onchange="javascript:chkChangeVal(this);" placeholder="이름(성명/모험단)" value="" />
				                              		</div>
			                              		</div>
			                              		<div class="col-4 p-0 m-0">
			                              			<div class="input-group">
				                              			<label for="userStat" class="col-8 p-2 text-end">휴면여부</label>
			                              				<input type="checkbox" id="userStat" name="userStat" class="form-check" onchange="javascript:chkChangeVal(this);" value="" />
				                              		</div>
			                              		</div>
		                              		</div>
		                              		<div class="row col-12 p-0 mx-0 mt-3 mb-0">
			                              		<div class="input-group p-0">
			                              			<label for="userPw" class="col-2 p-2 text-end">PW</label>
			                              			<input type="password" id="userPw" name="userPw" class="form-control" onchange="javascript:chkChangeVal(this);" placeholder="비밀번호" value="" />
			                              		</div>
		                              		</div>
		                              		<div class="row col-12 p-0 mx-0 mt-3 mb-0">
				                    			<div class="input-group p-0">
			                              			<label for="codeNote" class="col-2 p-2 text-end">비고</label>
			                              			<textarea id="codeNote" name="codeNote" class="form-control h-auto" onchange="javascript:chkChangeVal(this);"></textarea>
			                              		</div>
		                              		</div>
		                              		<div class="col-12 p-0 mx-0 mt-3 mb-1 text-end">
		                              			<input type="button" class="btn btn-primary" onclick="javascript:beforeSaveUser();" value="저장" />
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
        <link href="/css/style_jstree.css" rel="stylesheet" />
        <script src="/js/util/jstree.js"></script>
        <script src="/js/user/userList.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>