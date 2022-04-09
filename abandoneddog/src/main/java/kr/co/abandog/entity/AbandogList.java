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
@Table(name="abandog_list")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogList extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="list_num", nullable=false)
	private int list_num;
	
	@Column(name="list_title", length=50, nullable=false)
	private String list_title;
	
	@Column(name="list_content", length=500)
	private String list_content;
	
	@Column(name="list_file", length=200)
	private String list_file;
	
	@Column(name="list_count")
	private int list_count;
	
	@Column(name="list_datetime", columnDefinition="datetime")
	private Date list_datetime;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    private Member member_email; 
}
