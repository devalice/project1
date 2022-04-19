package kr.co.abandog.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.abandog.dto.AbandogDTO;
import kr.co.abandog.service.AbandogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/openapi/") //공통주소
public class AbandogRestController {

	private final AbandogService abandogService;

	public void callopenAPIWithJSON() {

		//유기견 총 수
		StringBuffer result = new StringBuffer();
		try {
			String apiUrl = "http://openapi.seoul.go.kr:8088/"
							+ "724d594d436962653337646b6c6278/" //발급받은 인증키 삽입
							+ "json/"
							+ "TbAdpWaitAnimalView/"
							+ "1/"   //시작페이지
							+ "1/"; //종료페이지

			URL url = new URL(apiUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();

			BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

			String returnLine;
			while((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		int totlcnt = abandogService.abandogCnt(result.toString()).intValue();
		log.info("총 수:"+ totlcnt);

		//유기견 불러오기
		result = new StringBuffer();
		try {
			String apiUrl = "http://openapi.seoul.go.kr:8088/"
							+ "724d594d436962653337646b6c6278/" //???에 자기가 발급받은 인증키 삽입
							+ "json/"
							+ "TbAdpWaitAnimalView/"
							+ "1/"   //시작페이지
							+ totlcnt +"/"; //종료페이지

			URL url = new URL(apiUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();

			BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

			String returnLine;
			while((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		//DB에 저장
		abandogService.abandogput(result.toString());

	}
	
	public void callImgopenAPIWithJSON() {

		//유기견 이미지 파일 총 수
		StringBuffer result = new StringBuffer();
		try {
			String apiUrl = "http://openapi.seoul.go.kr:8088/"
							+ "724d594d436962653337646b6c6278/"
							+ "json/"
							+ "TbAdpWaitAnimalPhotoView/"
							+ "1/" 
							+ "1/";

			URL url = new URL(apiUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();

			BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

			String returnLine;
			while((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		int totlcnt = abandogService.abandogCnt(result.toString()).intValue();
		log.info("이미지 총 수:"+ totlcnt);

		
		//유기견 이미지 불러오기
		result = new StringBuffer();
		try {
			String apiUrl = "http://openapi.seoul.go.kr:8088/"
							+ "724d594d436962653337646b6c6278/"
							+ "json/"
							+ "TbAdpWaitAnimalPhotoView/"
							+ "1/"
							+ totlcnt +"/";

			URL url = new URL(apiUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();

			BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

			String returnLine;
			while((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//DB에 저장
		abandogService.abandogImgput(result.toString());
	}
	
	@GetMapping(value="/abandoglist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object []>> getList(){
		return new ResponseEntity<>(abandogService.getList(), HttpStatus.OK);
	}

}
