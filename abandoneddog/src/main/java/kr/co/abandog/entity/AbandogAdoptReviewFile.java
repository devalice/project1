package kr.co.abandog.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="abandog_adopt_review_file")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogAdoptReviewFile {
	
	@EmbeddedId
	private AbandogAdoptReviewFileKey fileKey;

	@Column(name="review_file1", length=100)
	private String review_file1;
	
	@Column(name="review_file2", length=100)
	private String review_file2;
	
	@Column(name="review_file3", length=100)
	private String review_file3;
	
}
