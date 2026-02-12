<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : menuList.jsp
  * @Description : 메뉴관리
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.02.10	HHP            최초 생성
  *
  * author HHP
  * since 2026.02.10
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
		            		<form id="schMenuFrm" class="row col-12 p-0 m-0 justify-content-between" onsubmit="javascript:return false;">
		            			<div class="input-group px-0 w-auto">
		            				<label for="schMenuNm" class="input-group-text">메뉴명</label>
		            				<input type="text" id="schMenuNm" name="schMenuNm" class="form-control" value="" onkeyup="javascript:if(event.keyCode == 13) schMenu();" />
		            			</div>
		            			<input type="button" class="btn btn-primary col-auto" onclick="javascript:schMenu();" value="검색" />
		            		</form>
		            	</div>
		            	
	                	<div class="card my-4">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>시스템관리</h5></li>
		                            <li class="breadcrumb-item active">메뉴관리</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
		                    	 <div class="col-12 m-0 text-end">
		                 			<input type="button" class="btn bg-warning text-bg-warning" onclick="javascript:insertMenu('L');" value="상위메뉴 추가" />
		                 			<input type="button" class="btn bg-warning text-bg-warning" onclick="javascript:insertMenu('S');" value="하위메뉴 추가" />
		                 			<input type="button" class="btn bg-danger text-bg-danger" onclick="javascript:beforeDeleteMenu();" value="삭제" />
	                        	</div>
                        		
		                    	<div class="row col-12 p-0 m-0 mt-4">
			                    	<div class="col-4 p-0 m-0">
			                    		<div id="jstree"></div>
			                    	</div>
			                    	<div class="col-8 p-0 m-0">
			                    		<form id="editFrm" onsubmit="javascript:return false;">
				                    		<div class="row col-12 p-0 m-0">
				                    			<div class="col-6 p-0 m-0">
						                    		<div class="input-group">
				                              			<label for="menuLcd" class="col-4 p-2 text-end">대분류코드</label>
				                              			<input type="text" id="menuLcd" name="menuLcd" class="form-control bg-secondary" value="" readonly />
				                              		</div>
			                              		</div>
			                              		<div class="col-6 p-0 m-0">
				                              		<div class="input-group">
				                              			<label for="menuScd" class="col-4 p-2 text-end">소분류코드</label>
				                              			<input type="text" id="menuScd" name="menuScd" class="form-control bg-secondary" value="" readonly />
				                              		</div>
			                              		</div>
		                              		</div>
		                              		<div class="row col-12 p-0 mx-0 mt-3 mb-0">
				                    			<div class="col-6 p-0 m-0">
						                    		<div class="input-group">
				                              			<label for="menuNm" class="col-4 p-2 text-end">메뉴명</label>
				                              			<input type="text" id="menuNm" name="menuNm" class="form-control" onchange="javascript:chkChangeVal(this);" value="" />
				                              		</div>
			                              		</div>
			                              		<div class="col-6 p-0 m-0">
				                              		<div class="input-group">
				                              			<label for="menuPath" class="col-4 p-2 text-end">메뉴경로</label>
				                              			<input type="text" id="menuPath" name="menuPath" class="form-control" onchange="javascript:chkChangeVal(this);" value="" />
				                              		</div>
			                              		</div>
		                              		</div>
		                              		<div class="row col-12 p-0 mx-0 mt-3 mb-0">
				                    			<div class="input-group p-0">
			                              			<label for="useYn" class="col-2 p-2 text-end">사용여부</label>
			                              			<input type="checkbox" id="useYn" name="useYn" class="form-check" onchange="javascript:chkChangeVal(this);" value="" />
			                              		</div>
		                              		</div>
		                              		<div class="row col-12 p-0 mx-0 mt-3 mb-0">
				                    			<div class="input-group p-0">
			                              			<label for="menuNote" class="col-2 p-2 text-end">비고</label>
			                              			<textarea id="menuNote" name="menuNote" class="form-control h-auto" onchange="javascript:chkChangeVal(this);"></textarea>
			                              		</div>
		                              		</div>
		                              		<div class="col-12 p-0 mx-0 mt-3 mb-1 text-end">
		                              			<input type="button" class="btn btn-primary" onclick="javascript:beforeSaveMenu();" value="저장" />
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
        <script src="/js/menu/menuList.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>