package com.example.promotions.controller;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.promotions.service.PromotionService;

import jakarta.validation.Valid;

import com.example.promotions.dto.CreatePromotionRequest;
import com.example.promotions.model.Promotion;

@RestController
public class PromotionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionsController.class);

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/")
    public ResponseEntity<String> getDefault() {
        LOGGER.info("Received request for default endpoint");

        return ResponseEntity.ok("Testing Promotions API");
    }

    @GetMapping("/promotions")
    public ResponseEntity<Promotion[]> getPromotions() {
        LOGGER.info("Received request for all promotions");

        try {
            Promotion[] promotions = promotionService.getPromtions();
            return ResponseEntity.ok(promotions);
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/promotion/{id}")
    public ResponseEntity<Promotion> getPromotion(@PathVariable("id") Long id) {
        LOGGER.info("Received request for promotion with ID: {}", id);

        try {
            return ResponseEntity.ok(promotionService.getPromtion(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/promotion")
    public void createPromotion(@Valid @RequestBody CreatePromotionRequest request) {
        LOGGER.info("Received request to create promotion: {}", request);

        Promotion promotion = request.toPromotion();
        promotionService.createPromotion(promotion);
    }
}
