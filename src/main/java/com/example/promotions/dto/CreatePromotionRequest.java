package com.example.promotions.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

import com.example.promotions.model.Promotion;
import com.example.promotions.model.PromotionStatus;
import com.example.promotions.model.RewardType;

public class CreatePromotionRequest {

    @NotBlank
    private String couponCode;

    @Nullable
    private PromotionStatus status;

    @Nullable
    private ZonedDateTime startDate;

    @Nullable
    private ZonedDateTime endDate;

    @Nullable
    private RewardType rewardType;

    @Nullable
    private BigDecimal discountValue;

    // getters/setters omitted for brevity

    public Promotion toPromotion() {
        Promotion p = new Promotion();
        p.setCouponCode(couponCode);
        p.setStatus(status);
        p.setStartDate(startDate);
        p.setEndDate(endDate);
        p.setRewardType(rewardType);
        p.setDiscountValue(discountValue);
        return p;
    }
}
