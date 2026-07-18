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
package com.sldnfarchive.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sldnfarchive.model.OAuthVO;
import com.sldnfarchive.service.OAuthService;

/**
 * @Class Name : OAuthServiceImpl.java
 * @Description : OAuth Service Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.07.16		HHP           최초생성
 *
 * @author HHP
 * @since 2026.07.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("OAuthService")
public class OAuthServiceImpl extends EgovAbstractServiceImpl implements OAuthService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OAuthServiceImpl.class);
	
	/** kakaoAuth */
	@Resource(name = "kakaoOAuthVO")
	private OAuthVO kakaoOAuthVO;
	
	/**
	 * 인가 토큰 취득
	 * @param code
	 * @return accessToken
	 * @exception Exception
	 */
	@Override
	public String getAccessToken(String code) throws Exception {
		String url = "https://kauth.kakao.com/oauth/token";
		String id = kakaoOAuthVO.getClientId();
		String redirectUri = kakaoOAuthVO.getCallbackUrl();
		String secret = kakaoOAuthVO.getClientSecret();
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("grant_type", "authorization_code");
		param.add("client_id", id);
		param.add("redirect_uri", redirectUri);
		param.add("code", code);
		param.add("client_secret", secret);
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(param, header);
		ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.POST, req, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(res.getBody());
		
		String accessToken = jsonNode.get("access_token").asText();
		
		return accessToken;
	}
	
	/**
	 * 사용자 정보 조회
	 * @param accessToken
	 * @return userInfo
	 * @exception Exception
	 */
	@Override
	public EgovMap getUserInfo(String accessToken) throws Exception {
		EgovMap userInfo = new EgovMap();
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://kapi.kakao.com/v2/user/me";
		
		HttpHeaders header = new HttpHeaders();
		header.setBearerAuth(accessToken);
		header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<Void> entity = new HttpEntity<>(header);
		ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(res.getBody());
		
		Long id = node.path("id").asLong();
		String nickname = node.path("kakao_account").path("profile").path("nickname").asText(null);
		
		userInfo.put("id", id.toString());
		userInfo.put("nickname", nickname);
		
		return userInfo;
	}
	
}
