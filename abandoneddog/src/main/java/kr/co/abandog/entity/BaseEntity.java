package kr.co.abandog.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {
	
	@CreatedDate
	@Column(name = "inst_dtm", updatable=false, nullable= false)
	private LocalDateTime inst_dtm;
	
	@LastModifiedDate
	@Column(name="updt_dtm")
	private LocalDateTime updt_dtm;
	
	@Column(name="del_dtm")
	private LocalDateTime del_dtm;
}

