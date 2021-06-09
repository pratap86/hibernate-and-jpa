package com.pratap.jpa;

import com.pratap.jpa.coupon.entity.Coupon;
import com.pratap.jpa.coupon.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest(classes = MultipleDataSourcesApplication.class)
@ExtendWith(SpringExtension.class)
class MultipleDataSourcesApplicationTests {

	@Autowired
	CouponRepository couponRepository;

	@Test
	void testSaveCoupon() {
		Coupon coupon = new Coupon("JUNE21", new BigDecimal(10), LocalDate.of(2021, 7, 21));

		Coupon savedCoupon = couponRepository.save(coupon);
		System.out.println(savedCoupon);
	}

}
