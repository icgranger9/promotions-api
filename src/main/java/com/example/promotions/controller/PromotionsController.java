package com.example.promotions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.promotions.service.PromotionService;
import com.example.promotions.model.Promotion;

@RestController
public class PromotionsController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/promotions/{id}")
    public Promotion getPromotion(Long id) {
        return promotionService.getPromtion(id);
    }
}
