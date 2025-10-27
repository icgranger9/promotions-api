package com.example.promotions.service;

import java.util.Date;
import java.util.List;

import com.example.promotions.model.Promotion;

public interface PromotionService {

    public Promotion getPromtion(Long id);

    public Promotion[] getPromtions();

    public void createPromotion(Promotion promotion);

    public void updatePromotion(Long id, Promotion promotion);

    public void deletePromotion(Long id);

    public List<Promotion> getPromotionsInRange(Date startDate, Date endDate);
}
