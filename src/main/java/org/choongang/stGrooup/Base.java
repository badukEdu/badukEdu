package org.choongang.stGrooup;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@MappedSuperclass
public class Base {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdBy; // 생성일

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedBy; // 수정일
}