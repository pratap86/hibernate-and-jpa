package com.pratap.jpa.coupon.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@ToString
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private BigDecimal discount;

    @Getter
    @Setter
    @Column(name = "exp_date")
    private LocalDate expDate;

    protected Coupon(){}

    public Coupon(String code, BigDecimal discount, LocalDate expDate) {
        this.code = code;
        this.discount = discount;
        this.expDate = expDate;
    }
}
