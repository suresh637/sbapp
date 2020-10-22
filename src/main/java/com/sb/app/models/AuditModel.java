package com.sb.app.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    
	
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

////	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "created_at", nullable = false, updatable = false)
//	@CreatedDate
//	private Date createdAt;

////	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "updated_at", nullable = false)
//	@LastModifiedDate
//	private Date updatedAt;
    
    
//    @Column
//    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Paris")
//    private Date datetimewithzone;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}