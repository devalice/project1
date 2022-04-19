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

	@Column(name="review_file", length=100)
	private String review_file;
	
}
