package kr.co.abandog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Embeddable
@ToString(exclude = {"review"})
public class AbandogAdoptReviewFileKey implements Serializable{
	@Column(name = "file_num")
	private Integer file_num;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private AbandogAdoptReview review;	
}
