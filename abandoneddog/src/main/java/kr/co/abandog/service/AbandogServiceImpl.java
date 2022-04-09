package kr.co.abandog.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import kr.co.abandog.entity.Abandog;
import kr.co.abandog.entity.AbandogImg;
import kr.co.abandog.repository.AbandogImgRepository;
import kr.co.abandog.repository.AbandogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AbandogServiceImpl implements AbandogService {
	
	private final AbandogRepository abandogRepository;
	private final AbandogImgRepository abandogImgRepository;

	//호출 가능한 총 갯수
	@Override
	public Long abandogCnt(String json) {
		Long totalCnt = 0L;
		
		try{
			//JSON 파싱 객체 생성
			JSONParser jsonParser = new JSONParser();
			
			//JSON객체로 파서를 통해 저장
			JSONObject jsonObj = (JSONObject)jsonParser.parse(json);
			
			//데이터 분해
			JSONObject waitAbandog = (JSONObject) jsonObj.get("TbAdpWaitAnimalView");
			if(waitAbandog == null) {
				waitAbandog = (JSONObject) jsonObj.get("TbAdpWaitAnimalPhotoView");
			}
			
			totalCnt = (Long)waitAbandog.get("list_total_count");
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return totalCnt;
	}
	
	//서울시 보호 유기견 삽입
	@Override
	public String abandogput(String json) {
		try{
			//JSON 파싱 객체 생성
			JSONParser jsonParser = new JSONParser();
			
			//JSON객체로 파서를 통해 저장
			JSONObject jsonObj = (JSONObject)jsonParser.parse(json);
			
			//데이터 분해
			JSONObject waitAbandog = (JSONObject) jsonObj.get("TbAdpWaitAnimalView");
			
			JSONArray array = (JSONArray)waitAbandog.get("row");
			JSONObject jObj;
			
			for(int i=0; i<array.size(); i=i+1) {
				jObj = (JSONObject)array.get(i);
				
				String aban_dog_gender = jObj.get("SEXDSTN").toString();
				Integer abandog_age = Integer.parseInt(jObj.get("AGE").toString().substring(0, jObj.get("AGE").toString().indexOf("(")));
				String abandog_date = jObj.get("ENTRNC_DATE").toString(); //입소 날짜
				String abandog_name = jObj.get("NM").toString();
				String animal_no = jObj.get("ANIMAL_NO").toString();
				
				String state_cd = jObj.get("ADP_STTUS").toString(); //N(입양대기), P(입양진행중), C(입양완료)
				String type_cd = null;
				
				switch(jObj.get("BREEDS").toString()) {
					case "닥스훈트":
						type_cd = "D";
						break;
						
					case "말티즈":
						type_cd = "Ma";
						break;
						
					case "요크셔테리어":
						type_cd = "Y";
						break;
						
					case "푸들":
						type_cd = "P";
						break;
						
					case "믹스":
						type_cd = "Mi";
						break;
					
					default:
						type_cd = "E";
						break;
				}
				
				
				//DB저장
				abandogRepository.mergeAbandog(aban_dog_gender, abandog_age, abandog_date, 
											   abandog_name, animal_no, "openapi@naver.com", state_cd, type_cd);
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return "";
	}
	
	
	//서울시 보호 유기견 이미지 삽입
	@Override
	public String abandogImgput(String json) {
		try{
			//JSON 파싱 객체 생성
			JSONParser jsonParser = new JSONParser();
			
			//JSON객체로 파서를 통해 저장
			JSONObject jsonObj = (JSONObject)jsonParser.parse(json);
			
			//데이터 분해
			JSONObject waitAbandog = (JSONObject) jsonObj.get("TbAdpWaitAnimalPhotoView");
			
			
			JSONArray array = (JSONArray)waitAbandog.get("row");
			
			JSONObject jObj;
			
			for(int i=0; i<array.size(); i=i+1) {
				jObj = (JSONObject)array.get(i);
				
				String animal_no = jObj.get("ANIMAL_NO").toString();
				log.info("animal_no:" + animal_no);
				
				String phototype = jObj.get("PHOTO_KND").toString();
				log.info("phototype:" + phototype);
				
				double d_photonum = Double.parseDouble(jObj.get("PHOTO_NO").toString());
				int i_photonum = (int)d_photonum;
				Integer photonum = Integer.valueOf(i_photonum);
				log.info("photonum:" + photonum);
				
				String photoURL = jObj.get("PHOTO_URL").toString();
				log.info("photoURL:" + photoURL);
				
				//DB저장
				abandogImgRepository.mergeAbandogImg(animal_no, phototype, photonum, photoURL);
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return "";
	}

	
	@Override
	public List<Object []> getList() {
		List<Object []> abandog = abandogRepository.getAbandogList();
		
		List<Object []> arr = new ArrayList<>();
		for(Object [] ob : abandog) {
			Object[] obj = new Object[abandog.size()];
			obj[0] = new Object();
			obj[0] = entityToDTO((Abandog)ob[0]);
			
			obj[1] = new Object();
			obj[1] = ImgentityToDTO((AbandogImg)ob[1],(Abandog)ob[0]);
			arr.add(obj);
		}
		
		return arr;
	}

}
