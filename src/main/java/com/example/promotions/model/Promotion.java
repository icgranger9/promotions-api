package com.example.promotions.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    // 1. Unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id; 
    
    // 2. The code the customer uses (The 'HOW')
    private String couponCode;
    
    // 3. Status to turn it ON or OFF instantly
    private PromotionStatus status; // (Enum: ACTIVE, INACTIVE)

    // --- VALIDITY (The 'WHEN') ---
    // 4. Start time (mandatory)
    private ZonedDateTime startDate;
    
    // 5. End time (mandatory to prevent infinite discounts)
    private ZonedDateTime endDate;

    // --- REWARD / DISCOUNT (The 'WHAT') ---
    // 6. Type of reward (Enum: PERCENT_OFF, FIXED_AMOUNT_OFF, etc.)
    private RewardType rewardType; 
    
    // 7. The value of the discount (e.g., 15 for 15% or 10.00 for $10 off)
    private BigDecimal discountValue; 

    
}
