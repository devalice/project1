package kr.co.abandog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="abandog_img")
@ToString(exclude = {"abandog"})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogImg extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="abandog_img")
	private Integer abandog_img;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="animal_no", referencedColumnName ="animal_no")
	private Abandog abandog;
	
	@Column(name="phototype", length=10)
	private String phototype;
	
	@Column(name="photonum")
	private Integer photonum;
	
	@Column(name="photoURL", length=255)
	private String photoURL;

}
