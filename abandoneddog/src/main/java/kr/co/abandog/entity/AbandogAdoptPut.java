package kr.co.abandog.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;



//데이터베이스 테이블과 연동
@Entity
@Table(name="abandog_adopt_put")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogAdoptPut extends BaseEntity {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="put_num",nullable=false)
private int put_num;
		
@Column(name="put_title", length=50, nullable=false)
private String put_title;

@Column(name="put_content", length=500)
private String put_content;

@Column(name="put_count")
private int put_count;

@Column(name="put_datetime", columnDefinition="datetime", nullable=false)
private Date put_datetime;

@Column(name="put_writer", length=20, nullable=false)
private String put_writer;

@ManyToOne(fetch = FetchType.LAZY) 
private Member member;

	

}
