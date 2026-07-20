<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : profile.jsp
  * @Description : 유저 프로필
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2026.06.08	HHP            최초 생성
  *
  * author HHP
  * since 2026.06.08
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<div class="modal-container">
	<div class="col-12 m-0 text-end">              	 	
		<label for="myUploadFile" class="btn bg-secondary text-bg-secondary">프로필이미지</label>
   	</div>
                		
	<div class="row col-12 p-0 mx-0 mt-4 mb-0">
		<div class="col-4 p-0 m-0">
			<div class="col-12 ps-2 pe-0 py-0">
				<c:if test="${empty selectUser.fileNmDtl}">
				<img id="myProfileImg" class="col-12 p-0 m-0 profile${selectUser.userIdx}" src="/images/img_nouser.png" />
				</c:if>
				<c:if test="${not empty selectUser.fileNmDtl}">
				<img id="myProfileImg" class="col-12 p-0 m-0 profile${selectUser.userIdx}" src="/images/upload/${selectUser.fileNmDtl}" />
				</c:if>
			</div>
		</div>
		<div class="col-8 p-0 m-0">
  			<form id="myFrm" onsubmit="javascript:return false;">
    			<input type="file" id="myUploadFile" name="myUploadFile" class="d-none" accept="image/*" onchange="javascript:fileCheck(this);" />
   				
   				<div class="row d-block d-lg-flex col-12 p-0 m-0">
    				<div class="input-group p-0">
            			<label for="myUserMail" class="col-5 col-lg-2 p-2 text-end">ID</label>
            			<input type="text" id="myUserMail" name="myUserMail" class="form-control bg-secondary" placeholder="메일주소" value="${selectUser.userMail}" readonly />
            		</div>
        		</div>
        		<div class="row d-block d-lg-flex col-12 p-0 mx-0 mt-3 mb-0">
        			<div class="col-12 col-lg-6 p-0 m-0">
	     				<div class="input-group">
	             			<label for="myUserNm" class="col-5 col-lg-4 p-2 text-end">이름</label>
	             			<input type="text" id="myUserNm" name="myUserNm" class="form-control" onchange="javascript:chkChangeVal(this);" placeholder="이름(성명/모험단)" value="${selectUser.userNm}" />
	             		</div>
            		</div>
            		<div class="col-12 col-lg-6 p-0 mx-0 mb-0 mt-3 mt-lg-0">
            			<div class="input-group">
             				<label for="myUserStat" class="col-5 col-lg-8 p-2 text-end stat${selectUser.userStat}">휴면여부</label>
            				<input type="checkbox" id="myUserStat" name="myUserStat" class="form-check" onchange="javascript:chkChangeVal(this);" value="" />
             			</div>
            		</div>
        		</div>
        		<div class="row d-block d-lg-flex col-12 p-0 mx-0 mt-3 mb-0">
            		<div class="input-group p-0">
            			<label for="myUserPw" class="col-5 col-lg-2 p-2 text-end">PW</label>
            			<input type="password" id="myUserPw" name="myUserPw" class="form-control" onchange="javascript:chkChangeVal(this);" placeholder="비밀번호" value="${selectUser.userPw}" />
            		</div>
        		</div>
        		<div class="row d-block d-lg-flex col-12 p-0 mx-0 mt-3 mb-0">
   				<div class="input-group p-0">
            			<label for="myUserNote" class="col-5 col-lg-2 p-2 text-end">비고</label>
            			<textarea id="myUserNote" name="myUserNote" class="form-control h-auto" onchange="javascript:chkChangeVal(this);">${selectUser.userNote}</textarea>
            		</div>
        		</div>
   			</form>
   		</div>
	</div>
</div>

<script src="/js/main/profile.js"></script>
<script src="/js/util/file.js"></script>