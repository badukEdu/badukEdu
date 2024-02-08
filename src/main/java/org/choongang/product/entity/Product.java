package org.choongang.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    private Long purchaseID; // 구매 식별자, 자동 생성되는 고유한 번호 // pk
    private String productName; // 구매상품명
    private String buyerName; // 구매자명
    private String contactNumber; // 연락처
    private String orderTotal; // 주문합계
    private String paymentMethod; // 결제방법선택
    private String depositorName; // 입금자명

    private String contentImage; // 콘텐츠 이미지
    private String priceSubscriptionPeriod; // 가격/구독기간
    private String productDescription; // 상품소개
}
