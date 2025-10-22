package com.example.promotions.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumsTest {

    @Test
    void promotionStatusContainsExpectedValues() {
        PromotionStatus[] values = PromotionStatus.values();
        assertTrue(values.length >= 4);
        assertArrayEquals(new PromotionStatus[] { PromotionStatus.DRAFT, PromotionStatus.ACTIVE,
                PromotionStatus.INACTIVE, PromotionStatus.EXPIRED }, values);
    }

    @Test
    void rewardTypeContainsExpectedValues() {
        RewardType[] values = RewardType.values();
        assertTrue(values.length >= 4);
        assertTrue(java.util.Arrays.asList(values).contains(RewardType.BUY_ONE_GET_ONE));
    }
}