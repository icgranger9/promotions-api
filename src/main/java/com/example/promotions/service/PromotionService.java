package com.example.promotions.service;

import java.time.ZonedDateTime;

import com.example.promotions.model.Promotion;
import com.example.promotions.model.PromotionStatus;
import com.example.promotions.model.RewardType;

public interface PromotionService {

    public Promotion getPromtion(Long id);

    public Promotion[] getPromtions();

    public Promotion[] getPromotionsInRange(ZonedDateTime startDate, ZonedDateTime endDate);

    public Promotion[] getPromotionsByStatus(PromotionStatus status);

    public Promotion[] getPromotionsByType(RewardType rewardType);

    public Promotion createPromotion(Promotion promotion);

    public Promotion updatePromotion(Long id, Promotion promotion);

    public void deletePromotion(Long id);

}
