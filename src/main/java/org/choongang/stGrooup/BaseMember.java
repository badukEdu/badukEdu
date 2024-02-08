package org.choongang.stGrooup;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseMember {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy; // 생성자

    @LastModifiedBy
    @Column(insertable = false)
    private String modifiedBy; // 수정자

}
