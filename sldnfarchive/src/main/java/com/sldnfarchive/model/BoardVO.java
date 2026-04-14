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
 * @Class Name : BoardVO.java
 * @Description : BoardVO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.03.10		HHP           최초생성
 *
 * @author HHP
 * @since 2026.03.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class BoardVO {
	
	/** 검색할 게시판 형태 */
	private String schBoardType;
	
	/** 상위메뉴 검색 */
	private String schParentCd;
	
	/** 사용여부 검색 */
	private String schUseYn;
	
	/** 댓글기능 여부 검색 */
	private String schCommentYn;
	
	/** 검색할 게시판명 */
	private String schBoardNm;
	
	/** 게시판 번호 */
	private int boardNo;
	
	/** 게시판 형태 */
	private String boardType;
	
	/** 게시판명 */
	private String boardNm;
	
	/** 상위메뉴코드 */
	private String parentCd;
	
	/** 사용여부 */
	private String useYn;
	
	/** 댓글기능 사용여부 */
	private String commentYn;
	
	/** 비고 */
	private String boardNote;
	
	/** 등록자 */
	private String regNo;
	
	/** 등록일자 */
	private String regDate;
	
	/** 수정자 */
	private String upNo;
	
	/** 수정일자 */
	private String upDate;
	
	/** 관리 페이지 페이지 당 레코드 개수 */
	private int pageUnit;
	
	/** 관리 페이지 페이지 현재 페이지 넘버 */
	private int curPage;
	
	/** 관리 페이지 현재 인덱스 번호 */
	private int curIdx;
	

	public String getSchBoardType() {
		return schBoardType;
	}

	public void setSchBoardType(String schBoardType) {
		this.schBoardType = schBoardType;
	}
	
	public String getSchParentCd() {
		return schParentCd;
	}

	public void setSchParentCd(String schParentCd) {
		this.schParentCd = schParentCd;
	}

	public String getSchUseYn() {
		return schUseYn;
	}

	public void setSchUseYn(String schUseYn) {
		this.schUseYn = schUseYn;
	}	

	public String getSchCommentYn() {
		return schCommentYn;
	}

	public void setSchCommentYn(String schCommentYn) {
		this.schCommentYn = schCommentYn;
	}

	public String getSchBoardNm() {
		return schBoardNm;
	}

	public void setSchBoardNm(String schBoardNm) {
		this.schBoardNm = schBoardNm;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getBoardNm() {
		return boardNm;
	}

	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}

	public String getParentCd() {
		return parentCd;
	}

	public void setParentCd(String parentCd) {
		this.parentCd = parentCd;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getCommentYn() {
		return commentYn;
	}

	public void setCommentYn(String commentYn) {
		this.commentYn = commentYn;
	}

	public String getBoardNote() {
		return boardNote;
	}

	public void setBoardNote(String boardNote) {
		this.boardNote = boardNote;
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

	public int getCurIdx() {
		return curIdx;
	}

	public void setCurIdx(int curIdx) {
		this.curIdx = curIdx;
	}

}
