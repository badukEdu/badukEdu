package org.choongang.commons.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class Base {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 생성일

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt; // 수정일
}