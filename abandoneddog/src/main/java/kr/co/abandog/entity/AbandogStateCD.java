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
@Table(name="abandog_state_cd")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogStateCD extends BaseEntity{
	
	@Id
	@Column(name="state_cd", length=1, columnDefinition = "char")
	private String state_cd;
	
	@Column(name="state_name", length=30, nullable= false)
	private String state_name;

}
