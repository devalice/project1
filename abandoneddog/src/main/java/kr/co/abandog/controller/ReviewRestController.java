package kr.co.abandog.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/review/") //공통주소
public class ReviewRestController {
	
	//application.yml에서 설정해준 path
	@Value("${custom.path.uploadImg}")
	private String uploadPath;
	
	//디렉토리를 만드는 메서드
	private String makeDirectory() {
		String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		System.out.println(str);
		// '/'를 파일 구분자로 변경
		String realUploadPath = str.replace("/", File.separator);
		
		//디렉토리 생성
		File uploadPathDir = new File(uploadPath, realUploadPath);
		
		//File 객체가 없으면 디렉토리를 생성
		if(uploadPathDir.exists() == false) {
			uploadPathDir.mkdirs(); //mkdirs가 하위의 하위 폴더 생성
		}else {
			
		}
		
		//업로드 디렉토리 경로를 리턴
		return realUploadPath;		
	}
	
	//파일 업로드 처리 메서드
	@PostMapping("/fileuploadajax")
	public List<String> uploadFile(MultipartFile [] uploadFiles) {
		
		log.info("uploadFile...");
		
		if(uploadFiles == null) {
			
			log.info("uploadFiles null...");
			return null;
		}
		
		List<String> fileList = new ArrayList<String>();
		
		for(MultipartFile uploadFile : uploadFiles) {
			
			//이미지 파일이 아니면 업로드 안함
			if(uploadFile.getContentType().startsWith("image") == false) {
				return null;
			}
			
			//업로드 되는 원본 파일 이름 출력
			String originalName = uploadFile.getOriginalFilename();
			
			//IE 나 Edge는 파일의 경로로 전달되므로 파일의 경로를 제거
			String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);
			
			log.info("File Name:"+fileName);
			
			//업로드 될 디렉토리 경로를 생성
			String realUploadPath = makeDirectory();
			
			//uuid(범용고유식별자) 생성
			String uuid = UUID.randomUUID().toString();
			String saveName = uploadPath + File.separator + realUploadPath + File.separator + uuid + fileName;
			
			
			Path savePath = Paths.get(saveName);
			try {
				//파일 업로드
				uploadFile.transferTo(savePath);
			}catch(Exception e) {
				log.info("예외: "+e.getLocalizedMessage());
				e.printStackTrace();
			}
			
			fileList.add(saveName);
		}
		
		return fileList;
	}

}
