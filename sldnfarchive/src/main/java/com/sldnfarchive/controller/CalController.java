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
package com.sldnfarchive.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Class Name : CalController.java
 * @Description : Cal Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.07.22	 HHP          최초생성
 *
 * @author HHP
 * @since 2026.07.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@RequestMapping("/cal")
public class CalController {

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	private final String BASE_URL = "https://api.neople.co.kr/df";
	private final String API_KEY = "e5bpFIpV8gTY3BTZn0NQ9a8Tdj0rVls7";
	
	
	/**
	 * 데미지계산기 메인
	 * @return "cal/damageCal"
	 * @exception Exception
	 */
	@RequestMapping(value = "/damageCal.do")
	public String damageCal() throws Exception {
		System.out.println("============================");
		System.out.println("Success - damageCal.do");
		System.out.println("============================");
		
		return "cal/damageCal";
	}
	
	/**
	 * 데미지계산기 캐릭터목록
	 * @return "cal/calList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/calList.do")
	public String calList(HttpServletRequest req, ModelMap model) throws Exception {
		String serverId = req.getParameter("serverId");
		String characterName = req.getParameter("characterName");
		String url = BASE_URL + "/servers/" + serverId + "/characters?characterName=" + characterName + "&apikey=" + API_KEY;
		
		RestTemplate restTemplate = new RestTemplate();
		String jsonString = restTemplate.getForObject(url, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(jsonString);
		JsonNode rowsNode = rootNode.path("rows");
		
		List<EgovMap> calList = new ArrayList<EgovMap>();
		
		System.out.println("============================");
		System.out.println("Success - calList.do");
		System.out.println("============================");
		
		if(rowsNode.isArray()) {
			for(JsonNode node : rowsNode) {
				String characterId = node.path("characterId").asText(null);
				String charUrl = BASE_URL + "/servers/" + serverId + "/characters/" + characterId + "/status?apikey=" + API_KEY;
				
				String charJsonStr = restTemplate.getForObject(charUrl, String.class);
				JsonNode charRootNode = objectMapper.readTree(charJsonStr);
				
				EgovMap selectCal = new EgovMap();
				Map<String, Object> map = objectMapper.convertValue(charRootNode, Map.class);
				
				selectCal.putAll(map);
				calList.add(selectCal);
			}
		}
		
		model.addAttribute("calList", calList);
		
		return "cal/calList";
	}
	
	/**
	 * 데미지계산기 상세
	 * @return "cal/calDetail"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectCal.do")
	public String selectCal(HttpServletRequest req, ModelMap model) throws Exception {
		String serverId = req.getParameter("serverId");
		String characterId = req.getParameter("characterId");
		String commonUrl = BASE_URL + "/servers/" + serverId + "/characters/" + characterId;
		String url1 = commonUrl + "/status?apikey=" + API_KEY;
		String url2 = commonUrl + "/equip/equipment?apikey=" + API_KEY;
		String url3 = commonUrl + "/equip/oath?apikey=" + API_KEY;
		
		RestTemplate restTemplate = new RestTemplate();
		
		String jsonString1 = restTemplate.getForObject(url1, String.class);
		String jsonString2 = restTemplate.getForObject(url2, String.class);
		String jsonString3 = restTemplate.getForObject(url3, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode rootNode1 = objectMapper.readTree(jsonString1);
		JsonNode rootNode2 = objectMapper.readTree(jsonString2);
		JsonNode rootNode3 = objectMapper.readTree(jsonString3);
		
		// tab1
		JsonNode equipNode = rootNode2.path("equipment");
		JsonNode setItemInfo = rootNode2.path("setItemInfo");
		String setItemRarity = (setItemInfo.size() <= 0) ? "" : setItemInfo.get(0).path("setItemRarityName").asText("");
		String rarityClass = "";
		String setItemNm = (setItemInfo.size() <= 0) ? "" : setItemInfo.get(0).path("setItemName").asText("") + (!(setItemRarity.equals("") || setItemRarity.equals("태초")) ? " " + setItemRarity.split(" ")[1] : "");
		String setItemImg = "";
		int setPts = (setItemInfo.size() <= 0) ? 0 : setItemInfo.get(0).path("active").path("setPoint").path("current").asInt(0);
		int setPts2 = 0;
		int adjustedPts = (setItemInfo.size() <= 0) ? 0 : setItemInfo.get(0).path("active").path("setPoint").path("adjustedPoint").asInt(0);
		int curSetPts = setPts + adjustedPts;
		
		JsonNode oathNode = rootNode3.path("oath");
		JsonNode setOathInfo = oathNode.path("setInfo");
		String setOathId = setOathInfo.path("setId").asText("");
		String setOathRarity = setOathInfo.path("setRarityName").asText("");
		String setOathNm = setOathInfo.path("setOptionName").asText("");
		setOathNm = (setOathNm.equals("")) ? "" : setOathNm.split(" : ")[1] + (!(setOathRarity == null || setOathRarity.equals("태초")) ? " " + setOathRarity.split(" ")[1] : "");
		String setOathBaseNm = setOathInfo.path("setName").asText("");
		String setOathImg = "";
		String mainOathNm = oathNode.path("info").path("itemName").asText("");
		String mainOathRarity = oathNode.path("info").path("itemRarity").asText("");
		String mainOathImg = "";
		JsonNode crystalNode = oathNode.path("crystal");
		int setOathPts = setOathInfo.path("active").path("setPoint").path("current").asInt(0);
		int setOathPts2 = oathNode.path("info").path("setPoint").asInt(0);
		int curSetOathPts = setOathPts - adjustedPts;
		
		System.out.println("============================");
		System.out.println("Success - selectCal.do");
		System.out.println("============================");
		
		EgovMap selectCal = new EgovMap();
		
		// tab1
		Map<String, Object> map1 = objectMapper.convertValue(rootNode1, Map.class);
		
		selectCal.putAll(map1);
		
		if(setItemNm.contains("이상향") || setItemNm.contains("황금향")) setItemImg = "/images/img_resource/set115/0.png";
		else if(setItemNm.contains("용투장")) setItemImg = "/images/img_resource/set115/1.png";
		else if(setItemNm.contains("정화")) setItemImg = "/images/img_resource/set115/2.png";
		else if(setItemNm.contains("세렌디피티")) setItemImg = "/images/img_resource/set115/3.png";
		else if(setItemNm.contains("에너지")) setItemImg = "/images/img_resource/set115/4.png";
		else if(setItemNm.contains("페어리")) setItemImg = "/images/img_resource/set115/5.png";
		else if(setItemNm.contains("자연")) setItemImg = "/images/img_resource/set115/6.png";
		else if(setItemNm.contains("발키리")) setItemImg = "/images/img_resource/set115/7.png";
		else if(setItemNm.contains("에테리얼 오브 아츠")) setItemImg = "/images/img_resource/set115/8.png";
		else if(setItemNm.contains("그림자")) setItemImg = "/images/img_resource/set115/9.png";
		else if(setItemNm.contains("무리")) setItemImg = "/images/img_resource/set115/10.png";
		else if(setItemNm.contains("마력")) setItemImg = "/images/img_resource/set115/11.png";
		
		if(setItemRarity.contains("커먼")) rarityClass = "common";
		else if(setItemRarity.contains("언커먼")) rarityClass = "uncommon";
		else if(setItemRarity.contains("레어")) rarityClass = "rare";
		else if(setItemRarity.contains("유니크")) rarityClass = "unique";
		else if(setItemRarity.contains("크로니클")) rarityClass = "chronicle";
		else if(setItemRarity.contains("레전더리")) rarityClass = "legendary";
		else if(setItemRarity.contains("에픽")) rarityClass = "epic";
		else if(setItemRarity.contains("신화")) rarityClass = "myth";
		else if(setItemRarity.contains("태초")) rarityClass = "ancient";
		
		selectCal.put("setItemImg", setItemImg);
		selectCal.put("setItemRarity", rarityClass);
		
		if(setOathBaseNm.contains("황금")) setOathImg = "/images/img_resource/set115/0.png";
		else if(setOathBaseNm.contains("용투")) setOathImg = "/images/img_resource/set115/1.png";
		else if(setOathBaseNm.contains("정화")) setOathImg = "/images/img_resource/set115/2.png";
		else if(setOathBaseNm.contains("행운")) setOathImg = "/images/img_resource/set115/3.png";
		else if(setOathBaseNm.contains("한계")) setOathImg = "/images/img_resource/set115/4.png";
		else if(setOathBaseNm.contains("페어리")) setOathImg = "/images/img_resource/set115/5.png";
		else if(setOathBaseNm.contains("자연")) setOathImg = "/images/img_resource/set115/6.png";
		else if(setOathBaseNm.contains("발키리")) setOathImg = "/images/img_resource/set115/7.png";
		else if(setOathBaseNm.contains("에테리얼 오브 아츠")) setOathImg = "/images/img_resource/set115/8.png";
		else if(setOathBaseNm.contains("그림자")) setOathImg = "/images/img_resource/set115/9.png";
		else if(setOathBaseNm.contains("무리")) setOathImg = "/images/img_resource/set115/10.png";
		else if(setOathBaseNm.contains("마력")) setOathImg = "/images/img_resource/set115/11.png";
		
		if(setOathRarity.contains("커먼")) rarityClass = "common";
		else if(setOathRarity.contains("언커먼")) rarityClass = "uncommon";
		else if(setOathRarity.contains("레어")) rarityClass = "rare";
		else if(setOathRarity.contains("유니크")) rarityClass = "unique";
		else if(setOathRarity.contains("크로니클")) rarityClass = "chronicle";
		else if(setOathRarity.contains("레전더리")) rarityClass = "legendary";
		else if(setOathRarity.contains("에픽")) rarityClass = "epic";
		else if(setOathRarity.contains("신화")) rarityClass = "myth";
		else if(setOathRarity.contains("태초")) rarityClass = "ancient";
		
		if(mainOathNm.contains("그림자")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/1/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/1/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/1/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/1/3.gif";
		} else if(mainOathNm.contains("페어리")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/2/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/2/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/2/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/2/3.gif";
		} else if(mainOathNm.contains("황금")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/3/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/3/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/3/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/3/3.gif";
		} else if(mainOathNm.contains("용투")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/4/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/4/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/4/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/4/3.gif";
		} else if(mainOathNm.contains("정화")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/5/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/5/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/5/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/5/3.gif";
		} else if(mainOathNm.contains("행운")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/6/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/6/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/6/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/6/3.gif";
		} else if(mainOathNm.contains("한계")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/7/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/7/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/7/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/7/3.gif";
		} else if(mainOathNm.contains("자연")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/8/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/8/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/8/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/8/3.gif";
		} else if(mainOathNm.contains("발키리")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/9/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/9/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/9/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/9/3.gif";
		} else if(mainOathNm.contains("여우")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/10/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/10/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/10/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/10/3.gif";
		} else if(mainOathNm.contains("무리")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/11/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/11/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/11/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/11/3.gif";
		} else if(mainOathNm.contains("마력")) {
			if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/12/0.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/12/1.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/12/2.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/12/3.gif";
		} else {
			if(mainOathRarity.contains("레어")) mainOathImg = "/images/img_resource/oath/0/0.png";
			else if(mainOathRarity.contains("유니크")) mainOathImg = "/images/img_resource/oath/0/1.png";
			else if(mainOathRarity.contains("레전더리")) mainOathImg = "/images/img_resource/oath/0/2.png";
			else if(mainOathRarity.contains("에픽")) mainOathImg = "/images/img_resource/oath/0/3.png";
			else if(mainOathRarity.contains("태초")) mainOathImg = "/images/img_resource/oath/0/4.gif";
		}
		
		selectCal.put("setOathImg", setOathImg);
		selectCal.put("setOathRarity", rarityClass);
		selectCal.put("mainOathImg", mainOathImg);
		
		for(JsonNode item : equipNode) {
			String rarity = item.path("itemRarity").asText("");
			String ampYn = (item.path("amplificationName").asText("").equals("")) ? "강화" : "증폭";
			String ampYnEng = (item.path("amplificationName").asText("").equals("")) ? "rein" : "amp";
			JsonNode tune = item.path("tune");
			int lvl = 0;
			String lvlStr = "";
			int itemPts = 0;
			
			if(rarity.contains("커먼")) rarityClass = "common";
			else if(rarity.contains("언커먼")) rarityClass = "uncommon";
			else if(rarity.contains("레어")) rarityClass = "rare";
			else if(rarity.contains("유니크")) rarityClass = "unique";
			else if(rarity.contains("크로니클")) rarityClass = "chronicle";
			else if(rarity.contains("레전더리")) rarityClass = "legendary";
			else if(rarity.contains("에픽")) rarityClass = "epic";
			else if(rarity.contains("신화")) rarityClass = "myth";
			else if(rarity.contains("태초")) rarityClass = "ancient";
			
			if(tune.isArray() && tune.size() > 0) {
				lvl = tune.get(0).path("level").asInt(0);
				
				if(lvl == 1) lvlStr = "I";
				else if(lvl == 2) lvlStr = "II";
				else if(lvl == 3) lvlStr = "III";
				
				itemPts = tune.get(0).path("setPoint").asInt(0);
			}
			
			setPts2 += itemPts;
			
			selectCal.put(item.path("slotId").asText() + "id", item.path("itemId").asText(""));
			selectCal.put(item.path("slotId").asText(), item.path("itemName").asText(""));
			selectCal.put(item.path("slotId").asText() + "lvl", lvl);
			selectCal.put(item.path("slotId").asText() + "lvlStr", lvlStr);
			selectCal.put(item.path("slotId").asText() + "Rarity", rarityClass);
			selectCal.put(item.path("slotId").asText() + "reinforce", "+" + item.path("reinforce").asText("") + ampYn);
			selectCal.put(item.path("slotId").asText() + "AmpYn", ampYnEng);
		}
		
		for(JsonNode crystal : crystalNode) {
			String rarity = crystal.path("itemRarity").asText("");
			String itemNm = (rarity.equals("태초")) ? (crystal.path("itemName").asText("").split(":")[0] + " : " + crystal.path("itemRarity").asText("")) : crystal.path("itemName").asText("").split(":")[0];
			itemNm = (itemNm.equals("잔향의 안개 결정")) ? "고유" : itemNm;
			JsonNode tune = crystal.path("tune");
			int lvl = tune.path("level").asInt(0);
			String lvlStr = "";
			
			if(rarity.contains("커먼")) rarityClass = "common";
			else if(rarity.contains("언커먼")) rarityClass = "uncommon";
			else if(rarity.contains("레어")) rarityClass = "rare";
			else if(rarity.contains("유니크")) rarityClass = "unique";
			else if(rarity.contains("크로니클")) rarityClass = "chronicle";
			else if(rarity.contains("레전더리")) rarityClass = "legendary";
			else if(rarity.contains("에픽")) rarityClass = "epic";
			else if(rarity.contains("신화")) rarityClass = "myth";
			else if(rarity.contains("태초")) rarityClass = "ancient";
			
			if(lvl == 1) lvlStr = "I";
			else if(lvl == 2) lvlStr = "II";
			else if(lvl == 3) lvlStr = "III";
			
			int oathPts = tune.path("setPoint").asInt(0);
			
			setOathPts2 += oathPts;
			
			selectCal.put("crystalId" + crystal.path("slotNo"), crystal.path("itemId").asText(""));
			selectCal.put("crystalNm" + crystal.path("slotNo"), itemNm);
			selectCal.put("crystalRarity" + crystal.path("slotNo"), rarityClass);
			selectCal.put("crystallvl" + crystal.path("slotNo"), lvl);
			selectCal.put("crystallvlStr" + crystal.path("slotNo"), lvlStr);
		}
		
		selectCal.put("setItemNm", setItemNm);
		selectCal.put("setPts", (setItemNm.equals("")) ? setPts2 : setPts);
		selectCal.put("curSetPts", (setItemNm.equals("")) ? (setPts2 + adjustedPts) : curSetPts);
		selectCal.put("setOathId", setOathId);
		selectCal.put("setOathNm", setOathNm);
		selectCal.put("setOathPts", (setOathNm.equals("")) ? setOathPts2 : setOathPts);
		selectCal.put("curSetOathPts", (setOathNm.equals("")) ? (setOathPts2 - adjustedPts) : curSetOathPts);
		
		model.addAttribute("selectCal", selectCal);
		
		return "cal/calDetail";
	}
}
