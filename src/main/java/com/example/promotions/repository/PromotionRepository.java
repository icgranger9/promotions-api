package com.example.promotions.repository;

import com.example.promotions.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    // @NonNull
    // Optional<Promotion> findById(@NonNull Long id);

    // // Method to create a new promotion
    // Promotion save(@NonNull Promotion promotion);

}