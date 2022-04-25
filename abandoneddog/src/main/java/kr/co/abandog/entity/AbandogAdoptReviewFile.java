package kr.co.abandog.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="abandog_adopt_review_file")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogAdoptReviewFile{
	
	@EmbeddedId
	private AbandogAdoptReviewFileKey fileKey;
	
	@Column(name="reviewFile_uuid", length=100)
	private String reviewFile_uuid; //서버 파일 이름
	
	@Column(name="reviewFile_path", length=100)
	private String reviewFile_path; //파일 위치
	
	//게시물과 함께 생성되고 수정되므로 삭제 일자만 가짐
	@Column(name="del_dtm")
	private LocalDateTime del_dtm;

}
