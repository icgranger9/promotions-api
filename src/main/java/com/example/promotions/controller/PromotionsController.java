package com.example.promotions.controller;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.promotions.service.PromotionService;

import jakarta.validation.Valid;

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
            Promotion promotion = promotionService.getPromtion(id);
            return ResponseEntity.ok(promotion);
        } catch (NoSuchElementException e) {
            LOGGER.warn("No promotion found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/promotions/range")
    public ResponseEntity<Promotion[]> getPromotionsInRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        LOGGER.info("Received request for promotions in range: {} to {}", startDate, endDate);

        try {
            // Convert Date to ZonedDateTime, not production safe, just for demo purposes
            ZonedDateTime zStartDate = ZonedDateTime.ofInstant(startDate.toInstant(), ZonedDateTime.now().getZone());
            ZonedDateTime zEndDate = ZonedDateTime.ofInstant(endDate.toInstant(), ZonedDateTime.now().getZone());

            Promotion[] promotions = promotionService.getPromotionsInRange(zStartDate, zEndDate);
            return ResponseEntity.ok(promotions); // Placeholder
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/promotion")
    public ResponseEntity<Promotion> createPromotion(@Valid @RequestBody Promotion promotion) {
        LOGGER.info("Received request to create promotion: {}", promotion);
        try {
            Promotion savedPromo = promotionService.createPromotion(promotion);
            return ResponseEntity.ok(savedPromo);
        } catch (Exception e) {
            LOGGER.error("Error creating promotion: {}", e.getMessage());
        }

        return ResponseEntity.status(500).build();
    }

    @PutMapping("/promotion/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable("id") Long id,
            @Valid @RequestBody Promotion promotion) {
        LOGGER.info("Received request to update promotion with ID: {}, new body {}", id, promotion);

        try {
            Promotion updatedPromo = promotionService.updatePromotion(id, promotion);
            return ResponseEntity.ok(updatedPromo);
        } catch (NoSuchElementException e) {
            LOGGER.warn("No promotion found with ID: {}", id);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/promotion/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable("id") Long id) {
        LOGGER.info("Received request to delete promotion with ID: {}", id);

        try {
            promotionService.deletePromotion(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            LOGGER.warn("No promotion found with ID: {}", id);
        }

        return ResponseEntity.notFound().build();
    }
}
