<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : main.jsp
  * @Description : 메인화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.02.03	HHP            최초 생성
  *
  * author HHP
  * since 2026.02.03
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
	                        <div class="row col-12 p-0 mx-0 my-4">
	                            <div class="col-6 p-0 m-0 mb-4 pe-1">
	                            	<div class="card">
                                    	<div class="card-header"><strong>공지사항</strong></div>
                                    	<div class="card-body">
                                    		<table class="col-12 p-0 m-0 curPostList">
                                    			<tr>
                                    				<th class="text-center py-1" width="65%">제목</th>
                                    				<th class="text-center py-1" width="15%">조회수</th>
                                    				<th class="text-center py-1" width="20%">작성일자</th>
                                    			</tr>
                                    			<c:if test="${totalPostCnt > 0}">
                                    			<c:forEach var="curPostList" items="${curPostList}" begin="0" end="4" varStatus="status">
                                    			<tr>
                                    				<td id="curPost${curPostList.postNo}" class="px-2"><a class="text-decoration-none text-black" href="#" onclick="javascript:goDetail(this);">${curPostList.title}</a></td>
                                    				<td class="text-center">${curPostList.viewCnt}</td>
                                    				<td class="text-center">${curPostList.fmRegDate}</td>
                                    			</tr>
                                    			</c:forEach>
                                    			</c:if>
                                    			<c:if test="${totalPostCnt <= 0}">
                                    			<tr>
                                    				<td class="text-center" colspan="2">게시글이 존재하지 않습니다.</td>
                                    			</tr>
                                    			</c:if>
                                    		</table>
                                    	</div>
	                            	</div>
	                            </div>
	                            <div class="col-6 p-0 m-0 mb-4 ps-1">
	                            	<div class="card">
                                    	<div class="card-header"><strong>추천공략</strong></div>
                                    	<div class="card-body">
                                    		<table class="col-12 p-0 m-0 curPostList">
                                    			<tr>
                                    				<th class="text-center py-1" width="80%">제목</th>
                                    				<th class="text-center py-1" width="20%">작성일자</th>
                                    			</tr>
                                    			<c:if test="${totalVideoCnt > 0}">
                                    			<c:forEach var="curVideoList" items="${curVideoList}" begin="0" end="4" varStatus="status">
                                    			<tr>
                                    				<td class="px-2"><a class="text-decoration-none text-black" href="${curVideoList.videoUrl}" target="_blank"></a></td>
                                    				<td class="text-center">${curVideoList.fmRegDate}</td>
                                    			</tr>
                                    			</c:forEach>
                                    			</c:if>
                                    			<c:if test="${totalVideoCnt <= 0}">
                                    			<tr>
                                    				<td class="text-center" colspan="2">게시글이 존재하지 않습니다.</td>
                                    			</tr>
                                    			</c:if>
                                    		</table>
                                    	</div>
	                            	</div>
	                            </div>
	                        </div>
	                    </div>
	                </main>
	                <%@include file="/WEB-INF/jsp/template/innerFooter.jsp" %>
	            </div>
	        </div>
	        <script src="/js/main/main.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>