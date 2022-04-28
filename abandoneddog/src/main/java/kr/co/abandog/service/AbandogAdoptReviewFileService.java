package kr.co.abandog.service;

import java.util.List;

import kr.co.abandog.dto.AbandogAdoptReviewFileDTO;
import kr.co.abandog.entity.AbandogAdoptReview;
import kr.co.abandog.entity.AbandogAdoptReviewFile;
import kr.co.abandog.entity.AbandogAdoptReviewFileKey;

public interface AbandogAdoptReviewFileService {
	
	default AbandogAdoptReviewFile dtoToEntity(AbandogAdoptReviewFileDTO dto, AbandogAdoptReview review) {
		
		AbandogAdoptReviewFileKey fileKey = AbandogAdoptReviewFileKey.builder()
																	 .reviewFile_name(dto.getReviewFile_name())
																	 .review(review)
																	 .build();
				
		AbandogAdoptReviewFile reviewFile = AbandogAdoptReviewFile.builder()
				                                                  .reviewFile_path(dto.getReviewFile_path())
				                                                  .reviewFile_uuid(dto.getReviewFile_uuid())
				                                                  .fileKey(fileKey)
																  .build();
		return reviewFile;
	}
	
	default AbandogAdoptReviewFileDTO entityToDTO(AbandogAdoptReviewFile reviewfile) {
		AbandogAdoptReviewFileDTO dto = AbandogAdoptReviewFileDTO.builder()
																 .review_num(reviewfile.getFileKey().getReview().getReview_num())
																 .reviewFile_name(reviewfile.getFileKey().getReviewFile_name())
																 .reviewFile_path(reviewfile.getReviewFile_path())
														 	     .build();
		
		return dto;
	}
	
	//게시물 파일 등록
	public void reviewFileRegister(Integer review_num, AbandogAdoptReviewFileDTO dto);
	
	//게시물 파일 삭제
	public void reviewFileRemove(Integer review_num);
	
	//게시물 파일 상세보기
	public List<AbandogAdoptReviewFileDTO> get(Integer review_num);

}
