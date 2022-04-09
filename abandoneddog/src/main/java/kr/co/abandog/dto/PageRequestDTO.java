package kr.co.abandog.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
	private int page; //현재 페이지
	private int size; //페이지당 출력 갯수
	
	private String type;
	private String keyword;
	
	//기본 생성자를 이용해서 page와 size 값 초기화
	public PageRequestDTO() {
		page = 1;
		size = 10;
	}
	
	//pageable 객체를 생성해주는 메서드
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page -1, size, sort);
	}
}
