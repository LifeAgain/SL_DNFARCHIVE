/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sldnfarchive.model;

/**
 * @Class Name : MenuVO.java
 * @Description : MenuVO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2016.02.10		HHP           최초생성
 *
 * @author HHP
 * @since 2016.02.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class MenuVO {
	
	/** 검색할 메뉴명 */
	private String schMenuNm;
	
	/** 대분류코드 */
	private String menuLcd;
	
	/** 소분류코드 */
	private String menuScd;
	
	/** 메뉴명 */
	private String menuNm;
	
	/** 메뉴경로 */
	private String menuPath;
	
	/** 사용여부 */
	private String useYn;
	
	/** 비고 */
	private String menuNote;
	
	/** 등록자 */
	private String regNo;
	
	/** 등록일자 */
	private String regDate;
	
	/** 수정자 */
	private String upNo;
	
	/** 수정일자 */
	private String upDate;
	
	
	public String getSchMenuNm() {
		return schMenuNm;
	}

	public void setSchMenuNm(String schMenuNm) {
		this.schMenuNm = schMenuNm;
	}

	public String getMenuLcd() {
		return menuLcd;
	}

	public void setMenuLcd(String menuLcd) {
		this.menuLcd = menuLcd;
	}

	public String getMenuScd() {
		return menuScd;
	}

	public void setMenuScd(String menuScd) {
		this.menuScd = menuScd;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getMenuNote() {
		return menuNote;
	}

	public void setMenuNote(String menuNote) {
		this.menuNote = menuNote;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUpNo() {
		return upNo;
	}

	public void setUpNo(String upNo) {
		this.upNo = upNo;
	}

	public String getUpDate() {
		return upDate;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

}
