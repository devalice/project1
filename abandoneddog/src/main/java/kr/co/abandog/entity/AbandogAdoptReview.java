package kr.co.abandog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="abandog_adopt_review")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogAdoptReview extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="review_num")
	private Integer review_num;
	@Column(name="review_title", length=50)
	private String review_title;


	@ManyToOne(fetch = FetchType.LAZY) 
    private Member member; 
	
	@Column(name="review_content", length=500)
	private String review_content;
	@Column(name="review_file", length=100)
	private String review_file;
	@Column(name="review_count")
	private Date review_datetime;
	
    
}
