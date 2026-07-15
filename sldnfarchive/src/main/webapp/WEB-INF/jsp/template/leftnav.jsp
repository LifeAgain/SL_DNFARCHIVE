<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"	   uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : leftnav.jsp
  * @Description : 좌측 메뉴 템플릿
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

			<div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                        	<c:if test="${not empty largeMenuList}">
                        	<c:if test="${userIdx eq '1'}">
                        	<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts0" aria-expanded="false" aria-controls="collapseLayouts0">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div> ${largeMenuList[0].text}
                                <c:set var="cntA" value="0" />
                                <c:forEach var="subMenuListCntA" items="${subMenuList}">
                                	<c:if test="${fn:contains(subMenuListCntA.id, 'A')}">
                                		<c:set var="cntA" value="${cntA + 1}" />
                                	</c:if>
                                </c:forEach>
                                <c:if test="${cntA > 0}">
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            	</c:if>
                            </a>
                            <div class="collapse" id="collapseLayouts0" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                	<c:if test="${not empty largeMenuList[0]}">
                        			<c:forEach var="subMenuListA" items="${subMenuList}" varStatus="status">
                        			<c:if test="${fn:contains(subMenuListA.id, 'A') and subMenuListA.type ne 'default'}">
                                	<a class="nav-link" href="#" onclick="javascript:movejs_menu('${subMenuListA.path}');">${subMenuListA.text}</a>
                                	</c:if>
                                	</c:forEach>
                                	</c:if>
                                </nav>
                            </div>
                            </c:if>
                        	<c:forEach var="largeMenuList" items="${largeMenuList}" begin="1" varStatus="status"> 	
                        	<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts${status.index}" aria-expanded="false" aria-controls="collapseLayouts${status.index}">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div> ${largeMenuList.text}
                                <c:set var="cnt" value="0" />
                                <c:forEach var="subMenuListCnt" items="${subMenuList}">
                                	<c:if test="${fn:contains(subMenuListCnt.id, fn:substring(largeMenuList.id, 0, 1))}">
                                		<c:set var="cnt" value="${cnt + 1}" />
                                	</c:if>
                                </c:forEach>
                                <c:if test="${cnt > 0}">
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            	</c:if>
                            </a>
                            <div class="collapse" id="collapseLayouts${status.index}" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                	<c:if test="${not empty largeMenuList}">
                        			<c:forEach var="subMenuList" items="${subMenuList}" varStatus="status">
                        			<c:if test="${fn:contains(subMenuList.id, fn:substring(largeMenuList.id, 0, 1)) and subMenuList.type ne 'default'}">
                                	<a class="nav-link" href="#" onclick="javascript:movejs_menu('${subMenuList.path}');">${subMenuList.text}</a>
                                	</c:if>
                                	</c:forEach>
                                	</c:if>
                                </nav>
                            </div>
                        	</c:forEach>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </div>