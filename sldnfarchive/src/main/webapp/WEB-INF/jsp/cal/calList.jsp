<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : calList.jsp
  * @Description : 데미지계산기 캐릭터목록
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.07.24	HHP            최초 생성
  *
  * author HHP
  * since 2026.07.24
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
		            		<form id="schCalFrm" class="row d-block d-md-flex col-12 p-0 m-0 justify-content-between" onsubmit="javascript:return false;">
		            			<div class="row d-block d-md-flex col-12 p-0 m-0 col-md-auto gap-2">
		            				<div class="col-12 col-md-auto p-0 m-0">
			            				<select id="serverId" name="serverId" class="form-select">
			            					<option value="all">전체</option>
			            					<option value="adven">모험단</option>
			            					<option value="cain">카인</option>
			            					<option value="diregie">디레지에</option>
			            					<option value="siroco">시로코</option>
			            					<option value="prey">프레이</option>
			            					<option value="casillas">카시야스</option>
			            					<option value="hilder">힐더</option>
			            					<option value="anton">안톤</option>
			            					<option value="bakal">바칼</option>
			            				</select>
		            				</div>
		            				<div class="col-12 col-md-auto p-0 mx-0 mb-0 mt-2 mt-md-0">
			            				<input type="text" id="characterName" name="characterName" class="form-control w-100" value="" onkeyup="javascript:if(event.keyCode == 13) schCal();" />
		            				</div>
		            			</div>
		            			<div class="text-end text-md-start w-auto px-0 mt-2 mt-md-0">
		            				<input type="button" class="btn btn-primary col-auto" onclick="javascript:schCal();" value="검색" />
		            			</div>
		            		</form>
		            	</div>
		            	
	                	<div class="card my-4">
	                		<form id="calFrm" name="calFrm" method="post" onsubmit="javascript: return false;">
	                		<div class="card-header">
		                        <ol class="breadcrumb mb-0 pt-2">
		                        	<li class="breadcrumb-item"><h5>데미지계산기</h5></li>
		                            <li class="breadcrumb-item active">데미지계산기</li>
		                        </ol>
		                    </div>
		                    
		                    <div class="card-body">
	                    		<div id="calList">
			                    	<table class="col-12 p-0 m-0">
			                    		<tr class="row row-cols-4 col-12 p-0 m-0">
			                    			<c:forEach var="calList" items="${calList}" varStatus="status">
			                    			<td class="card p-0 m-0">
			                    				<div id="info${calList.characterId}" class="character_info">
			                    					<div id="serv_${calList.serverId}" class="character_server">
			                    						서버
			                    					</div>
				                    				<div class="character_img" onclick="javascript:selectCal(this);">
				                    					<img src="https://img-api.neople.co.kr/df/servers/${calList.serverId}/characters/${calList.characterId}?zoom=3" alt="characterAvatar" />
				                    				</div>
				                    			</div>
				                    			<div class="row text-center col-12 px-0 py-1 m-0 justify-content-center align-items-center gap-2"><img class="col-auto p-0 m-0" src="/images/fame.png" /> ${calList.fame}</div>
				                    			<div class="text-center py-1"><h5 class="p-0 m-0">${calList.characterName}</h5></div>
				                    			<div class="text-center py-1">${calList.adventureName}</div>
				                    			<div class="text-center py-1"><span class="btn btn-secondary">정신력</span></div>
				                    			<div class="text-center py-1">1</span></div>
			                    			</td>
			                    			</c:forEach>
			                    		</tr>
			                    	</table>
			                    </div>
		                    </div>
		                    
		                    <hr class="m-0 py-3" />
		                    </form>
	                    </div>
                    </div>
                </main>
	            <%@include file="/WEB-INF/jsp/template/innerFooter.jsp" %>
            </div>
        </div>
        <script src="/js/board/videoList.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>