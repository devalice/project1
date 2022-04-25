package kr.co.abandog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AbandogAdoptReviewFileDTO {

	private Integer review_num;
	private String reviewFile_name; //원본 파일 이름

	private String reviewFile_uuid; //서버 파일 이름
	private String reviewFile_path; //파일 위치
	
}
