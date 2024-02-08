package org.choongang.commons.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class BaseMember extends Base{

    @CreatedBy
    @Column(length = 40, updatable = false)
    private String createdBy; // 생성자

    @LastModifiedBy
    @Column(length = 40, insertable = false)
    private String modifiedBy; // 수정자

}
