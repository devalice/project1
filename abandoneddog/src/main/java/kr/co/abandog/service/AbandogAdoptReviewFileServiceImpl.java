package kr.co.abandog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.abandog.dto.AbandogAdoptReviewDTO;
import kr.co.abandog.dto.AbandogAdoptReviewFileDTO;
import kr.co.abandog.entity.AbandogAdoptReview;
import kr.co.abandog.entity.AbandogAdoptReviewFile;
import kr.co.abandog.entity.Member;
import kr.co.abandog.repository.AbandogAdoptReviewFileRepository;
import kr.co.abandog.repository.AbandogAdoptReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AbandogAdoptReviewFileServiceImpl implements AbandogAdoptReviewFileService{

	private final AbandogAdoptReviewRepository adopReviewRepository;
	
	private final AbandogAdoptReviewFileRepository adopReviewFileRepository;
	
	//게시물 파일 등록
	@Override
	public void reviewFileRegister(Integer review_num, AbandogAdoptReviewFileDTO dto) {
		
		log.info("reviewFileRegister..");
		
		//파일이 비어있으면
		if(dto.getReviewFile_name() == null || dto.getReviewFile_name().isEmpty()) {
			return;
		}
		
		//객체 쪼개기
		String[] file_name = dto.getReviewFile_name().split(",");
		String[] file_path = dto.getReviewFile_path().split(",");
				
		List <AbandogAdoptReviewFileDTO> list = new ArrayList<AbandogAdoptReviewFileDTO>();
		for(int i=0; i < file_name.length; i++) {
			AbandogAdoptReviewFileDTO fileDTO = new AbandogAdoptReviewFileDTO();
			fileDTO.setReview_num(review_num);
			fileDTO.setReviewFile_name(file_name[i]);
			fileDTO.setReviewFile_path(file_path[i]);
			list.add(fileDTO);
			
			log.info("fileDTO: "+ fileDTO);
		}
		
		//게시물 파일 테이블 저장
		Object review = adopReviewRepository.getReviewByReviewNum(review_num);
		Object [] ar = (Object []) review;		
		for(int i=0; i<list.size(); i++) {
			AbandogAdoptReviewFile reviewFile = dtoToEntity(list.get(i), (AbandogAdoptReview)ar[0]);
			adopReviewFileRepository.save(reviewFile);
		}
	}
	
	
	//게시물 파일 삭제
	@Override
	public void reviewFileRemove(Integer review_num) {
		adopReviewFileRepository.deleteByReviewNum(review_num);
		
	}

	//게시물 파일 상세 보기
	@Override
	public List<AbandogAdoptReviewFileDTO> get(Integer review_num) {
		
		List<AbandogAdoptReviewFileDTO> list = new ArrayList<AbandogAdoptReviewFileDTO>();
		
		List<Object> reviewfile = adopReviewFileRepository.getReviewByReviewNum(review_num);
		
		for(Object ob: reviewfile) {
			list.add(entityToDTO((AbandogAdoptReviewFile)ob));
		}
		
		return list;
	}

}
