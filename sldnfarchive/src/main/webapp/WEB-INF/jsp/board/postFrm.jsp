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
	                	<div class="card my-4">
	                		<form:form modelAttribute="postVO" id="postForm" name="postForm" method="post" onsubmit="javascript: return false;">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>${postInfo.parentNm}</h5></li>
		                            <li class="breadcrumb-item active">${postInfo.boardNm}</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
		                    	<div class="board-frm">
			                    	<table class="col-12 p-0 m-0">
			                    		<tr>
			                    			<td class="text-end pe-2 py-2"><label for="title"><strong>제목</strong></label></td>
			                    			<td class="p-2">
			                    				<c:if test="${flag eq 'I'}">
			                    					<input type="text" id="title" name="title" class="form-control m-0" onchange="javascript:chkChangeVal(this);" value="" />
			                    				</c:if>
			                    				<c:if test="${flag eq 'U'}">
			                    					<input type="text" id="title" name="title" class="form-control m-0" onchange="javascript:chkChangeVal(this);" value="${selectPost.title}" />
			                    				</c:if>
			                    			</td>
			                    		</tr>
			                    		<tr>
			                    			<td class="text-end pe-2 py-2"><label for="content"><strong>내용</strong></label></td>
			                    			<td class="p-2">
			                    				<c:if test="${flag eq 'I'}">
			                    					<textarea id="content" name="content" rows="5" class="form-control m-0" onchange="javascript:chkChangeVal(this);"></textarea>
			                    				</c:if>
			                    				<c:if test="${flag eq 'U'}">
			                    					<textarea id="content" name="content" rows="5" class="form-control m-0" onchange="javascript:chkChangeVal(this);">${selectPost.content}</textarea>
			                    				</c:if>
			                    			</td>
			                    		</tr>
			                    		<tr>
			                    			<td class="text-end pe-2 py-2"><label for="uploadFile1"><strong>첨부1</strong></label></td>
			                    			<td class="p-2">
			                    				<input type="hidden" id="fileNo1" name="fileNo1" value="${fileList[0].fileNo}" />
			                    				<input type="file" id="uploadFile1" name="uploadFile1" />
			                    			</td>
			                    		</tr>
			                    		<tr>
			                    			<td class="text-end pe-2 py-2"><label for="uploadFile2"><strong>첨부2</strong></label></td>
			                    			<td class="p-2">
			                    				<input type="hidden" id="fileNo2" name="fileNo2" value="${fileList[1].fileNo}" />
			                    				<input type="file" id="uploadFile2" name="uploadFile2" />
			                    			</td>
			                    		</tr>
			                    		<tr>
				                    		<td colspan="2" class="text-end py-2">
				                    			<input type="button" class="btn btn-primary" onclick="javascript:beforeSavePost();" value="작성" />
				                    			<input type="button" class="btn btn-secondary" onclick="javascript:goList();" value="취소" />
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
        <script src="/js/board/postFrm.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>