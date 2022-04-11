package kr.co.abandog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="abandog_adopt_review")
@ToString
@Getter
//@Builder
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogAdoptReview extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="review_num")
	private Integer review_num;
	@Column(name="review_title", length=50)
	private String review_title;
	@Column(name="review_content", length=500)
	private String review_content;

	@ManyToOne(fetch = FetchType.LAZY) 
    private Member member; 
	
	public void setReviewTitle(String review_title) {
		this.review_title = review_title;
	}
	
	public void setReviewContent(String review_content) {
		this.review_content = review_content;
	}
	
}
