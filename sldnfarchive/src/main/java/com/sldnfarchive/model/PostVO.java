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
 * @Class Name : PostVO.java
 * @Description : PostVO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.04.09		HHP           최초생성
 *
 * @author HHP
 * @since 2026.04.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class PostVO {
	
	/** 검색할 제목/내용 */
	private String schKeyword;
	
	/** 검색할 작성자 */
	private String schAuthor;
	
	/** 게시글 번호 */
	private int postNo;
	
	/** 게시판 번호 */
	private int boardNo;
	
	/** 제목 */
	private String title;
	
	/** 내용 */
	private String content;
	
	/** 파일명 */
	private String fileNm;
	
	/** 업로드파일명 */
	private String uploadFileNm;
	
	/** 조회수 */
	private int viewCnt;
	
	/** 댓글 번호 */
	private int commentNo;
	
	/** 등록자 */
	private String regNo;
	
	/** 등록일자 */
	private String regDate;
	
	/** 수정자 */
	private String upNo;
	
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

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getUploadFileNm() {
		return uploadFileNm;
	}

	public void setUploadFileNm(String uploadFileNm) {
		this.uploadFileNm = uploadFileNm;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
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
