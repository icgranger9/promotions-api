package com.example.promotions.service.impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.promotions.model.Promotion;
import com.example.promotions.repository.PromotionRepository;
import com.example.promotions.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

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
    public void createPromotion(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public void updatePromotion(Promotion promotion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePromotion'");
    }

    @Override
    public void deletePromotion(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePromotion'");
    }

    @Override
    public List<Promotion> getPromotionsInRange(Date startDate, Date endDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPromotionsInRange'");
    }
}
