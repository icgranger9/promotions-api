package com.example.promotions.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {

    @Test
    void beanPropertiesWork() {
        Promotion p = new Promotion();
        p.setCouponCode("SAVE10");
        p.setStatus(PromotionStatus.ACTIVE);
        p.setRewardType(RewardType.PERCENT_OFF);
        p.setDiscountValue(new BigDecimal("10"));
        p.setStartDate(ZonedDateTime.now());
        p.setEndDate(ZonedDateTime.now().plusDays(7));

        assertEquals("SAVE10", p.getCouponCode());
        assertEquals(PromotionStatus.ACTIVE, p.getStatus());
        assertEquals(RewardType.PERCENT_OFF, p.getRewardType());
        assertEquals(new BigDecimal("10"), p.getDiscountValue());
        assertNotNull(p.getStartDate());
        assertNotNull(p.getEndDate());
    }
}