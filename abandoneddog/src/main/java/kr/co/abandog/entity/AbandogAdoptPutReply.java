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

@Entity
@Table(name="abandog_adopt_put_reply")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbandogAdoptPutReply extends BaseEntity{
	
		
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="put_reply_num", nullable=false)
private int put_reply_num;

@Column(name="put_reply_content", length=200, nullable=true)
private String put_reply_content;


@Column(name="put_num",nullable=false)
private int put_num;

@Column(name="reply_writer", length=20, nullable=false)
private String reply_writer;

@ManyToOne(fetch = FetchType.LAZY) 
private Member member;

@ManyToOne(fetch = FetchType.LAZY) 
private AbandogAdoptPut abandogAdoptPut;
	
}
