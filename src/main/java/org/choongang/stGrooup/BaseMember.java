package org.choongang.stGrooup;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@MappedSuperclass
public class BaseMember {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy; // 생성자

    @LastModifiedBy
    @Column(insertable = false)
    private String modifiedBy; // 수정자

}
