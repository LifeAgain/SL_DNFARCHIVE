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
 * @Class Name : VideoVO.java
 * @Description : VideoVO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.06.08		HHP           최초생성
 *
 * @author HHP
 * @since 2026.06.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class VideoVO {
	
	/** 검색할 제목/내용 */
	private String schKeyword;
	
	/** 검색할 작성자 */
	private String schAuthor;
	
	/** 동영상 번호 */
	private int videoNo;
	
	/** 게시판 형태 */
	private String title;
	
	/** 게시판명 */
	private String content;
	
	/** 동영상 주소 */
	private String videoUrl;
	
	/** 등록자 */
	private int regNo;
	
	/** 등록일자 */
	private String regDate;
	
	/** 수정자 */
	private int upNo;
	
	/** 수정일자 */
	private String upDate;
	
	/** 현재 페이지 */
	private int curPage = 1;
	
	/** 페이지 당 레코드 개수 */
	private int pageUnit = 10;
	
	/** 화면 당 페이지 개수 */
	private int pageSize = 10;
	
	/** 한 화면 첫 페이지 */
	private int curIdx = 1;
	

	public String getSchKeyword() {
		return schKeyword;
	}

	public void setSchKeyword(String schKeyword) {
		this.schKeyword = schKeyword;
	}

	public String getSchAuthor() {
		return schAuthor;
	}

	public void setSchAuthor(String schAuthor) {
		this.schAuthor = schAuthor;
	}

	public int getVideoNo() {
		return videoNo;
	}

	public void setVideoNo(int videoNo) {
		this.videoNo = videoNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public int getRegNo() {
		return regNo;
	}

	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUpNo() {
		return upNo;
	}

	public void setUpNo(int upNo) {
		this.upNo = upNo;
	}

	public String getUpDate() {
		return upDate;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurIdx() {
		return curIdx;
	}

	public void setCurIdx(int curIdx) {
		this.curIdx = curIdx;
	}

}
