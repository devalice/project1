package kr.co.abandog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Embeddable
@ToString(exclude = {"review"})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbandogAdoptReviewFileKey implements Serializable{
	@Column(name = "reviewFile_name")
	private String reviewFile_name; //원본 파일 이름
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AbandogAdoptReview review;	
}
