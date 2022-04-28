package kr.co.abandog.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.co.abandog.dto.AbandogAdoptReviewDTO;
import kr.co.abandog.dto.PageRequestDTO;
import kr.co.abandog.dto.PageResultDTO;
import kr.co.abandog.entity.AbandogAdoptReview;
import kr.co.abandog.entity.Member;
import kr.co.abandog.repository.AbandogAdoptReviewFileRepository;
import kr.co.abandog.repository.AbandogAdoptReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class AbandogAdoptReviewServiceImpl implements AbandogAdoptReviewService {
	private final AbandogAdoptReviewRepository adopReviewRepository;
	
	//목록 보기 요청
	@Override
	public PageResultDTO<AbandogAdoptReviewDTO, Object[]> getList(PageRequestDTO dto) {
		log.info("getList 호출");
		Page<Object []> result = adopReviewRepository.searchPage(dto.getType(), 
																 dto.getKeyword(), 
																 dto.getPageable(Sort.by("review_num").descending()));
		
		Function<Object[], AbandogAdoptReviewDTO> fn = (en -> entityToDTO((AbandogAdoptReview)en[0],
																		  (Member)en[1]));
		
		
		return new PageResultDTO<>(result, fn);
	}
	
	//상세 보기 요청
	@Override
	public AbandogAdoptReviewDTO get(Integer review_num) {
		
		Object review = adopReviewRepository.getReviewByReviewNum(review_num);
		Object [] ar = (Object []) review;
		
		return entityToDTO((AbandogAdoptReview)ar[0], (Member)ar[1]);
	}
	
	//게시물 등록
	@Override
	public Integer reviewRegister(AbandogAdoptReviewDTO dto) {
		log.info("reviewRegister 호출");
		
		AbandogAdoptReview review = dtoToEntity(dto);
		adopReviewRepository.save(review);
		return review.getReview_num();
	}

	//게시물 수정
	@Override
	public void reviewModify(AbandogAdoptReviewDTO dto) {
		log.info("AbandogAdoptReviewDTO 호출");
		
		Object review = adopReviewRepository.getReviewByReviewNum(dto.getReview_num());
		Object [] ar = (Object []) review;
		
		((AbandogAdoptReview)ar[0]).setReviewTitle(dto.getReview_title());
		((AbandogAdoptReview)ar[0]).setReviewContent(dto.getReview_content());

		adopReviewRepository.save((AbandogAdoptReview)ar[0]);
	}

	//게시물 삭제
	@Override
	public void reviewRemove(Integer review_num) {
		adopReviewRepository.deleteByReviewNum(review_num);
	}

}
