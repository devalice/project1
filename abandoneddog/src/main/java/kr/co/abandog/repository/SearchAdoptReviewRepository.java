package kr.co.abandog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//최대한 현재 구현되어 있는 내용을 수정하지 않고 동작하도록 하기 위해 새로운 인터페이스 생성
public interface SearchAdoptReviewRepository {
	
	//목록 보기를 위한 메서드
	Page<Object []> searchPage(String type, String keyword, Pageable pageable);

}
