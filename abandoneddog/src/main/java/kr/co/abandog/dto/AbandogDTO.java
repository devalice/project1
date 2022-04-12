package kr.co.abandog.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogDTO {
	
	private Integer abandog_id;
	private String abandog_name;
	private Integer abandog_age;
	private String aban_dog_gender;
	private String abandog_date;
	private String abandog_location;
	private String abandog_guardian;
	private String animal_no;
	private String member_email; //유기견 관리중인 회원 정보
	private String state_cd; //상태
	private String type_cd;	//종류
	
	//입력 날짜와 수정된 날짜
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	
}
