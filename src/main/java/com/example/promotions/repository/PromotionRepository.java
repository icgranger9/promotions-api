package com.example.promotions.repository;

import com.example.promotions.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    @NonNull
    Optional<Promotion> findById(@NonNull Long id);

    @NonNull
    @Query("SELECT p FROM Promotion p WHERE p.startDate >= :start AND p.endDate <= :end")
    Optional<Promotion[]> findPromotionsContainedInDateRange(@Param("start") @NonNull ZonedDateTime startDate,
            @Param("end") @NonNull ZonedDateTime endDate);

}