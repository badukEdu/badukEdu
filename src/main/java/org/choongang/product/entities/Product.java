package org.choongang.product.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.choongang.commons.entities.Base;

@Entity
@Data
public class Product extends Base {
    @Id
    @GeneratedValue
    private Long num; // 구매 식별자, 자동 생성되는 고유한 번호 // pk

    @Column
    private String orderTotal; // 주문합계
    @Column
    private String paymentMethod; // 결제방법선택
    @Column
    private String depositorName; // 입금자명









}