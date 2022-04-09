package kr.co.abandog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="abandog_type_cd")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogTypeCD extends BaseEntity{
	
	@Id
	@Column(name="type_cd", length=2, columnDefinition = "char")
	private String type_cd;
	
	@Column(name="kinds", length=30, nullable= false)
	private String kinds;

}
