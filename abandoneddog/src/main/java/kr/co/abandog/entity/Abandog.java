package kr.co.abandog.entity;

import java.io.Serializable;

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
@Table(name="abandog")
@ToString(exclude = {"member", "stateCD","typeCD"})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Abandog extends BaseEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="abandog_id", length=30)
	private Integer abandog_id;
	
	@Column(name="abandog_name", length=30, nullable= false)
	private String abandog_name;
	
	@Column(name="abandog_age")
	private Integer abandog_age;
	
	@Column(name="aban_dog_gender", length=1, columnDefinition = "char")
	private String aban_dog_gender;
	
	@Column(name="abandog_date", length=11)
	private String abandog_date; //입소 날짜
	
	@Column(name="abandog_location", length=50)
	private String abandog_location;
	
	@Column(name="abandog_guardian", length=30)
	private String abandog_guardian;
	
	@Column(name="abandog_image", length=200)
	private String abandog_image;
	
	@Column(name="animal_no", unique=true)
	private String animal_no; //서울시 보호소 데이터 pk
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_email")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="state_cd")
	private AbandogStateCD stateCD;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="type_cd")
	private AbandogTypeCD typeCD;
}
