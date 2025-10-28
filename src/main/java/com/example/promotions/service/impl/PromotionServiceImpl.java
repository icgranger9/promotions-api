package com.example.promotions.service.impl;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.promotions.model.Promotion;
import com.example.promotions.model.PromotionStatus;
import com.example.promotions.model.RewardType;
import com.example.promotions.repository.PromotionRepository;
import com.example.promotions.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(PromotionServiceImpl.class);

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public Promotion getPromtion(Long id) {
        return promotionRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("NO CUSTOMER PRESENT WITH ID = " + id));
    }

    @Override
    public Promotion[] getPromtions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.toArray(new Promotion[0]);
    }

    @Override
    public Promotion[] getPromotionsInRange(ZonedDateTime startDate, ZonedDateTime endDate) {
        return promotionRepository.findPromotionsContainedInDateRange(startDate, endDate)
                .orElseThrow(() -> new NoSuchElementException("NO PROMOTIONS PRESENT IN THE GIVEN DATE RANGE"));
    }

    @Override
    public Promotion[] getPromotionsByStatus(PromotionStatus status) {
        return promotionRepository.findPromotionsByStatus(status)
                .orElseThrow(() -> new NoSuchElementException("NO PROMOTIONS PRESENT WITH STATUS = " + status));
    }

    @Override
    public Promotion[] getPromotionsByType(RewardType rewardType) {
        return promotionRepository.findPromotionsByRewardType(rewardType)
                .orElseThrow(() -> new NoSuchElementException("NO PROMOTIONS PRESENT WITH TYPE = " + rewardType));
    }

    @Override
    public Promotion createPromotion(Promotion promotion) {
        LOGGER.info("Creating promotion: {}", promotion);
        Promotion savedPromo = promotionRepository.save(promotion);

        return savedPromo;
    }

    @Override
    public Promotion updatePromotion(Long id, Promotion promotion) {
        // Find existing promotion
        Promotion existingPromotion = promotionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("NO PROMOTION PRESENT WITH ID = " + id));

        // Update fields
        existingPromotion.setCouponCode(promotion.getCouponCode());
        existingPromotion.setStatus(promotion.getStatus());
        existingPromotion.setStartDate(promotion.getStartDate());
        existingPromotion.setEndDate(promotion.getEndDate());
        existingPromotion.setRewardType(promotion.getRewardType());
        existingPromotion.setDiscountValue(promotion.getDiscountValue());

        // Save updated promotion
        Promotion updatedPromo = promotionRepository.save(existingPromotion);
        return updatedPromo;
    }

    @Override
    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }
}
