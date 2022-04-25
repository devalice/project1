package kr.co.abandog.service;

import kr.co.abandog.dto.AbandogAdoptReviewFileDTO;
import kr.co.abandog.entity.AbandogAdoptReview;
import kr.co.abandog.entity.AbandogAdoptReviewFile;
import kr.co.abandog.entity.AbandogAdoptReviewFileKey;
import kr.co.abandog.entity.Member;

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
	
	default AbandogAdoptReviewFileDTO entityToDTO(AbandogAdoptReviewFile review, Member member) {
		AbandogAdoptReviewFileDTO dto = AbandogAdoptReviewFileDTO.builder()
														 	     .build();
		
		return dto;
	}
	
	//게시물 파일 등록
	public void reviewFileRegister(Integer review_num, AbandogAdoptReviewFileDTO dto);

}
