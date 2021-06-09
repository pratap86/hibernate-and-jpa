package com.pratap.jpa.coupon.repository;

import com.pratap.jpa.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
