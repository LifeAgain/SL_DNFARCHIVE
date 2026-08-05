<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : selectCal.jsp
  * @Description : 상세보기
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.07.26	HHP            최초 생성
  *
  * author HHP
  * since 2026.07.26
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
		                    	<div id="calDetail">
		                    		<div>
		                    			<span onclick="javascript:calList();"><i class="fa-solid fa-arrow-left"></i></span>
		                    			<div></div>
		                    		</div>
			                    	<div class="card mt-4">
			                    		<div class="card-header col-12 p-0 m-0">
			                    			<ul id="tabList" class="nav flex-nowrap flex-md-wrap col-12 p-0 m-0 justify-content-md-center">
			                    				<li id="tab1" class="px-3 py-2 flex-shrink-0 active" onclick="javascript:tabChange(this);">장착장비</li>
			                    				<li id="tab2" class="px-3 py-2 flex-shrink-0" onclick="javascript:tabChange(this);">스탯</li>
			                    				<li id="tab3" class="px-3 py-2 flex-shrink-0" onclick="javascript:tabChange(this);">세부스탯</li>
			                    				<li id="tab4" class="px-3 py-2 flex-shrink-0" onclick="javascript:tabChange(this);">아바타&크리쳐</li>
			                    				<li id="tab5" class="px-3 py-2 flex-shrink-0" onclick="javascript:tabChange(this);">버프강화</li>
			                    				<li id="tab6" class="px-3 py-2 flex-shrink-0" onclick="javascript:tabChange(this);">스킬개화</li>
			                    				<li id="tab7" class="px-3 py-2 flex-shrink-0" onclick="javascript:tabChange(this);">딜표</li>
			                    				<li id="tab8" class="px-3 py-2 flex-shrink-0" onclick="javascript:tabChange(this);">스킬정보</li>
			                    				<li id="tab9" class="px-3 py-2 flex-shrink-0" onclick="javascript:tabChange(this);">버프계산</li>
			                    			</ul>
			                    		</div>
			                    		<div class="card-body">
			                    			<div id="box1" class="active">
			                    				<table class="col-12 p-0 m-0">
			                    					<tr class="card flex-row">
			                    						<td class="d-flex col-2 justify-content-center align-items-center py-2 bg-grey">세트</td>
			                    						<td class="d-flex col-2 justify-content-center align-items-center py-2 bg-grey">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="${selectCal.setItemImg}" alt="set" />
			                    								<div class="col-auto d-none d-md-table-cell p-0 m-0 oath_img"></div>
			                    							</div>
			                    						</td>
			                    						<td class="d-flex col-4 text-center justify-content-center align-items-center py-2 bg-grey rarity_${selectCal.setItemRarity}"><strong>${selectCal.setItemNm}</strong></td>
			                    						<td class="d-flex col-4 text-end justify-content-end align-items-center px-0 pe-md-3 py-2 bg-grey">
			                    							<div class="d-block d-md-flex gap-2">
			                    								<div class="setPts"><strong class="d-block d-md-inline-block me-0 me-md-1">세트포인트</strong><span>${selectCal.curSetPts}</span></div>
			                    								<div class="setPts"><strong class="d-block d-md-inline-block me-0 me-md-1">보정전</strong><span>${selectCal.setPts}</span></div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="d-flex col-2 justify-content-center align-items-center py-2 bg-grey">서약</td>
			                    						<td class="d-flex col-2 justify-content-center align-items-center py-2 bg-grey">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="${selectCal.setOathImg}" alt="oath" />
			                    								<div class="col-auto d-none d-md-table-cell p-0 m-0 oath_img"></div>
			                    							</div>
			                    						</td>
			                    						<td class="d-flex col-4 text-center justify-content-center align-items-center p-2 px-md-0 bg-grey rarity_${selectCal.setOathRarity}"><strong>${selectCal.setOathNm}</strong></td>
			                    						<td class="d-flex col-4 text-end justify-content-end align-items-center px-0 pe-md-3 py-2 bg-grey">
			                    							<div class="d-block d-md-flex gap-2">
			                    								<div class="oathPts"><strong class="d-block d-md-inline-block me-0 me-md-1">서약포인트</strong><span>${selectCal.curSetOathPts}</span></div>
			                    								<div class="oathPts"><strong class="d-block d-md-inline-block me-0 me-md-1">보정전</strong><span>${selectCal.setOathPts}</span></div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">무기</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.weaponid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="${selectCal.mainOathImg}" alt="oath" />
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.weaponrarity}"><strong>${selectCal.weapon}</strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.weaponampyn}">${selectCal.weaponreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">칭호</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.titleid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img"></div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.titlerarity}"><strong>${selectCal.title}</strong></td>
			                    						<td class="col-4 text-center py-2"></td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">상의</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.jacketid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId10}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm10}</span>
			                    									<c:if test="${selectCal.crystallvl10 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr10}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.jacketrarity}"><strong>${selectCal.jacket} <c:if test="${selectCal.jacketlvlstr ne ''}"><span class="tune_badge">${selectCal.jacketlvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.jacketampyn}">${selectCal.jacketreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">머리어깨</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.shoulderid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId9}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm9}</span>
			                    									<c:if test="${selectCal.crystallvl9 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr9}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.shoulderrarity}"><strong>${selectCal.shoulder} <c:if test="${selectCal.shoulderlvlstr ne ''}"><span class="tune_badge">${selectCal.shoulderlvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.shoulderampyn}">${selectCal.shoulderreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">하의</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.pantsid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId8}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm8}</span>
			                    									<c:if test="${selectCal.crystallvl8 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr8}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.pantsrarity}"><strong>${selectCal.pants} <c:if test="${selectCal.pantslvlstr ne ''}"><span class="tune_badge">${selectCal.pantslvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.pantsampyn}">${selectCal.pantsreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">신발</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.shoesid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId0}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm0}</span>
			                    									<c:if test="${selectCal.crystallvl0 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr0}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.shoesrarity}"><strong>${selectCal.shoes} <c:if test="${selectCal.shoeslvlstr ne ''}"><span class="tune_badge">${selectCal.shoeslvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.shoesampyn}">${selectCal.shoesreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">벨트</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.waistid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId1}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm1}</span>
			                    									<c:if test="${selectCal.crystallvl1 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr1}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.waistrarity}"><strong>${selectCal.waist} <c:if test="${selectCal.waistlvlstr ne ''}"><span class="tune_badge">${selectCal.waistlvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.waistampyn}">${selectCal.waistreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">목걸이</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.amuletid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId2}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm2}</span>
			                    									<c:if test="${selectCal.crystallvl2 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr2}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.amuletrarity}"><strong>${selectCal.amulet} <c:if test="${selectCal.amuletlvlstr ne ''}"><span class="tune_badge">${selectCal.amuletlvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.amuletampyn}">${selectCal.amuletreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">팔찌</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.wristid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId3}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm3}</span>
			                    									<c:if test="${selectCal.crystallvl3 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr3}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.wristrarity}"><strong>${selectCal.wrist} <c:if test="${selectCal.wristlvlstr ne ''}"><span class="tune_badge">${selectCal.wristlvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.wristampyn}">${selectCal.wristreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">반지</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
				                    							<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.ringid}" alt="equip" />
				                    							<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId4}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm4}</span>
			                    									<c:if test="${selectCal.crystallvl4 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr4}</span>
			                    									</c:if>
			                    								</div>
		                    								</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.ringrarity}"><strong>${selectCal.ring} <c:if test="${selectCal.ringlvlstr ne ''}"><span class="tune_badge">${selectCal.ringlvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.ringampyn}">${selectCal.ringreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">보조장비</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.supportid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId5}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm5}</span>
			                    									<c:if test="${selectCal.crystallvl5 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr5}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.supportrarity}"><strong>${selectCal.support} <c:if test="${selectCal.supportlvlstr ne ''}"><span class="tune_badge">${selectCal.supportlvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.supportampyn}">${selectCal.supportreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">마법석</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.magicStonid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId6}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm6}</span>
			                    									<c:if test="${selectCal.crystallvl6 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr6}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.magicStonrarity}"><strong>${selectCal.magicSton} <c:if test="${selectCal.magicStonlvlstr ne ''}"><span class="tune_badge">${selectCal.magicStonlvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.magicStonampyn}">${selectCal.magicStonreinforce}</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">귀걸이</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.earringid}" alt="equip" />
			                    								<div class="col-auto p-0 m-0 oath_img position-relative">
			                    									<img src="https://img-api.neople.co.kr/df/items/${selectCal.crystalId7}" alt="oath" />
			                    									<span class="position-absolute top-0 start-0 crystal_name">${selectCal.crystalNm7}</span>
			                    									<c:if test="${selectCal.crystallvl7 > 0}">
			                    									<span class="position-absolute bottom-0 end-0 px-1 crystal_lvl">${selectCal.crystallvlStr7}</span>
			                    									</c:if>
			                    								</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0 rarity_${selectCal.earringrarity}"><strong>${selectCal.earring} <c:if test="${selectCal.earringlvlstr ne ''}"><span class="tune_badge">${selectCal.earringlvlstr}</span></c:if></strong></td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2 ${selectCal.earringampyn}">${selectCal.earringreinforce}</td>
			                    					</tr>
			                    				</table>
			                    			</div>
			                    			
			                    			<div id="box4" class="d-none">
			                    				<table class="col-12 p-0 m-0">
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">모자</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.headgearavatarid}" alt="avatar" />
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.headgearavatarcloneid}" alt="avatarclone" />
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.headgearavatarclonenm}</div>
			                    								<div class="text-center text-secondary">${selectCal.headgearavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.headgearemblemrarity1}">${selectCal.headgearemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.headgearemblemrarity2}">${selectCal.headgearemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">머리</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.hairavatarid}" alt="avatar" />
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.hairavatarcloneid}" alt="avatarclone" />
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.hairavatarclonenm}</div>
			                    								<div class="text-center text-secondary">${selectCal.hairavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.hairemblemrarity1}">${selectCal.hairemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.hairemblemrarity2}">${selectCal.hairemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">얼굴</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.faceavatarid}" alt="avatar" />
		                    									<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.faceavatarcloneid}" alt="avatarclone" />
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.faceavatarclonenm}</div>
			                    								<div class="text-center text-secondary">${selectCal.faceavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.faceemblemrarity1}">${selectCal.faceemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.faceemblemrarity2}">${selectCal.faceemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">상의</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.jacketavatarid}" alt="avatar" />
			                    								<img class="col-auto p-0" src="https://img-api.neople.co.kr/df/items/${selectCal.jacketavatarcloneid}" alt="avatarclone" />
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.jacketavatarclonenm}</div>
			                    								<div class="text-center text-secondary">${selectCal.jacketavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.jacketemblemrarity1}">${selectCal.jacketemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.jacketemblemrarity2}">${selectCal.jacketemblemnm2}</div>
			                    								<div class="text-center rarity_${selectCal.jacketemblemrarity3}">${selectCal.jacketemblemnm3}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">하의</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.pantsavatarid}" alt="avatar" />
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.pantsavatarcloneid}" alt="avatarclone" />
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.pantsavatarclonenm}</div>
			                    								<div class="text-center text-secondary">${selectCal.pantsavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.pantsemblemrarity1}">${selectCal.pantsemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.pantsemblemrarity2}">${selectCal.pantsemblemnm2}</div>
			                    								<div class="text-center rarity_${selectCal.pantsemblemrarity3}">${selectCal.pantsemblemnm3}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">신발</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.shoesavatarid}" alt="avatar"/>
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.shoesavatarcloneid}" alt="avatarclone" />
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.shoesavatarclonenm}</div>
			                    								<div class="text-center text-secondary">${selectCal.shoesavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.shoesemblemrarity1}">${selectCal.shoesemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.shoesemblemrarity2}">${selectCal.shoesemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">목가슴</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.breastavatarid}" alt="avatar" />
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.breastavatarcloneid}" alt="avatarclone" />
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.breastavatarclonenm}</div>
			                    								<div class="text-center text-secondary">${selectCal.breastavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.breastemblemrarity1}">${selectCal.breastemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.breastemblemrarity2}">${selectCal.breastemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">허리</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.waistavatarid}" alt="avatar" />
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.waistavatarcloneid}" alt="avatarclone" />
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.waistavatarclonenm}</div>
			                    								<div class="text-center text-secondary">${selectCal.waistavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.waistemblemrarity1}">${selectCal.waistemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.waistemblemrarity2}">${selectCal.waistemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">피부</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
				                    							<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.skinavatarid}" alt="avatar" />
				                    							<div class="col-auto p-0 m-0 oath_img"></div>
		                    								</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.skinavatarnm}</div>
			                    								<div class="text-center text-secondary">${selectCal.skinavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.skinemblemrarity1}">${selectCal.skinemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.skinemblemrarity2}">${selectCal.skinemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">오라</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.auroraavatarid}" alt="avatar" />
			                    								<div class="col-auto p-0 m-0 oath_img"></div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.auroraavatarnm}</div>
			                    								<div class="text-center text-secondary"></div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.auroraemblemrarity1}">${selectCal.auroraemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.auroraemblemrarity2}">${selectCal.auroraemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">무기</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.weaponavatarid}" alt="avatar" />
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.weaponavatarcloneid}" alt="avatarclone" />
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">
			                    							<div>
			                    								<div class="text-center">${selectCal.weaponavatarclonenm}</div>
			                    								<div class="text-center text-secondary">${selectCal.weaponavataroption}</div>
			                    							</div>
			                    						</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.weaponemblemrarity1}">${selectCal.weaponemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.weaponemblemrarity2}">${selectCal.weaponemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-2">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">오라스킨</td>
			                    						<td class="col-2 d-flex justify-content-center align-items-center py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.auraSkinavatarid}" />
			                    								<div class="col-auto p-0 m-0 oath_img"></div>
			                    							</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">${selectCal.auraSkinavatarnm}</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.auraSkinemblemrarity1}">${selectCal.auraSkinemblemnm1}</div>
			                    								<div class="text-center rarity_${selectCal.auraSkinemblemrarity2}">${selectCal.auraSkinemblemnm2}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    					<tr class="card flex-row mt-4">
			                    						<td class="col-2 d-none d-md-flex justify-content-center align-items-center py-2">크리쳐</td>
			                    						<td class="col-2 py-2">
			                    							<div class="row col-12 p-0 m-0 equip_img gap-2 justify-content-center align-items-center">
			                    								<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.creatureId}" />
			                    								<div class="col-auto p-0 m-0 oath_img d-none d-md-block"></div>
			                    							</div>
			                    							<div class="row col-12 p-0 m-0 artifact_img gap-2 justify-content-center align-items-center mt-2">
		                    									<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.artifactIdRED}" />
		                    									<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.artifactIdBLUE}" />
		                    									<img class="col-auto p-0 m-0" src="https://img-api.neople.co.kr/df/items/${selectCal.artifactIdGREEN}" />
		                    								</div>
			                    						</td>
			                    						<td class="col-6 col-md-4 d-flex justify-content-center align-items-center p-2 px-md-0">${selectCal.creatureNm}</td>
			                    						<td class="col-4 d-flex justify-content-end align-items-center ps-0 pe-2 pe-md-3 py-2">
			                    							<div>
			                    								<div class="text-center rarity_${selectCal.artifactRarityRED}">${selectCal.artifactNmRED}</div>
			                    								<div class="text-center rarity_${selectCal.artifactRarityBLUE}">${selectCal.artifactNmBLUE}</div>
			                    								<div class="text-center rarity_${selectCal.artifactRarityGREEN}">${selectCal.artifactNmGREEN}</div>
			                    							</div>
			                    						</td>
			                    					</tr>
			                    				</table>
			                    			</div>
			                    		</div>
			                    	</div>
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
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>