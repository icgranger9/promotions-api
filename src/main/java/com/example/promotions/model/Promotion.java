package com.example.promotions.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.springframework.lang.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    // 1. Unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. The code the customer uses (The 'HOW')
    @NonNull
    private String couponCode;

    // 3. Status to turn it ON or OFF instantly
    @Nullable
    private PromotionStatus status; // (Enum: ACTIVE, INACTIVE)

    // --- VALIDITY (The 'WHEN') ---
    // 4. Start time (mandatory)
    @Nullable
    private ZonedDateTime startDate;

    // 5. End time (mandatory to prevent infinite discounts)
    @Nullable
    private ZonedDateTime endDate;

    // --- REWARD / DISCOUNT (The 'WHAT') ---
    // 6. Type of reward (Enum: PERCENT_OFF, FIXED_AMOUNT_OFF, etc.)
    @Nullable
    private RewardType rewardType;

    // 7. The value of the discount (e.g., 15 for 15% or 10.00 for $10 off)
    @Nullable
    private BigDecimal discountValue;

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", couponCode='" + couponCode + '\'' +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", rewardType=" + rewardType +
                ", discountValue=" + discountValue +
                '}';
    }

}
