package com.example.promotions.service;

import java.time.ZonedDateTime;

import com.example.promotions.model.Promotion;

public interface PromotionService {

    public Promotion getPromtion(Long id);

    public Promotion[] getPromtions();

    public Promotion[] getPromotionsInRange(ZonedDateTime startDate, ZonedDateTime endDate);

    public Promotion createPromotion(Promotion promotion);

    public Promotion updatePromotion(Long id, Promotion promotion);

    public void deletePromotion(Long id);

}
